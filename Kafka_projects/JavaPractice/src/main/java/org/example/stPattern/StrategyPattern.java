package org.example.stPattern;

import org.example.stPattern.strPac.NormalSpeed;
import org.example.stPattern.strPac.SpeedCar;

public class StrategyPattern {

    public static void main(String[] args) {

        SportCar sportCar = new SportCar(new SpeedCar());
        OffRoad offRoad = new OffRoad(new SpeedCar());
        NormalCar normalCar = new NormalCar(new NormalSpeed());

        sportCar.method2();
        offRoad.method2();
        normalCar.method2();
    }
}
