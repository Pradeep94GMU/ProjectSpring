package org.example.stPattern;

import org.example.stPattern.strPac.StratergyInterface;

public class Vechile {

    StratergyInterface stratergyInterface;

    public Vechile(StratergyInterface stratergyInterface) {
        this.stratergyInterface = stratergyInterface;
    }

    //we have a has a realtion but how to call the funtion among each object??
    public void method2(){
        stratergyInterface.method();
        return;
    }
}
