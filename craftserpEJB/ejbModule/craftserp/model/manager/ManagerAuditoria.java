package craftserp.model.manager;

import java.net.InetAddress;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.omg.CORBA.PERSIST_STORE;

import craftserp.model.entities.AudBitacora;

/**
 * Session Bean implementation class ManagerAuditoria
 */
@Stateless
@LocalBean
public class ManagerAuditoria {
	
	@EJB
	private ManagerDAO managerDAO;
	@EJB
	private ManagerSeguridad managerSeguridad;
	private EntityManager em;
   
    public ManagerAuditoria() {
        // TODO Auto-generated constructor stub
    }
    
    
   public void crearEvento(Integer idusuario,Class clase,String metodo,String descripcion) throws Exception{
		AudBitacora evento=new AudBitacora();
		//cambio para probar git
		
		if(idusuario==null||idusuario==0)
			throw new Exception("Error auditoria: debe indicar el codigo del usuario.");
		if(metodo==null||metodo.length()==0)
			throw new Exception("Error auditoria: debe indicar el metodo que genera el evento.");

		evento.setMetodo(clase.getSimpleName()+"/"+metodo);
		evento.setDescripcion(descripcion);
		evento.setFechaEvento(new Date());
		evento.setDireccionIp("localhost");
		
		
		InetAddress direccionIp=InetAddress.getLocalHost();
		
		String iplocal=direccionIp.getHostAddress();
		evento.setDireccionIp(iplocal);
		evento.setCodigoEvento(idusuario);
		em.persist(evento);
		
	}
  

}
