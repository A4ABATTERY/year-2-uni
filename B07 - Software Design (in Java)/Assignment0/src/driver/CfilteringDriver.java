/**********************************************************
// Assignment0: Cfiltering driver
// UTORID: biswas19
// UT Student #:1005403449
// Author:Arunav Biswas
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences. In this semester
// we will select any three of your assignments from total of 5 and run it
// for plagiarism check. 
// *********************************************************/
package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import a0.Cfiltering;

public class CfilteringDriver {

  /**
   * Reads user movie ratings from a text file, calculates similarity scores and
   * prints a score matrix.
   */
  public static void main(String[] args) {
    try {

      // open file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // Read dimensions: number of users and number of movies
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());
//      System.out.println("For debugging:#Users = " + numberOfUsers);
  //    System.out.println("For debugging:#Movies= " + numberOfMovies);

      /*
       * create a new Cfiltering object that contains: a) 2d matrix
       * i.e.userMovieMatrix (#users*#movies) b) 2d matrix i.e. userUserMatrix
       * (#users*#users)
       */
      Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);

      // this is a blankline being read
      br.readLine();

      // read each line of movie ratings and populate the
      // userMovieMatrix
      String row;
      int i, j; // rows and cols
      i = j = -1;
      while ((row = br.readLine()) != null) {
        // allRatings is a list of all String numbers on one row
        String allRatings[] = row.split(" ");
	i++; //move to next row;
	j = -1; //reset cols everytime the 'cursor' moves to the next row.
        for (String singleRating : allRatings) {
          // make the String number into an integer
          // populate userMovieMatrix
         // System.out.println("For debugging:Rating is :" + singleRating);
//	  System.out.println(allRatings.length + " is len of arr of ratings");
          // TODO: COMPLETE THIS I.E. POPULATE THE USER_MOVIE MATRIX
	  j++; // move to next col
	  int temp = Integer.parseInt(singleRating);
	  cfObject.populateUserMovieMatrix(i, j, temp);
        }
      }
//	cfObject.printUserUserMatrix();
      // close the file
//      System.out.println("For debugging:Finished reading file");
      fStream.close();

      /*
       * COMPLETE THIS ( I.E. CALL THE APPROPRIATE FUNCTIONS THAT DOES THE
       * FOLLOWING)
       */
      // TODO:1.) CALCULATE THE SIMILARITY SCORE BETWEEN USERS.
	cfObject.calculateSimilarityScore();
      // TODO:2.) PRINT OUT THE userUserMatrix
	cfObject.printUserUserMatrix();
      // TODO:3.) PRINT OUT THE MOST SIMILAR PAIRS OF USER AND THE MOST
	cfObject.findAndprintMostSimilarPairOfUsers();	
      // DISSIMILAR
    cfObject.findAndprintMostDissimilarPairOfUsers();
      // PAIR OF USERS.
    } catch (FileNotFoundException e) {
      System.err.println("Do you have the input file in the root folder "
             + "of your project?");
      System.err.print(e.getMessage());
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }

}
