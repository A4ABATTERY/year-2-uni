package commands;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.ArrayList;
import commands.ShellHistory;
import JShellfilesystem.JShellFileSystem;
import filetypes.Directory;
import filetypes.FileObject;
import JShellReturnTypes.*;
import filetypes.SaveJFS;

public class LoadSystem extends Command{
	
	/**
	 * Executes the input by first validating it
	 * if everything is good it then calls the
	 * loadObj function
	 * @return the string inputed after save command
	 */
	public List<RetType> execute(List<String> args){
		List<RetType> output = validate(args);
		if (ShellHistory.isEmpty())
			if  (output.get(0) instanceof StdOutput) {
				output.set(0, loadObj(args.get(1)));
				}
		else 
			output.set(0, new LoadError());
		return output;
	}
	/**
	 * Loads the inputed file.
	 * Allow it to be edited
	 * @param fileName
	 * @return Error message is PathNotFound otherwise save inputed file
	 */
	private RetType loadObj(String fileName) {
        ObjectInputStream ois;
        SaveJFS loadShell;
		try {
            FileInputStream fi = new FileInputStream(fileName);
            ois = new ObjectInputStream(new FileInputStream(fileName));

            loadShell = (SaveJFS) ois.readObject();
            loadShell.loadSystem();
            if(ois != null) 
                ois.close();
		}catch(Exception e) {
			System.out.println(e);
		}
        return null;
	}
	/**
	 * Validate the input to make sure it was only of size 2
	 * and a the file to be saved is of valid characters
	 * @return error message if invalid
	 */
	protected List<RetType> validate(List<String> args){
		List<RetType> error = new ArrayList<>();
		error.add(null);
		if ((args.size()==2) && (FileObject.parseValidName(args.get(1))|| 
				(args.get(1).contains("/")||(args.get(1).contains("~"))))){
			error.set(0, new StdOutput(""));	
		}else if (args.size()==2) {
			error.set(0, new InvalidFileName(args.get(1)));
		}else {
			error.set(0, new InvalidNumberOfArgs(args, "Load"));
		}
		return error;
	}
}