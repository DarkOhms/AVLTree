
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

	  public void insert(T data) {
	    if (root == null) {
	      root = new AVLTreeNode<T>(data);
	    } else {
	      recursiveInsert(data, root);
	    }
	  }

	  public boolean search(T data) {
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

	  private void recursiveInsert(T data, AVLTreeNode<T> current) {

	    if (current.isLeaf()) {
	      if (current.getData().compareTo(data) > 0) {
	        current.setLeft(new AVLTreeNode<T>(data));
	      } else
	        current.setRight(new AVLTreeNode<T>(data));
	    } else {
	      if (current.getData().compareTo(data) > 0) {// go left

	        if (current.getLeft() == null) {
	          current.setLeft(new AVLTreeNode<T>(data));
	        } else
	          recursiveInsert(data, current.getLeft());

	      } else {// go right

	        if (current.getRight() == null) {
	          current.setRight(new AVLTreeNode<T>(data));
	        } else {
	          recursiveInsert(data, current.getRight());
	        }
	      }
	    } // end not leaf case
	  }// end recursive insert

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
	    }
	  }
}
