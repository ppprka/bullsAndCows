package bullscows;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        System.out.println("Input the length of the secret code:");
        Scanner sc = new Scanner(System.in);
        String k = sc.next();
        if (k.matches("\\d+")){
            int n = Integer.parseInt(k);
            if(n>36){
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                return;
            }
            if(n<1){
                System.out.println("Error: minimum number of possible symbols in the code is 1 (0-9, a-z).");
                return;
            }
            System.out.println("Input the number of possible symbols in the code:");
            String p = sc.next();
            int m = Integer.parseInt(p);
            if (p.matches("\\d+") && m>=n) {
                if(m>36){
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                    return;
                }
                if(m<1){
                    System.out.println("Error: minimum number of possible symbols in the code is 1 (0-9, a-z).");
                    return;
                }
                char[] arr1 = code(n, m);
                System.out.println("Okay, let's start a game!");
                int turn = 1;
                int l = 0;
                while (l == 0) {
                    int cows = 0;
                    int bulls = 0;
                    System.out.println("Turn " + turn + ". Answer:");
                    turn++;
                    String answer = sc.next();
                    char[] arr2 = answer.toCharArray();
                    //int[] arr3 = new int[arr2.length];
                    //for (int i = 0; i < arr3.length; i++) {
                    //arr3[i] = Character.getNumericValue(arr2[i]);
                    //}
                    for (int i = 0; i < arr1.length; i++) {
                        if (arr1[i] == arr2[i]) {
                            bulls++;
                        }
                        for (int j = 0; j < arr2.length; j++) {
                            if (arr1[i] == arr2[j] && arr1[i] != arr2[i]) {
                                cows++;
                            }
                        }
                    }
                    if (bulls == n) {
                        System.out.println("Grade: " + bulls + " bulls");
                        System.out.print("Congrats! You guessed the secret code.");
                        l++;
                    } else if (cows > 0 && bulls == 0) {
                        System.out.println("Grade: " + cows + " cow(s).");

                    } else if (cows > 0 && bulls > 0 && bulls < n) {
                        System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s).");

                    } else if (bulls > 0 && bulls < n && cows == 0) {
                        System.out.println("Grade: " + bulls + " bull(s).");

                    } else if (bulls == 0 && cows == 0) {
                        System.out.println("Grade: None.");
                    }
                }
            }
            else{
                System.out.println("Error: it's not possible to generate a code with a length of "+k+" with "+m+" unique symbols.");
            }
        }
        else{
            char dm= (char)34;
            System.out.println("Error: "+dm+k+dm+" isn't a valid number");
        }
    }
    public static char[] code(int n, int m){
        Random random=new Random();
        char[] arr1 = new char[n];
        String str ="0123456789abcdefghijklmnopqrstuvwxyz";
        char[] alphabet=str.toCharArray();
        int x = 0;
        outer: while (x == 0) {
            for (int i = 0; i < n; i++) {
                arr1[i]=alphabet[random.nextInt(m)];
            }
            if (arr1[0] == 0) {
                break;
            } else {
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        if (arr1[i] == arr1[j]) {
                            continue outer;
                        }
                    }
                }
                x++;
            }
        }
        System.out.print("The secret is prepared: ");
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
        if(m<11){
            System.out.print(" (0-"+alphabet[m-1]+").");
        }
        else {
            System.out.println(" (0-9, a-"+alphabet[m-1]+").");
        }
        return arr1;
    }

}
