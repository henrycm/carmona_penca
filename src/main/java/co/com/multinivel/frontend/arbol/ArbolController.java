package co.com.multinivel.frontend.arbol;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Controller;

@Controller
@ViewScoped
@ManagedBean(name="arbolController")
public class ArbolController
{
  private TreeNode root;
  private TreeNode selectedNode;
  
  public ArbolController()
  {    
    this.root = new DefaultTreeNode("Root", null);
    List<?> localList = this.root.getChildren();
    for (Iterator<?> localIterator = localList.iterator(); localIterator.hasNext();)
    {
      DefaultTreeNode localDefaultTreeNode12 = (DefaultTreeNode)localIterator.next();
      System.err.println(localDefaultTreeNode12.getData());
    }
  }
  
  public TreeNode getRoot()
  {
    return this.root;
  }
  
  public TreeNode getSelectedNode()
  {
    return this.selectedNode;
  }
  
  public void setSelectedNode(TreeNode paramTreeNode)
  {
    this.selectedNode = paramTreeNode;
  }
  
  public void displaySelectedSingle(ActionEvent paramActionEvent)
  {
    if (this.selectedNode != null)
    {
      FacesMessage localFacesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", this.selectedNode.getData().toString());
      
      FacesContext.getCurrentInstance().addMessage(null, localFacesMessage);
    }
  }
}


/* Location:           D:\tmp\arbol\WEB-INF\classes\
 * Qualified Name:     co.com.multinivel.controller.ArbolController
 * JD-Core Version:    0.7.0.1
 */