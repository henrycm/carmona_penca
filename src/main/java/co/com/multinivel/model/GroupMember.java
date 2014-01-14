package co.com.multinivel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "group_members")
public class GroupMember implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private String id;
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;
	@ManyToOne
	@JoinColumn(name = "group_id")
	private GroupAuthority groupAuthority;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GroupAuthority getGroupAuthority() {
		return this.groupAuthority;
	}

	public void setGroupAuthority(GroupAuthority groupAuthority) {
		this.groupAuthority = groupAuthority;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.model.GroupMember
 */