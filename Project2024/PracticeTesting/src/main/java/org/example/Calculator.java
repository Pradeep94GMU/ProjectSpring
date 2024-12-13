package org.example;

// Calculator.java
public class Calculator {

    CalMultiply calMultiply = new CalMultiply();

    public Calculator(CalMultiply calMultiply) {
        this.calMultiply = calMultiply;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return calMultiply.multiply(a,b); //it is using a 3rd party service so better not to use or may be it iwll not be available in some case so we can use a proxy for that service: MOCKITO

    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return a / b;
    }
}

