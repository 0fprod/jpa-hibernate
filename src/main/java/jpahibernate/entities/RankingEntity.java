package jpahibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RANKING")
public class RankingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RANKING")
	@SequenceGenerator(name = "SQ_RANKING", sequenceName = "SQ_RANKING", allocationSize = 1)
	@Column(name = "RANKING_ID")
	private Long rankingId;

	@Column(name = "CALIFICATION")
	private Double calification;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID")
	private ProjectEntity project;

	public RankingEntity(Double calification) {
		this.calification = calification;
	}

	public RankingEntity() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calification == null) ? 0 : calification.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((rankingId == null) ? 0 : rankingId.hashCode());
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
		RankingEntity other = (RankingEntity) obj;
		if (calification == null) {
			if (other.calification != null)
				return false;
		} else if (!calification.equals(other.calification))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (rankingId == null) {
			if (other.rankingId != null)
				return false;
		} else if (!rankingId.equals(other.rankingId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RankingEntity [rankingId=" + rankingId + ", calification=" + calification + "]";
	}

	public Long getRankingId() {
		return rankingId;
	}

	public void setRankingId(Long rankingId) {
		this.rankingId = rankingId;
	}

	public Double getCalification() {
		return calification;
	}

	public void setCalification(Double calification) {
		this.calification = calification;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

}
