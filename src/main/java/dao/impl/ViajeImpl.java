package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import controller.util.IOGeneral;
import dao.interfaces.ViajeDAO;
import dominio.Camion;
import dominio.Chofer;
import dominio.Viaje;

public class ViajeImpl implements ViajeDAO{

	public int obtenerUltId() throws Exception {
		Connection con = null;
		PreparedStatement prep = null;
		con = Conexion.getConnection();
		prep = con.prepareStatement("SELECT TOP (1) * from Viajes order by ViajeID desc");
		ResultSet rs = prep.executeQuery();
		int ultId = 0;
		if(rs.next())
			ultId = rs.getInt(1);
		prep.close();
		return ultId;
	}

	public void agregarViaje(Viaje v, long idChofer, int idCamion) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		con = Conexion.getConnection();
		ps = con.prepareStatement("insert into Viajes values(?,?,?,?,?,?,?,?,?)");
		ps.setInt(1, v.getIdViaje());
		ps.setString(2, v.getDestino());
		ps.setString(3, v.getOrigen());
		ps.setBoolean(4, false); //Al agregar un viaje el dato inicio y fin del transporte seran false
		ps.setBoolean(5, false);
		ps.setDouble(6, v.getTiempoViaje());
		ps.setDouble(7, v.getDistancia());
		ps.setLong(8, idChofer);
		ps.setInt(9, idCamion);
		ps.execute();
		ps.close();
		
	}



	
	public List <Viaje> buscarViajesChoferID(int idChofer){
		Connection con = null;
		PreparedStatement prep = null;		
		try {
			List <Viaje> viajeList = new ArrayList<Viaje>();
			con = Conexion.getConnection();
			prep = con.prepareStatement("SELECT * from Viajes WHERE ChoferId = " + idChofer);
			ResultSet rs = prep.executeQuery();			
			while(rs.next()) {
			int idViaje = rs.getInt(1);	
			String destino = rs.getString(2);
			String origen = rs.getString(3);
			boolean inicio = rs.getBoolean(4);
			boolean finalizado = rs.getBoolean(5);
			double tiempoViaje = rs.getDouble(6);
			double distancia = rs.getDouble(7);
			viajeList.add(new Viaje(idViaje, destino, origen, inicio, finalizado, tiempoViaje, distancia));
			}
			prep.close();
			return viajeList;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateViaje(Viaje v, long idChofer, int idCamion) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			IOGeneral io = new IOGeneral();
			con = Conexion.getConnection();
			ps = con.prepareStatement("UPDATE Viajes SET Destino=?, Origen=?, Iniciado=?, Finalizado=?, tiempoViaje=?, Distancia=?, ChoferId=?, CamionId=? WHERE ViajeID=?");
			ps.setString(1, v.getDestino());
			ps.setString(2, v.getOrigen());
			ps.setBoolean(3, v.isInicio());
			ps.setBoolean(4, v.isFinalizado());
			ps.setDouble(5, v.getTiempoViaje());
			ps.setDouble(6, v.getDistancia());
			ps.setLong(7, idChofer);
			ps.setInt(8, idCamion);
			ps.setInt(9, v.getIdViaje());
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public void eliminarViaje(int id) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		con = Conexion.getConnection();
		ps = con.prepareStatement("DELETE FROM Viajes WHERE ViajeID = " + id);
		ps.execute();
		ps.close();					
	}
	

	public int buscarViajeChofer(int idViaje){
		Connection con = null;
		PreparedStatement prep = null;		
		try {
			List <Viaje> viajeList = new ArrayList<Viaje>();
			con = Conexion.getConnection();
			prep = con.prepareStatement("SELECT * from Viajes WHERE ViajeID = " + idViaje);
			ResultSet rs = prep.executeQuery();	
			int idChofer = 0;
			if(rs.next()) {
				idChofer = rs.getInt(8);
			}
			prep.close();
			return idChofer;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int buscarViajeCamion(int idViaje) {
		Connection con = null;
		PreparedStatement prep = null;	
		int idViajeCamion = 0;
		try {
			con = Conexion.getConnection();
			prep = con.prepareStatement("SELECT * from Viajes WHERE ViajeID = " + idViaje);
			ResultSet rs = prep.executeQuery();			
			if(rs.next()) { //If ya que solo tiene un viaje establecido el camion
				idViajeCamion = rs.getInt(9);
			}
			return idViajeCamion;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;			
		}
	}

	@Override
	public Viaje buscarViaje(int idViaje) throws Exception {
			Connection con = null;
			Viaje v = null;
			PreparedStatement prep = null;	
			con = Conexion.getConnection();
			prep = con.prepareStatement("SELECT * from Viajes WHERE ViajeID = " + idViaje);
			ResultSet rs = prep.executeQuery();			
			if(rs.next()) { //If ya que solo tiene un viaje establecido el camion
				String destino = rs.getString(2);
				String origen = rs.getString(3);
				boolean inicio = rs.getBoolean(4);
				boolean finalizado = rs.getBoolean(5);
				double tiempoViaje = rs.getDouble(6);
				double distancia = rs.getDouble(7);
				v = new Viaje(idViaje, destino, origen, inicio, finalizado, tiempoViaje, distancia);
			}
			con.close();
			return v;
	}



	@Override
	public List<Viaje> buscarViajesChofer(long l) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int buscarViajeChoferID(int idViaje) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
