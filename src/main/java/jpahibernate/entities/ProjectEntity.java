package jpahibernate.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECTS")
public class ProjectEntity {

	@Id
	@Column(name = "PROJECT_ID")
	private String projectId;

	@Column(name = "NAME")
	private String name;

	@OneToOne(mappedBy = "project", fetch = FetchType.LAZY)
	private GroupEntity group;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<ProjectMetricsEntity> metrics;

	@OneToMany(mappedBy = "project", orphanRemoval = false)
	private List<ProjectReportsEntity> reports;

	@ManyToMany(mappedBy = "projects")
	private List<BadgesEntity> badges;

	public ProjectEntity() {

	}

	public ProjectEntity(String projectId, String name) {
		this.projectId = projectId;
		this.name = name;
	}

	public void addMetrics(ProjectMetricsEntity pme) {
		if(!metrics.contains(pme)) {
			metrics.add(pme);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((badges == null) ? 0 : badges.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((metrics == null) ? 0 : metrics.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((reports == null) ? 0 : reports.hashCode());
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
		ProjectEntity other = (ProjectEntity) obj;
		if (badges == null) {
			if (other.badges != null)
				return false;
		} else if (!badges.equals(other.badges))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (metrics == null) {
			if (other.metrics != null)
				return false;
		} else if (!metrics.equals(other.metrics))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (reports == null) {
			if (other.reports != null)
				return false;
		} else if (!reports.equals(other.reports))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectEntity [projectId=" + projectId + ", name=" + name + ", metrics=" + metrics + ", reports="
				+ reports + ", ]";
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
	}

	public List<ProjectMetricsEntity> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<ProjectMetricsEntity> metrics) {
		this.metrics = metrics;
	}

	public List<ProjectReportsEntity> getReports() {
		return reports;
	}

	public void setReports(List<ProjectReportsEntity> reports) {
		this.reports = reports;
	}

	public List<BadgesEntity> getBadges() {
		return badges;
	}

	public void setBadges(List<BadgesEntity> badges) {
		this.badges = badges;
	}

	

	
}
