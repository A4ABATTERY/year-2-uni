package commands;

import filetypes.Directory;
import filetypes.File;
import JShellfilesystem.JShellFileSystem;
import JShellReturnTypes.*;
import driver.Parser;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * The Get Class Retrieve the file at specified URL and add it to the current
 * working directory.
 */
public class Get extends Command {
    /**
     * this function takes the URL address and return the content of the URL in
     * form of String
     * 
     * @param url the web address
     * @return the content of the webpage
     * @throws IOException invalid URL
     */
    private String getURLContent(String url) throws IOException {
        // convert the URL address in URL object
        URL website = new URL(url);
        // open the inputstream for the webpage
        InputStream connection = website.openStream();
        // scanner to get the webpage content in UTF-8 encoding
        Scanner scanner = new Scanner(connection, "UTF-8");
        // StringBuilder to concat each line of content
        StringBuilder content = new StringBuilder();
        // check if the webpage content has next line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            content.append(line);
        }
        scanner.close();
        // return the whole webpage content
        return content.toString();
    }

    /**
     * This method runs the main functionality of Get. It takes the URL and
     * parses the desingated file name. Then the function will save the content
     * of web page to file in the current directory.
     * 
     * @param args command line arguments
     * @return return terminal output or error if opeartion fails
     */
    public List<RetType> execute(List<String> args) {
        // check invalid number of arguments error
        List<RetType> error = validate(args);
        if (error.isEmpty()) {
            // setup JShell output
            List<RetType> outputArr = new ArrayList<>();
            // get the URL in the argument
            String url = args.get(1);
            // parse and get the file name without its file extension
            String[] pathAndFile = Parser.parsePath(url);
            String[] fileNameWithExtension = pathAndFile[1].split("\\.");
            String filename = fileNameWithExtension[0];
            // get the current working directory
            Directory currDir = (Directory) JShellFileSystem.getCurrentDir();
            // get the file by file name
            File file = (File) currDir.getFileObjInDirectory(filename);
            // if the file does not exist
            if (file == null) {
                // get the webpage content and save it to new file
                try {
                    String content = getURLContent(url);
                    File newFile =
                            new File(filename, currDir, content);
                    currDir.add(newFile);
                } catch (IOException e) {
                    // handle invalid URL error
                    outputArr.add((WebsiteNotFound) new WebsiteNotFound(url));
                }
            } else {
                // handle file already exists error
                outputArr.add((FileObjAlreadyExists) new FileObjAlreadyExists(
                        filename));
            }
            return outputArr;
        }
        return error;
    }

    /**
     * This function checks if the number of arguments match the command
     * criteria
     * 
     * @param args command line arguments
     * @return invalid number of arguments error
     */
    protected List<RetType> validate(List<String> args) {
        List<RetType> error = new ArrayList<>(1);
        if (args.size() != 2) {
            error.add(0, new InvalidNumberOfArgs(args, "get"));
        }
        return error;
    }
}
