package dao.impl;

import java.sql.Connection;
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
			prep = con.prepareStatement("SELECT TOP (1) * from Choferes order by IdUsuario desc");
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
			prep = con.prepareStatement("SELECT * from Choferes WHERE documento = " + dni);
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
	
	
	public Chofer buscarChofer(int id) {
		Connection con = null;
		PreparedStatement prep = null;
		Chofer c = null;
		IOGeneral io = new IOGeneral();
		try {
			con = Conexion.getConnection();
			prep = con.prepareStatement("SELECT * from Choferes WHERE IdUsuario = " + id);
			ResultSet rs = prep.executeQuery();
			if(rs.next()) {
				UsuarioDAO usdao = UsuarioFactory.getImplementation("BD");
				Usuario us = usdao.buscarUsuarioID(id);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				long dni = rs.getLong(4);
				String fechaNacS = rs.getString(5);
				Calendar fechaNac = io.convertirFechaStringaCalendar(fechaNacS);
				String categoria = rs.getString(6);
				int movil = rs.getInt(7); 
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
			ps = con.prepareStatement("UPDATE Choferes SET nombre=?, apellido=?, documento=?, fechaNac=?, Categoria=?, Movil=? WHERE IdUsuario=?");
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
			IOGeneral io = new IOGeneral();
			con = Conexion.getConnection();
			ps = con.prepareStatement("insert into Choferes values(?,?,?,?,?,?,?)");
			ps.setLong(1, c.getIdUsuario());
			ps.setString(2, c.getNombre());
			ps.setString(3, c.getApellido());
			ps.setLong(4, c.getDni());
			ps.setString(5, io.convertirFechaCalendaraString(c.getFechaNac()));
			ps.setString(6, c.getCategoria());
			ps.setInt(7, c.getMovil());
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
			prep = con.prepareStatement("SELECT * from Choferes");
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
	public void eliminarChofer(int id) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement("DELETE FROM Choferes WHERE IdUsuario = " + id);
			ps.execute();
			ps.close();
			UsuarioDAO usDAO = UsuarioFactory.getImplementation("BD");
			usDAO.eliminarUsuario(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
