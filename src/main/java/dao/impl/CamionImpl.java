package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.util.IOGeneral;
import dao.factory.ViajeFactory;
import dao.interfaces.CamionDAO;
import dao.interfaces.ViajeDAO;
import dominio.Camion;
import dominio.Chofer;

public class CamionImpl implements CamionDAO{

	public int obtenerUltId() {
		Connection con = null;
		Statement state = null;
		try {
			con = Conexion.getConnection();
			state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT TOP (1) * from camion order by idCamion desc");
			int ultId = 0;
			if(rs.next()) {
				ultId = rs.getInt(1);
			}
			state.close();
			return ultId;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void eliminarCamion(int id) {
		Connection con = null;
		Statement state = null;
		try {
			con = Conexion.getConnection();
			state = con.createStatement();
			state.executeQuery("DELETE FROM camion WHERE idCamion = " + id);
			state.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int buscarCamionPatente(String patente) {
		Connection con = null;
		Statement state = null;
		Chofer c = null;
		IOGeneral io = new IOGeneral();
		try {
			int id = 0;
			con = Conexion.getConnection();
			state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * from camion WHERE dominio = '" + patente + "'");
			if(rs.next()) {
				id = rs.getInt(1);
				return id;
			}
			state.close();
			return id;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void updateViajeCamion(int idCamion, int idViaje) {
		Connection con = null;
		Statement state = null;
		try {
			con = Conexion.getConnection();
			state = con.createStatement();
			state.executeUpdate("UPDATE camion SET idViaje=" + idViaje +" WHERE idCamion="+ idCamion + "");
//			state.setInt(1, idViaje);
//			state.setInt(2, idCamion);
//			state.executeUpdate();
			state.close();
			return;
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public void updateCamion(Camion c) {
		Connection con = null;
		Statement state = null;
		try {
			con = Conexion.getConnection();
			state = con.createStatement();
			ViajeDAO viajedao = ViajeFactory.getImplementation("BD");
			int idViaje = 0;
			idViaje = viajedao.buscarViajeCamion(c.getIdCamion());
			
			state.executeUpdate("UPDATE camion SET marca='"+ c.getMarca() +"', modelo='" + c.getModelo() +
					"', dominio='"+ c.getDominio() +"', capacidad= '" + c.getCarga() +
					"', tanque= '"+ c.getTanque() +"', consumo= '" + c.getConsumo() + 
					"', idViaje='"+ idViaje +"' WHERE idCamion=" + c.getIdCamion() +"");
			state.close();
			return;
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
	
	}
	
	public void agregarCamion(Camion c) {
		Connection con = null;
		Statement state = null;
		try {
			con = Conexion.getConnection();
			state = con.createStatement();
			state.executeUpdate("INSERT INTO camion VALUES ('" + c.getIdCamion() + "' , '" + c.getMarca() + "' , '"
			+ c.getModelo()	+ "' , '" + c.getDominio() + "' , '" + c.getCarga() 
			+ "' , '" + c.getTanque() + "' , '" + c.getConsumo() + "' , '" +  "3"  + "')");
			state.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return;			
		}
	}
	
	public Camion buscarCamion(int id) {
		Connection con = null;
		Statement state = null;
		Camion c = null;
		try {
			con = Conexion.getConnection();
			state = con.createStatement();
			
			//prep = con.prepareStatement("SELECT * from Camiones WHERE camionID = " + id);
			ResultSet rs = state.executeQuery("SELECT * from camion WHERE idCamion =" + id);
			if(rs.next()) {
				String marca = rs.getString(2);
				String modelo = rs.getString(3);
				String patente = rs.getString(4);
				double capacidad = rs.getDouble(5);
				double tanque = rs.getDouble(6);
				double consumo = rs.getDouble(7);
				c = new Camion(id, marca, modelo, patente, capacidad, tanque, consumo, null);// Se puede cambiar el viaje?
			}
			state.close();
			return c;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List <Camion> cargarCamionesSinViaje(){
		Connection con = null;
		//PreparedStatement prep = null;
		Statement state = null;
		List <Camion> listCamion = new ArrayList<Camion>();
		try {
			con = Conexion.getConnection();
			state = con.createStatement();
			//con.prepareStatement("SELECT * from Camiones WHERE viajeID = 0"); //O sea estan vacios
			ResultSet rs = state.executeQuery("SELECT * from camion WHERE idViaje = 0"); //O sea estan vacios
			while(rs.next()) {
				int idCamion = rs.getInt(1);
				String marca = rs.getString(2);
				String modelo = rs.getString(3);
				String patente = rs.getString(4);
				double capacidad = rs.getDouble(5);
				double tanque = rs.getDouble(6);
				double consumo = rs.getDouble(7);
				listCamion.add(new Camion(idCamion, marca, modelo, patente, capacidad, tanque, consumo, null));
			}
			state.close();
			return listCamion;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;					
		}
	}
	
	
	
}
