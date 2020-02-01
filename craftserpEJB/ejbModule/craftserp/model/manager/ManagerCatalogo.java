package craftserp.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import craftserp.model.entities.ProCatalogo;


/**
 * Session Bean implementation class ManagerCatalogo
 */
@Stateless
@LocalBean
public class ManagerCatalogo {

	@PersistenceContext
	private EntityManager em;

	public ManagerCatalogo() {
		// TODO Auto-generated constructor stub
	}

	public List<ProCatalogo> findAllCatalogo() {
		String consulta = "select o from ProCatalogo o";
		Query q = em.createQuery(consulta, ProCatalogo.class);
		return q.getResultList();

	}

	public void insertarCatalogo(ProCatalogo catalogo) throws Exception {
		if (findCatalogoById(catalogo.getIdCatalogo()) != null)
			throw new Exception("Ya existe un id idicado");
		em.persist(catalogo);

	}

	public void InsertarCat(ProCatalogo catalogo) {
		ProCatalogo e= new ProCatalogo();

		e.setIdCatalogo(catalogo.getIdCatalogo());
		e.setNombre(catalogo.getNombre());
		e.setDescripcion(catalogo.getDescripcion());
		e.setImagen(catalogo.getImagen());
		em.persist(e);
	}

	public ProCatalogo findCatalogoById(Integer id) {
		return em.find(ProCatalogo.class, id);

	}

	public void eliminarCatalogo(Integer id) {
		ProCatalogo catalogo = findCatalogoById(id);
		if (catalogo != null)
			em.remove(catalogo);

	}

	public void ActualizarCatalogo(ProCatalogo catalogo) throws Exception {
		ProCatalogo p = findCatalogoById(catalogo.getIdCatalogo());
		if (p == null)
			throw new Exception("No existe el catalogo con el id especificado");
		p.setIdCatalogo(catalogo.getIdCatalogo());
		p.setNombre(catalogo.getNombre());
		p.setDescripcion(catalogo.getDescripcion());
		p.setImagen(catalogo.getImagen());

		em.merge(p);

	}
}
