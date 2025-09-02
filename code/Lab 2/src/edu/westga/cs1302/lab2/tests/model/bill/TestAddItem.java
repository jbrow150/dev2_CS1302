package edu.westga.cs1302.lab2.tests.model.bill;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;

class TestAddItem {

	@Test
	void testAddItemNull() {
		//arrange
		Bill bill = new Bill();
		
		 // assert + act
        assertThrows(IllegalArgumentException.class, () -> {
            bill.addItem(null);
        });
		
	}
	
	@Test
	void testAddItemSingle() {
		  // arrange
        Bill bill = new Bill();
        BillItem item = new BillItem("Burger", 10.50);

        // act
        bill.addItem(item);

        // assert
        assertEquals(1, bill.getItems().size());
        assertSame(item, bill.getItems().get(0));
		}
	
	@Test
	void testAddItemmultiple() {
		  // arrange
        Bill bill = new Bill();
        BillItem item1 = new BillItem("Burger", 10.50);
        BillItem item2= new BillItem("Fries", 5.25);

        // act
        bill.addItem(item1);
        bill.addItem(item2);

        // assert
        assertEquals(2, bill.getItems().size());
        assertSame(item1, bill.getItems().get(0));
        assertSame(item2, bill.getItems().get(1));
		} 
	}

