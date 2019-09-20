package filetypes;

import java.io.Serializable;
import JShellfilesystem.JShellFileSystem;

/**
 * This is the parent class to Directory and File it only specifies
 * a name and a path name for where the file is stored.
 */
public class FileObject implements Serializable{

    public static final long serialVersionUID = 0L;

    private String name;
    //private String pathname;
    private Directory parentDirectory;
    public static char[] invalidCharacters = new char[]{'/','!','@','#','$',
        '%','^','&','*','(',')','{','}','~','|','<','>','?'};
    
    /**
     * Constructor that assigns a name and a path to the object.
     * @param name Used to set name of object
     * @param path Used to set pathname of object
     */
    public FileObject(String name, Directory parentDir){
       this.name = name;
       this.parentDirectory = parentDir;
    }

    /**
	 * Executes the input by first validating it
	 * if everything is good it then calls the
	 * loadObj function
	 * @return the string inputed after save command
	 */
    public String getPathName(){
      String path;
      if(parentDirectory != null) {
        if(parentDirectory.equals(JShellFileSystem.getRootDir())) {
          path = parentDirectory.getPathName()+this.name;
        } else {
          path = parentDirectory.getPathName()+"/"+this.name;
        }
      } else{
        path =this.name;
      }
      return path;
    }

  /**
   * returns the name of the object
   * 
   * @Return name of the object
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the object.
   * 
   * @param n is the name that is set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * checks if a the string passed to it can be used as a 
   * valid filename. True if is valid false if is not valid.
   * 
   * @param fileName a string
   * @return boolean.
   */
   public static boolean parseValidName(String fileName){
       if(fileName.equals("")) {
         return false;
       }
       for(char invalidChar: invalidCharacters) {
         if(fileName.indexOf(invalidChar) != -1) {
           return false;
         }
       }
       return true;
   }
    /**
     * this method gets the Depth of the File object
     * @return
     */
    public int getDepth() {
      String pathname = this.getPathName();
      int depth;
      if(pathname.equals("/")){
    	  depth = 0;
      } else{
    	  depth = pathname.length() - pathname.replace("/", "").length();
      }
      return depth;
    }
    /**
     * this method gets the relative object point relative to starting point
     * @param start
     * @return number
     */
    public int getRelativeDepth(int start){
        int actualDepth = this.getDepth();
        if(actualDepth >= start){
            return actualDepth - start;
        }
        return -1;
    }
    /**
     * this method duplicates character given
     * @param a
     * @return string
     */
    public String getDepthInTermsOfaChar(char a){
        int b = this.getDepth();
        String str = "";
        for(int i = 0; i < b; i++){
            str += a;
        }
        return str;
    }
    /**
     * this method duplicates b times
     * @param a
     * @param b 
     * @return string
     */
    public String getDepthInTermsOfaChar(String a, int b){
        String str = "";
        b = this.getDepth() - b;
        for(int i = 0; i < b; i++){
            str += a;
        }
        return str;
    }
    /**
     * This method check if the object is an instance of Directory
     * @Return boolean.
     */
    public boolean isDir(){
      boolean output = false;
      if (this instanceof Directory){
        output = true;
      }
      return output;
    }

    /**
     * This sets the path name of the object
     * @param newPathName Assigned to the pathname of object
     */

    public Directory getParentDirectory() {
      return parentDirectory;
    }
    /**
     * This method sets parent Directory 
     * @param parent
     */
    public void setParentDirectory(Directory parent) { 
      this.parentDirectory = parent;
      //parent.add(this);
    }
    
    /**
     * this method determines if the child belongs the parent directory
     * @param dir
     * @return boolean value
     */
    public boolean isChildOf(Directory dir){
    	if((this.getPathName()).startsWith(dir.getPathName())){
    		return true;
    	} else{
    		return false;
    	}
    }
}
