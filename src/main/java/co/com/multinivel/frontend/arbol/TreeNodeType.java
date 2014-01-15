package co.com.multinivel.frontend.arbol;

public enum TreeNodeType
{
  LEAF("leaf"),  NODE("node");
  
  private String type;
  
  private TreeNodeType(String paramString)
  {
    this.type = paramString;
  }
  
  public String toString()
  {
    return this.type;
  }
  
  public String getType()
  {
    return this.type;
  }
}


/* Location:           D:\tmp\arbol\WEB-INF\classes\
 * Qualified Name:     co.com.multinivel.controller.TreeNodeType
 * JD-Core Version:    0.7.0.1
 */