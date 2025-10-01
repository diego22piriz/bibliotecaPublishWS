package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import configuracion.WebServiceConfiguracion;
import datatypes.DtArticulo;
import datatypes.DtBibliotecario;
import datatypes.DtFecha;
import datatypes.DtLector;
import datatypes.DtLibro;
import datatypes.DtPrestamo;
import interfaces.Fabrica;
import interfaces.IControlador;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorPublish {
	private Fabrica fabrica;
	private IControlador icon;
	private WebServiceConfiguracion configuracion;
	private Endpoint endpoint;

	public ControladorPublish() {
		fabrica = Fabrica.getInstancia();
		icon = fabrica.getIControlador();
		configuracion = new WebServiceConfiguracion();
	}

	@WebMethod(exclude = true)
	public void publicar() {
		endpoint = Endpoint.publish("http://" + configuracion.getConfigOf("#WS_IP") + ":" 
				+ configuracion.getConfigOf("#WS_PORT") + "/controlador", this);
		System.out.println("Web Service publicado en: http://" + configuracion.getConfigOf("#WS_IP") 
				+ ":" + configuracion.getConfigOf("#WS_PORT") + "/controlador");
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
        return endpoint;
	}
	
	// MÉTODOS PARA USUARIOS
	@WebMethod
	public void agregarLector(DtLector dtLector) {
		try {
			icon.agregarLector(dtLector);
		} catch (Exception e) {
			System.err.println("Error al agregar lector: " + e.getMessage());
		}
	}
	
	@WebMethod
	public void agregarBibliotecario(DtBibliotecario dtBibliotecario) {
		try {
			icon.agregarBibliotecario(dtBibliotecario);
		} catch (Exception e) {
			System.err.println("Error al agregar bibliotecario: " + e.getMessage());
		}
	}
	
	// MÉTODOS PARA MATERIALES
	@WebMethod
	public void agregarLibro(DtLibro dtLibro) {
		icon.agregarLibro(dtLibro);
	}
	
	@WebMethod
	public void agregarArticulo(DtArticulo dtArticulo) {
		icon.agregarArticulo(dtArticulo);
	}
	
	// MÉTODOS PARA PRÉSTAMOS
	@WebMethod
	public void agregarPrestamo(DtPrestamo dtPrestamo) {
		try {
			icon.agregarPrestamo(dtPrestamo);
		} catch (Exception e) {
			System.err.println("Error al agregar préstamo: " + e.getMessage());
		}
	}
	
	@WebMethod
	public void suspenderUsuario(String correo) {
		icon.suspenderUsuario(correo);
	}
	
	@WebMethod
	public void cambiarZona(String correo, String zona) {
		icon.cambiarZona(correo, zona);
	}
	
	// MÉTODOS PARA LISTAR
	@WebMethod
	public String[] listarLectores() {
		return icon.listarLectores().toArray(new String[0]);
	}
	
	@WebMethod
	public String[] listarBibliotecarios() {
		return icon.listarBibliotecarios().toArray(new String[0]);
	}
	
	@WebMethod
	public String[] listarMateriales() {
		return icon.listarMateriales().toArray(new String[0]);
	}
	
	@WebMethod
	public String[] listarIdsMateriales() {
		return icon.listarIdsMateriales().toArray(new String[0]);
	}
	
	@WebMethod
	public String[] obtenerMaterialesConPrestamosPendientes() {
		return icon.obtenerMaterialesConPrestamosPendientes().toArray(new String[0]);
	}
	
	@WebMethod
	public String[] obtenerMaterialesPorRangoFechas(DtFecha fechaInicio, DtFecha fechaFin) {
		return icon.obtenerMaterialesPorRangoFechas(fechaInicio, fechaFin).toArray(new String[0]);
	}
	
	@WebMethod
	public String[] obtenerTodasLasZonas() {
		return icon.obtenerTodasLasZonas().toArray(new String[0]);
	}
}
