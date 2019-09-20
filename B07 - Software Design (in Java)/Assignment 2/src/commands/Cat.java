package commands;


import filetypes.Directory;
import filetypes.File;
import filetypes.FileObject;
import JShellfilesystem.JShellFileSystem;
import JShellReturnTypes.*;
import driver.Parser;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Cat Class finds files instead the current directory returns the contents
 * of those files.
 */
public class Cat extends Command {

  /**
   * This method runs the main functionality of Cat. Takes each argument and
   * splits it into a Path, and a filename at the path Then the function will
   * look in the directory (that is at the path) for the file Finally the
   * contnents of the file are gotten from the file if the file or path is not
   * found, an error is returned.
   * 
   * @param args is a list of Strings that serve as parameters.
   * @return the contents of a found file, or what errors have been found with
   *         the arguments.
   */
  public List<RetType> execute(List<String> args) {
    List<RetType> error = validate(args);
    if (error.isEmpty()) {
      List<RetType> outputArr = new ArrayList<>();
      String[] pathAndFile;
      for (int i = 1; i < args.size(); i++) {
        String path = args.get(i);
        pathAndFile = Parser.parsePath(path);
        String location = pathAndFile[0];
        String filename = pathAndFile[1];
        Directory parentDir = Directory.getDirAtPath(location);
        if (parentDir != null) {
          FileObject file = parentDir.getFileObjInDirectory(filename);
          if (file != null && !file.isDir()) {
            outputArr.add(new StdOutput(((File)file).getContents()));
          } else {
            outputArr.add((FileObjNotFound) new FileNotFound(filename));
          }
        } else {
          outputArr.add(new PathNotFound(location));
        }
        if (i < args.size() - 1) {
          outputArr.add(new StdOutput("\n\n"));
        }
      }

      return outputArr;
    }
    return error;
  }
  /**
   * This method checks if the parameters inserted match the requirements of
   * the Cat class.
   * 
   * @param args is a list of Strings that serve as parameters.
   * @return A string depicting any problems with args, or the output of the
   *         command.
   */
  protected List<RetType> validate(List<String> args) {
    List<RetType> error = new ArrayList<>(1);
    if (args.size() < 2) {
      error.add(0, new InvalidNumberOfArgs(args, "cat"));
    }
    return error;
  }
}
