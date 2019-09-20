package commands;

import java.util.ArrayList;
import java.util.List;

import filetypes.Directory;
import filetypes.FileObject;
import JShellReturnTypes.InvalidNumberOfArgs;
import JShellReturnTypes.RetType;
import JShellReturnTypes.StdOutput;
import JShellfilesystem.JShellFileSystem;

public class Tree extends Command{

    /**
     * This method runs the main functionality of Tree which is
     * to display the entire file system in a tree format
     * 
     * @param args is a list of String that serve as parameters.
     * @return a tree representation of the entire file system
     */
	@Override
	public List<RetType> execute(List<String> args) {
		// TODO Auto-generated method stub
		//Directory
		int iter = 0;
		int initialDepth = 0;
		List<RetType> outputs = validate(args);
		String out = "/\n";
		if(outputs.get(0) == null){
			outputs.remove(0);
	      	for(FileObject subFileObj: (Directory)JShellFileSystem.
	      			getRootDir()){
	      		if(iter != 0){
	          		int numTab = subFileObj.getDepth()-initialDepth;
	          		for(int tab = 0; tab < numTab; tab++){
	          			out += "\t";
	          		}
	          		out += subFileObj.getName()+"\n";
	      		}
	      		iter++;
	      	}
	      	outputs.add(new StdOutput(out.substring(0,out.length()-1)));
		}
        return outputs;
	}

   /**
    * This function parses arguments and outputs surface level argument
    * errors
    * @param args which is just the arguments given to the tree command
    * @return the errors of copy if they exist
    */
	@Override
	protected List<RetType> validate(List<String> args) {
		// TODO Auto-generated method stub
	    List<RetType> error = new ArrayList<>();
	    error.add(null);
	    if(args.size()>1){
	        error.set(0, new InvalidNumberOfArgs(args, "tree"));
	    }
	    return error;
	}
}