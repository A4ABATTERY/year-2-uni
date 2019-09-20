package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import JShellReturnTypes.*;
import filetypes.FileObject;
import filetypes.Directory;
import JShellfilesystem.JShellFileSystem;

/**
 * The ChangeDirectory class changes the current Directory inside the
 * JShellFileSystem class.
 */
public class ChangeDirectory extends Command {

  /**
   * This method runs the main functionality of ChangeDirectory.
   * 
   * @param args is a list of Strings that serve as parameters.
   * @return a string indicating what the new current directory is.
   */
  public List<RetType> execute(List<String> args) {
    List<RetType> errors = validate(args);
    String pathname = (args.size() < 2) ? "" : args.get(1);
    Directory destinationDir = null;
    if (errors.get(0) == null) {
      destinationDir = Directory.getDirAtPath(pathname);
      if (destinationDir != null) {
        JShellFileSystem.setCurrentDir(destinationDir);
      } else {
        errors.set(0, new PathNotFound(args.get(1)));
      }
    }
    return errors;
  }

  /**
   * This method checks if the parameters inserted match the requirements of
   * the ChangeDirectory class.
   * 
   * @param args is a list of Strings that serve as parameters.
   * @return A string depicting any problems with args, or the output of the
   *         command.
   */
  protected List<RetType> validate(List<String> args) {
    List<RetType> error = new ArrayList<>();
    error.add(null);
    if (args.size() > 2) {
      error.set(0,new InvalidNumberOfArgs(args, "cd"));
    }
    return error;
  }
}