package edu.westga.cs1302.bill.test.model.billCalculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

class TestCalculator {

	@Test
	void testBillSubtotalWithValidItems() {
		BillItem[] items = { new BillItem("Burger", 10.00), new BillItem("Fries", 5.00) };
		assertEquals(15.00, BillCalculator.billSubtotal(items));
	}

	@Test
	void testBillTax() {
		BillItem[] items = { new BillItem("Burger", 10.00) };
		assertEquals(1.00, BillCalculator.billTax(items));
	}

	@Test
	void testBillTip() {
		BillItem[] items = { new BillItem("Burger", 10.00) };
		assertEquals(2.00, BillCalculator.billTip(items));
	}

	@Test
	void testBillTotal() {
		BillItem[] items = { new BillItem("Burger", 10.00) };
		assertEquals(13.00, BillCalculator.billTotal(items));
	}

	@Test
	void testNullArrayThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			BillCalculator.billSubtotal(null);
		});
	}

	@Test
	void testArrayWithNullItemThrowsException() {
		BillItem[] items = { new BillItem("Burger", 10.00), null };
		assertThrows(IllegalArgumentException.class, () -> {
			BillCalculator.billSubtotal(items);
		});

	}
}
