package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.util.IOGeneral;
import dao.factory.CamionFactory;
import dao.factory.ChoferFactory;
import dao.factory.UsuarioFactory;
import dao.factory.ViajeFactory;
import dao.impl.CamionImpl;
import dao.impl.ChoferImpl;
import dao.interfaces.CamionDAO;
import dao.interfaces.ChoferDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.ViajeDAO;
import dominio.Camion;
import dominio.Chofer;
import dominio.Usuario;
import dominio.Viaje;

/**
 * Servlet implementation class servletEdit
 */
@WebServlet("/servletEdit")
public class servletEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Camion cam;
	private static Chofer chof;
	private static Viaje viaje;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salir = request.getParameter("Exit");
		String btnEnviarEditar = request.getParameter("btnEnviarEditar");
		String btnEnviarEliminar = request.getParameter("btnEnviarEliminar");
		String campoID = request.getParameter("campoID");
		String radio = request.getParameter("radio");
		int id = Integer.parseInt(campoID);
		if(btnEnviarEliminar != null) {
			System.out.println("IDDDDDDDDDDDDDDDDDD "+id);
			System.out.println("RADIOOO " + radio);
			if(radio.equals("Chofer")) {
				System.out.println("funcionaaa");
				ChoferDAO chofDAO = ChoferFactory.getImplementation("BD");
				try {
					chofDAO.eliminarChofer(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(radio.equals("Camion")) {
				CamionDAO camionDAO = CamionFactory.getImplementation("BD");
				try {
					camionDAO.eliminarCamion(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(btnEnviarEditar != null) {
			System.out.println(btnEnviarEditar);
			String radio2 = request.getParameter("radio");
			//System.out.println("EDIT:" + id + " " + split[1]);
			request.getSession().setAttribute("datoSeleccionado", radio2);
			if(radio2.equals("Chofer")) {
				ChoferDAO choDAO = ChoferFactory.getImplementation("BD");
				try {
					chof = choDAO.buscarChofer(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(radio2.equals("Camion")) {
				CamionDAO camDAO = CamionFactory.getImplementation("BD");
				try {
					cam = camDAO.buscarCamion(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(radio2.equals("Viaje")) {
				ViajeDAO viajeDAO = ViajeFactory.getImplementation("BD");
				try {
					viaje = viajeDAO.buscarViaje(id);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				CamionDAO camDAO = CamionFactory.getImplementation("BD");
				try {
					int idCamion = viajeDAO.buscarViajeCamion(id);
					System.out.println(idCamion);
					cam = camDAO.buscarCamion(idCamion);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ChoferDAO chofdao = ChoferFactory.getImplementation("BD");
				try {
					int idChofer = viajeDAO.buscarViajeChoferID(id);
					chof = chofdao.buscarChofer(idChofer);
				}
				 catch (Exception e) {
						e.printStackTrace();
					}
				
			}
			response.sendRedirect("Editar.jsp");
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	public Camion getCamion() {
		return cam;
	}
	
	public Chofer getChofer() {
		return chof;
	}
	
	public Viaje getViaje() {
		return viaje;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btnEditar = request.getParameter("btnEditar");
		String split[] = btnEditar.split(" ");
		String datoModificar = split[1];
		if(datoModificar.equals("Camion")) {
			String marca = request.getParameter("marca");
			String modelo = request.getParameter("modelo");
			String dominio = request.getParameter("dominio");
			String capacidadToneladasS = request.getParameter("capacidadToneladas");
			String cantLitrosNaftaS = request.getParameter("cantLitrosNafta");
			String cantLitrosConsumidosS = request.getParameter("cantLitrosConsumidos");
			double cantLitrosNafta = Double.parseDouble(cantLitrosNaftaS);
			double capacidadToneladas = Double.parseDouble(capacidadToneladasS);
			double cantLitrosConsumidosKM = Double.parseDouble(cantLitrosConsumidosS);
			int id = cam.getIdCamion();
			Viaje vi = cam.getViaje();
			cam = new Camion(id, marca, modelo, dominio, capacidadToneladas, cantLitrosNafta, cantLitrosConsumidosKM, vi);
			CamionDAO camDAO = CamionFactory.getImplementation("BD");
			try {
				camDAO.updateCamion(cam);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(datoModificar.equals("Chofer")) {
			String nombreUsuario = request.getParameter("nombreUsuario");
			String claveUsuario = request.getParameter("claveUsuario");
			String nombre = request.getParameter("nombreChofer");
			String apellido = request.getParameter("apellidoChofer");
			String documentoChofer = request.getParameter("documentoChofer");
			long dni = Long.parseLong(documentoChofer);
			String fechaNacChof = request.getParameter("fechaNacChof");
			IOGeneral io = new IOGeneral();
			Calendar fechaNac = io.convertirFechaStringaCalendar(fechaNacChof);
			String movilS = request.getParameter("celularChof");
			int movil = Integer.parseInt(movilS);
			String categoria = request.getParameter("radioCategoria");
			long idUsuario = chof.getIdUsuario();
			if(!(categoria.equals(chof.getCategoria()))) {
				ViajeDAO viajeDAO = ViajeFactory.getImplementation("BD");
				CamionDAO camDAO = CamionFactory.getImplementation("BD");
				List <Viaje> viajesEliminar = new ArrayList<Viaje>();
				try {
				viajesEliminar = viajeDAO.buscarViajesChofer(chof.getIdUsuario());
				for(Viaje v: viajesEliminar) {
					int idCamion = viajeDAO.buscarViajeCamion(v.getIdViaje());
					Camion camionViejo = camDAO.buscarCamion(idCamion);
					camDAO.updateViajeCamion(camionViejo.getIdCamion(), 0);
					camionViejo.setViaje(null);
					viajeDAO.eliminarViaje(v.getIdViaje());
				}
				viajesEliminar.removeAll(viajesEliminar);
				chof.setListaViaje(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			chof = new Chofer(idUsuario, claveUsuario, nombreUsuario, "Chofer", nombre, apellido, dni, fechaNac, categoria, movil, null);
			ChoferDAO chofDAO = ChoferFactory.getImplementation("BD");
			try {
				chofDAO.updateChofer(chof);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UsuarioDAO usDAO = UsuarioFactory.getImplementation("BD");
			try {
				usDAO.updateUsuario(((Usuario)chof));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(datoModificar.equals("Viaje")) {
			String selectChofer = request.getParameter("selectChofer");
			String selectDestino = request.getParameter("selectDestino");
			String selectPatente = request.getParameter("selectPatente");
			String selectOrigen = request.getParameter("selectOrigen");
			String distanciaS = request.getParameter("distancia");
			double distancia = Double.parseDouble(distanciaS);
			long dni = Long.parseLong(selectChofer);
			int idViaje = viaje.getIdViaje();
			boolean inicio = viaje.isInicio();
			boolean fin = viaje.isFinalizado();
			double tiempoViaje = viaje.getTiempoViaje();
			if(distancia != viaje.getDistancia()) {
				tiempoViaje = distancia/200; //La cantidad de km recorrido por dia
			}
			ChoferDAO chofDAO = ChoferFactory.getImplementation("BD");
			int idChofer = 0;
			try {
				idChofer = chofDAO.buscarChoferDNI(dni);
			} catch (Exception e) {
				e.printStackTrace();
			}
			CamionDAO camDAO = CamionFactory.getImplementation("BD");
			int idCamion = 0;
			try {
				idCamion = camDAO.buscarCamionPatente(selectPatente);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			viaje = new Viaje(idViaje, selectDestino, selectOrigen, inicio, fin, tiempoViaje, distancia);
			ViajeDAO viajeDAO = ViajeFactory.getImplementation("BD");
			try {
				viajeDAO.updateViaje(viaje, idChofer, idCamion);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				camDAO.updateViajeCamion(idCamion, idViaje);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		response.sendRedirect("Administrador.jsp");
		//doGet(request, response);
	}

}
