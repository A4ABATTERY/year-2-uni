package commands;

import java.util.ArrayList;
import java.util.List;
import JShellReturnTypes.*;
import filetypes.Directory;
import JShellfilesystem.JShellFileSystem;

/**
 * The PushDirectory puts a pointer of a Directory into the Directory Stack.
 */
public class PushDirectory extends Command{
  /**
   * This method runs the main functionality of PushDirectory.
   * pushes the current directory on to the stack of directories 
   * inside the DirectoryStack class. Then changed directory to the
   * dir that the user called the command on.
   * @param args is a list of String that serve as parameters.
   * @return an empty String, or a String indicting an error.
   */
  public List<RetType> execute(List<String> args) {
    // TODO Auto-generated method stub
    List<RetType> error = validate(args);
    
    if(error.get(0) == null) {
      (JShellFileSystem.getDirectoryStack()).push(
              (Directory)JShellFileSystem.getCurrentDir());
      Directory destination = (Directory)Directory.getDirAtPath(args.get(1));
      if(destination != null) {
        JShellFileSystem.setCurrentDir(destination);
      } else {
        error.set(0,new PathNotFound(args.get(1)));
      }
    }
    return error;
  }
  /**
   * This method checks if the parameters inserted match the
   * requirements of the PushDirectory class.
   * PushDirectory only takes in 2 arguments. All other cases
   * return an error.
   * @param args is a list of Strings that serve as parameters.
   * @return a string indicating any problems with args.
   */
  protected List<RetType> validate(List<String> args) {
    List<RetType> error = new ArrayList<>();
    error.add(null);
    if(args.size() != 2){
      error.set(0,new InvalidNumberOfArgs(args, "pushd"));
    }
    return error;
  }
}