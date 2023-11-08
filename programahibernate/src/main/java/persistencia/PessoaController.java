package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dominio.Pessoa;

public class PessoaController {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-hibernate");
	EntityManager em = emf.createEntityManager();
	
	public void create(Pessoa _pessoa) {
		em.getTransaction().begin();
		em.merge(_pessoa);
		em.getTransaction().commit();
		emf.close();
	}
	
	public void remove(Pessoa _pessoa) {		
		em.getTransaction().begin();
		Query q = em.createNativeQuery("delete pessoa from pessoa where matricula = "+_pessoa.getMatricula());
		q.executeUpdate();
		em.getTransaction().commit();
		emf.close();
	}
	
	public List<Pessoa> read() {
		em.getTransaction().begin();
		Query consulta = em.createQuery("select pessoa from pessoa");
		List<Pessoa> lista = consulta.getResultList();
		em.getTransaction().commit();
		emf.close();
		return lista;
	}
}
