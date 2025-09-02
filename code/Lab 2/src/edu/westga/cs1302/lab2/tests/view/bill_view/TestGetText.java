package edu.westga.cs1302.lab2.tests.view.bill_view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;
import edu.westga.cs1302.lab2.view.BillView;

class TestGetText {

	 @Test
	    void testWhenListEmpty() {
	        // Arrange
	        Bill bill = new Bill();
	        BillView view = new BillView();
	        String newLine = System.lineSeparator();
	        
	        // Act
	        String result = view.getText(bill);
	        
	        // Assert
	        String expected = "ITEMS" + newLine + newLine +
	                          "SUBTOTAL - $0.0" + newLine +
	                          "TAX - $0.0" + newLine +
	                          "TIP - $0.0" + newLine +
	                          "TOTAL - $0.0";
	                          
	        assertEquals(expected, result);
	    }
	 
	 @Test
	    void testWhenTotalIsZero() {
	        // Arrange
	        Bill bill = new Bill();
	        BillView view = new BillView();
	        String newLine = System.lineSeparator();

	        // Act
	        String result = view.getText(bill);

	        // Assert
	        String expected = "ITEMS" + newLine + newLine +
	                          "SUBTOTAL - $0.0" + newLine +
	                          "TAX - $0.0" + newLine +
	                          "TIP - $0.0" + newLine +
	                          "TOTAL - $0.0";

	        assertEquals(expected, result);
	    }
	 
	 @Test
	    void testWhenValid() {
	        // Arrange
	        Bill bill = new Bill();
	        bill.addItem(new BillItem("Burger", 10.00));
	        bill.addItem(new BillItem("Fries", 5.00));
	        BillView view = new BillView();
	        String newLine = System.lineSeparator();

	        // Act
	        String result = view.getText(bill);

	        // Assert
	        double subTotal = 15.00;
	        double tax = subTotal * Bill.TAX_RATE; // 0.1
	        double tip = subTotal * Bill.TIP_RATE; // 0.2
	        double total = subTotal + tax + tip;

	        String expected = "ITEMS" + newLine +
	                          "Burger - 10.0" + newLine +
	                          "Fries - 5.0" + newLine + newLine +
	                          "SUBTOTAL - $" + subTotal + newLine +
	                          "TAX - $" + tax + newLine +
	                          "TIP - $" + tip + newLine +
	                          "TOTAL - $" + total;

	        assertEquals(expected, result);
	    }
}
