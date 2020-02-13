package craftserp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;



import craftserp.model.entities.SegCiudad;
import craftserp.model.manager.ManagerCiudad;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

@Named
@SessionScoped

public class BeanCiudad implements Serializable {
	
	
	
	@EJB
	private ManagerCiudad managerCiudad;
	private List<SegCiudad> listaCiudad;
	private SegCiudad ciudad;
	private int idtipolibro;
	private SegCiudad ciudadSelecionado;
	private String nombreCiudad;

	@PostConstruct
	public void inicializar() {
		listaCiudad = managerCiudad.findAllCiudad();
		ciudad = new SegCiudad();
	}

	public void actionListenerInsertarCiudad() {
		try {
			managerCiudad.insertarCiudad(nombreCiudad);
			listaCiudad = managerCiudad.findAllCiudad();
			ciudad = new SegCiudad();
			JSFUtil.crearMensajeInfo("Datos de ciudad Registrada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError("Esta ciudad ya se encuentra registrada");
		}
	}

       //sirve para eliminar
	
	public void actionListenerEliminarCiudad(Integer idciudad) {
		managerCiudad.eliminarCiudad(idciudad);
		listaCiudad = managerCiudad.findAllCiudad();
		JSFUtil.crearMensajeInfo("Ciudad eliminado");
	}

	public void actionListenerSeleccionarCiudad(SegCiudad ciudad) {
		ciudadSelecionado = ciudad;
	}

	/*public void actionListenerActualizarCiudad() {
		try {
			managerCiudad.actualizarCiudad(ciudadSelecionado);
			listaCiudad = managerCiudad.findAllCiudad();
			JSFUtil.crearMensajeInfo("Datos actualizados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}*/
	
	/*public String actionReporte(){
		Map<String,Object> parametros=new HashMap<String,Object>();
		/*parametros.put("p_titulo_principal",p_titulo_principal);
		parametros.put("p_titulo",p_titulo);*/
		/*FacesContext context=FacesContext.getCurrentInstance();
		ServletContext servletContext=(ServletContext)context.getExternalContext().getContext();
		String ruta=servletContext.getRealPath("Administrador/MODULO.SEGURIDAD/craftserp.jasper");
		System.out.println(ruta);
		HttpServletResponse response=(HttpServletResponse)context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=blogs.pdf");
		response.setContentType("application/pdf");
		try {
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection(
		"jdbc:postgresql://localhost:5432/craftserp","postgres", "");
		JasperPrint impresion=JasperFillManager.fillReport(ruta, parametros,connection);
		JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
		context.getApplication().getStateManager().saveView ( context ) ;
		System.out.println("reporte generado.");
		context.responseComplete();
		} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		e.printStackTrace();
		}
		return "";
		}*/

	// set y get
	public List<SegCiudad> getListaCiudad() {
		return listaCiudad;
	}

	public void setListaCiudad(List<SegCiudad> listaCiudad) {
		this.listaCiudad = listaCiudad;
	}

	public SegCiudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(SegCiudad ciudad) {
		this.ciudad = ciudad;
	}

	public int getIdtipolibro() {
		return idtipolibro;
	}

	public void setIdtipolibro(int idtipolibro) {
		this.idtipolibro = idtipolibro;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public SegCiudad getCiudadSelecionado() {
		return ciudadSelecionado;
	}

	public void setCiudadSelecionado(SegCiudad ciudadSelecionado) {
		this.ciudadSelecionado = ciudadSelecionado;
	}

}
