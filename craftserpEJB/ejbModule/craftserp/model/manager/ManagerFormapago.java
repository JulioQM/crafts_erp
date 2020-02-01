package craftserp.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import craftserp.model.entities.VenFormaPago;

/**
 * Session Bean implementation class ManagerFormapago
 */
@Stateless
@LocalBean
public class ManagerFormapago {

    @PersistenceContext
    private EntityManager em;
    public ManagerFormapago() {
        // TODO Auto-generated constructor stub
    }

    public List<VenFormaPago> findAllFormapago(){
    	String consulta = "select o from VenFormaPago o";
		Query q = em.createQuery(consulta, VenFormaPago.class);
		return q.getResultList(); 
    }
    
	public VenFormaPago findFormaPagoByNombre(String nombre){
		return em.find(VenFormaPago.class, nombre);
	}
	
	public VenFormaPago findFormaPagoById(Integer id) {
		return em.find(VenFormaPago.class, id);
	}
	
	public void actualizarFormapago(VenFormaPago id) throws Exception{
		VenFormaPago pago = findFormaPagoById(id.getIdFormaPago());
		if(pago==null)
			throw new Exception("No existe la forma de pago");
		pago.setIdFormaPago(id.getIdFormaPago());
		pago.setNombre(id.getNombre());
		em.merge(pago);
	}
	
	public void eliminarFormapago(Integer id) {
		VenFormaPago formaPago = findFormaPagoById(id);
		if(formaPago != null) {
			em.remove(formaPago);
		}
	}
	/*public void eliminarFormapago(Integer id) {
		VenFormaPago formapago = findFormaPagoById(id);
		if (formapago != null)
			em.remove(formapago);

	}*/

	public void InsertarFormaPago(VenFormaPago nombre) throws Exception {
		VenFormaPago fp = new VenFormaPago();
		fp.setNombre(nombre.getNombre());
		em.persist(fp);
	}

}
