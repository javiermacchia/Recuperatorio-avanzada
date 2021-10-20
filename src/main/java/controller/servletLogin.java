package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Camion;
import dominio.Chofer;
import dominio.Usuario;
import dominio.Viaje;
import dao.impl.UsuarioImpl;
import dao.impl.ViajeImpl;


@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Usuario us = new Usuario();
	private static Chofer ch = new Chofer();
	private static List <Camion> camionesList = new ArrayList<Camion>();
	private static List <Chofer> choferesList = new ArrayList<Chofer>();   
	private static List <Viaje> viajesList = new ArrayList<Viaje>();


    public servletLogin() {
        super();
      
        // TODO Auto-generated constructor stub
    }
    public Usuario getUsuario() {
    	return us;
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String salir = request.getParameter("salir");
    	if (salir!=null) {
    	desconectar(request, response);
    	} else {
    		response.sendRedirect("Administrador.jsp");
    	}
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		UsuarioImpl usD = new UsuarioImpl();
		String nombreUs = request.getParameter("usuario");
		String clave = request.getParameter("password");
		us = usD.BuscarUsuarios(nombreUs, clave);
		if (us != null && us.getPerfil().equals("administrador")) {
			agregarCookies(response, nombreUs, clave);
			response.sendRedirect("Administrador.jsp");
		} else if (us != null && us.getPerfil().equals("Chofer")){
			ViajeImpl chi = new ViajeImpl();
			List <Viaje> aux = chi.buscarViajesChoferID(us.getIdUsuario());
			agregarCookies(response, nombreUs, clave);
			for (int i=0; i<aux.size();i++) {
			ch.getListaViaje().add(aux.get(i));
			}
			response.sendRedirect("Chofer.jsp");
		} else {
			response.sendRedirect("loginError.jsp");
		}
	}
	
	public void agregarCookies(HttpServletResponse response, String nombreUs, String clave) throws UnsupportedEncodingException{
    Cookie cookieUs = new Cookie("usuario", URLEncoder.encode(nombreUs, "UTF-8"));
	Cookie cookieClave = new Cookie("claveUsuario", URLEncoder.encode(clave, "UTF-8"));
	cookieUs.setMaxAge(365*24*60*60);
	cookieClave.setMaxAge(365*24*60*60);
	response.addCookie(cookieUs);
	response.addCookie(cookieClave);
    }
	
	
	 public void desconectar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 	us=null;
		 	ch=null;
		 	viajesList=null;
		 	choferesList=null;
		 	camionesList=null;
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for(Cookie c: cookies) { //Borra todas las cookies
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
            response.sendRedirect("login.jsp");
    }
	 

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	public void menuChofer(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public void menuAdmin(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
    public List <Camion> getCamiones(){
    	return camionesList;
    }
    
    public List <Chofer> getChoferes(){
    	return choferesList;
    }
    
    public List <Viaje> getViajes(){
    	return viajesList;
    }
	public static Chofer getCh() {
		return ch;
	}
	public static void setCh(Chofer ch) {
		servletLogin.ch = ch;
	}
	
	
}
