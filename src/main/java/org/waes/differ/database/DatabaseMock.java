package org.waes.differ.database;

import org.waes.differ.model.Sides;

import java.util.HashMap;
import java.util.Map;

/*
 * This is a class in place of the database where production
 * data would be stored.
 */
public class DatabaseMock {

	private static Map<Long, Sides> allSides = new HashMap<>();
	
	public static Map<Long, Sides> getAllSides(){
		return allSides;
	}
}
