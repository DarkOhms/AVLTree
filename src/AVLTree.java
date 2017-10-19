
public class AVLTree<T extends Comparable <T>> {
	  private AVLTreeNode<T> root;
	  private AVLTreeNode<T> parentOfCursor;

	  public AVLTree() {

	  }

	  public AVLTree(AVLTreeNode<T> newRoot) {
	    root = newRoot;
	  }

	  public AVLTree(T data) {
	    root = new AVLTreeNode<T>(data);
	  }

	  /*
	   * public AVLTree(AVLTree<T>){ copy tree }
	   */
      public boolean isEmpty() {
    	  if(root.getData() == null)
    		  return true;
    	  else
    		  return false;
      }

	  public boolean search(T data) {
	    if(this.isEmpty())
	    	return false;
	    else
		  return recursiveSearch(data, root);
	  }

	  private boolean recursiveSearch(T data, AVLTreeNode<T> current) {
	    if (current == null) {
	      return false;
	    } else {
	      if (current.getData().equals(data)) {// found it
	        return true;
	      } else {// look to continue search left or right

	        if (current.getData().compareTo(data) > 0) {
	          return recursiveSearch(data, current.getLeft());
	        } else {
	          return recursiveSearch(data, current.getRight());
	        }
	      } // end not found in current
	    } // non null case
	  }
	  
	  public T findMin() {
		 if(this.isEmpty()){
			 return null;
		 }
		  if (root.getLeft() == null)
		      return root.getData();
		    else
		      return root.getLeft().getLeftmostData();

		  }

	  public void insert(T data) {
		  if(!search(data)) {//check for duplicate
		    if (root == null) {
			  root = new AVLTreeNode<T>(data);
			  root.setHeight();
		    } else {
			  root = recursiveInsert(data, root);
		    }
		  }else
			  System.out.println(data.toString() + " already exists in the tree.");
	  }
	  
	  private AVLTreeNode<T> recursiveInsert(T data, AVLTreeNode<T> current) {

	    if (current.isLeaf()) {//leaf case
	      
	      if (current.getData().compareTo(data) > 0) {
	        current.setLeft(new AVLTreeNode<T>(data));
	        current.getLeft().setHeight();
	      } else {
	        current.setRight(new AVLTreeNode<T>(data));
	        current.getRight().setHeight();
	      }
	      
	    } else {//non leaf case
	      if (current.getData().compareTo(data) > 0) {// go left

	        if (current.getLeft() == null) {
	          current.setLeft(new AVLTreeNode<T>(data));
	          current.getLeft().setHeight();
	        } else{
	          current.setLeft(recursiveInsert(data, current.getLeft()));
	          current = balance(current);
	        }

	      } else {// go right

	        if (current.getRight() == null) {
	          current.setRight(new AVLTreeNode<T>(data));
	          current.getRight().setHeight();
	        } else{
	          current.setRight(recursiveInsert(data, current.getRight()));
	          current = balance(current);
	        }
	      }
	    } // end not leaf case
	   current.setHeight();
	   return current;
	  }// end recursive insert

    private AVLTreeNode<T> balance(AVLTreeNode<T> current) {
      
      if(current.getHeight(current.getLeft()) == current.getHeight(current.getRight())+2) {//left imbalance
	    if(current.getLeft().getLeft() == null || current.getHeight(current.getLeft().getLeft()) < current.getHeight(current.getLeft().getRight()))//scenario 2 leftReft
	      current = leftRightRotation(current);
	    else//scenario 1 leftLeft
	    	current = leftLeftRotation(current);
      }else if(current.getHeight(current.getRight()) == current.getHeight(current.getLeft())+2) {//right imbalance
    	  if(current.getRight().getRight() == null ||  current.getHeight(current.getRight().getRight()) < current.getHeight(current.getRight().getLeft()) )//scenario 4 rightLeft
    	    current = rightLeftRotation(current);
    	  else//scenario 3 rightRight
    		current = rightRightRotation(current);
      }//end else if
      return current;
    }
	 
