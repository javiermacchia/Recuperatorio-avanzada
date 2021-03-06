package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import controller.util.IOGeneral;
import dao.factory.UsuarioFactory;
import dao.factory.ViajeFactory;
import dao.interfaces.ChoferDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.ViajeDAO;
import dominio.Chofer;
import dominio.Usuario;
import dominio.Viaje;

public class ChoferImpl implements ChoferDAO{

	public int obtenerUltId() {
		Connection con = null;
		PreparedStatement prep = null;
		try {
			con = Conexion.getConnection();
			prep = con.prepareStatement("SELECT TOP (1) * from chofer order by dni desc");
			ResultSet rs = prep.executeQuery();
			int ultId = 0;
			if(rs.next()) {
				ultId = rs.getInt(1);
			}
			prep.close();
			return ultId;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int buscarChoferDNI(long dni) {
		Connection con = null;
		PreparedStatement prep = null;
		Chofer c = null;
		IOGeneral io = new IOGeneral();
		try {
			int id = 0;
			con = Conexion.getConnection();
			prep = con.prepareStatement("SELECT * from chofer WHERE dni = " + dni);
			ResultSet rs = prep.executeQuery();
			if(rs.next()) {
				id = rs.getInt(1);
				return id;
			}
			prep.close();
			return id;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public Chofer buscarChofer(long id) {
		Connection con = null;
		PreparedStatement prep = null;
		Chofer c = null;
		IOGeneral io = new IOGeneral();
		try {
			con = Conexion.getConnection();
			prep = con.prepareStatement("SELECT * from chofer WHERE dni = " + id);
			ResultSet rs = prep.executeQuery();
			if(rs.next()) {
				UsuarioDAO usdao = UsuarioFactory.getImplementation("BD");
				Usuario us = usdao.buscarUsuarioID(id);
				String nombre = rs.getString(1);
				String apellido = rs.getString(2);
				long dni = rs.getLong(3);
				String fechaNacS = rs.getString(4);
				Calendar fechaNac = io.convertirFechaStringaCalendar(fechaNacS);
				String categoria = rs.getString(5);
				int movil = rs.getInt(6); 
				//List <Viaje> viajes = new ArrayList<Viaje>(); //Por el momento no necesitamos los viajes del chofer
				//ViajeDAO vidao = ViajeFactory.getImplementation("BD");
				//viajes = vidao.buscarViajesChofer(id);
				c = new Chofer(id, us.getClave(), us.getNombreUsuario(), "Chofer", nombre, apellido, dni, fechaNac, categoria, movil, null); //Los datos de viajes no se necesitan por ahora
			}
			prep.close();
			return c;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateChofer(Chofer c) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			IOGeneral io = new IOGeneral();
			con = Conexion.getConnection();
			ps = con.prepareStatement("UPDATE chofer SET nombre=?, apellido=?, documento=?, fechaNac=?, Categoria=?, Movil=? WHERE dni=?");
			ps.setString(1, c.getNombre());
			ps.setString(2, c.getApellido());
			ps.setLong(3, c.getDni());
			String fecha = io.convertirFechaCalendaraString(c.getFechaNac());
			ps.setString(4, fecha);
			ps.setString(5, c.getCategoria());
			ps.setInt(6, c.getMovil());
			ps.setLong(7, c.getIdUsuario());
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public void agregarChofer(Chofer c) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Calendar cal = c.getFechaNac(); //This to obtain today's date in our Calendar var.

			java.sql.Date date = new Date (cal.getTimeInMillis());
			
			IOGeneral io = new IOGeneral();
			con = Conexion.getConnection();
			ps = con.prepareStatement("insert into chofer values(?,?,?,?,?,?)");
			ps.setString(1, c.getNombre());
			ps.setString(2, c.getApellido());
			ps.setLong(3, c.getIdUsuario());
			ps.setDate(4, (date));
			ps.setString(5, c.getCategoria());
			ps.setInt(6, c.getMovil());
			ps.execute();
			
			ps.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return;			
		}
	}
	
	public List <Chofer> cargarTodoChoferes() {
		Connection con = null;
		PreparedStatement prep = null;
		List <Chofer> choferes = new ArrayList<Chofer>();
		IOGeneral io = new IOGeneral();
		try {
			con = Conexion.getConnection();
			prep = con.prepareStatement("SELECT * from chofer");
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int idChofer = rs.getInt(1);
				UsuarioDAO usdao = UsuarioFactory.getImplementation("BD");
				Usuario us = usdao.buscarUsuarioID(idChofer);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				long dni = rs.getLong(4);
				String fechaNacS = rs.getString(5);
				Calendar fechaNac = io.convertirFechaStringaCalendar(fechaNacS);
				String categoria = rs.getString(6);
				int movil = rs.getInt(7); 
				List <Viaje> viajes = new ArrayList<Viaje>();
				ViajeDAO vidao = ViajeFactory.getImplementation("BD");
				viajes = vidao.buscarViajesChofer(idChofer);
				choferes.add(new Chofer(idChofer, us.getClave(), us.getNombreUsuario(), us.getPerfil(), nombre, apellido, dni, fechaNac, categoria, movil, viajes));
				
			}
			prep.close();
			return choferes;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void eliminarChofer(long id) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			con = Conexion.getConnection();
			ps2 = con.prepareStatement("UPDATE camion SET idChofer = '0' Where idChofer = '"+id+"'");
			ps = con.prepareStatement("DELETE FROM chofer WHERE dni = " + id);
			ps2.execute();
			ps2.close();
			ps.execute();
			ps.close();
			UsuarioDAO usDAO = UsuarioFactory.getImplementation("BD");
			usDAO.eliminarUsuario(id);
		}
		catch(Exception e) {
			System.out.println("NO EXISTE EL USUARIO");
			e.printStackTrace();
		}
	}

}
