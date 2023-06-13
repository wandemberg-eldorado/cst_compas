package org.example.axe;

import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.entities.Mind;
import org.example.axe.codelets.QASensory;
import org.example.axe.codelets.Reactivity;
import org.example.axe.codelets.ClassificationCaller;

public class AxeMind extends Mind {
    String apiURL;

    public AxeMind(String apiURI, String pathToJson){
        this.apiURL = apiURI;
        this.setupMind(pathToJson);
    }

    private void setupMind(String pathToJson){
        //declaring memories
        MemoryObject sensoryMemory = createMemoryObject("sensoryMemory");
        MemoryObject reactivityMemory = createMemoryObject("reactivityMemory");

        //declaring, mounting and inserting Codelets
        QASensory qaSensory = new QASensory(pathToJson);
        qaSensory.addOutput(sensoryMemory);
        this.insertCodelet(qaSensory);

        Reactivity reactivity = new Reactivity();
        reactivity.addInput(sensoryMemory);
        reactivity.addOutput(reactivityMemory);
        this.insertCodelet(reactivity);

        ClassificationCaller classificationCaller = new ClassificationCaller(this.apiURL);
        classificationCaller.addInput(reactivityMemory);
        this.insertCodelet(classificationCaller);
    }
}
