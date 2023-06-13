package org.example.axe.codelets;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QASensory extends Codelet {
    Memory sensoryMemory;
    private String pathToCSV;
    ArrayList<Integer[]> full_data = new ArrayList<>();
    public QASensory(String pathToCSV){
        this.timeStep = 1000;
        this.pathToCSV = pathToCSV;
        getFullData();

    }

    @Override
    public void accessMemoryObjects() {
        if(sensoryMemory == null){
            sensoryMemory = this.getOutput("sensoryMemory");
        }
    }

    @Override
    public void calculateActivation() {

    }

    @Override
    public void proc() {
        if(!full_data.isEmpty()){
            sensoryMemory.setI(full_data.get(0));
            full_data.remove(0);
        }
        else{
            sensoryMemory.setI(null);
        }

    }

    void getFullData(){
        try {
            //parsing a CSV file into CSVReader class constructor
            System.out.println(pathToCSV);
            CSVReader reader = new CSVReader(new FileReader(Paths.get(pathToCSV).toFile()));
            String[] nextLine;


            //reads one line at a time
            while ((nextLine = reader.readNext()) != null) {
                ArrayList<Integer> entry = new ArrayList();
                for (String token: nextLine) {
                    try{
                        entry.add(Integer.parseInt(token));
                    }
                    catch (Exception e){
                        entry = null;
                    }

                }

                if(entry != null){
                    Integer[] arr = new Integer[entry.size()];
                    arr = entry.toArray(arr);

                    full_data.add(arr);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
