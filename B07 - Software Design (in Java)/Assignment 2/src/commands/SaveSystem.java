package commands;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import JShellReturnTypes.RetType;
import JShellfilesystem.JShellFileSystem;
import filetypes.Directory;
import filetypes.FileObject;
import JShellReturnTypes.*;
import filetypes.SaveJFS;

public class SaveSystem extends Command{
	
	/**
	 * Executes the input by first validating it
	 * if everything is good it then calls the
	 * saveObj function
	 * @return the string inputed after save command
	 */

	public List<RetType> execute(List<String> args){
		List<RetType> output = validate(args);
		if (output.get(0) instanceof StdOutput){
			output.set(0, saveObj(args.get(1)));
		}
		return output;
	}

	/**
	 * Saves the inputed file.
	 * @param fileName
	 * @return Error message is PathNotFound otherwise save inputed file
	 */
	private RetType saveObj(String fileName) {
		ObjectOutputStream saveSystem;
        FileOutputStream fs;
        try{
            System.out.println("0");
            fs = new FileOutputStream(fileName);
        }catch(Exception e){
            System.out.print(e);
        }
		try {
			System.out.println(("1 " + fileName));
			saveSystem = new ObjectOutputStream(new FileOutputStream(fileName));
			
		}catch(Exception e) {
				System.out.println(e);
				return new PathNotFound(fileName);
		}
		Directory rootDir = (Directory) JShellFileSystem.getRootDir();
        String curDir = JShellFileSystem.getCurrentDir().getPathName();
        ArrayList<Directory> dirStk = 
                        JShellFileSystem.getDirectoryStack().getStack();
        List<String> hist = ShellHistory.getCmds();
       SaveJFS saveShell = new SaveJFS(dirStk, rootDir.getDirectoryContents(),
               hist, curDir); 
		try{
			System.out.println(("2"));
			saveSystem.writeObject(saveShell);
		}catch(Exception e){
			System.out.println(e);
		}
		try{
			System.out.println(("3"));
			saveSystem.close();
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
			error.set(0, new InvalidNumberOfArgs(args, "Save"));
		}
		return error;
	}

}