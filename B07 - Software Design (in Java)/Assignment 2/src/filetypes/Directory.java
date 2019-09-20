package filetypes;

import java.util.Iterator;
import JShellfilesystem.JShellFileSystem;
import driver.Parser;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * The Directory Class holds FileObjects, and can check if file names exist
 * inside it and return it. It can also add FileObjects to its contents.
 */
 
public class Directory extends FileObject implements Iterable<FileObject>, 
                                                            Serializable{
    public static final long serialVersionUID = 0L;
    
	private ArrayList<FileObject> directoryContents;
    private Directory self;
	private ArrayList<FileObject> itrStk;
	/**
	 * this method uses the iterator but first adds the Directory onto the
	 * variable created
	 */
	public class DirectoryIterator implements Iterator<FileObject> {
    
        public DirectoryIterator(Directory dir){
            itrStk = new ArrayList<>();
            itrStk.add(dir);
        }
        /** 
         * this method checks if the Directory has a next
         */
		public boolean hasNext() {
			return !(itrStk.isEmpty());
		}
		/**
		 * this method Pop File Object if it has another directory
		 */
		public FileObject next() {
			if (hasNext()) {
				FileObject itr = itrStk.remove(itrStk.size() - 1);
				if (itr.isDir())
					itrStk.addAll(((Directory) itr).directoryContents);
				return itr;
			}
            return null;
		}

	}

	@Override
	public Iterator<FileObject> iterator() {
		return (new DirectoryIterator(self));
	}


	// private static DirectoryStack dirStack = new DirectoryStack();

	/**
	 * Gets the path of the current Directory
	 * 
	 * @return a String of the current Directory path.
	 */
	public static String getCurrDirPathName() {
		return JShellFileSystem.getCurrentDir().getPathName();
	}

	/**
	 * This method creates a new Directory
	 * 
	 * @param name   is the file name
	 * @param path   is the file path name
	 * @param parent is the Directory that the new Directory is going to be in
	 */
	public Directory(String name, Directory parent) {
		super(name, parent);
		this.directoryContents = new ArrayList<FileObject>();
		this.self = this;
	}

	/**
	 * This method adds a new FileObject inside the Directory
	 * 
	 * @param fileObj is the new FileObject to be added
	 */
	public void add(FileObject fileObj) {
		if (getFileObjInDirectory(fileObj.getName()) == null) {
			this.directoryContents.add(fileObj);
		}
	}
	/**
	 * This method adds file object to directory Contents
	 * @param fileObj
	 */
	public void addForce(FileObject fileObj){
		FileObject fileOrDirFound = getFileObjInDirectory(fileObj.getName()) ;
		remove(fileOrDirFound);
		this.directoryContents.add(fileObj);
	}
	/**
	 * this method removes file Object from directory contents
	 * @param fileObj
	 */
	public void remove(FileObject fileObj) {
	  this.directoryContents.remove(fileObj);
	}
	/**
	 * This method gets the contents inside a directory
	 * @return contents in directory
	 */
	public ArrayList<FileObject> getDirectoryContents() {
	  return this.directoryContents;
	}
	
	/**
	 * this method sets the content of a directory to another directory
	 * @param a is the new directory to be added
	 */
	public void setDirectoryContents(ArrayList<FileObject> a) {
		this.directoryContents = a;
	}
	
	/**
	 * This method searches the directory for a FileObject with the same
	 * name as the input, and returns that object is it is found. if no object
	 * is found, it returns null.
	 * 
	 * @param name
	 * @return a FileObject with the same name as the input, otherwise returns
	 *         null.
	 */
	public FileObject getFileObjInDirectory(String name) {
	    FileObject elementFound = null;
		for (FileObject fileObj : directoryContents) {
			if (fileObj.getName().equals(name)) {
			    elementFound = fileObj;
			}
		}
		return elementFound;
	}
    
    /**
     * The method takes a string Path to a directory. The function then figures
     * out if it needs to traverse from the currentDirectory to get to the end 
     * of the path, or needs to traverse from the rootDirectory to get to the 
     * end of the path. If the path is invalid at any point during the 
     * traversal, then null is returned If the end of the path does not exist, 
     * then null is returned.
     * 
     * @param path is a string of the path name of the directory that you want
     *             change into
     * @return the Directory found using the String path.
     */
    public static Directory getDirAtPath(String path) {
      Directory currentDirectory = (Directory) JShellFileSystem.getCurrentDir();
      String[] dirnames = path.split("/");
      int i = 0;
      if (dirnames.length == 0) {
        currentDirectory = (Directory) JShellFileSystem.getRootDir();
      } else {
        String firstDir = dirnames[0];
        if (firstDir.equals("..")) {
          currentDirectory = currentDirectory.getParentDirectory();
          i = i + 1;
        } else if (firstDir.equals(".")) {
          i = i + 1;
        } else if (firstDir.equals("")) {
          currentDirectory = (Directory) JShellFileSystem.getRootDir();
          i = i + 1;
        }
      }
      while (i < dirnames.length && currentDirectory != null) {
        String dirname = dirnames[i];
        FileObject nextDir = currentDirectory.getFileObjInDirectory(dirname);
        if(nextDir == null || !nextDir.isDir()) {
          currentDirectory = null;
        } else {
          currentDirectory = (Directory) nextDir;
          i = i + 1;
        }
      }
      return currentDirectory;
    }
    /**
     * This getter method returns the root Directory if the path has "/"
     * @param path
     * @return root directory
     */
    public static FileObject getFileObjAtPath(String path) {
      if(path.equals("/")){
    	  return JShellFileSystem.getRootDir();
      }
      String[] splitPath = Parser.parsePath(path);
      String location = splitPath[0];
      String fileObjName = splitPath[1];
      
      FileObject foundFobj = null;
      if(getDirAtPath(location) != null){
    	  foundFobj = getDirAtPath(location).getFileObjInDirectory(fileObjName);
      }
      
      
      return foundFobj;
    }

}
