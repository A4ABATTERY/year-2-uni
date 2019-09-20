
/**********************************************************
// Assignment0: Rearranging
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
package a0;
/*
 * 
 */
public class Rearranging {

	/*
	 * TODO: You are asked to complete the method
	 * rearranging. This method takes in as input an 
	 * array of ints and returns back nothing (void).
	 * You cannot change the function 
	 * signature of this method. 
	 */
    public static void rearranging(int[] a){
        int i = 1;
        while( i < a.length -1){
            if(a[i] > a[i+1]){
                swap(i, i+1, a);
                i = -1;
            }
                i++;
            

        }
    }
//		swap(0,5, items);
//		swap(4,5, items);
//		swap(0,1, items);
        		

	
	/*
	 * TODO: You are asked to complete the method
	 * swap. This method takes in as input two ints
	 * and an array of ints. The int i and int j 
	 * are the index i and index j in the array items.
	 * You are asked to swap the value at the index i in items
	 * with the value at the index j. You cannot change the function 
	 * signature of this method. 
	 */
	private static void swap(int i,int j,int[] items)
	{
		//Temp var to store a value while swapping.
		int temp;
		//Performing Swap
		temp = items[i];
		items[i] = items[j];
		items[j] = temp;
		//End of Swap
	}

	public static void main(String[] args) {
		/* You can modify the main function in any way you like.
		 * We will not mark your main function.  
		 */
		int [] items={7,-3,0,0,8,-2};
		System.out.println("just testing stuff");
		/*
		 * printing the values in the items before 
		 * calling the method rearranging
		 */
		for(int item:items)
		{
			System.out.print(item + " ");
		}
		System.out.print("\n");
		
		/*
		//calling the rearranging method
		*/
		Rearranging.rearranging(items);
		/*
		 * printing the values in the items after 
		 * calling the method rearranging
		 */
		for(int item:items)
		{
			System.out.print(item + " ");
		}
		System.out.print("\n");
	}
}
