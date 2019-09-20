package UserData;

import Matrices.SquareMatrix;
import Matrices.FMatrix;
import Matrices.Matrix;

import java.io.FileNotFoundException;
import java.lang.Number;
import java.text.DecimalFormat;
import java.io.FileNotFoundException;

/**
 * The container of userData. It contains a SquareMatrix of how similar
 * any two users are.
 * @param <E> Needs to extend from class Number
 * @param <T> Needs to extends from class Number
 */
public class UserData<E extends Number, T extends Number>{
    /**
     * The squareMatrix: userUserMatrix
     */
    private SquareMatrix<E, T> userUserMatrix = null;

    /**
     * Initilaises the userUserMatrix from a file.
     * @param fileName File/path to file that contains relevant data of Users.
     * @throws FileNotFoundException
     */
    public UserData(String fileName) throws FileNotFoundException {
       Matrix<T> m = FMatrix.constructFMatrix(fileName);
       if(m != null)
            userUserMatrix = new SquareMatrix<E, T>(m.getMatrix().length, m);
       else
           throw new FileNotFoundException("File \'"+fileName+"\' not found, or " +
                   "File in improper format");
       m = null;
    }

    /**
     * Prints out the matrix in a readable manner.
     * @return String
     */
    public String toString(){
        String str = "";
        System.out.println("\n");
        System.out.println("User * User matrix:");
        DecimalFormat flFrmt = new DecimalFormat("0.0000");
        for(Number[] arr : userUserMatrix.getMatrix()){
            for(Number num : arr){
                str += flFrmt.format((Float) num) + " ";
            }
            str += '\n';
        }
        return str;
    }

    /**
     * Compares similar and dis-similar users.
     * @return String showing which users are similar and dis-similar
     */
    public String getSimAndDisSimRatings(){
         String str = "The most similar pairs of users are: \n";
         str += findSimPairs(findMinOrMaxPair(true)) + '\n';
         str += "The most Dissimilar pairs of users are: \n";
         str += findSimPairs(findMinOrMaxPair(false)) + '\n';
         return str;
    }

    /**
     * Finds the maximum or minimum value in the array
     * @param lookForMax Pass in 'True' if one needs to look for the max value
     *                   Pass in 'False' if one needs to look for the min value
     * @return Float
     */
    private Float findMinOrMaxPair(Boolean lookForMax) {
        Number max;
        if(lookForMax)
            max = -99999;
        else
            max = 99999;
        int size = userUserMatrix.getMatrix().length;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (i != j) {
                    float curr = userUserMatrix.get(i, j).floatValue();
                    if(lookForMax && max.floatValue() < curr) {
                        max = curr;
                    }else if(!(lookForMax) && max.floatValue() > curr){
                        max = curr;
                    }
                }
            }
        }
        return (Float) max;
    }

    /**
     * Finds pairs of users that share the same value.
     * Uses iteration for a symmetrical matrix.
     * @param find is the value that is being looked for
     * @return String of users that match the 'find' value.
     */
    private String findSimPairs(Number find){
        String str = "";
        for(int i = 0; i < userUserMatrix.getMatrix().length; i++)
            for(int j = i; j < userUserMatrix.getMatrix()[0].length; j++) {
                Number num = userUserMatrix.get(i, j);
                if (i != j)
                    if (num.floatValue() == find.floatValue()) {
                        str += "User " + (i + 1) + " and user " + (j + 1) + ": " +
                                "Share a score of " +
                                String.format("%.4f", find.floatValue()) + '\n';
                    }
            }

        return str;
    }
}
