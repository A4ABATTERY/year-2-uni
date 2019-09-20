package driver;

import UserData.UserData;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class CfilteringDriver {

    /**
     * uses standard input to get a string from the user.
     *
     * @return A string that was inputed by the user
     */

    public static String getStringFromUser(){
        String str = "";
        try{
            Scanner input = new Scanner(System.in);
            str = input.nextLine();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return str;

    }

    /**
     * The run function of the program.
     * this initialises and calls upon other classes to carry out the
     * functionality of the Cfiltering program.
     *
     */
    private static void runProg() {
        System.out.print("Enter the name of the input file: ");
        String fileName = getStringFromUser();
        try {
            UserData<Float, Integer> userRatings = new UserData<Float, Integer>(fileName);
            System.out.println(userRatings);
            System.out.println(userRatings.getSimAndDisSimRatings());
        }catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }

    }
    public static void main(String[] args){
        runProg();

    }


}
