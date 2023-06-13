package org.example.axe.codelets;

import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.io.rest.HttpCodelet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ClassificationCaller extends HttpCodelet {
    String getURI;
    Memory reactivity;

    public ClassificationCaller(String apiURI){
        this.getURI = apiURI + "/classify/";
    }

    @Override
    public void accessMemoryObjects() {
        if(reactivity == null){
            reactivity = this.getInput("reactivityMemory");
        }
    }

    @Override
    public void calculateActivation() {
    }

    @Override
    public void proc() {
        Object request = reactivity.getI();
        if(request != null){
            JSONArray jArray = new JSONArray();

            for (Integer value : (Integer[]) request){
                jArray.put(value);
            }
            String response = " API GET request failed!";

            try{
                response = this.sendGET(this.getURI +jArray);
            }catch (IOException e){e.printStackTrace();}

            reactivity.setI(null);
            System.out.println(response);


        }


    }
}
