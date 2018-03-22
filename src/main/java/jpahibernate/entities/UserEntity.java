package jpahibernate.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserEntity {

	@Id
	@Column(name = "DAS")
	private String das;

	@ManyToMany(mappedBy = "users")
	private List<GroupEntity> groups;

	@OneToOne(fetch = FetchType.LAZY)
	private RoleEntity role;

	@ManyToMany(mappedBy = "users")
	private List<BadgesEntity> badges;

	public UserEntity() {
	}

	public UserEntity(String das, RoleEntity role) {
		this.das = das;
		this.role = role;
	}

	public void addGroup(GroupEntity group) {
		if (!groups.contains(group)) {
			groups.add(group);
			group.addUser(this);
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((badges == null) ? 0 : badges.hashCode());
		result = prime * result + ((das == null) ? 0 : das.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (badges == null) {
			if (other.badges != null)
				return false;
		} else if (!badges.equals(other.badges))
			return false;
		if (das == null) {
			if (other.das != null)
				return false;
		} else if (!das.equals(other.das))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserEntity [das=" + das + ", role=" + role + "]";
	}

	public String getDas() {
		return das;
	}

	public void setDas(String das) {
		this.das = das;
	}

	public List<GroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupEntity> groups) {
		this.groups = groups;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public List<BadgesEntity> getBadges() {
		return badges;
	}

	public void setBadges(List<BadgesEntity> badges) {
		this.badges = badges;
	}

}
