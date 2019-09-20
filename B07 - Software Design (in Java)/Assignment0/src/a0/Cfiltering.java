// **********************************************************
// Assignment0: Cfiltering
// UTORID: biswas19
// UT Student #:10054403449
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
// *********************************************************
package a0;

public class Cfiltering {
  // this is a 2d matrix i.e. user*movie
  private int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*movie
  private float userUserMatrix[][];

  /**
   * Default Constructor.
   */
  public Cfiltering() {
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new float[1][1];
  }

  /*
   * TODO:COMPLETE THIS I.E. APPROPRIATELY CREATE THE userMovieMatrix AND
   * userUserMatrix WITH CORRECT DIMENSIONS.
   */
  /**
   * Constructs an object which contains two 2d matrices, one of size
   * users*movies which will store integer movie ratings and one of size
   * users*users which will store float similarity scores between pairs of
   * users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
//	System.out.println("num of User: " + numberOfUsers + "  num of mov: " + numberOfMovies);
	userMovieMatrix = new int[numberOfUsers][numberOfMovies];
	userUserMatrix = new float[numberOfUsers][numberOfUsers];
//	System.out.println("dim of usrMovMatrix: " + userMovieMatrix.length + userMovieMatrix[3].length);

  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input
   * parameters it takes in a rowNumber, columnNumber and a rating value. The
   * rating value is then inserted in the UserMovieMatrix at the specified
   * rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
   */
  public void populateUserMovieMatrix(int rowNumber, int columnNumber,
      int ratingValue) {

//	System.out.print("row: " + rowNumber);
//	System.out.print("  col: " + columnNumber);
//	System.out.println("  Val: " + ratingValue);
    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

/**
	This function is used to calculate the "distance" between all the rating points
	of two users. The 'movie rating' array of each user (second dimension of userMovieMatrix[][])
	is passed in.
*/
  public float takeDiffNSquare(int a[], int b[]){
	// int a[] is the user 1, int b[] is user 2.
	int temp = 0;
	assert(a.length == b.length); // just a little sanity check for myself.
	int size; // since the array is a square, i dont need to use seperate vars for rows and cols
	size = a.length;
	for(int i = 0; i < size; i++)
		temp += (b[i] - a[i]) * (b[i] - a[i]);

	double returnValue = ((1 / (  1 +( Math.sqrt(temp))  ) )); 
//	System.out.println("user to user: " + returnValue);
	return ((float) returnValue);
  }
  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC. Add/remove
   * 
   * @param AND
   * 
   * @return as required below.
   */
  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */
  public void calculateSimilarityScore() {
	int rows, cols;	
	rows = cols = userUserMatrix.length;
	for(int i = 0; i < rows; i++){
		userUserMatrix[i][i] = takeDiffNSquare(userMovieMatrix[i], userMovieMatrix[i]);	
		for(int j = 1 + i ; j < cols; j++){
			userUserMatrix[i][j] = takeDiffNSquare(userMovieMatrix[i], userMovieMatrix[j]);	
			userUserMatrix[j][i] = userUserMatrix[i][j];	
		}
	}
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */

  public void printUserUserMatrix() {
	int rows, cols;
//	rows = userMovieMatrix.length;
//	cols = userMovieMatrix[0].length;
	cols = rows = userUserMatrix.length;
	float temp;
	for(int i = 0; i < rows; i++){
		for(int j = 0; j < cols; j++){
	//		System.out.print(userMovieMatrix[i][j] + " " );
			temp = userUserMatrix[i][j];
			System.out.print(String.format("%.4f",temp) + " " );
		}
		System.out.print('\n');
	}
		
		
  }
  private void checkForOtherPairsWithThisScore(float score, int a, int b){
        assert(score == userUserMatrix[a][b]);
        int size = userUserMatrix.length;
        int numOfUniquePairs = ((size - 1) * size) / 2; // (nCr);
        int iArr[] = new int[numOfUniquePairs];
        int jArr[] = new int[numOfUniquePairs];
        int itr = 0; // for the two arrays iArr, and jArr;
        for(int i = 0; i < size; i++){
            for(int j = 1 + i; j < size; j++ ){
                if((score == userUserMatrix[i][j]) && (i != j) && !((i == a && j == b) || (i == b && j ==a)) ){
//                    System.out.println("run " + (itr+1));
                    iArr[itr] = i + 1;
                    jArr[itr] = j + 1;
                    itr++; 
                }
            }
        }
        if(itr != 0){
                System.out.print("There are " + itr + " pairs who share the same score");
                   System.out.println("(" + String.format("%.4f", score) + ").");
                for(int i = 0; i < itr; i++){
                    System.out.println("User " +  iArr[i] + " and user " + jArr[i]);
                }
        }
  }        
  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */

  public void findAndprintMostSimilarPairOfUsers() {
	int size = userUserMatrix.length;
   // int numOfRuns  = 0; // Just used for debugging
	int iTemp, jTemp; // These two vals will store the users who match 
	float temp = 0; iTemp = jTemp = 0; // initialization of vars;
	for(int i = 0; i < size; i++){
		for(int j = 1 + i; j < size; j++){  // j=1+i because the matrix is always symmetric
						   // it just make the program faster
			if((temp < userUserMatrix[i][j]) && (i != j)){
				temp = userUserMatrix[i][j];
				iTemp = i;
				jTemp = j;		
			}
                //numOfRuns ++;
               // System.out.println("run " + numOfRuns + " users are : " + (i+1) + " " + (j+1));
		}
	}
    iTemp++; jTemp++;
	System.out.print("The most similar pair of users are user " + iTemp );
	System.out.print(" and user " + jTemp + ". Their similarity score is "); 
	System.out.println(String.format("%.4f", temp) + ".");	
    checkForOtherPairsWithThisScore(temp, (iTemp - 1), (jTemp - 1));
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */
  public void findAndprintMostDissimilarPairOfUsers() {
	int size = userUserMatrix.length;
	int iTemp, jTemp;// int numOfRuns = 0;
    float temp = 1.1f; iTemp = jTemp = 0;
    for(int i = 0; i < size; i++){
        for(int j = 1 + i; j < size; j ++){
            if (temp > userUserMatrix[i][j]){
                temp = userUserMatrix[i][j];
                iTemp = i;
                jTemp = j;
            }
            if(i == size - 1 && iTemp == 0 && jTemp == iTemp){
                // Array is all the same
                jTemp ++;
            }
               // numOfRuns ++;
               // System.out.println("run " + numOfRuns + " users are : " + (i+1) + " " + (j+1));
        }
    }
    iTemp++; jTemp++;
    System.out.print("The most dissimilar pair of users are user " + iTemp);
    System.out.print(" and user " + jTemp + ". Their similarity score is ");
    System.out.println(String.format("%.4f", temp) + ".");
    checkForOtherPairsWithThisScore(temp, (iTemp - 1), (jTemp - 1));
  }
}
