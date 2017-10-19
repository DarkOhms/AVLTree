import java.util.concurrent.*;

public class TestStub {

	public static void main(String[] args) {
		
		AVLTree<Integer> tree1 = new AVLTree<Integer>();
		
	
	    int ARRAY_SIZE = 31;
		int[] numbers = {16, 0, 13,26,9,25,7,29,19,11,21,10,4,17,23,5,18,28,24,1,8,6,15,20,14,30,3,27,2,22,12};
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
	   }
	   */
	   for(int i = 0; i < ARRAY_SIZE; i++) {// load tree 1
		 System.out.print("insert: ");
		 System.out.println(numbers[i]);//printing order of insertion
	     tree1.insert(numbers[i]);
	   }
	   
	   tree1.delete(0);
	   tree1.leftInOrderTraversal();
       
	}

}
