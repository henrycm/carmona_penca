package co.com.multinivel.frontend.arbol;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

public class TreeNodeImpl
  extends DefaultTreeNode
{
  private static final long serialVersionUID = 5333810777428638968L;
  
  public TreeNodeImpl(TreeNodeType paramTreeNodeType, Object paramObject, TreeNode paramTreeNode)
  {
    super(paramTreeNodeType.getType(), paramObject, paramTreeNode);
  }
  
  public TreeNodeImpl(Object paramObject, TreeNode paramTreeNode)
  {
    super(paramObject, paramTreeNode);
  }
  
  public String getType()
  {
    if (isLeaf()) {
      return TreeNodeType.LEAF.getType();
    }
    return TreeNodeType.NODE.getType();
  }
}


/* Location:           D:\tmp\arbol\WEB-INF\classes\
 * Qualified Name:     co.com.multinivel.controller.TreeNodeImpl
 * JD-Core Version:    0.7.0.1
 */