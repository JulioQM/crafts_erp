package craftserp.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import craftserp.model.entities.ProProductoCatalogo;



/**
 * Session Bean implementation class ManagerProductoCatalogo
 */
@Stateless
@LocalBean
public class ManagerProductoCatalogo {

	@PersistenceContext
	private EntityManager em;
    public ManagerProductoCatalogo() {
        // TODO Auto-generated constructor stub
    }
    
    public List<ProProductoCatalogo> findAllProductoCatalogo() {
		String consulta = "select o from ProProductoCatalogo o";
		Query q = em.createQuery(consulta, ProProductoCatalogo.class);
		return q.getResultList();

	}

	public void insertarProductoCatalogo(ProProductoCatalogo catalogo) throws Exception {
		if (findProductoCatalogoById(catalogo.getIdProductoCatalogo()) != null)
			throw new Exception("Ya existe un id idicado");
		em.persist(catalogo);

	}

	public void InsertarProductoCat(ProProductoCatalogo catalogo) {
		ProProductoCatalogo e= new ProProductoCatalogo();

		e.setIdProductoCatalogo(catalogo.getIdProductoCatalogo());
		e.setProCatalogo(catalogo.getProCatalogo());
		e.setProProducto(catalogo.getProProducto());
	
		em.persist(e);
	}

	public ProProductoCatalogo findProductoCatalogoById(Integer id) {
		return em.find(ProProductoCatalogo.class, id);

	}

	public void eliminarProductoCatalogo(Integer id) {
		ProProductoCatalogo catalogo = findProductoCatalogoById(id);
		if (catalogo != null)
			em.remove(catalogo);

	}

	public void ActualizarProductoCatalogo(ProProductoCatalogo catalogo) throws Exception {
		ProProductoCatalogo p = findProductoCatalogoById(catalogo.getIdProductoCatalogo());
		if (p == null)
			throw new Exception("No existe el catalogo con el id especificado");
		p.setIdProductoCatalogo(catalogo.getIdProductoCatalogo());
		p.setProCatalogo(catalogo.getProCatalogo());
		p.setProProducto(catalogo.getProProducto());

		em.merge(p);

	}

}
