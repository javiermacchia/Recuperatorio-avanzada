package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.factory.CamionFactory;
import dao.factory.ViajeFactory;
import dao.interfaces.CamionDAO;
import dao.interfaces.ViajeDAO;
import dominio.Viaje;



	/**
	 * Servlet implementation class servletChofer
	 */
	@WebServlet("/servletChofer")
	public class servletChofer extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private static Viaje viajeAux = new Viaje();
		private static servletLogin sl = new servletLogin();
		
		public void setViaje(Viaje viaje) {
			viajeAux = viaje;
		}
		
		public Viaje getViaje() {
			return viajeAux;
		}
		
	    public servletChofer() {
	        super();
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String inicioViajeS = request.getParameter("inicioViaje");
			String finViajeS = request.getParameter("finViaje");
			boolean finViaje = false;
			boolean inicioViaje = false;
			if(inicioViajeS != null) {
				inicioViaje = true;
			}
			if(finViajeS != null) {
				finViaje = true;
			}
			viajeAux.setInicio(inicioViaje);
			viajeAux.setFinalizado(finViaje);
			ViajeDAO viajedao = ViajeFactory.getImplementation("BD");
			try {
				int idCamion = viajedao.buscarViajeCamion(viajeAux.getIdViaje());
				viajedao.updateViaje(viajeAux, sl.getUsuario().getIdUsuario(), idCamion);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			viajeAux = null;
			sl.menuChofer(response);
			//doGet(request, response);
		}

	}

