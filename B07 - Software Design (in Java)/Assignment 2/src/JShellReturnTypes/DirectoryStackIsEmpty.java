package JShellReturnTypes;

public class DirectoryStackIsEmpty extends StdError{
	/**
	 * method checks if Directory Stack is Empty
	 */
    public DirectoryStackIsEmpty(){
    }
    /**
	 * the method returns an error message if Directory Stack is empty but user
	 * trys to push a directory first
	 * @param fileName
	 */
    public String toString(){
        return "Directory Stack is empty. Please push a directory first";
    }
}