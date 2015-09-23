package edu.nesterenko.touroperator.logic;

import java.util.regex.Pattern;

import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.dao.ResortHotelDao;
import edu.nesterenko.touroperator.entity.Resort;
import edu.nesterenko.touroperator.entity.ResortHotel;
import edu.nesterenko.touroperator.resource.RegexManager;

public class ResortHotelLogic {
	
	private ResortHotelLogic() {}
	
	public static void addResortHotel(String name, String description, 
			int resortId, int stars) throws LogicException {
		if(name == null || name.isEmpty()) {
			throw new LogicException("name is empty");
		}
		if((description == null || description.isEmpty()) || Pattern.matches(
				RegexManager.getProperty("pattern.onlylitter"), 
				name)) {
			ResortHotelDao resortHotelDao = new ResortHotelDao();
			ResortHotel resortHotel = new ResortHotel(0, name, description, 
					new Resort(resortId, null, null, null), stars);
			try {
				resortHotelDao.add(0, resortHotel);
			} catch (DaoException e) {
				throw new LogicException(e);
			}
		} else {
			throw new LogicException("Bad params");
		} 	
	}
}
