package Hashmap;

import commands.Command;
import commands.Find;
import commands.ListDirectory;
import commands.LoadSystem;
import commands.MakeDirectory;
import commands.Move;
import commands.ChangeDirectory;
import commands.Echo;
import commands.PrintWorkingDirectory;
import commands.Exit;
import commands.Get;
import commands.CommandManual;
import commands.Copy;
import commands.PushDirectory;
import commands.SaveSystem;
import commands.PopDirectory;
import commands.ShellHistory;
import commands.Cat;
import commands.Tree;

import java.util.HashMap;

/**
 * Stores command objects. These objects are returned by the class. The objects
 * are only ever initialised once.
 */
public class HashMapCom {
    public static final HashMap<String, Command> CmdsInShell =
            new HashMap<String, Command>();

    /**
     * This method only ever initialises the hashmap once. Does nothing if
     * called more than once.
     */
    public static void initHashMap() {
        if (CmdsInShell.isEmpty()) {
            CmdsInShell.put("ls", new ListDirectory());
            CmdsInShell.put("mkdir", new MakeDirectory());
            CmdsInShell.put("cd", new ChangeDirectory());
            CmdsInShell.put("echo", new Echo());
            CmdsInShell.put("pwd", new PrintWorkingDirectory());
            CmdsInShell.put("exit", new Exit());
            CmdsInShell.put("man", new CommandManual());
            CmdsInShell.put("pushd", new PushDirectory());
            CmdsInShell.put("popd", new PopDirectory());
            CmdsInShell.put("history", new ShellHistory());
            CmdsInShell.put("cat", new Cat());
            CmdsInShell.put("cp", new Copy());
            CmdsInShell.put("mv", new Move());
            CmdsInShell.put("save", new SaveSystem());
            CmdsInShell.put("load", new LoadSystem());
            CmdsInShell.put("get", new Get());
            CmdsInShell.put("tree", new Tree());
            CmdsInShell.put("find", new Find());
        }

    }

    /**
     * This method returns back an object of the Command class
     *
     * @param input a valid key that exists in the Hashmap
     * @return an object of Command that exists in Hashmap.
     */
    public static Command get(String input) {

        if (CmdsInShell.containsKey(input))
            return CmdsInShell.get(input);
        return null;
    }
}