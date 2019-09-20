package commands;

import java.util.List;
import java.util.ArrayList;
import JShellReturnTypes.*;

/**
 * The ShellHistory class displays all of the previous inputs.
 */
public class ShellHistory extends Command {
	private static List<String> cmdsEntered;
    
    public static void setShellHistory(List<String> a){
        cmdsEntered.addAll(a);
    }
    public static List<String> getCmds(){
        return cmdsEntered;
    }

	/**
	 * This constructor initialises the ArrayList that stores the past commands
	 * that the user has entered
	 */
	public ShellHistory() {
		cmdsEntered = new ArrayList<String>();
	}

	public static boolean isEmpty() {
		return (cmdsEntered.size() == 1);
	}

	/**
	 * This method appends a string to the end of the ArrayList. The string
	 * appended is the command that the user entered.
	 */
	public static void appendCmdEntered(String str) {

		int arrSize = cmdsEntered.size() - 1;
		String temp = " " + (arrSize + 1) + "  " + str + " ";
		str = " " + (arrSize + 2) + "  " + str + " ";
		if ((arrSize == -1) || !(cmdsEntered.get(arrSize).equals(temp))) {
			cmdsEntered.add(str);
		}
	}

	/**
	 * This method gets the command the user entered at a specific index.
	 * 
	 * @param atI is an index integer
	 * @return A String of a specific given input
	 */
	private String get(int atI) {
		return cmdsEntered.get(atI);
	}

	/**
	 * This method can get the specificed inputs sent in by the user.
	 * toString(42) (42 is an random num, could be anything) If a coder needs to
	 * get the entire history of commands this method needs to be passed the
	 * value -1. ie. toString(-1) if a number is negative or too large, it
	 * doesn't print anything.
	 * 
	 * @param nEntries number of entries you want to display
	 * @return a String with of a certain amount of inputs
	 */
	private String entriesToString(int nEntries) {
		String str = "";
		int arrSize = cmdsEntered.size();
		nEntries = arrSize - nEntries;
		if ((nEntries >= 0) && (nEntries < arrSize))
			for (int i = nEntries; i < arrSize; i++) {
				str += get(i);
				if (i < arrSize - 1)
					str += '\n';
			}
		else
			return entriesToString();
		return str;
	}
	/**
	 * This method returns a string tht is composed of on integer
	 * @return
	 */
	private String entriesToString() {
		String str = "";
		for (int i = 0; i < cmdsEntered.size(); i++) {
			str += get(i);
			if (i < cmdsEntered.size() - 1)
				str += '\n';
		}
		return str;
	}

	/**
	 * This method runs the main functionality of ShellHistory.
	 * 
	 * @param args is a list of String that serve as parameters.
	 * @return an empty String, or a String indicting an error.
	 */
	public List<RetType> execute(List<String> args) {
		List<RetType> output = validate(args);
		if (!output.isEmpty()) {
			if ((output.get(0).toString()).equals("all")) {
				output.set(0, new StdOutput(entriesToString()));
			} else if ((output.get(0).toString()).equals("PrintN"))
				output.set(0, new StdOutput(
						(entriesToString(Integer.parseInt(args.get(1))))));
		}
		return output;
	}

	/**
	 * This method checks if the parameters inserted match the requirements of
	 * the PushDirectory class.
	 * 
	 * @param args is a list of Strings that serve as parameters.
	 * @return a string indicating any problems with args.
	 */
	protected List<RetType> validate(List<String> args) {
		// String str = "";
		List<RetType> error = new ArrayList<>();
		int numOargs = args.size();
		if (numOargs == 1) {
			error.add(new StdOutput("all"));
		} else if (numOargs == 2) {
			int nEntries = 0;
			try {
				nEntries = Integer.parseInt(args.get(1));
			} catch (NumberFormatException e) {
				error.add(new InvalidArguments(args, "history"));
			} catch (NullPointerException e) {
				error.add(null);
			}
			if (nEntries > 0)
				error.add(new StdOutput("PrintN"));
			else
				error.add(new InvalidArguments(args, "history"));
		} else {
			error.add(new InvalidNumberOfArgs(args, "history"));
		}
		return error;
	}

}

