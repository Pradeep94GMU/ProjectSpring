package codeforces;

import java.util.Arrays;
import java.util.Scanner;

public class WayTooLongWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfLines = sc.nextInt(); //number of lines

        //ho skta hai ki kuch bas jaye next line me so consume first the leftover
        sc.nextLine();  // Consume the leftover newline character

        //next n lines me alag alag words hai
        String[] words = new String[numOfLines];

        for(int i = 0; i < numOfLines; i++){
            words[i] = sc.nextLine();


        }

        System.out.println(Arrays.toString(words));
        for(String each: words){
            if(each.length() > 10){
                char first = each.charAt(0);
                char last = each.charAt(each.length() - 1);

                int len = each.length() - 2;
                String res = first +""+len+last;
                System.out.println(res);
            }else{
                System.out.println(each);
            }
        }

    }
}
