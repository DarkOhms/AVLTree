import java.util.Scanner;
import java.util.concurrent.*;

public class TestStub {

	public static void main(String[] args) {
		
		AVLTree<Integer> tree1 = new AVLTree<Integer>();
		AVLTree<Integer> tree2 = new AVLTree<Integer>();
		
		int minimum;
	
	    int ARRAY_SIZE = 31;
		int[] numbers = {24,21,3,17,7,26,1,8,18,23,6,2,29,11,20,28,19,15,5,16,4,9,27,22,0,14,12,13,10,25,30};
		
	   /*
	   ThreadLocalRandom random = ThreadLocalRandom.current();
	   
	   for(int i=0; i < ARRAY_SIZE; i++) {//load array
		 int next;
		 boolean duplicate = true;
		 
		 next = random.nextInt(ARRAY_SIZE);
		 
		 if(i != 0) {
		   while(duplicate) {//check for duplicates
		     duplicate = false;
		     
		     for(int j = 0; j < i; j++){
			   if(numbers[j] == next){
			     duplicate = true;
				 next = random.nextInt(ARRAY_SIZE);
				 continue;
			   }
		     }
		   }
		   numbers[i] = next;   
		 }else
			 numbers[0] = next;
	   }*/
	   
	   for(int i = 0; i < ARRAY_SIZE; i++) {// load tree 1
	     tree1.insert(numbers[i]);
	   }
	   
	   Scanner makeItStop = new Scanner(System.in);
	   while(true) {
		   
		   while(!tree1.isEmpty()) {
			   minimum = tree1.findMin();
			   tree1.delete(minimum);
			   System.out.println("The system process with priority " + minimum + " is scheduled to run!");
               tree2.insert(minimum);
               System.out.println("The process with priority " + minimum + " has run out of its timeslice!");
               
		   }
		   
		   System.out.println("Every process has got a chance to run; Press \"Enter\" to start the next round!");
		   
		   makeItStop.nextLine();
		   AVLTree<Integer> temp = tree1;
		   tree1 = tree2;
		   tree2 = temp;
	   }
       
	}

}
