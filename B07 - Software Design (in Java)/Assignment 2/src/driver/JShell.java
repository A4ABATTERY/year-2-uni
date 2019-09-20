// **********************************************************
// Assignment2:
// Student1: Birathan Somasundaram
// UTORID user_name: somasu22
// UT Student #: 1003199175
// Author:
//
// Student2: Arunav Biswas
// UTORID user_name: biswas19
// UT Student #: 1005403449
// Author:
//
// Student3: Subagan Kamaleswaran
// UTORID user_name: kamales8
// UT Student #: 1002163712
// Author: Subagan Kamaleswaran
//
// Student4: David Do
// UTORID user_name: dodavid2
// UT Student #: 1004440009
// Author: David Do
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package driver;

import java.util.List;
import java.util.ArrayList;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import JShellfilesystem.JShellFileSystem;
import commands.CommandManual;
import commands.ShellHistory;
import filetypes.Directory;
import Hashmap.HashMapCom;
import JShellReturnTypes.*;
import Redirection.Redirection;

/**
 * The JShell class emulates a Linux Shell, takes inputs from the user, 
 * and gets commands from user.
 */
public class JShell {
    // static HashMapCom shellCommands = HashMapCom.initHashMap();

    /**
     * Gets the input from the user
     * 
     * @return a string of a input to be processed by JShell
     */
    public static String getUserInput() {
        String str = "";
        try {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            str = br.readLine();
        } catch (Exception e) {
        }
        ShellHistory.appendCmdEntered(str);
        return str;
    }

    /**
     * This method displays a String to the User.
     * 
     * @param str is any String
     */
    public static void outputToUser(List<RetType> outputs) {
        if (outputs != null)
            for (RetType output : outputs) {
                if (output != null && !output.toString().equals("exit"))
                    System.out.println(output);
            }
    }

    /**
     * This method runs a Command and returns the command results
     * 
     * @param args is a list of String that serve as parameters.
     * @return a string. This string is received from the execute() method of
     *         the Command.
     */
    public static List<RetType> runCommand(List<String> args) {
        if (args.size() > 0) {
            if ((HashMapCom.get(args.get(0)) != null)) {
                return Redirection.redirectionHandler(args);
            } else {
                List<RetType> output = new ArrayList<RetType>();
                output.add(new InvalidArguments(args, "JShell"));
                return output;
            }
        } else {
            return null;
        }
    }

    /**
     * This method initializes the JShellFileSystem, HashMapCom, and
     * CommandManual, and sets up a loop for the user to continuously input.
     * Loop/shell ends when the user enters "exit"
     */
    public static void runShell() {
        String str = "";
        List<String> args;
        List<RetType> output;
        HashMapCom.initHashMap(); // Initialize the hashmap.
        CommandManual.initManual(); // Initialize the commandManual.
        do {
            System.out.print(
                    "group_0413@A2:~" + Directory.getCurrDirPathName() + "$ ");
            str = getUserInput(); // get user input
            args = Parser.parseArguments(str);
            output = runCommand(args); // get output from command in shell
            outputToUser(output);
        } while (output == null || !output.toString().equals("[exit]"));
    }

    /**
     * Starts the shell, by calling runShell()
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // init(); // init filesystem (TEMP)
        // System.out.println("Hello World");
        JShellFileSystem.initJShellFileSystem(new Directory("/", null));
        runShell();
    }


}
