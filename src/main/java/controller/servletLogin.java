package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Camion;
import dominio.Chofer;
import dominio.Usuario;
import dao.impl.UsuarioImpl;


@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Usuario us = new Usuario();
	private static Camion ca = new Camion();

    public servletLogin() {
        super();
      
        // TODO Auto-generated constructor stub
    }
    public Usuario getUsuario() {
    	return us;
    }
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Usuario us = new Usuario();
		UsuarioImpl usD = new UsuarioImpl();
		us = usD.BuscarUsuarios(request.getParameter("usuario"), request.getParameter("password"));
		if (us != null && us.getPerfil().equals("administrador")) {
			response.sendRedirect("Administrador.jsp");
		} else if (us != null && us.getPerfil().equals("Chofer")){
			response.sendRedirect("Chofer.jsp");
		} else {
			response.sendRedirect("loginError.jsp");
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	public void menuChofer(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	public List<Chofer> getChoferes() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Camion> getCamiones() {
		// TODO Auto-generated method stub
		return null;
	}
	public void menuAdmin(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	
}
