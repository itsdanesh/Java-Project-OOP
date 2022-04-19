package input;

import java.util.Scanner;

public class Input {

    public static Scanner inputScanner = new Scanner(System.in);

    public static int readAndVerifyOptionInput(int start, int end){
        int optionInput=0;
        do {
            optionInput= inputScanner.nextInt();
            inputScanner.nextLine();
            if(optionInput<start || optionInput>end){
                System.out.println("Invalid menu option. Please type another option");
            }
        }while (optionInput<start || optionInput>end);
        return optionInput;
    }

    public static double readDouble(String message){
        System.out.println(message);
        double value=Input.inputScanner.nextDouble();
        Input.inputScanner.nextLine();
        return value;
    }

    public static String readString(String message){
        System.out.println(message);
        String value=Input.inputScanner.nextLine();
        return value;
    }

    public static int readInt(String message){
        System.out.println(message);
        int value=Input.inputScanner.nextInt();
        Input.inputScanner.nextLine();
        return value;
    }


}
