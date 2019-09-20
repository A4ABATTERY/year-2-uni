package commands;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import JShellReturnTypes.*;
/**
 * The CommandManual class holds and displays helpful info about how to use
 * other Commands.
 */
public class CommandManual extends Command{
	public static final HashMap<String, String> manual = new HashMap<>();


	/**
	 * This method runs the main functionality of CommandManual.
     * It gets commands from a Hashtable
	 * @param args is a list of Strings that serve as parameters
	 * @return A string describing how to use a command.
	 */
    public List<RetType> execute(List<String> args){
    	List<RetType> output = validate(args);
        RetType temp = output.get(0);
        if(temp instanceof StdOutput){
    	    output.set(0, new StdOutput(manual.get(args.get(1))));
        }else if(temp  == null){
            output.set(0, new StdOutput(manual.get("man")));
        }
        return output;
    }
//    @Override
	/**
	 * This method initialises the hashtable that is uses by this class
     * it assigns commands calls (ie. ls, cd, mkdir) to the appropriete
     * manual/instruction on how to use the command.
	 */
    public static void initManual() {
    manual.put("man", "Manual\ntype in 'man $SomeCommand'\n"+
            "Commands that exist:\n"+
            " ls  -  List Directory\n mkdir  -  Make Directory\n"+
            " cd  -  Change Directory\n echo  -  Echo\n"+
            " pwd -  PrintWorkingDirectory\n history - history\n"+
            " pushd - Push Directory\n popd - Pop Directory\n"+
            " cat  - Concatenate\n exit - Exit\n mv - MoveFileObject\n"+
						" cp - CopyFileObject\n get - Get file at URL\n"+
						" save - Save\n load - Load\n find - find file\n"+
						" tree - display entire file system as a tree" );
    manual.put("ls", "LIST\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"ls [PATH ...] If no paths are given, print contents of current\n"+
    		"directory with a new line following each of the content.\n"+
    		"Otherwise, for each path p:\n"+
    		"-If p specifies a file, print p\n"+
    		"-If p specifies a directory, print p,+':'+, contents of that\n"+
    		"  directory, then an extra new line.\n"+
    		"-If p does not exist, print a suitable message.\n"+
    		"\n"+
    		"Example:\n"+
    		"\n"+
    		"ls\n"+
    		"Print the contents of the current directory.\n"+
    		"\n"+
    		"ls DIR\n"+
    		"Print the contents of the directory DIR.\n"+
    		"If DIR does not exist then print an error message.\n"+
    		"\n"+
    		"ls PATH\n"+
    		"Follow path provided and print contents of the directory at\n"+
    		" end of the path.\n"+
				"\n"+
				"ls -R PATH\n"+
				"Print all subdirectories recursively.\n"+
				"If no path is given print the contents of the current\n"+
				"directory, with a new line following each of the content.");
    manual.put("mkdir", "MAKE DIRECTORY\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"Create directories, each of which may be relative to the\n"+
    		"current directory or may be a full path.\n"+
    		"\n"+
    		"Example:\n"+
    		"\n"+
    		"mkdir DIR\n"+
    		"Make a new directory named DIR in current directory.\n"+
    		"\n"+
    		"mkdir PATH\n"+
    		"Example: mkdir this/path/exists/ThisDirWasMade\n"+
    		"Make new directory named ThisDirWasMade in exists directory\n"+
    		"assuming the path provided exists.");
    manual.put("cd", "CHANGE DIRECTORY\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"Change directory to DIR, which may be relative to the current\n"+
    		"directory or may be a full path.\n"+
    		"\n"+
    		"Example:\n"+
    		"\n"+
    		"cd\n"+
    		"Change Current directory to home directory.\n"+
    		"\n"+
    		"cd ..\n"+
    		"Change Current directory to parent directory.\n"+
    		"\n"+
    		"cd /\n"+
    		"Change Current directory to root directory.\n"+
    		"\n"+
    		"cd DIR\n"+
    		"Change Current directory to DIR directory.\n"+
    		"\n"+
    		"cd PATH\n"+
    		"Change Current directory to the directory named at the end of\n"+
    		"path by taking the path inputted it by the user");
    manual.put("echo", "ECHO\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"If a file isn't provided, print STRING in shell. Otherwise,\n"+
    		"put STRING into OUTFILE. This creates a new file if OUTFILE\n"+
    		"doesn't exists and erases the old contents if OUTFILE already\n"+
    		"exists. In either case, only thing in OUTFILE is STRING.\n"+
    		"\n"+
    		"Example:\n"+
    		"\n"+
    		"echo STRING [> OUTFILE]\n"+
    		"Place STRING into OUTFILE\n"+
				"if OUTFILE not provided print STRING on the shell.\n"+
    		"\n"+
    		"echo STRING >> OUTFILE\n"+
    		"Like the previous command, but appends instead of overwrites.\n");
    manual.put("pwd", "PrintWorkingDirectory\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"Print current working directory (including the whole path).\n"+
    		"\n"+
    		"Example:\n"+
    		"\n"+
    		"pwd\n"+
    		"Print path from the home directory to the working directory.\n");
    manual.put("exit", "EXIT\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"Quit the program.");
    manual.put("pushd", "PUSHD\n"+
    		"\n" +
    		"Description:\n"+
    		"\n"+
    		"Save current directory by pushing onto directory stack then\n"+
    		"change new current working directory to DIR. The push must be\n"+
    		"consistent as per the LIFO behavior of a stack.pushd command\n"+
    		"saves old current directory in directory stack so that it can\n" +
    		"be returned to at any time (popd).Size of the directory stack\n"+
    		"is dynamic and dependent on the pushd and the popd commands.\n"+
    		"\n"+
    		"Example:\n"+
    		"pushd doc\n"+
    		"Change current directory to doc directory and push doc into\n"+
    		"the directory stack.\n");
    manual.put("popd", "POPD\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"Remove top entry from the directory stack, and cd into it. The\n"+
    		"removal must be consistent with LIFO behavior of a stack. The\n"+
    		"popd command removes the top most directory from the directory\n"+
    		"stack and makes it the current directory.\n"+
    		"\n"+
    		"Example:\n"+
    		"popd doc\n"+
    		"Change current directory to doc directory and pop the doc\n"+
    		"into directory stack.\n");
    manual.put("history", "HISTORY\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"history [number]\n"+
    		"This command will print recent commands, one per line.\n"+
				"\n"+
				"Example:\n"+
    		"1.cd ..\n"+
    		"2.mkdir textFolder\n"+
    		"3.echo â€œHello Worldâ€�\n"+
    		"4.fsjhdfks\n"+
    		"5.history\n"+
    		"\n"+
    		"Output from history has two columns: First column numbered so\n"+
    		"the line with highest number is the most recent command. Most\n"+
    		"recent command is history.Second column has the actual command.\n"+
    		"\n" +
    		"Truncate output by specifying a number (>=0) after the command.\n"+
				"\n" +
				"View last 3 commands typed using command line: history 3\n"+
    		"And the output will be as follows:\n"+
    		"4.fsjhdfks\n"+
    		"5.history\n"+
    		"6.history 3\n");
    manual.put("cat", "Concatenate\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"cat FILE1 [FILE2 ...]\n"+
    		"Display the contents of FILE1 and other files (i.e.File2 ...)\n"+
    		"concatenated in shell. There will be three line breaks to\n"+
    		"separate the contents of one file from the other file. If\n"+
    		"theres more than one file, it will be displayed\n"+
			"\n"+
    		"Example:\n"+
    		"cat File1\n"+
    		"Display the content of File1 in the shell\n"+
    		"\n"+
    		"- cat File1 â€¦ File2 â€¦");
		manual.put("mv", "MoveFileObject\n"+
				"\n"+
				"Description:\n"+
				"\n"+
  			"mv OLDPATH NEWPATH\n"+
			"Move item OLDPATH to NEWPATH.\n"+
	  		"OLDPATH and NEWPATH may be relative to current directory or may\n"+
			"be full paths. If NEWPATH is directory, move item to directory.\n"+
	  		"\n"+
	  		"Example:\n"+
	   		"mv file1 file2\n"+
    		"move file1 to file2");
		manual.put("cp", "CopyFileObject\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"cp OLDPATH NEWPATH\n"+
    		"Copy item OLDPATH to paste into NEWPATH. Both OLDPATH and\n"+
    		"NEWPATH may be relative to the current directory or may be\n"+
			"full paths. If NEWPATH is a directory, move item to directory.\n"+
    		"\n"+
    		"Example:\n"+
    		"cp file1 file2\n"+
    		"copy file1 to file2");
		manual.put("get", "Get\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"get URL\n"+
    		"Retrieves the file from given URL and adds to current Directory\n"+
    		"\n"+
    		"Example:\n"+
    		"get http://www.cs.cmu.edu/spok/grimmtmp/073.txt\n"+
    		"Will get contents of the file, ex :073.txt and create a file\n"+
			"called 073.txt with contents in current working directory.");
		manual.put("save", "Save\n"+
    		"\n"+
    		"Description:\n"+
    		"\n"+
    		"save PATH\n"+
    		"save the session of the JShell before its closed down\n"+
    		"\n"+
    		"Example:\n"+
    		"save /Users/User1/Desktop/save.txt\n"+
    		"Creates file save.txt on computer and save the JShell session");
		manual.put("load", "Load\n"+
	  		"\n"+
	  		"Description:\n"+
	  		"\n"+
	  		"load FileName\n"+
	  		"load last saved session of the JShell before it was closed down\n"+
	  		"\n"+
	  		"Example:\n"+
	  		"load /Users/User1/Desktop/save.txt\n"+
	  		"opens file save.txt into the JShell");
		manual.put("find", "Find\n"+
			"\n"+
			"Description:\n"+
			"\n"+
			"find path ... -type [f|d] -name expression\n"+
			"searches the directory that has the name given in the path\n"+				
			"\n"+
			"Example:\n"+
			"find /users/Desktop -type f -name xyz\n"+
			"search directory Desktop and find files (indicated by type f)\n"+
			"that have the name exactly xyz");
		manual.put("tree", "Tree\n"+
			"\n"+
			"Description:\n"+
			"\n"+
			"tree\n"+
			"Takes in no input parameter. Displays entire filesystem as a\n"+
			"tree starting from the root directories.\n"+
			"\n"+
			"Example:\n"+
			"if root directory contains two subdirectories as A and B then\n"+
			"you will display following:\n"+ "\\ \n" + "   A\n" + "   B\n\"");}

	/**
	 * This method checks if the parameters inserted match the
	 * requirements of the CommandManual class.
     * Makes sure that 3 or more args are not entered.
	 * @param args is a list of Strings that serve as parameters
	 * @return A string depicting any problems with args, if they exist.
	 */
    protected List<RetType> validate(List<String> args) {
        List<RetType> error = new ArrayList<>();
        error.add(null);
        if((args.size() == 2) && (manual.containsKey(args.get(1)))){
            error.set(0,new StdOutput(""));    
        }else if(args.size() == 1){
            error.set(0, null);
        }else if(args.size() > 2){
            error.set(0, new InvalidNumberOfArgs(args, "man"));
        }else{
            error.set(0, new InvalidArguments(args, "man"));
        }
        return error;
    }
}