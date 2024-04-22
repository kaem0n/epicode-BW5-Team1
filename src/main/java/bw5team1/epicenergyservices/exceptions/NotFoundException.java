package bw5team1.epicenergyservices.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id){
        super("No Record with Id " + id + " found");
    }

}