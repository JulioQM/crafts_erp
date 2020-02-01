package craftserp.controller;

import java.io.Serializable; 
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import craftserp.model.entities.ProCatalogo;
import craftserp.model.manager.ManagerCatalogo;

@Named
@SessionScoped

public class BeanCatalogo  implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerCatalogo managercatalogo;
	private List<ProCatalogo> listaProCatalogos;
	private ProCatalogo catalogo;
	private ProCatalogo catalogoSeleccionado;
	
	@PostConstruct
	public void inicializar() {
		listaProCatalogos=managercatalogo.findAllCatalogo();
		catalogo= new ProCatalogo();
	}

	public void actionListenerInsertar() {
		try {
			managercatalogo.InsertarCat(catalogo);;
		listaProCatalogos=managercatalogo.findAllCatalogo();
		catalogo= new ProCatalogo();
		JSFUtil.crearMensajeInfo("Datos de catalogo insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void actionLIstenerEliminarCatalogo(Integer id) {
		managercatalogo.eliminarCatalogo(id);
		listaProCatalogos= managercatalogo.findAllCatalogo();
		JSFUtil.crearMensajeInfo("Catalogo Eliminado");
	}
	
	public void actionListenerSeleccionarCatalogo(ProCatalogo catalogo) {
		
		catalogoSeleccionado=catalogo;
	}
	
	public void actionListenerActualizarCatalogo() {
		
		try {
			managercatalogo.ActualizarCatalogo(catalogoSeleccionado);
			listaProCatalogos= managercatalogo.findAllCatalogo();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public List<ProCatalogo> getListaProCatalogos() {
		return listaProCatalogos;
	}

	public void setListaProCatalogos(List<ProCatalogo> listaProCatalogos) {
		this.listaProCatalogos = listaProCatalogos;
	}

	public ProCatalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(ProCatalogo catalogo) {
		this.catalogo = catalogo;
	}

	public ProCatalogo getCatalogoSeleccionado() {
		return catalogoSeleccionado;
	}

	public void setCatalogoSeleccionado(ProCatalogo catalogoSeleccionado) {
		this.catalogoSeleccionado = catalogoSeleccionado;
	}
	

}
