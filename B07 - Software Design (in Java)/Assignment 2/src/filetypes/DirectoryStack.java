package filetypes;

import java.util.ArrayList;
import filetypes.Directory;

/**
 * The DirectoryStack class is a stack that holds references to Directories,
 * and can push and pop Directories when needed.
 */
public class DirectoryStack {
    ArrayList <Directory> directoryStack;

    /**
     * This constructor method creates a new DirectoryStack
     */
    public DirectoryStack() {
      directoryStack = new ArrayList<Directory>();
    }

    /**
     * This method returns a boolean based on if there are FileObjects in it or
     * not.
     * @return true if it is empty, false otherwise
     */
    public boolean isEmpty() {
      return (this.directoryStack.size()==0);
    }

    /**
     * Puts a Directory into the DirectoryStack
     * @param dir is a Directory
     */
    public void push(Directory dir) {
      this.directoryStack.add(dir);
    }

    /**
     * Pops a Directory out of the DirectoryStack
     * @return Returns a a directory from the stack in LIFO manner.
     */
    public Directory pop() {
      Directory lastDir = null;
      if(!isEmpty()) {
        lastDir = this.directoryStack.get(directoryStack.size()-1);
        directoryStack.remove(lastDir);
      }

      return lastDir;
    }
    /**
     * this setter method sets directoryStack when called upon
     * @param a
     */
    public void setStack(ArrayList<Directory> a){
        directoryStack = a;
    }
    /**
     * this getter method gets directoryStack when called upon
     * @return
     */
    public ArrayList<Directory> getStack(){
        return directoryStack;
    }
}
