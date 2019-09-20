package commands;

import java.util.ArrayList;
import java.util.List;
import filetypes.Directory;
import filetypes.File;
import JShellfilesystem.JShellFileSystem;
import filetypes.FileObject;
import driver.Parser;
import JShellReturnTypes.*;

/**
 * The Echo class echoes the input in JShell.
 */
public class Echo extends Command {
  /**
   * This method runs the main functionality of Echo.
   * 
   * @param args command line arguments
   * @return return terminal output or error if opeartion fails
   */
  public List<RetType> execute(List<String> args) {
    // check if the number of arguments is correct
    List<RetType> output = validate(args);
    // when no error returns, perform echo command
    if (output.isEmpty()) {
      // add the string input to output
      output.add(0, new StdOutput(args.get(1)));
    }
    return output;
  }
  /**
   * This function checks if the number of arguments match the command criteria
   * 
   * @param args command line arguments
   * @return invalid number of arguments error
   */
  protected List<RetType> validate(List<String> args) {
    List<RetType> errors = new ArrayList<>();
    if (args.size() != 2)
      errors.add(0, new InvalidNumberOfArgs(args, "echo"));
    return errors;
  }
}