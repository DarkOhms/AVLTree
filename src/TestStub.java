import java.util.concurrent.*;

public class TestStub {

	public static void main(String[] args) {
		
		AVLTree<Integer> tree1 = new AVLTree<Integer>();
		AVLTree<Integer> tree2 = new AVLTree<Integer>();
		
		int minimum;
	
	    int ARRAY_SIZE = 31;
		int[] numbers = new int[ARRAY_SIZE];
		
	   
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
	   }
	   
	   for(int i = 0; i < ARRAY_SIZE; i++) {// load tree 1
	     tree1.insert(numbers[i]);
	   }
	   
	   while(true) {
		   
		   minimum = tree1.findMin();
	   }
       
	}

}
