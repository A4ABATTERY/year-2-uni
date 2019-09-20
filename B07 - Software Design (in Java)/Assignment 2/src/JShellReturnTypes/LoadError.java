package JShellReturnTypes;

public class LoadError extends StdError{

	/**
	 * method checks if load was typed after another command
	 */
	public LoadError() {
        data = "";
    }
	/**
	 * the method returns an error message if load wasn't the first
	 * command entered
	 * @param fileName
	 */
	public String toString(){
        return "JShell: Load was not the first command entered";
    }
}
