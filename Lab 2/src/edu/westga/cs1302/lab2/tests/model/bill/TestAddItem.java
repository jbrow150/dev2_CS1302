package edu.westga.cs1302.lab2.tests.model.bill;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.Bill;

class TestAddItem {

	@Test
	void testAddItemNull() {
		//arrange
		Bill bill = new Bill();
		//act
		bill.addItem(null);
		//assert
		assertThrows(IllegalArgumentException.class, () ->  { bill.addItem(null); }); 
		
	}

}
