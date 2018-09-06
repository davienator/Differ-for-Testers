package org.waes.differ;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.waes.differ.model.Sides;
import org.waes.differ.service.SidesService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SidesServiceTest {

	@Test
	public void test1AddSideValue() {
		SidesService tester = new SidesService();
		assertEquals("Value 12345678 should be added to 1/left.",
				"12345678", tester.addSideValue(1, "left", "12345678").getLeft());
		assertEquals("Value abcdefgh should be added to 1/right.",
				"abcdefgh", tester.addSideValue(1, "right", "abcdefgh").getRight());
	}
	
	@Test
	public void test2GetSidesById() {
		SidesService tester = new SidesService();
		Sides sidesCheck = new Sides("12345678", "abcdefgh");
		assertEquals("Existent sides left values should be returned by ID.",
				sidesCheck.getLeft(), tester.getSidesById(1).getLeft());
		assertEquals("Existent sides right values should be returned by ID.",
				sidesCheck.getRight(), tester.getSidesById(1).getRight());
	}

	@Test
	public void test3UpdateSideValue() {
		SidesService tester = new SidesService();
		assertEquals("Value 09876543 should be updated to 1/left.",
				"09876543", tester.addSideValue(1, "left", "09876543").getLeft());
		assertEquals("Value ijklmnop should be updated to 1/right.",
				"ijklmnop", tester.addSideValue(1, "right", "ijklmnop").getRight());
	}
	
	@Test
	public void test4IsNotBase64String() {
		SidesService tester = new SidesService();
		assertEquals("Value 1234 is Base64", false, tester.IsNotBase64String("1234"));
		assertEquals("Value 1 is not Base64", true, tester.IsNotBase64String("1"));
		assertEquals("Value 123 is not Base64", true, tester.IsNotBase64String("123"));
		assertEquals("Value 12345 is not Base64", true, tester.IsNotBase64String("12345"));
		assertEquals("Value 1-34 is not Base64", true, tester.IsNotBase64String("1-34"));
		assertEquals("Value 1\34 is not Base64", true, tester.IsNotBase64String("1\34"));
		assertEquals("Value 1*34 is not Base64", true, tester.IsNotBase64String("1*34"));
		assertEquals("Value 1 34 is not Base64", true, tester.IsNotBase64String("1 34"));
	}


}
