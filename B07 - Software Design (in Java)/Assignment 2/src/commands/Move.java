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
import filetypes.FileObject;

public class Move extends Command{
	  /**
	   * The function executes Move and moves a file/directory from one
	   * location to another, overwriting any pre-existing files/directories
	   * @param args which is just the arguments given to the move command
	   * @return the errors of copy if they exist
	   */
	  @Override
	  public List<RetType> execute(List<String> args) {
	    // TODO Auto-generated method stub
	    List<RetType> error = validate(args);
	    if(error.isEmpty()) {
	      //moving object path
	      String moverPath = args.get(1);
	      String[] splitPath = Parser.parsePath(moverPath);
	      String sourcePath = splitPath[0];
	      String moverObjName = splitPath[1];
	      
	      //destination path
	      String destinationPath = args.get(2);
	      
	      Directory sourceDir = Directory.getDirAtPath(sourcePath);
	      Directory destinationDir = Directory.getDirAtPath(destinationPath);
	
	      if(destinationDir == null){
	    	  error.add(new PathNotFound(destinationPath));
	      } else if(sourceDir == null) {
	        error.add(new PathNotFound(sourcePath+moverObjName));
	      } else {
	        FileObject mover = sourceDir.getFileObjInDirectory(moverObjName);
	        if(mover != null) {
		        if(mover.isDir() && destinationDir.isChildOf((Directory)mover)){
		            error.add(new InvalidArguments(args, "mv"));
		        } else{
		            sourceDir.remove(mover);
		            mover.setParentDirectory(destinationDir);
		            destinationDir.addForce(mover);
		        }
	        }else {
	          error.add(new PathNotFound(sourcePath+moverObjName));
	        }
	      }
	    }
	
	    return error;
	  }
	
	  /**
	   * This function parses arguments and outputs surface level argument
	   * errors
	   * @param args which is just the arguments given to the move command
	   * @return the errors of move if they exist
	   */
	  @Override
	  protected List<RetType> validate(List<String> args) {
	    // TODO Auto-generated method stub
	    List<RetType> error = new ArrayList<>(1);
	    if (args.size() != 3) {
	      error.add(0, new InvalidNumberOfArgs(args, "mv"));
	    }
	    return error;
	  }
}