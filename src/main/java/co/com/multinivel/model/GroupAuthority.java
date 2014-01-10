package co.com.multinivel.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "group_authorities")
public class GroupAuthority implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "group_id")
	private String groupId;
	@Column(name = "authority")
	private String authority;
	@OneToMany(mappedBy = "groupAuthority")
	private Set<GroupMember> groupMembers;

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Set<GroupMember> getGroupMembers() {
		return this.groupMembers;
	}

	public void setGroupMembers(Set<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupId() {
		return this.groupId;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.model.GroupAuthority
 * 
 * 
 */