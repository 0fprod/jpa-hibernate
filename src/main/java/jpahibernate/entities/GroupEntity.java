package jpahibernate.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GROUPS")
public class GroupEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_GROUP")
	@SequenceGenerator(name = "SQ_GROUP", sequenceName = "SQ_GROUP", allocationSize = 1)
	@Column(name = "GROUP_ID")
	private Long groupId;

	@Column(name = "NAME")
	private String name;

	@ManyToMany
	@JoinTable(name = "GROUPS_USERS", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "USER_DAS") })
	private List<UserEntity> users;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "GROUP_PROJECTS", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PROJECT_ID") })
	private ProjectEntity project;

	public GroupEntity(String name) {
		this.name = name;
	}

	public GroupEntity() {

	}

	public void addUser(UserEntity user) {
		if (!users.contains(user))
			users.add(user);

	}

	public void removeUser(UserEntity user) {
		if (users.contains(user))
			users.remove(user);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		GroupEntity other = (GroupEntity) obj;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroupEntity [groupId=" + groupId + ", name=" + name + ", users=" + users + ", project=" + project + "]";
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

}
