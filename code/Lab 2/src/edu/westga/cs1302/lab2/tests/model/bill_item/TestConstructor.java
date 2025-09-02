package edu.westga.cs1302.lab2.tests.model.bill_item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.BillItem;

class TestConstructor {

	@Test
    void testValidConstructor() {
        // arrange
        String expectedName = "Pizza";
        double expectedAmount = 12.99;

        // act
        BillItem item = new BillItem(expectedName, expectedAmount);

        // assert
        assertEquals(expectedName, item.getName());
        assertEquals(expectedAmount, item.getAmount(), 0.0001);
    }
	
	 @Test
	    void testWhenConstructorNull() {
		 // Arrange
	        String name = null;
	        double amount = 10.00;

	        // Act & Assert
	        assertThrows(IllegalArgumentException.class, () -> {
	            new BillItem(name, amount);
	        });
	    }
	 
	 @Test
	    void testWhenConstructorNegative() {
		// Arrange
	        String name = "Burger";
	        double amount = -5.00;

	        // Act & Assert
	        assertThrows(IllegalArgumentException.class, () -> {
	            new BillItem(name, amount);
	        });
	    }
	 
	 @Test
	    void testWhenConstructorZeroAmount() {
	        // Arrange
	        String name = "Soda";
	        double amount = 0.00;

	        // Act & Assert
	        assertThrows(IllegalArgumentException.class, () -> {
	            new BillItem(name, amount);
	        });
	    }

}
