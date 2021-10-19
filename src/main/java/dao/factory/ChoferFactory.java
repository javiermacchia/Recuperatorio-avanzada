package dao.factory;

import dao.impl.ChoferImpl;
import dao.interfaces.ChoferDAO;

public class ChoferFactory {

	public static ChoferDAO getImplementation(String source) {
		if (source.equals("BD")) {
			return new ChoferImpl();
		}
		return null;
	}
	
}
