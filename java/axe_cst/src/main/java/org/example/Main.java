package org.example;

import org.example.axe.AxeMind;

public class Main {
    public static void main(String[] args) {
        String apiURI = "http://127.0.0.1:5000";
        String pathToJson = "/home/wander/Projects/cst_pack/cst_compas/java/axe_cst/src/main/resources/x_test.csv";


        AxeMind anaMind = new AxeMind(apiURI, pathToJson);
        // start
        anaMind.start();

        try{
            Thread.sleep(10000); // 10 secs
        }catch (Exception e){ e.printStackTrace();}
    
        //stop
        anaMind.shutDown();
    }
}