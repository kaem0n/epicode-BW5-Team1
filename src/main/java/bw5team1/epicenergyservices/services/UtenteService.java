package bw5team1.epicenergyservices.services;

import bw5team1.epicenergyservices.entities.Utente;
import bw5team1.epicenergyservices.enums.TipoUtente;
import bw5team1.epicenergyservices.exceptions.BadRequestException;
import bw5team1.epicenergyservices.exceptions.NotFoundException;
import bw5team1.epicenergyservices.payloads.utente.NewUtenteDTO;
import bw5team1.epicenergyservices.repositories.UtenteDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    private UtenteDAO utenteDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Utente save(NewUtenteDTO body) throws IOException{
        utenteDAO.findByEmail(body.email()).ifPresent(
          utente -> {
              throw  new BadRequestException("L'email " + body.email() + " è già in uso");
          }
        );
        Utente utente = new Utente(body.username(), bcrypt.encode(body.password()), body.email(), body.nome(), body.cognome(), "https://ui-avatars.com/api/?name="+ body.nome().charAt(0) + "+" + body.cognome().charAt(0));
        return utenteDAO.save(utente);
    }

    public Page<Utente> getUtente(int page, int size, String sort){
        if(size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return utenteDAO.findAll(pageable);
    }

    public Utente findById(UUID id){
        return utenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Utente findByIdAndUpdate(UUID id, Utente body){

        Utente found = this.findById(id);
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setPassword(body.getPassword());
        found.setUsername(body.getUsername());
        found.setAvatarUrl("https://ui-avatars.com/api/?name=" + body.getNome().charAt(0) + "+" + body.getCognome().charAt(0));
        return utenteDAO.save(found);
    }

    public void findByIDAndDelete(UUID id) {
        Utente found = this.findById(id);
        utenteDAO.delete(found);
    }

    public Utente uploadAvatar(UUID id, MultipartFile file) throws IOException{
        Utente found = this.findById(id);
        String avatarUrl = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatarUrl(avatarUrl);
        return utenteDAO.save(found);
    }

    public Utente findByEmail(String email){
        return utenteDAO.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato!"));
    }


}


