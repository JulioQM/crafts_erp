package craftserp.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import craftserp.model.manager.ManagerParametros;
import craftserp.model.entities.Parametro;;

@Named
@RequestScoped
public class BeanParametro {
	@EJB
	private ManagerParametros managerParametros;
	
	public List<Parametro> getListaParametros(){
		return managerParametros.findAllParametros();
	}
	public BeanParametro() {
		
	}

}
