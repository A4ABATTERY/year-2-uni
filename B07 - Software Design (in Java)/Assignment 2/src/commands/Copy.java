package commands;

import java.util.ArrayList;
import java.util.List;
import JShellReturnTypes.InvalidArguments;
import JShellReturnTypes.InvalidNumberOfArgs;
import JShellReturnTypes.PathNotFound;
import JShellReturnTypes.RetType;
import JShellfilesystem.JShellFileSystem;
import driver.Parser;
import filetypes.Directory;
import filetypes.File;
import filetypes.FileObject;

public class Copy extends Command {

  /**
   * The function executes copy and copies a file/directory from one location
   * to another, overwriting any pre-existing files/directories
   * @param args which is just the arguments given to the copy command
   * @return the errors of copy if they exist
   */
  @Override
  public List<RetType> execute(List<String> args) {
    // TODO Auto-generated method stub
    List<RetType> error = validate(args);
    if (error.isEmpty()) {
      String moverPath = args.get(1);
      String[] splitPath = Parser.parsePath(moverPath);
      String sourcePath = splitPath[0];
      String moverObjName = splitPath[1];

      // destination path
      String destinationPath = args.get(2);

      Directory sourceDir = Directory.getDirAtPath(sourcePath);
      Directory destinationDir = Directory.getDirAtPath(destinationPath);

      if (destinationDir == null) {
        error.add(new PathNotFound(destinationPath));
      } else if (sourceDir == null) {
        error.add(new PathNotFound(sourcePath + moverObjName));
      } else {
        FileObject mover = sourceDir.getFileObjInDirectory(moverObjName);
        if (mover != null) {
          if (mover.isDir()) {
            if (destinationDir.isChildOf((Directory) mover)) {
              error.add(new InvalidArguments(args, "mv"));
            } else {
              Directory cpyDir = makeCopy((Directory) mover);
              cpyDir.setParentDirectory(destinationDir);
              destinationDir.addForce(cpyDir);
            }
          } else {
            File cpyFile = new File(moverObjName, destinationDir);
            destinationDir.addForce(cpyFile);
          }
        } else {
          error.add(new PathNotFound(sourcePath + moverObjName));
        }
      }
    }

    return error;

  }

  /**
   * This function parses arguments and outputs surface level argument
   * errors
   * @param args which is just the arguments given to the copy command
   * @return the errors of copy if they exist
   */
  @Override
  protected List<RetType> validate(List<String> args) {
    // TODO Auto-generated method stub
    List<RetType> error = new ArrayList<>(1);
    if (args.size() != 3) {
      error.add(0, new InvalidNumberOfArgs(args, "cp"));
    }
    return error;
  }

  /**
   * This function is responsible for making a copy of a directory
   * @param dir is the directory to copy
   * @return rootCpy with returns the root of the clone directory
   */
  public Directory makeCopy(Directory dir) {
    Directory rootCpy = new Directory(dir.getName(), null);
    ArrayList<Directory> dirCpyStk = new ArrayList<Directory>();
    dirCpyStk.add(rootCpy);// PUSHING
    int iter = 0;
    for (FileObject fobj : dir) {
      if (iter != 0) {
        int stkEnd = dirCpyStk.size() - 1;
        Directory popDir = dirCpyStk.get(stkEnd);
        if (((fobj.getParentDirectory()).getName()).equals(popDir.getName())){
          if (fobj.isDir()) {
            Directory fobjCpy = new Directory(fobj.getName(), popDir);
            popDir.add(fobjCpy);
            dirCpyStk.add(fobjCpy); // PUSHING
          } else {
            File fobjCpy = new File(fobj.getName(), popDir);
            popDir.add(fobjCpy);
          }
        } else {
          dirCpyStk.remove(stkEnd);// POPPING
        }
      }
      iter++;
    }
    return rootCpy;
  }
}