	public void leftInOrderTraversal() {

	    root.leftRightTraversal(root);

	  }
	  
	  public void rightInOrderTraversal() {

	    root.rightLeftTraversal(root);

	  }

	  public void delete(T data) {
	    if (search(data)) {
	      recursiveDelete(data, root);
	    }
	  }

	  private void recursiveDelete(T data, AVLTreeNode<T> current) {

	    if (current.getData().equals(data)) {// found it
	      if (current.isLeaf()) {
	        if(parentOfCursor.getRight() == current){
	          parentOfCursor.setRight(null);
	        }else{
	          parentOfCursor.setLeft(null);
	        }
	      } else {
	        if (current.getLeft() != null && current.getRight() != null) {
	          // promote immediate successor
	          promoteImmediateSuccessor(current);
	        } else {
	          if (current.getLeft() != null) {
	            // promote immediate predecessor
	            promoteImmediatePredecessor(current);
	          }
	        }
	      }
	    } else {// look to continue search left or right
	      parentOfCursor = current;
	      if (current.getData().compareTo(data) > 0) {
	        recursiveDelete(data, current.getLeft());
	      } else {
	        recursiveDelete(data, current.getRight());
	      }
	    } // end not found in current
	  }

	  private void promoteImmediateSuccessor(AVLTreeNode<T> current) {
	    parentOfCursor = null;
	  
	    recursivePromoteSuccessor(current);

	  }

	  private void recursivePromoteSuccessor(AVLTreeNode<T> current) {
	    if (current.getRight() == null) {// limit call
	      parentOfCursor.setRight(parentOfCursor.getRight().removeLeftmost());
	    } else {
	      current.setData(current.getRight().getLeftmostData());
	      parentOfCursor = current;
	      recursivePromoteSuccessor(current.getLeftmost(current.getRight()));
	      current.setHeight();
	      current = balance(current);
	      
	    }
	  }

	  private void promoteImmediatePredecessor(AVLTreeNode<T> current) {
	    parentOfCursor = null;
	    
	    recursivePromotePredecessor(current);
	  }

	  private void recursivePromotePredecessor(AVLTreeNode<T> current) {
	    if (current.getLeft() == null) {// limit call
	      parentOfCursor.setLeft(parentOfCursor.getLeft().removeRightmost());
	    } else {
	      current.setData(current.getLeft().getRightmostData());
	      parentOfCursor = current;
	      recursivePromotePredecessor(current.getRightmost(current.getLeft()));
	      current.setHeight();
	      current = balance(current);
	    }
	  }
	  
	  /* Rotation Methods
	   * k1 < k2 < k3 in any given rotation
	     height adjustments will be made here for the children ONLY, 
	     because the returned node will have its height set after
	     return to insert
	  */
	  
	  private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k2){
		  AVLTreeNode<T> k1 = k2.getLeft(); //copy of left subtree
		  k2.setLeft(k1.getRight());//should set null
		  k1.setRight(k2);
		  k2.height += -1;
		  return k1;
	  }
	  
	  private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> k3){
		  AVLTreeNode<T> k1 = k3.getLeft(); //copy of left subtree
		  AVLTreeNode<T> k2 = k1.getRight();
		  k2.setLeft(k1);
		  k3.setLeft(k2.getRight());
		  k2.setRight(k3);
		  k1.setRight(null);
		  k1.height += -1;
		  k3.height += -1;
		  return k2;
	  }
	  
	  private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> k1){
		  AVLTreeNode<T> k2 = k1.getRight(); //copy of left subtree
		  k1.setRight(k2.getLeft());//null?
		  k2.setLeft(k1);
		  k1.height += -1;
		  return k2;
	  }
	  
	  private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> k1){
		  AVLTreeNode<T> k3 = k1.getRight(); //copy of right subtree
		  AVLTreeNode<T> k2 = k3.getLeft();
		  k2.setLeft(k1);
		  k3.setLeft(k2.getRight());
		  k2.setRight(k3);
		  k1.setRight(null);
		  k3.height += -1;
		  k1.height += -1;
		  return k2;
	  }
}
