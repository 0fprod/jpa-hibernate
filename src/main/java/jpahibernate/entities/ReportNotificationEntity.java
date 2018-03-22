package jpahibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATION")
public class ReportNotificationEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_NOTIFICATION")
	@SequenceGenerator(name = "SQ_NOTIFICATION", sequenceName = "SQ_NOTIFICATION", allocationSize = 1)
	@Column(name = "NOTIFICATION_ID")
	private Long notificationId;
	
	@Column(name = "LIST_OF_PEOPLE")
	private String listOfPeople;
	
	@OneToOne
	private ProjectReportsEntity report;
}
