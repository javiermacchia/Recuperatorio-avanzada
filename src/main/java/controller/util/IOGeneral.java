package controller.util;

import java.util.Calendar;

public class IOGeneral {

	public Calendar convertirFechaStringaCalendar(String fechaS) {
		//System.out.println(fechaS);
		Calendar fecha = Calendar.getInstance();
		String split[] = fechaS.split("-");
		fecha.set(Integer.parseInt(split[2]), (Integer.parseInt(split[1])-1), Integer.parseInt(split[0]));
		return fecha;
	}
	
	public String convertirFechaCalendaraString(Calendar fecha) {
		String fechaS = "";
		return fechaS = fecha.get(Calendar.DATE) + "-" + (fecha.get(Calendar.MONTH)+1) + "-" + fecha.get(Calendar.YEAR);
	}
	
	public boolean pesoCorrecto(double peso, String categoria) {
		if(categoria.equals("C1") && (peso <= 12000)) {
			return true;
		}
		else if(categoria.equals("C2") && (peso <= 24000) ) {
			return true;
		}
		else if(categoria.equals("C3") && (peso > 24000) ) {
			return true;
		}
		else {
			return false;
		}
	}
}
