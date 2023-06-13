package org.example.axe.codelets;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;

import java.util.Arrays;
import java.util.HashMap;

public class Reactivity extends Codelet {
    Memory sensoryMemory;
    Memory reactivityMemory;

    @Override
    public void accessMemoryObjects() {
        if(sensoryMemory == null){
             sensoryMemory = this.getInput("sensoryMemory");
        }
        if( reactivityMemory == null){
            reactivityMemory = this.getOutput("reactivityMemory");
        }
    }

    @Override
    public void proc() {
        Object info = sensoryMemory.getI();
        if(info != null){
            System.out.println(Arrays.toString((Integer[])info));
            reactivityMemory.setI(info);
        }
        else{reactivityMemory.setI(null);}
    }

    @Override
    public void calculateActivation() {}
}
