package jpahibernate.test;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpahibernate.entities.BadgesEntity;
import jpahibernate.entities.GroupEntity;
import jpahibernate.entities.ProjectEntity;
import jpahibernate.entities.ProjectMetricsEntity;
import jpahibernate.entities.ProjectReportsEntity;
import jpahibernate.entities.RankingEntity;
import jpahibernate.entities.RoleEntity;
import jpahibernate.entities.UserEntity;

@SuppressWarnings("unused")
public class TestApp {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");;

	public static void main(String[] args) {

//		 createGroups();
//		 createRoles();
//		 createUsers();
//		 mixGroupUser();
//		 createProjects();
//		 mixGroupProject();
//		 createMetrics(); //Fix - Select from Ranking Where project id = :param
		 createReports();
		 createBadges();
		 mixBadgesAndProjects();
		 mixBadgesAndUsers();
				
		listAll();			
		getRanking();
	}

	private static void createGroups() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(new GroupEntity("GROUP 1"));
		em.persist(new GroupEntity("GROUP 2"));
		em.persist(new GroupEntity("GROUP 3"));

		em.getTransaction().commit();
		em.close();
	}

	private static void createRoles() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(new RoleEntity("ROLE_ADMIN"));
		em.persist(new RoleEntity("ROLE_SQM"));
		em.persist(new RoleEntity("ROLE_DEV"));

		em.getTransaction().commit();
		em.close();
	}

	private static void createUsers() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		RoleEntity admin = (RoleEntity) em.createQuery("FROM RoleEntity re WHERE re.name = :name")
				.setParameter("name", "ROLE_ADMIN").getSingleResult();
		RoleEntity dev = (RoleEntity) em.createQuery("FROM RoleEntity re WHERE re.name = :name")
				.setParameter("name", "ROLE_DEV").getSingleResult();
		RoleEntity sqm = (RoleEntity) em.createQuery("FROM RoleEntity re WHERE re.name = :name")
				.setParameter("name", "ROLE_SQM").getSingleResult();

		em.persist(new UserEntity("A1", admin));
		em.persist(new UserEntity("A2", dev));
		em.persist(new UserEntity("A3", dev));
		em.persist(new UserEntity("A4", dev));
		em.persist(new UserEntity("A5", sqm));
		em.persist(new UserEntity("A6", sqm));
		em.persist(new UserEntity("A7", sqm));

		em.getTransaction().commit();
		em.close();
	}

	private static void mixGroupUser() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		UserEntity user1 = em.find(UserEntity.class, "A2");
		UserEntity user2 = em.find(UserEntity.class, "A3");
		UserEntity user3 = em.find(UserEntity.class, "A4");
		UserEntity user4 = em.find(UserEntity.class, "A5");
		UserEntity user5 = em.find(UserEntity.class, "A6");

		GroupEntity group1 = em.find(GroupEntity.class, 1L);
		GroupEntity group2 = em.find(GroupEntity.class, 2L);
		GroupEntity group3 = em.find(GroupEntity.class, 3L);

		group1.addUser(user1);
		group1.addUser(user2);

		group2.addUser(user3);
		group2.addUser(user4);
		group2.addUser(user5);

		group3.addUser(user1);
		group3.addUser(user2);
		group3.addUser(user3);

		em.persist(group1);
		em.persist(group2);
		em.persist(group3);

		em.getTransaction().commit();
		em.close();
	}

	private static void createProjects() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		ProjectEntity project1 = new ProjectEntity("ID1", "Project 1");
		ProjectEntity project2 = new ProjectEntity("ID2", "Project 2");
		ProjectEntity project3 = new ProjectEntity("ID3", "Project 3");

		em.persist(project1);
		em.persist(project2);
		em.persist(project3);

		em.getTransaction().commit();
		em.close();
	}

	private static void mixGroupProject() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		ProjectEntity project1 = em.find(ProjectEntity.class, "ID1");
		ProjectEntity project2 = em.find(ProjectEntity.class, "ID2");
		ProjectEntity project3 = em.find(ProjectEntity.class, "ID3");

		GroupEntity group1 = em.find(GroupEntity.class, 1L);
		GroupEntity group2 = em.find(GroupEntity.class, 2L);
		GroupEntity group3 = em.find(GroupEntity.class, 3L);
		
		group1.setProject(project1);
		group2.setProject(project2);
		group3.setProject(project3);
		
		em.persist(group1);
		em.persist(group2);
		em.persist(group3);

		em.getTransaction().commit();
		em.close();
	}

	private static void createMetrics() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		ProjectEntity project1 = em.find(ProjectEntity.class, "ID1");
		project1.addMetrics(new ProjectMetricsEntity(250));
		analyzeProject(project1, em);
		
		ProjectEntity project2 = em.find(ProjectEntity.class, "ID2");
		project2.addMetrics(new ProjectMetricsEntity(181));
		analyzeProject(project2, em);
		
		ProjectEntity project3 = em.find(ProjectEntity.class, "ID3");
		project3.addMetrics(new ProjectMetricsEntity(98));
		analyzeProject(project3, em);
		
		em.merge(project1);
		em.merge(project2);
		em.merge(project3);
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static void createReports() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		ProjectEntity project1 = em.find(ProjectEntity.class, "ID1");
		project1.addReports(new ProjectReportsEntity("DOC1"));
	
		
		ProjectEntity project2 = em.find(ProjectEntity.class, "ID2");
		project2.addReports(new ProjectReportsEntity("DOC1"));
		project2.addReports(new ProjectReportsEntity("DOC2"));
				
		em.merge(project1);
		em.merge(project2);
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static void createBadges() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		BadgesEntity be1 = new BadgesEntity("BadgeProjectOro");
		BadgesEntity be2 = new BadgesEntity("BadgeProjectPlata");
		BadgesEntity be3 = new BadgesEntity("BadgeProjectBronce");
		BadgesEntity be4 = new BadgesEntity("BadgeUserOro");
		BadgesEntity be5 = new BadgesEntity("BadgeUserPlata");
		BadgesEntity be6 = new BadgesEntity("BadgeUserBronce");
		
		em.persist(be1);
		em.persist(be2);
		em.persist(be3);
		em.persist(be4);
		em.persist(be5);
		em.persist(be6);
		
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static void mixBadgesAndProjects() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		BadgesEntity be1 = em.find(BadgesEntity.class, 1L);
		BadgesEntity be2 = em.find(BadgesEntity.class, 2L);
		BadgesEntity be3 = em.find(BadgesEntity.class, 3L);

		
		be1.addProject(em.find(ProjectEntity.class, "ID1"));
				
		be2.addProject(em.find(ProjectEntity.class, "ID2"));
		be3.addProject(em.find(ProjectEntity.class, "ID2"));
		
		be1.addProject(em.find(ProjectEntity.class, "ID3"));
		be2.addProject(em.find(ProjectEntity.class, "ID3"));
		be3.addProject(em.find(ProjectEntity.class, "ID3"));
				
		em.merge(be1);
		em.merge(be2);
		em.merge(be3);
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static void mixBadgesAndUsers() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		BadgesEntity be4 = em.find(BadgesEntity.class, 4L);
		BadgesEntity be5 = em.find(BadgesEntity.class, 5L);
		BadgesEntity be6 = em.find(BadgesEntity.class, 6L);
		
		be4.addUser(em.find(UserEntity.class, "A6"));
		be5.addUser(em.find(UserEntity.class, "A6"));
		be6.addUser(em.find(UserEntity.class, "A6"));
		
		be4.addUser(em.find(UserEntity.class, "A7"));
		be6.addUser(em.find(UserEntity.class, "A7"));
		
		be4.addUser(em.find(UserEntity.class, "A1"));
		
		be4.addUser(em.find(UserEntity.class, "A2"));
				
		em.getTransaction().commit();
		em.close();
	}
	
	private static void analyzeProject(ProjectEntity project, EntityManager em) {			
		Random rand = new Random();
		RankingEntity re = (RankingEntity) em.find(RankingEntity.class, project.getProjectId());		
		
		if(null == re)
			re = new RankingEntity(10.0 * rand.nextDouble());				
		else 
			re.setCalification(10.0 * rand.nextDouble());
				
		re.setProject(project);		
		em.persist(re);
	}
	
	private static void getRanking() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		List<RankingEntity> ranking = em.createQuery("FROM RankingEntity ORDER BY calification DESC").getResultList();
		
		for(RankingEntity r : ranking) {
			System.out.println(" ** " + r);
		}
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static void removeOneGroup() {
		
	}
	
	private static void removeOneUser() {
		
	}
	
	private static void removeOneProject() {
		
	}
	
	private static void listAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<GroupEntity> groups = em.createQuery("FROM GroupEntity").getResultList();
		List<RoleEntity> roles = em.createQuery("FROM RoleEntity").getResultList();
		List<UserEntity> users = em.createQuery("FROM UserEntity").getResultList();
		List<ProjectEntity> projects = em.createQuery("FROM ProjectEntity").getResultList();
		List<ProjectMetricsEntity> metrics = em.createQuery("FROM ProjectMetricsEntity").getResultList();
		List<ProjectReportsEntity> reports = em.createQuery("FROM ProjectReportsEntity").getResultList();
		List<BadgesEntity> badges = em.createQuery("FROM BadgesEntity").getResultList();


		System.out.println(" -- Hay " + groups.size() + " grupos:");
		for (GroupEntity g : groups) {
			System.out.println(" ---- " + g);
		}

		System.out.println(" -- Hay " + roles.size() + " roles:");
		for (RoleEntity r : roles) {
			System.out.println(" ---- " + r);
		}

		System.out.println(" -- Hay " + users.size() + " usuarios:");
		for (UserEntity u : users) {
			System.out.println(" ---- " + u);
		}

		System.out.println(" -- Hay " + projects.size() + " proyectos:");
		for (ProjectEntity p : projects) {
			System.out.println(" ---- " + p);
		}

		System.out.println(" -- Hay " + metrics.size() + " metricas:");
		for (ProjectMetricsEntity pm : metrics) {
			System.out.println(" ---- " + pm);
		}

		System.out.println(" -- Hay " + reports.size() + " informes:");
		for (ProjectReportsEntity pr : reports) {
			System.out.println(" ---- " + pr);
		}
		
		System.out.println(" -- Hay " + badges.size() + " medallas:");
		for (BadgesEntity be : badges) {
			System.out.println(" ---- " + be);
		}
		
		em.getTransaction().commit();
		em.close();
	}

}
