//package jpahibernate.entity;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "USERS")
//public class User implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Column(name = "DAS")
//	private String das;
//
//	@Column(name = "NAME")
//	private String name;
//
//	@ManyToMany(mappedBy = "users")
//	private List<Group> groups;
//
//	public User() {
//	}
//
//	public void addGroup(Group g) {
//		if(!groups.contains(g)) {
//			groups.add(g);
//			g.addUser(this);
//		}
//	}
//	
//	public User(String das, String name) {
//		this.das = das;
//		this.name = name;
//	}
//
//	public String getDas() {
//		return das;
//	}
//
//	public void setDas(String das) {
//		this.das = das;
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
//	public List<Group> getGroups() {
//		return groups;
//	}
//
//	public void setGroups(List<Group> groups) {
//		this.groups = groups;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((das == null) ? 0 : das.hashCode());
//		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
//		User other = (User) obj;
//		if (das == null) {
//			if (other.das != null)
//				return false;
//		} else if (!das.equals(other.das))
//			return false;
//		if (groups == null) {
//			if (other.groups != null)
//				return false;
//		} else if (!groups.equals(other.groups))
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "User [das=" + das + ", name=" + name + "]";
//	}
//
//}
