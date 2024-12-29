package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static int fibonacci(int n) {


        // n = n - 1 + n - 2
        if(n == 0) return 0;
        if(n == 1) return 1;

        return fibonacci(n - 1) + fibonacci( n - 2 ); //3 => fib(2) + fib(1) == > fib(2) => fib(1) + fib(0)

    }

    public static void main(String[] args) {
        System.out.println(fibonacci(9));
        System.out.println(res);

    }
}