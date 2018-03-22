package jpahibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "METRICS")
public class ProjectMetricsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_METRICS")
	@SequenceGenerator(name = "SQ_METRICS", sequenceName = "SQ_METRICS", allocationSize = 1)
	@Column(name = "METRICS_ID")
	private Long metricsId;

	@Column(name = "LINES_OF_CODE")
	private Integer linesOfCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID")
	private ProjectEntity project;

	public ProjectMetricsEntity() {

	}

	public ProjectMetricsEntity(Integer linesOfCode) {
		this.linesOfCode = linesOfCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((linesOfCode == null) ? 0 : linesOfCode.hashCode());
		result = prime * result + ((metricsId == null) ? 0 : metricsId.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
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
		ProjectMetricsEntity other = (ProjectMetricsEntity) obj;
		if (linesOfCode == null) {
			if (other.linesOfCode != null)
				return false;
		} else if (!linesOfCode.equals(other.linesOfCode))
			return false;
		if (metricsId == null) {
			if (other.metricsId != null)
				return false;
		} else if (!metricsId.equals(other.metricsId))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectMetricsEntity [metricsId=" + metricsId + ", linesOfCode=" + linesOfCode + "]";
	}

	public Long getMetricsId() {
		return metricsId;
	}

	public void setMetricsId(Long metricsId) {
		this.metricsId = metricsId;
	}

	public Integer getLinesOfCode() {
		return linesOfCode;
	}

	public void setLinesOfCode(Integer linesOfCode) {
		this.linesOfCode = linesOfCode;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

}
