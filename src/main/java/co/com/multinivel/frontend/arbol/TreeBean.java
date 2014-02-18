package co.com.multinivel.frontend.arbol;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.shared.dto.DatosArbol;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@ManagedBean
@ViewScoped
public class TreeBean implements Serializable {
	private static final long serialVersionUID = 2417620239014385855L;
	private AfiliadoService afs;

	private TreeNode root;
	private TreeNode selectedNode;
	private int numeroAfiliados;
	private String nombreAfiliado;
	private String nivel;
	private String cedula;
	private String nombre;
	private int afiliados;
	private String telefono;
	private String direccion;
	private String email;
	private TreeNode[] selectedNodes;

	public String getTelefono() {
		return this.telefono;
	}

	public void ensa() {
		System.out.println("SI SE lllegooo");
	}

	public void setTelefono(String paramString) {
		this.telefono = paramString;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String paramString) {
		this.email = paramString;
	}

	public TreeNode[] getSelectedNodes() {
		return this.selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] paramArrayOfTreeNode) {
		this.selectedNodes = paramArrayOfTreeNode;
	}

	public String getNombreAfiliado() {
		return this.nombreAfiliado;
	}

	public void setNombreAfiliado(String paramString) {
		this.nombreAfiliado = paramString;
	}

	public int getNumeroAfiliados() {
		return this.numeroAfiliados;
	}

	public void setNumeroAfiliados(int paramInt) {
		this.numeroAfiliados = paramInt;
	}

	public TreeBean() {
		try {
			FacesContext localFacesContext = FacesContext.getCurrentInstance();

			String str = (String) localFacesContext.getExternalContext().getRequestParameterMap()
					.get("cedula");

			ServletContext ctx = (ServletContext) localFacesContext.getExternalContext()
					.getContext();

			afs = (AfiliadoService) WebApplicationContextUtils.getWebApplicationContext(ctx)
					.getBean(AfiliadoService.class);
			DatosArbol localDatosArbol = afs.ArbolAfiliado(str);
			this.root = localDatosArbol.getTreeNode();
			setNumeroAfiliados(localDatosArbol.getNumeroAfiliados());
			setNombreAfiliado(localDatosArbol.getNombreAfiliado());
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public TreeNode getModel() {
		return this.root;
	}

	public TreeNode getSelectedNode() {
		return this.selectedNode;
	}

	public void displaySelectedMultiple(ActionEvent paramActionEvent) {
		if ((this.selectedNodes != null) && (this.selectedNodes.length > 0)) {
			StringBuilder localStringBuilder = new StringBuilder();
			for (TreeNode localObject : this.selectedNodes) {
				localStringBuilder.append((localObject).getData().toString());
				localStringBuilder.append("<br />");
			}
			Object localObject = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected",
					localStringBuilder.toString());

			FacesContext.getCurrentInstance().addMessage(null, (FacesMessage) localObject);
		}
	}

	public void setSelectedNode(TreeNode paramTreeNode) throws MultinivelServiceException {
		this.selectedNode = paramTreeNode;

		String[] arrayOfString = paramTreeNode.toString().split(",\\s*");
		this.nivel = arrayOfString[0];
		this.cedula = arrayOfString[1];

		Afiliado localAfiliado = afs.consultar(arrayOfString[1]);
		this.email = localAfiliado.getEmail();
		this.telefono = localAfiliado.getTelefono();
		this.direccion = localAfiliado.getDireccion();

		this.nombre = arrayOfString[2];
		this.afiliados = this.selectedNode.getChildCount();
	}

	public void onNodeSelect(NodeSelectEvent paramNodeSelectEvent) {
		FacesMessage localFacesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected",
				paramNodeSelectEvent.getTreeNode().getData().toString());
		FacesContext.getCurrentInstance().addMessage(paramNodeSelectEvent.getComponent().getId(),
				localFacesMessage);
	}

	public void onNodeExpand(NodeExpandEvent paramNodeExpandEvent) {
		FacesMessage localFacesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded",
				paramNodeExpandEvent.getTreeNode().getData().toString());
		FacesContext.getCurrentInstance().addMessage(paramNodeExpandEvent.getComponent().getId(),
				localFacesMessage);
	}

	public void onNodeCollapse(NodeCollapseEvent paramNodeCollapseEvent) {
		System.out.println("NodeCollapseEvent Fired");
		FacesMessage localFacesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed",
				paramNodeCollapseEvent.getTreeNode().getData().toString());
		FacesContext.getCurrentInstance().addMessage(paramNodeCollapseEvent.getComponent().getId(),
				localFacesMessage);
	}

	public String getNivel() {
		return this.nivel;
	}

	public String getCedula() {
		return this.cedula;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getAfiliados() {
		return this.afiliados;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String paramString) {
		this.direccion = paramString;
	}
}

/*
 * Location: D:\tmp\arbol\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.controller.TreeBean
 * 
 * JD-Core Version: 0.7.0.1
 */