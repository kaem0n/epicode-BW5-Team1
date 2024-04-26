package bw5team1.epicenergyservices.tools;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class CsvHandler {

    public List<String[]> getEntries(File csv) throws IOException, CsvException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(csv)).build();
        return reader.readAll();
    }

}
