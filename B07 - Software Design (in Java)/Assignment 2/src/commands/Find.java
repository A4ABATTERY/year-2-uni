package commands;

import java.util.ArrayList;
import java.util.List;

import filetypes.Directory;
import filetypes.FileObject;
import JShellReturnTypes.FileObjNotFound;
import JShellReturnTypes.InvalidArguments;
import JShellReturnTypes.InvalidNumberOfArgs;
import JShellReturnTypes.PathNotFound;
import JShellReturnTypes.RetType;
import JShellReturnTypes.StdOutput;

public class Find extends Command{
	
  /**
   * The function executes Find which just searches given directory paths
   * for a given file/directory name
   * @param args which is just the arguments given to the find command
   * @return the pathnames of where the file/dir was found, errors if
   *	     paths do not exist
   */
	@Override
	public List<RetType> execute(List<String> args) {
		// TODO Auto-generated method stub
		List<RetType> outputs =  validate(args);
		if(outputs.get(0)==null){
			outputs.remove(0);
			List<Directory> dirsToSearch= new ArrayList<>();
			boolean findDir = args.get(args.size()-3).equals("d");
			String fileObjName = args.get(args.size()-1);
			for(int i = 1; i < args.size()-4 ; i++){
				String path = args.get(i);
				Directory dirToSearch = Directory.getDirAtPath(path);
				if(dirToSearch == null){
					outputs.add(new PathNotFound(path));
				}else{
					for(FileObject fileObj : dirToSearch){
						if(fileObj.getName().equals(fileObjName) && 
								fileObj.isDir() == findDir){
							outputs.add(new StdOutput(fileObj.getPathName()));
						}
					}
				}
				if(i != args.size()-5){
					outputs.add(new StdOutput(""));
				}			
			}			
		}
		return outputs;
	}

	  /**
	   * This function parses arguments and outputs surface level argument
	   * errors
	   * @param args which is just the arguments given to the find command
	   * @return the errors of move if they exist
	   */
	@Override
	protected List<RetType> validate(List<String> args) {
		// TODO Auto-generated method stub
	    List<RetType> error = new ArrayList<>();
	    error.add(null);
	    if(args.size() < 4){
	        error.set(0, new InvalidNumberOfArgs(args, "find"));
	    } else if(!args.get(args.size()-2).equals("-name") ||
	    		  !(args.get(args.size()-3).equals("f") ||
	    				  args.get(args.size()-3).equals("d"))|| 
	    		  !args.get(args.size()-4).equals("-type")){
	    	error.set(0, new InvalidArguments(args, "find"));
	    }
	    return error;
	}
}