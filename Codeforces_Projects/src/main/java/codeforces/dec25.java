package codeforces;

import java.util.Scanner;

public class dec25 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        if(t % 2 == 0 && t > 2){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }


    }
}