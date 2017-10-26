
/*
 * Class AVLTreeNode
 * 
 * (+)AVLTreeNode<T>(T newData)
 * 
 * Members
 * (-)T data
 * (-)AVLTreeNode<T> left, right
 * (#)int height
 * 
 * Methods
 * (+)T getData()
 * (+)void setData(T newData)
 * (+)AVLTreeNode<T> getLeft(), getRight()
 * (+)void setLeft(), setLeft()
 * (+)boolean isLeaf()
 * (+)T getLeftmostData()
 * (+)T findMin()
 * (#)void setHeight()
 * (#)int getHeight(AVLTreeNode<T> node)
 * (+)AVLTreeNode<T> getLeftmost(AVLTreeNode<T> current)
 * (#)leftRightTraversal(AVLTreeNode<T> current)
 * (#)rightLeftTraversal(AVLTreeNode<T> current)
 * (+)T getRightmostData()
 * (+)AVLTreeNode<T> getRightmost(AVLTreeNode<T> current)
 * (+)AVLTreeNode<T> removeLeftmost()
 * (+)AVLTreeNode<T> removeRightmost()
 * (+)T getImmediateSuccesorData()
 * (+)T getImmediatePredecessorData()
 * (#)void process(AVLTreeNode<T> node) 
 * 
 */
import java.lang.Math;

public class AVLTreeNode<T extends Comparable<T>> {

  private T data;
  private AVLTreeNode<T> left = null;
  private AVLTreeNode<T> right = null;
  int height;


  public AVLTreeNode(T newData) {
    data = newData;
  }


  public T getData() {
    return data;
  }

 public void setData(T newData) {
	 data = newData;
 }

  public AVLTreeNode<T> getLeft() {
    return left;
  }

  public AVLTreeNode<T> getRight() {
    return right;
  }

  public void setLeft(AVLTreeNode<T> newLeft) {
    left = newLeft;
  }

  public void setRight(AVLTreeNode<T> newRight) {
    right = newRight;
  }

  public boolean isLeaf() {
    return (left == null) && (right == null);
  }

  
  public T getLeftmostData() {
	    if (left == null)
	      return data;
	    else
	      return left.getLeftmostData();

	  }

  
  void setHeight() {
    
	this.height = Math.max(getHeight(this.getLeft()), getHeight(this.getRight())) + 1;
	//System.out.println(this.height);
  }
  
  int getHeight(AVLTreeNode<T> node) {
      if(node==null) {
    	  return -1;
      }else
	    return node.height;
  }
  
    public AVLTreeNode<T> getLeftmost(AVLTreeNode<T> current) {
    if (current.getLeft() == null)
      return current;
    else
      return getLeftmost(current.getLeft());

  }


  void leftRightTraversal(AVLTreeNode<T> current) {
    if (current.getLeft() != null)
      leftRightTraversal(current.getLeft());

    process(current);

    if (current.getRight() != null)
      leftRightTraversal(current.getRight());
  }
  
  void rightLeftTraversal(AVLTreeNode<T> current) {
    if (current.getRight() != null)
      rightLeftTraversal(current.getRight());

    process(current);

      if (current.getLeft() != null)
        rightLeftTraversal(current.getLeft());
  }

  public T getRightmostData() {
    if (right == null)
      return data;
    else
      return right.getRightmostData();

  }

  public AVLTreeNode<T> getRightmost(AVLTreeNode<T> current) {
    if (current.getRight() == null)
      return current;
    else
      return getRightmost(current.getRight());

  }

  public AVLTreeNode<T> removeLeftmost() {
    if (left == null)
      return right;
    else {
      left = left.removeLeftmost();
      return this;
    }
  }

  public AVLTreeNode<T> removeRightmost() {
    if (right == null)
      return left;
    else {
      right = right.removeRightmost();
      return this;
    }
  }

  public T getImmediateSuccesorData() {
    T data;

    if (right.isLeaf()) {
      data = right.getData();
      removeRightmost();
    } else {
      data = right.getLeftmostData();
      right.removeLeftmost();
    }
    return data;
  }

  public T getImmediatePredecessorData() {
    T data;

    if (left.isLeaf()) {
      data = left.getData();
      removeLeftmost();
    } else {
      data = left.getRightmostData();
      left.removeRightmost();
    }
    return data;
  }

  void process(AVLTreeNode<T> node) {
    System.out.println(String.valueOf(node.getData()));
   // System.out.print("Height is: ");
   // System.out.print(getHeight(node));
  }
}
