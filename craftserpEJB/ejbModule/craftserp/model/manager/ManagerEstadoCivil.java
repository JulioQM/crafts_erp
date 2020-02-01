package craftserp.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import craftserp.model.entities.SegEstadoCivil;


/**
 * Session Bean implementation class ManagerEstadoCivil
 */
@Stateless
@LocalBean
public class ManagerEstadoCivil {
	@PersistenceContext
	private EntityManager em;

	public ManagerEstadoCivil() {

	}

	// me sirve para consultar el estado civil
	public List<SegEstadoCivil> findAllEstadoCivil() {
		String consulta = "SELECT s FROM SegEstadoCivil s";
		Query q = em.createQuery(consulta, SegEstadoCivil.class);
		return q.getResultList();
	}

	public SegEstadoCivil findEstadoCivilByNombre(String nombre) {
		return em.find(SegEstadoCivil.class, nombre);
	}

	public void insertarEstadoCivil(String nombre) {
		SegEstadoCivil estadocivil = new SegEstadoCivil();
		estadocivil.setNombre(nombre);
		em.persist(estadocivil);
	}

	// metodo buscar
	public SegEstadoCivil buscarEstadoCivil(Integer id) {
		SegEstadoCivil estadocivil = new SegEstadoCivil();
		estadocivil = em.find(SegEstadoCivil.class, id);
		return estadocivil;

	}

//merodo de eliminar
	public void eliminarEstadoCivil(Integer id) {
		SegEstadoCivil estadocivil = new SegEstadoCivil();
		estadocivil = em.find(SegEstadoCivil.class, id);
		em.remove(estadocivil);
	}

	// actualizar
	public void actualizarEstadoCivil(SegEstadoCivil estado) throws Exception {
		SegEstadoCivil estadocivil = buscarEstadoCivil(estado.getIdEstadoCivil());
		if (estadocivil == null)
			throw new Exception("No existe la ciudad con la nombre especifica");
		estadocivil.setNombre(estado.getNombre());
		em.merge(estadocivil);
	}
}
