package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import JShellfilesystem.JShellFileSystem;
import commands.*;
import filetypes.*;

public class GetTest {
        @After
        public void restore() {
                // as JShell system only initialize once, so restore back the
                // current
                // directory to root, clean the content in directory in order
                // to run the
                // whole test suite at once
                JShellFileSystem.setCurrentDir(
                                (Directory) JShellFileSystem.getRootDir());
                ((Directory) JShellFileSystem.getRootDir())
                                .setDirectoryContents(
                                                new ArrayList<FileObject>());
        }

        @Test
        public void testExecute() {
                // input arguments
                List<String> args = new ArrayList<String>(Arrays.asList("get",
                "https://raw.githubusercontent.com/ThierrySans/"
                + "CSCC01/master/404.html"));
                // initialize JShell system
                JShellFileSystem.initJShellFileSystem(new Directory("/", null));
                // initialize get command
                Get get = new Get();
                // execute get command
                get.execute(args);
                Directory dir = (Directory) JShellFileSystem.getCurrentDir();
                // get the file stored
                File file = (File) dir.getFileObjInDirectory("404");
                String output = file.toString();
                assertEquals("---layout: default---<span class=\"light\">404"
                                + "</span> Sorry this page does not exist.",
                                output);
        }

        @Test
        public void testWebsiteNotFoundError() {
                // input arguments for invalid web address
                List<String> args = new ArrayList<String>(
                                Arrays.asList("get", "fakewebsite"));
                JShellFileSystem.initJShellFileSystem(new Directory("/", null));
                // initialize get command
                Get get = new Get();
                assertEquals("JShell: Website not found: \"fakewebsite\" was "
                		+ "not found",
                                get.execute(args).get(0).toString());
        }

        @Test
        public void testValidate() {
                // test invalid arguments error
                List<String> args = new ArrayList<String>(Arrays.asList("get"));
                Get get = new Get();
                assertEquals("JShell: get: Too many, or too few arguments: "
                		+ "get ",
                                get.execute(args).get(0).toString());
        }
}
