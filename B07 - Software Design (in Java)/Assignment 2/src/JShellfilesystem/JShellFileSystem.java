package JShellfilesystem;


import filetypes.Directory;
import filetypes.FileObject;
import filetypes.DirectoryStack;
import java.util.ArrayList;
/**
 * This is the JShellFileSystem. There is only one ever made.
 * The singleton method initJShellFileSystem() makes sure of that.
 * It holds the current directory, root direectory and the directory
 * stack that is to be used by PushDirectory, and PopDirectory.
 */
public class JShellFileSystem<T extends FileObject>{
  
    private static FileSystem<FileObject> /*<E>*/ single;

    private static class FileSystem<T extends FileObject>{
        T rootDir;
        T currDir;
        /*JStack<E>*/ DirectoryStack dirStk;
        public FileSystem(T a){
            rootDir = a;
            currDir = rootDir;
            dirStk = new DirectoryStack();
        }
    }

    /**
     * Initialises the filesystem.
     * This can be called multiple times, but it would do anything
     * after it has been called once.
     */
    public static <T extends FileObject> FileSystem<FileObject> 
    initJShellFileSystem(T a){
        if(single == null){
//           single = a.constructInstance();
           single = new FileSystem<FileObject>(a);
        }
        return single;

    }
    /**import java.util.Stack;
     * Returns the referece to the rootdir
     * @return reference to the rootDir
     */
    public static <T> FileObject getRootDir(){
      return single.rootDir;
    }
    /**
     * Returns the reference to the current dir
     * @return reference to the current dir
     */
    public static <T> FileObject getCurrentDir(){
        return single.currDir;
    }
    /*
     * returns a reference to the direectoryStack object
     * @return reference to dirStack
     */
    public static DirectoryStack getDirectoryStack(){
        return single.dirStk;
    } 
    /**
     * Sets the current dir to a reference that is given to it
     * @param dir Given a reference to a directory object.
     */
    public static void setCurrentDir(Directory dir) {
        single.currDir = dir;
    }
    /**
     * Sets the DirectoryStack to a reference thats given to it
     * @param a
     */
    public static void setDirectoryStack(ArrayList<Directory> a){
        single.dirStk.setStack(a);
    }
}