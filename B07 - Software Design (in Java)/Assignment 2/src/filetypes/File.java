package filetypes;

/**
 * The File class has a file name, and holds contents in the form of a string.
 */
public class File extends FileObject {
    /**
     * File has the same properties as FileObject, but has a new variable
     * called contents, which holds string for the user.
     */
    String contents;

    /**
     * This constructor method makes a new File with blank contents
     * 
     * @param filename is the name of the File
     * @param path     is the path of the File
     */
    public File(String filename, Directory parent) {
        super(filename, parent);
        this.contents = "";
    }

    /**
     * This constructor method makes a new File with contents
     * 
     * @param filename is the name of the File
     * @param path     is the path of the File
     * @param contents is the contents of the File
     */
    public File(String filename, Directory parent, String contents) {
        super(filename, parent);
        this.contents = contents;
    }

    /**
     * This method gets the contents of the File
     * 
     * @return the contents of the File
     */
    public String getContents() {
        return contents;
    }

    /**
     * This method sets the contents of the File
     * 
     * @param newString is the new String to replace the contents
     */
    public void setContents(String newString) {
        contents = newString;
    }

    /**
     * This method appends a new String to the existing String
     * 
     * @param addition is the new String to be appended
     */
    public void append(String addition) {
        contents = contents + "\n" + addition;
    }

    /**
     * This method overwrites the default to String method in order to 
     * print out the contents.
     * 
     * @return the contents of the File
     */
    public String toString() {
        return contents;
    }

}
