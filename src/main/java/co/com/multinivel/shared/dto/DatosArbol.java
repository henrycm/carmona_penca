package co.com.multinivel.shared.dto;

import org.primefaces.model.TreeNode;

public class DatosArbol
{
  private int numeroAfiliados;
  private String nombreAfiliado;
  private TreeNode treeNode;
  
  public DatosArbol(int paramInt, TreeNode paramTreeNode, String paramString)
  {
    this.numeroAfiliados = paramInt;
    this.nombreAfiliado = paramString;
    this.treeNode = paramTreeNode;
  }
  
  public String getNombreAfiliado()
  {
    return this.nombreAfiliado;
  }
  
  public void setNombreAfiliado(String paramString)
  {
    this.nombreAfiliado = paramString;
  }
  
  public int getNumeroAfiliados()
  {
    return this.numeroAfiliados;
  }
  
  public void setNumeroAfiliados(int paramInt)
  {
    this.numeroAfiliados = paramInt;
  }
  
  public TreeNode getTreeNode()
  {
    return this.treeNode;
  }
  
  public void setTreeNode(TreeNode paramTreeNode)
  {
    this.treeNode = paramTreeNode;
  }
  
  public DatosArbol(int paramInt, TreeNode paramTreeNode)
  {
    this.numeroAfiliados = paramInt;
    this.treeNode = paramTreeNode;
  }
}


/* Location:           D:\tmp\arbol\WEB-INF\classes\
 * Qualified Name:     co.com.multinivel.dto.DatosArbol
 * JD-Core Version:    0.7.0.1
 */