package dao.factory;

import dao.impl.CamionImpl;
import dao.interfaces.CamionDAO;

public class CamionFactory {

	public static CamionDAO getImplementation(String source) {
		if (source.equals("BD")) {
			return new CamionImpl();
		}
		return null;
	}
}
