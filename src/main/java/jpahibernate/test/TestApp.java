package jpahibernate.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpahibernate.entities.GroupEntity;
import jpahibernate.entities.ProjectEntity;
import jpahibernate.entities.ProjectMetricsEntity;
import jpahibernate.entities.RoleEntity;
import jpahibernate.entities.UserEntity;

public class TestApp {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");;

	public static void main(String[] args) {

		// createGroups();
		// createRoles();
		// createUsers();
		// mixGroupUser();
		// createProjects();
		// mixGroupProject();
		// createMetrics();

		listAll();
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
		ProjectEntity project2 = new ProjectEntity("ID2", "Project 3");
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
		
		ProjectMetricsEntity pm_rev1 = new ProjectMetricsEntity(250);		
		ProjectEntity project1 = em.find(ProjectEntity.class, "ID1");
		
		pm_rev1.setProject(project1);
		project1.addMetrics(pm_rev1);
		//TODO aprovechar y re-calcular el ranking
		
		em.merge(project1);
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static void createReports() {
		
	}
	
	private static void listAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<GroupEntity> groups = em.createQuery("FROM GroupEntity").getResultList();
		List<RoleEntity> roles = em.createQuery("FROM RoleEntity").getResultList();
		List<UserEntity> users = em.createQuery("FROM UserEntity").getResultList();
		List<ProjectEntity> projects = em.createQuery("FROM ProjectEntity").getResultList();
		List<ProjectMetricsEntity> metrics = em.createQuery("FROM ProjectMetricsEntity").getResultList();

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

		
		em.getTransaction().commit();
		em.close();
	}

}
