package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import dao.interfaces.CamionDAO;
import dao.interfaces.ChoferDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.ViajeDAO;
import dominio.Camion;
import dominio.Chofer;
import dominio.Viaje;

/**
 * Servlet implementation class servletAdmin
 */
@WebServlet("/servletAdmin")
public class servletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static servletLogin sl = new servletLogin();   
    private static String destino = "";
    private static String origen = "";
    private static String patente= "";
    private static double distancia = 0;
    
    public servletAdmin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectChofer = request.getParameter("selectChofer");


		long dni = Long.parseLong(selectChofer);
		Chofer cho = null;
		Camion camion = null;
		for(int i=0; i<sl.getChoferes().size(); i++) {
			if(sl.getChoferes().get(i).getDni() == dni) {
				cho = sl.getChoferes().get(i);
			}
		}
		ViajeDAO viajeDAO = ViajeFactory.getImplementation("BD");
		int idViaje = 0;
		try {
			idViaje = viajeDAO.obtenerUltId();
			idViaje++;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		double tiempoViaje = distancia/200;
		Viaje v = new Viaje(idViaje, destino, origen, false, false, tiempoViaje, distancia); 
		for(int i=0; i<sl.getCamiones().size(); i++) {
			if(sl.getCamiones().get(i).getDominio().equals(patente)) {
				camion = sl.getCamiones().get(i);
			}
		}
		CamionDAO camDAO = CamionFactory.getImplementation("BD");
		try {
			camDAO.updateViajeCamion(camion.getIdCamion(), idViaje);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		destino = "";
		origen = "";
		patente = "";
		distancia = 0;
	try {
		viajeDAO.agregarViaje(v, cho.getIdUsuario(), camion.getIdCamion());
	} catch (Exception e) {
		e.printStackTrace();
	}	
	sl.menuAdmin(response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btnEnviar = request.getParameter("btnEnviar");
		if(btnEnviar != null) {
			String marca = request.getParameter("marca");
			String modelo = request.getParameter("modelo");
			String dominio = request.getParameter("dominio");
			String capacidadToneladasS = request.getParameter("capacidadToneladas");
			String cantLitrosNaftaS = request.getParameter("cantLitrosNafta");
			String cantLitrosConsumidosS = request.getParameter("cantLitrosConsumidos");
			double capacidadToneladas = Double.parseDouble(capacidadToneladasS);
			double cantLitrosNafta = Double.parseDouble(cantLitrosNaftaS);
			double cantLitrosConsumidos = Double.parseDouble(cantLitrosConsumidosS);
			System.out.println("AGREGAR");
		    CamionDAO camionDAO = CamionFactory.getImplementation("BD");
		    System.out.println("AGREGADO");
		    int ultId = 0;
			try {
				ultId = camionDAO.obtenerUltId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int id = ultId;
			id++;
			
			Viaje vi = new Viaje();
			//System.out.println(id);
			sl.getCamiones().add(new Camion(id, marca, modelo, dominio.toUpperCase(), capacidadToneladas, cantLitrosNafta, cantLitrosConsumidos, vi)); //Es un viaje por defecto por la relacion en la BD
			try {
				camionDAO.agregarCamion(sl.getCamiones().get(sl.getCamiones().size()-1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			sl.menuAdmin(response);
			IOGeneral io = new IOGeneral();
			String nombreUsuario = request.getParameter("nombreUsuario");
			String claveUsuario = request.getParameter("claveUsuario");
			String radioCategoria = request.getParameter("radioCategoria");
			String fechaNacChofS = request.getParameter("fechaNacChof");
			Calendar fechaNac = Calendar.getInstance();
			fechaNac = io.convertirFechaStringaCalendar(fechaNacChofS);
			String documentoChofer = request.getParameter("documentoChofer");
			long dni = Long.parseLong(documentoChofer);
			String nombreChofer = request.getParameter("nombreChofer");
			String apellidoChofer = request.getParameter("apellidoChofer");
			String celularChof = request.getParameter("celularChof");
			int celular = Integer.parseInt(celularChof);
			 UsuarioDAO usDAO = UsuarioFactory.getImplementation("BD");
			    
				sl.getChoferes().add(new Chofer(dni, claveUsuario, nombreUsuario, "Chofer", nombreChofer, apellidoChofer, dni, fechaNac, radioCategoria, celular, null));
				
				ChoferDAO choDAO = ChoferFactory.getImplementation("BD");
				try {
					usDAO.agregarUsuario(nombreUsuario, claveUsuario, dni);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					choDAO.agregarChofer(sl.getChoferes().get(sl.getChoferes().size()-1));
				} catch (Exception e) {
					e.printStackTrace();
				}

				sl.menuAdmin(response);
			String pantente = request.getParameter("pantenteBuscar");
			String destinoS = request.getParameter("selectDestino");
			String origenS = request.getParameter("selectOrigen");
			String distanciaS = request.getParameter("distancia");
			System.out.println(distanciaS);
			double distanciad = Double.parseDouble(distanciaS);
			Camion camion = null;
			for(int i=0; i<sl.getCamiones().size(); i++) {
				if(sl.getCamiones().get(i).getDominio().equals(pantente)) {
					camion = sl.getCamiones().get(i);
					destino = destinoS;
					origen = origenS;
					patente = pantente;
					distancia = distanciad;
					selectChofer(response, camion);
					break;
				}
				else {
					if(sl.getCamiones().size() == (i-1)) {
						request.getSession().setAttribute("ErrorCamion", "La patente no existe. Ingresela de nuevo");
						request.getRequestDispatcher("Administrador.jsp").forward(request, response);
					}
				}
			}		
			
		}
	}
	
	
	
	public void selectChofer(HttpServletResponse response, Camion camion) throws IOException {
		PrintWriter out = response.getWriter();
		IOGeneral io = new IOGeneral();
		List <Chofer> choferesDisponibles = new ArrayList<Chofer>();
		for(int i=0; i<sl.getChoferes().size(); i++) {
			boolean cumple = io.pesoCorrecto(camion.getCarga(), sl.getChoferes().get(i).getCategoria());
			if(cumple == true) {
				choferesDisponibles.add(sl.getChoferes().get(i));
			}
		}
		out.print("<form action=\"servletAdmin\" method=\"get\">");
		out.print("<label name=\"lblCamion\">Seleccione un chofer para el camion: " + camion.getDominio() + "</label>");
		out.print("<br>");
		out.print("<select name=\"selectChofer\"  id=\"selectChofer\" required>");
		for(Chofer c: choferesDisponibles) {
		out.print("<option>" + c.getDni() + "</option>");
		}
		out.print("</select>");
		out.print("<input type=\"submit\" name=\"btnEnviarChof\" value=\"Seleccionar chofer\">");
		out.print("</form>");
}

}
