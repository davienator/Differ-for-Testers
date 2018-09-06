package org.waes.differ.service;

import org.waes.differ.database.DatabaseMock;
import org.waes.differ.exceptions.DataNotBase64Exception;
import org.waes.differ.exceptions.IdNotFoundException;
import org.waes.differ.model.Sides;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SidesService {

	private Map<Long, Sides> allSides = DatabaseMock.getAllSides();
	
	public List<Sides> getAllSides(){
		return new ArrayList<>(allSides.values());
	}
	
	public Sides getSidesById(long id){
		if (allSides.get(id) == null) {
			throw new IdNotFoundException("ID " + id + " not initialized.");
		}
		return allSides.get(id);
	}
	
	public Sides addSideValue(long id, String side, String value) {
		if (IsNotBase64String(value)) {
			throw new DataNotBase64Exception("Data in body not Base64 formatted.");
		}
		if (allSides.get(id) == null) {
			Sides sides = new Sides();
			sides.updateSides(side, value);
			allSides.put(id, sides);
		}
		else {
			allSides.get(id).updateSides(side, value);
		}
		return allSides.get(id);
	}
	
	public Boolean IsNotBase64String(String inputString)
    {
		if (0 < inputString.length() % 4) {
			return true;
		} else {
	        Pattern p = Pattern.compile("[^A-Za-z0-9]|=[^=]|={3,}$");
	        Matcher m = p.matcher(inputString);
	        return m.find();
		}
    }
}
