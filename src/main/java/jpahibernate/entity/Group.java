//package jpahibernate.entity;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "GROUPS")
//public class Group implements Serializable{
//
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Column(name = "GROUP_ID")
//	private Long groupId;
//
//	@Column(name = "NAME")
//	private String name;
//
//	@ManyToMany
//	@JoinTable(
//			name = "GROUPS_USERS", 
//			joinColumns = { @JoinColumn(name = "GROUP_ID") }, 
//			inverseJoinColumns = { @JoinColumn(name = "USER_DAS") }
//			)
//	private List<User> users;
//
//	public Group() {
//	}
//
//	public Group(Long groupId, String name) {
//		this.groupId = groupId;
//		this.name = name;
//	}
//	
//	public void addUser(User u) {
//		if(!users.contains(u)) {
//			users.add(u);			
//		}
//	}
//	
//	public void removeUser(User u) {
//		if(users.contains(u)) {
//			users.remove(u);
//		}
//	}
//
//	public Long getGroupId() {
//		return groupId;
//	}
//
//	public void setGroupId(Long groupId) {
//		this.groupId = groupId;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
//
//	@Override
//	public String toString() {
//		return "Group [groupId=" + groupId + ", name=" + name + ", users=" + users + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((users == null) ? 0 : users.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Group other = (Group) obj;
//		if (groupId == null) {
//			if (other.groupId != null)
//				return false;
//		} else if (!groupId.equals(other.groupId))
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		if (users == null) {
//			if (other.users != null)
//				return false;
//		} else if (!users.equals(other.users))
//			return false;
//		return true;
//	}
//
//}
