//package jpahibernate.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "ROLES")
//public class Role {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RANKING")
//	@SequenceGenerator(name = "SQ_RANKING", sequenceName = "SQ_RANKING", allocationSize = 1)
//	@Column(name = "ROLE_ID")
//	private Long roleId;
//
//	@Override
//	public String toString() {
//		return "Role [roleId=" + roleId + "]";
//	}
//
//	public Role() {
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
//		Role other = (Role) obj;
//		if (roleId == null) {
//			if (other.roleId != null)
//				return false;
//		} else if (!roleId.equals(other.roleId))
//			return false;
//		return true;
//	}
//
//	public Long getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(Long roleId) {
//		this.roleId = roleId;
//	}
//	
//}
