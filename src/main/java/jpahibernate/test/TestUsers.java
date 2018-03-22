//package jpahibernate.test;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import jpahibernate.entity.Group;
//import jpahibernate.entity.Role;
//import jpahibernate.entity.User;
//
//public class TestUsers {
//
//	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");;
//	
//	public static void main(String[] args) {
//		
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		
//		Role r= new Role();
//		em.persist(r);
//		
//		em.getTransaction().commit();
//		
//		em.close();
//		
////		createUser();
////		createGroup();
////		mixUsersAndGroups();
////		removeGroup(1L);
//		
////		removeUser("A2");
//		
////		listGroupOfUser("A1");
////		listGroupOfUser("A2");
//		
////		removeUserFromGroup("A2",1L);
//		
////		listAll();
//	}
//
//	private static void removeUserFromGroup(String user_das, Long group_id) {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		
//		Group g = em.find(Group.class, group_id);
//		User u = em.find(User.class, user_das);
//		
//		g.removeUser(u);
//		
//		em.merge(g);
//		
//		em.getTransaction().commit();
//		em.close();
//	}
//	
//	private static void removeUser(String user_das) {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		
//		User u = em.find(User.class, user_das);
//		//Primero eliminar de los grupos al usuario
//		for(Group g : u.getGroups()) {
//			Group group = em.find(Group.class, g.getGroupId());
//			group.removeUser(u);
//			em.merge(group);
//		}
//		
//		em.remove(u);
//		
//		em.getTransaction().commit();
//		em.close();
//	}
//	
//	private static void removeGroup(Long group_id) {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		
//		Group g = em.find(Group.class, group_id);
//		em.remove(g);
//		
//		em.getTransaction().commit();
//		em.close();
//	}
//	
//	private static void listGroupOfUser(String user_das) {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		
//		User user = em.find(User.class, user_das);
//		
//		System.out.println("Grupos del usuario " + user_das);
//		for(Group g: user.getGroups()) {
//			System.out.println("- " + g);
//		}
//		
//		em.getTransaction().commit();
//		em.close();
//	}
//	
//	private static void mixUsersAndGroups() {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		
//		//LoadUsers
//		User user1 = em.find(User.class, "A1");
//		User user2 = em.find(User.class, "A2");
//		User user3 = em.find(User.class, "A3");
//		//LoadGroups
//		Group group1 = em.find(Group.class, 1L);
//		Group group2 = em.find(Group.class, 2L);
//		
//		
//		group1.addUser(user1);
//		group1.addUser(user2);
//		group2.addUser(user2);
//		group2.addUser(user3);
//		
//		
//		em.merge(group1);
//		em.merge(group2);
//		
//		em.getTransaction().commit();
//		em.close();
//	}
//		
//	private static void createUser() {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		
//		User u1 = new User("A1", "userName1");
//		User u2 = new User("A2", "userName2");
//		User u3 = new User("A3", "userName3");
//		
//		em.persist(u1);
//		em.persist(u2);
//		em.persist(u3);
//					
//		em.getTransaction().commit();
//		em.close(); 
//	}
//	
//	private static void createGroup() {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		
//		em.persist(new Group(1L,"Group1"));
//		em.persist(new Group(2L,"Group2"));
//		
//		em.getTransaction().commit();
//		em.close(); 
//	}
//	
//	@SuppressWarnings("unchecked")
//	private static void listUsers() {
//		EntityManager em = emf.createEntityManager();
//			
//		List<User> users = (List<User>) em.createQuery("FROM User").getResultList();
//		
//		System.out.println("Hay " + users.size() + " usuarios:");
//		
//		for(User user : users) {
//			System.out.println(" * Usuario: " + user);
//		}
//		
//
//		em.close(); 
//	}
//		
//	@SuppressWarnings("unchecked")
//	private static void listGroups() {
//		EntityManager em = emf.createEntityManager();
//
//		List<Group> groups = (List<Group>) em.createQuery("FROM Group").getResultList();
//		
//		System.out.println("Hay " + groups.size() + " grupos: ");
//		for(Group group : groups) {
//			System.out.println(" * Grupo: " + group);
//		}
//		
//	
//		em.close(); 
//	}
//	
//	private static void listAll() {
//		listUsers();
//		listGroups();
//	}
//}
//
