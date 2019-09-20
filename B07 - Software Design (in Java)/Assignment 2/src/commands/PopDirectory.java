package commands;

import java.util.ArrayList;
import java.util.List;
import JShellReturnTypes.*;
import filetypes.Directory;
import JShellfilesystem.JShellFileSystem;

/**
 * The PopDirectory class pops a directory into the DirectoryStack class.
 */
public class PopDirectory extends Command{
  /**
   * This method runs the main functionality of PopDirectory.
   * Calls on directoryStack to pop a directory off of its stack.
   * And uses the Filesystem to change the currentDirectory to the
   * directory that was poped off
   * @param args is a list of String that serve as parameters.
   * @return returns an empty string, or a string indicating errors with the
   * parameters.
   */
  public List<RetType> execute(List<String> args) {
    // TODO Auto-generated method stub
    List<RetType> error = validate(args);

    if(error.get(0) == null) {
      Directory destination = (JShellFileSystem.getDirectoryStack()).pop();
      if(destination  == null) {
        error.set(0,new DirectoryStackIsEmpty());
      } else {
        JShellFileSystem.setCurrentDir(destination);
      }
    }
    return error;
  }

  /**
   * This method checks if the parameters inserted match the
   * requirements of the PopDirectory class.
   * popd should be the only arg passed in. If there is more
   * than one arg, an error is returned.
   * @param args is a list of Strings that serve as parameters.
   * @return A string depicting any problems with args.
   */
  protected List<RetType> validate(List<String> args) {
    List<RetType> error = new ArrayList<>();
    error.add(null);
    if(args.size()>1){
        error.set(0, new InvalidNumberOfArgs(args, "popd"));
    }
    return error;
  }
}