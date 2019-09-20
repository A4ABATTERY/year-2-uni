package JShellReturnTypes;

public class StdOutput implements RetType {
	/**
	 * the method returns data if it isn't an error
	 * @param fileName
	 */
    protected String data;

    public StdOutput(String output) {
        data = output;
    }

    public String toString() {
        return data;
    }
}