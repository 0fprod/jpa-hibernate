package jpahibernate.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "REPORTS")
public class ProjectReportsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REPORTS")
	@SequenceGenerator(name = "SQ_REPORTS", sequenceName = "SQ_REPORTS", allocationSize = 1)
	@Column(name = "REPORT_ID")
	private Long reportId;

	@Column(name = "NAME")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private ProjectEntity project;
	
	@OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
	private ReportNotificationEntity notification;

	public ProjectReportsEntity() {

	}

	public ProjectReportsEntity(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((reportId == null) ? 0 : reportId.hashCode());
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
		ProjectReportsEntity other = (ProjectReportsEntity) obj;
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
		if (reportId == null) {
			if (other.reportId != null)
				return false;
		} else if (!reportId.equals(other.reportId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectReportsEntity [reportId=" + reportId + ", name=" + name + ", ]";
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

}
