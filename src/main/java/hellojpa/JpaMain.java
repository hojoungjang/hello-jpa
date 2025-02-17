package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-jpa");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // create
            Member member = new Member();
            member.setId(1L);
            member.setName("Hello JPA");
            em.persist(member);

            // read
            Member findMember = em.find(Member.class, 1L);
            System.out.println("Member ID: " + findMember.getId());
            System.out.println("Member name: " + findMember.getName());

            // update
            findMember.setName("Hello ORM");

            // remove
            em.remove(findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
