package bw5team1.epicenergyservices;

import bw5team1.epicenergyservices.entities.Provincia;
import bw5team1.epicenergyservices.repositories.ComuneDAO;
import bw5team1.epicenergyservices.repositories.ProvinciaDAO;
import bw5team1.epicenergyservices.tools.CsvHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseCheckRunner implements CommandLineRunner {
    @Autowired
    private ComuneDAO cd;
    @Autowired
    private ProvinciaDAO pd;
    @Autowired
    private CsvHandler csvHandler;

    @Override
    public void run(String... args) throws Exception {
        List<String[]> datiProvince = csvHandler.getEntries(new File("./province-italiane.csv"));

        for (int i = 1; i < datiProvince.size(); i++) {
            String[] data = datiProvince.get(i)[0].split(";");
            String sigla = data[0];
            String nome = data[1];
            String regione = data[2];
            Provincia entry = new Provincia(nome, regione, sigla);

            pd.save(entry);
        }
    }
}
