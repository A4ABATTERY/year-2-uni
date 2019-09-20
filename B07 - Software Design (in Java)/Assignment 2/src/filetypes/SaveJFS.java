package filetypes;

import java.util.ArrayList;
import java.util.List;
import JShellfilesystem.*;
import filetypes.Directory;
import filetypes.DirectoryStack;
import commands.ShellHistory;
import java.io.Serializable;

public class SaveJFS implements Serializable{
    
    private static final long serialVersionUID = -62904123833L;

    public ArrayList<Directory> dirStack;
    public ArrayList<FileObject> dirContent;
    public List<String> history;
    public String currDirPath;

    public SaveJFS(ArrayList<Directory>dirStk, ArrayList<FileObject>dirCont, 
            List<String> hist, String curDirP){

        dirStack = dirStk;
        dirContent = dirCont;
        history = hist;
        currDirPath = curDirP;
        System.out.println(currDirPath);

    }
    
    private void loadTransitionhelper(ArrayList<FileObject> arr){
        Directory nwRoot = (Directory) JShellFileSystem.getRootDir();
        for(FileObject f : arr){
            f.setParentDirectory(nwRoot);
        }
    }

    public void loadSystem(){
    if(dirContent != null){
    loadTransitionhelper(dirContent);
    ((Directory)JShellFileSystem.getRootDir()).setDirectoryContents(dirContent);
    }
    if(history != null){
    ShellHistory.setShellHistory(history);
    }
    if(Directory.getDirAtPath(currDirPath) != null){
    JShellFileSystem.setCurrentDir(Directory.getDirAtPath(currDirPath));
    }else{
        try{
            System.out.println(currDirPath);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    if(dirStack != null){
    JShellFileSystem.setDirectoryStack(dirStack);
    }    
    }






}
