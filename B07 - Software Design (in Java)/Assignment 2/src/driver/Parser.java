package driver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import filetypes.Directory;
import JShellfilesystem.JShellFileSystem;

public class Parser {
    private final static String regexArguments = "[^\\s\"']+|\"([^\"]*)\"";
    private final static Pattern splitArguments = 
    		Pattern.compile(regexArguments);

    /**
     * This method converts inputs from JShell (a string) into a ArrayList
     * so that it can be used by Commands.
     * @param arguments is a String from JShell
     * @return a list of parameters in the form of a String
     */
    public static List<String> parseArguments(String arguments){
        List <String> args = new ArrayList<String>();
        Matcher textSplitter = splitArguments.matcher(arguments);
        while (textSplitter.find()){
            if(textSplitter.group(1)!= null){
                args.add(textSplitter.group(1));
            }
            else {
                args.add(textSplitter.group());
            }
        }
        return args;
    }

    /**
     * This method converts a string path name into a list of file object names
     * for use by certain commands
     * @param path is a String
     * @return a list of file names in the form of a String
     */
        
    public static String[] parsePath(String path) {
      String dirname = "";
      String location="";
      Directory currDir = (Directory) JShellFileSystem.getCurrentDir();
      if(path.charAt(path.length()-1) == '/') {
        //removes / in path
        path = path.substring(0,path.length()-1);
      }
      String currPath = JShellFileSystem.getCurrentDir().getPathName()==
    		  "/"? "":currDir.getPathName();
      //CASE 1: MAKE DIRECTORY IN CURRENT DIR
      if(path.indexOf("/") == -1){
        dirname = path;
        location= currPath+"/";
      } else if (path.indexOf("/") != 0) {
      //CASE 2: MAKE DIRECTORY FROM PATH WITH RESP. TO CURR
        dirname = path.substring(path.lastIndexOf("/")+1);
        location= currPath+ "/"+path.substring(0,path.lastIndexOf('/')+1) ;
      } else {
      //CASE 3: MAKE DIRECTORY FROM PATH WITH RESP. TO ROOT
        dirname = path.substring(path.lastIndexOf('/')+1);
        location = path.substring(0,path.lastIndexOf('/')+1);
      }
      return new String[]{location, dirname};
    }
}

