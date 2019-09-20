# Running java program.

if [ $# -eq 0 ]
then
    echo "No arguments provided"
    echo "use 'bash ./runJShell.sh -t' to run testClasses"
    echo "use 'bash ./runJShell.sh -n' to run JShell"
    exit 1
fi





if [[ $1 = "-n" ]]
then

echo "\n\n\nCompile and run driver.Jshell\n\n\n"

    JSHELL=\
"src/commands/*.java "\
"src/driver/*.java "\
"src/filetypes/*.java "\
"src/Hashmap/*.java "\
"src/Redirection/*.java "\
"src/JShellfilesystem/*.java "\
"src/JShellReturnTypes/*.java"
    
    rm bin/*/*.class
    rm src/*/*.class

    javac -d bin/ $JSHELL
    java -cp bin/ driver.JShell
    exit 1
fi

if [[ $1 = "-t" ]]
then
    echo "We need to write test Classes and need Junit working"
    exit 1
fi



