package craftserp.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import craftserp.model.entities.SegCiudad;

/**
 * Session Bean implementation class ManagerCiudad1
 */
@Stateless
@LocalBean
public class ManagerCiudad {

    @PersistenceContext
	private EntityManager em;

	public ManagerCiudad() {
		// TODO Auto-generated constructor stub
	}

// me sirve para consultar las ciudades que existe
	public List<SegCiudad> findAllCiudad() {
		String consulta = "SELECT s FROM SegCiudad s order by s.idCiudad";
		Query q = em.createQuery(consulta, SegCiudad.class);
		return q.getResultList();
	}

// me sirve para buscar el nombre de la ciudad
	public SegCiudad findCiudadByNombre(int idciudad) {
		return em.find(SegCiudad.class, idciudad);
	}

	public SegCiudad findCiudadByN(String nombre) {
		return em.find(SegCiudad.class, nombre);
	}

// me sirve para registrar las ciudad
	public void insertarCiudad(String nombre) {
		SegCiudad ciudad = new SegCiudad();
		ciudad.setNombre(nombre);
		em.persist(ciudad);
	}
//metodo para eliminar la ciudad
	public void eliminarCiudad(Integer idciudad) {
		SegCiudad ciudad = findCiudadByNombre(idciudad);
		if (ciudad != null)
			em.remove(ciudad);
	}
//metodo para actualizar la ciudad
	public void actualizarCiudad(SegCiudad idciudad) throws Exception {
		SegCiudad ciudad = findCiudadByNombre(idciudad.getIdCiudad());
		if (ciudad == null)
			throw new Exception("No existe la ciudad con el nombre especificado");
		ciudad.setNombre(idciudad.getNombre());
		em.merge(ciudad);
	}
}
