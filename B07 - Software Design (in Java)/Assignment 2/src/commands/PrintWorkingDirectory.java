package commands;

import JShellfilesystem.JShellFileSystem;
import java.util.ArrayList;
import java.util.List;
import JShellReturnTypes.*;
/**
 * The PrintWorkingDirectory class returns the pathname of the current
 * Directory.
 */
public class PrintWorkingDirectory extends Command {
    /**
     * This method runs the main functionality of PrintWorkingDirectory.
     * gets current path of current directory from the filesystem. 
     * Then returns it.
     * @param args is a list of String that serve as parameters.
     * @return the pathname of the current Directory.
     */
  public List<RetType> execute(List<String> args) {
      List<RetType> output = new ArrayList<>(1);
      output.add(new StdOutput(JShellFileSystem.getCurrentDir().getPathName()));
      return output;
  }
    /**
     * This method returns an empty String.
     * @param args is a list of Strings that serve as parameters.
     * @return An empty string.
     */
  public List<RetType> validate(List<String> args) {
	  return null;
  }
}