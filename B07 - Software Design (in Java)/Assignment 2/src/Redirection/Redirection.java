package Redirection;

import java.util.ArrayList;
import java.util.List;
import JShellReturnTypes.*;
import commands.*;
import driver.*;
import filetypes.*;
import Hashmap.*;

/**
 * Redirection is the class to handle standard JShell output to file
 */
public class Redirection {
    /**
     * this function is to determine the file rediection mode
     * 
     * @param args command line arguments
     * @return the stdout redirection mode, 1 for overwrite, 2 for append mode,
     *         0 for no file redirection
     */
    private static int checkRedirection(List<String> args) {
        if (args.size() >= 3) {
            // check if the output is redirected and is in rewrite mode
            int mode = (args.get(args.size() - 2)).equals(">") ? 1 : 0;
            // check if the output is redirected and is in append mode
            return mode = (args.get(args.size() - 2)).equals(">>") ? 2 : mode;
        }
        // return 0 if number of arguments < 3
        return 0;
    }

    /**
     * this function detects and removes '>' and ">>" arguments in args
     * 
     * @param args command line arguments
     * @return modified command line arguments
     */
    private static List<String> argumentHandler(List<String> args) {
        if ((args.size() >= 3)
                && ((args.contains(">>") || args.contains(">")))) {
            // return args wihout the '>' or '>>' and the file name
            return args.subList(0, args.size() - 2);
        }
        return args;
    }

    /**
     * this function is to perform file opeartion in either overwirte or append
     * mode
     * 
     * @param splitPath the split path that contains the parent path and file
     *                  name
     * @param contents  the file content
     * @param mode      the file redirection mode
     * @return error list
     */
    private static List<RetType> FileOperation(List<String> args,
            String contents, int mode) {
        // initialize error list
        List<RetType> error = new ArrayList<RetType>();
        // split the path into location and filename
        String[] splitPath = Parser.parsePath(args.get(args.size() - 1));
        // get the parent path
        String location = splitPath[0];
        // get the filename
        String filename = splitPath[1];
        // get the parent path directory
        Directory locationDir = Directory.getDirAtPath(location);
        // check if the parent directory exists
        if (locationDir != null) {
            // get the file by name
            FileObject file = locationDir.getFileObjInDirectory(filename);
            // check if the file already exists
            if (file != null) {
                // check whether the fileobject is file type
                if (!(file instanceof File)) {
                    error.add(new FileObjAlreadyExists(location+filename));
                    return error;
                }
                // file operation according to the rediection mode
                if (mode == 1) {
                    ((File) file).setContents(contents);
                } else if (mode == 2) {
                    ((File) file).append(contents);
                }
            } else {
                // check if the file name is valid
                if (FileObject.parseValidName(filename)) {
                    // create new file and set its content
                    File newFile = new File(filename, locationDir, contents);
                    locationDir.add(newFile);
                }
            }
        } else {
            // return error if directory does not exist
            error.add((DirectoryNotFound) new DirectoryNotFound(location));
            return error;
        }
        error.add(null);
        return error;
    }

    /**
     * the main function of redirection class to handle JShell standard output
     * redirection to file
     * 
     * @param args command line arguments
     * @return the JShell output after handling file redirection
     */
    public static List<RetType> redirectionHandler(List<String> args) {
        // check whether file redirection exists
        int mode = checkRedirection(args);
        if (mode != 0) {
            // get the new arguments
            List<String> new_args = argumentHandler(args);
            // get the JShell output of the command
            List<RetType> outputs =
                    (HashMapCom.get(args.get(0))).execute(new_args);
            // initialize file content
            String contents = "";
            if (outputs != null) {
                for (RetType output : outputs) {
                    // if the JShell output is standard output,add it to file
                    // content
                    if (output instanceof StdOutput)
                        contents += output.toString();
                }
            }
            // handle the command that does not return any standard output
            if (contents.isEmpty())
                return outputs;
            // perform file operation if there is standard JShell output
            List<RetType> returnList = FileOperation(args, contents, mode);
            return returnList;
        }
        return HashMapCom.get(args.get(0)).execute(args);
    }
}
