package commands;

import java.util.ArrayList;
import java.util.List;

import filetypes.Directory;
import driver.Parser;
import filetypes.FileObject;
import JShellReturnTypes.*;

/**
 * The MakeDirectory class creates Directories in the current Directory or
 * at a Directory in a given path.
 */
public class MakeDirectory extends Command{
//  @Override

  /**
   * This method runs the main functionality of MakeDirectory.
   * Uses a forLoop to go through all args that are passed.
   * Each iteration will make a new directory as per what the user passed in
   * if at any point there is an error, it is noted and the loop continues to
   * the next arguments.
   * At the end, if there were any errors, they would be returned
   * @param args is a list of String that serve as parameters.
   * @return returns an empty string, or a string indicating errors with the
   * parameters.
   */
    public List<RetType> execute(List<String> args) {
      //System.out.println("asdf");
      List<RetType> output = new ArrayList<>();
      List<RetType> error = validate(args);
      //IF no errors are made
      if(error.get(0) == null) {
        //get path from args
        for(int i = 1; i< args.size();i++) {
          String path = args.get(i);
          String[] splitPath = Parser.parsePath(path);
          String location = splitPath[0];
          String dirname = splitPath[1];
          String pathname = location+dirname;
          //System.out.println(location);
          //System.out.println("Made dir name:" + dirname + ", path:"+pathname);
          Directory locationDir = Directory.getDirAtPath(location);
          if(locationDir == null) {
            output.add(new PathNotFound(location));
          } else {
            if(locationDir.getFileObjInDirectory(dirname)==null) {
              if(FileObject.parseValidName(dirname)) {
                Directory newDir =  new Directory(dirname, locationDir);
                locationDir.add(newDir);
              } else {
                output.add(new InvalidFileName(dirname));
              }
            } else {
              output.add(new FileObjAlreadyExists(pathname));
            }
          }
        }
      } else {
        output = error;
      }

      return output;
    }
  /**
   * This method checks if the parameters inserted match the
   * requirements of the MakeDirectory class.
   * ie, mkdir needs more than 1 argument to function.
   * @param args is a list of Strings that serve as parameters.
   * @return A string depicting any problems with args.
   */
    protected List<RetType> validate(List<String> args) {
      List<RetType> error = new ArrayList<>();
      error.add(null);
      if(args.size() == 1) {
        error.set(0,new InvalidArguments(args, "mkdir"));
      }
      return error;
    }
}