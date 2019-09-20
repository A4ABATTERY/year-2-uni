package commands;

// import commands.Command;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import JShellfilesystem.JShellFileSystem;
import filetypes.Directory;
import filetypes.FileObject;
import driver.Parser;
import JShellReturnTypes.*;

public class ListDirectory extends Command {
    /**
     * This method runs the main functionality of ListDirectory it uses
     * validate to figure out if multiple files need dirs/files need to
     * be listed, or if the currentDirectory needs to be listed
     *
     * @param args is a list of String that serve as parameters.
     * @return a String of all of the file names found
     */
    public List<RetType> execute(List<String> args) {
        List<RetType> outputs = validate(args);
        RetType lsFunction = outputs.remove(0);
        if(lsFunction == null){
            Directory curDir = (Directory) JShellFileSystem.getCurrentDir();
            String contents = lisFileObj(curDir);
            if(!contents.equals("")){
            	outputs.add(new StdOutput(lisFileObj(curDir)));
            }
        }else if(lsFunction.toString().equals("-R")){
            outputs = listFileObj_R(outputs);
        }else if(lsFunction.toString().equals("dirs&Paths")){
            outputs = listMultipleFileObjects(outputs);
        } else{

        }
        return outputs;
    }

    /**
     * This method works specifically for recursively displaying the contents
     * of given pathnames of directories or files
     *
     * @param args is a list of Strings that serve as pathnames
     * @return outputs which is just the output of the recursive display
     */
    public List<RetType> listFileObj_R(List<RetType> outputs){
    	if(outputs.size() == 0){
    		outputs.add(new StdOutput(JShellFileSystem.getCurrentDir()
    				.getPathName()));
    	}
        for(int i = 0; i < outputs.size(); i++){
          RetType output = outputs.get(i);
          if(output instanceof StdOutput){
            String path = output.toString();
            String out;
            FileObject fileObj = Directory.getFileObjAtPath(path);
            if(fileObj == null){
            	outputs.set(i, new PathNotFound(path));
            } else if(fileObj.isDir()){
            	out= path+":";
            	int initialDepth = fileObj.getDepth();
            	int iter = 0;
            	for(FileObject subFileObj: (Directory)fileObj){
            		if(iter != 0){
            			if(iter == 1){
            				out += "\n";
            			}
                		int numTab = subFileObj.getDepth()-initialDepth;
                		for(int tab = 0; tab < numTab; tab++){
                			out += "\t";
                		}
                		if(subFileObj.isDir()){
                			out += subFileObj.getName()+":";
                			if(!((Directory)subFileObj).getDirectoryContents()
                					.isEmpty()){
                				out += "\n";
                			}
                		} else{
                			out += subFileObj.getName()+"\n";
                		}
            		}
            		iter++;
            	}
            	outputs.set(i, new StdOutput(out));
            }else{
            	out = path;
            	outputs.set(i, new StdOutput(out));
            }
          }
        }
        return outputs;
    }
    /**
     * This method works specifically for multiple files or directories that
     * that need to be listed. uses a forLoop to iterate through the arguments
     * (which are dirs or files that may or may not exists) and prints them
     * accordingly. if a file or a dir is not found, an error message with be
     * printed just for that dir/file.
     *
     * @param args is a list of String that serve as parameters
     */
    public static List<RetType> listMultipleFileObjects(List<RetType> outputs){
        int size = outputs.size();
        List<RetType> finalOutputs = new ArrayList<>();
        for(int i = 0; i < size; i++){
            RetType output  = outputs.remove(0);
            if(output instanceof StdOutput){
                String path = output.toString();
                FileObject fObj= Directory.getFileObjAtPath(path);
               
                if(fObj.isDir()){
                    finalOutputs.add(new StdOutput(path+":\n\t"
                            + lisFileObj(fObj).replace("\n", "\n\t")));
                } else{
                	finalOutputs.add(output);
                }
              
            }else{
                finalOutputs.add(output);
            }
        }
        return finalOutputs;
    
    }
    /**
     * This method lists directory contents if 'dir' is a directory
     * if 'dir' is a File, then it will just return the file name.
     *
     * @param dir is a FileObject.
     * @return It returns a String, that is either the contnents of a dir or is
     *         the name of a file
     */
    public static String lisFileObj(FileObject fobj){
    String str = "";
		if (fobj != null){
			if(fobj.isDir()){
				int dirLength = ( (Directory) fobj).getDirectoryContents()
						.size();
				for(int i = 0; i < dirLength; i++){
					str +=
						( (Directory) fobj).getDirectoryContents().get(i)
						.getName();
					if( i < dirLength-1 )
						str += '\n';
				}
			} else{
				str = fobj.getName();
			}
		}else{
			    str += "File Not Found Error";
	    }
		return str;
    }

    /**
     * This method checks if the parameters inserted match the requirements of
     * the ListDirectory class.
     *
     * @param args is a list of Strings that serve as parameters
     * @return A string depicting if the user has enterd in just "ls" or if the
     *         user has entered "ls $PATH1 $PATHn $DIR1 $DIRn $FILE1 $FILEn".
     *         OR "ls -R $path1 $pathn $Dir1 $dirn $file1 $filen".
     */
    protected List<RetType> validate(List<String> args) {
        List<RetType> outputs = new ArrayList<>();
        if(args.size() < 2) // $ls
            (outputs).add(null);
        else // $ls -R dirs paths || $ls dirs paths
            if((args.get(1)).equals("-R")){
                                     // $ls -R dirs paths
                outputs.add(new StdOutput("-R"));
                outputs = validateHelper(args, outputs, 2);
            }else if(!(args.contains("-R"))){ // $ls dirs paths
                outputs.add(new StdOutput("dirs&Paths"));
                outputs = validateHelper(args, outputs, 1);
            }else{
                outputs.add(new InvalidArguments(args, "ls"));
            }
        return outputs;
    }
    /**
     * Error handle in order to take negative numeric and automatically
     * go to basecase (i =1)
     * get pathname is File Object at path is not null. 
     * if null send error message
     * @param args
     * @param outputs
     * @param i
     * @return
     */
    private List<RetType> validateHelper(List<String> args,
                                        List<RetType> outputs, int i){
        if(i < 0){
            i = 1;
        }
        FileObject temp = null;
        for( ; i < args.size(); i++){
            String arg = args.get(i);
            temp = Directory.getFileObjAtPath(arg);
            if(temp != null)
                outputs.add(new StdOutput(temp.getPathName()));
            else
                outputs.add(new FileObjNotFound(arg));
        }
        return outputs;
    }
}