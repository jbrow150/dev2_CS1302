package edu.westga.cs1302.bill.view;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

/**
 * Supports displaying the information contained in a Bill.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class BillView {

	/**
	 * Return a String containing the list of bill items and total for the bill.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param bill the bill to be viewed
	 * 
	 * @return a String containing the list of bill items and total for the bill
	 */
	public static String getText(Bill bill) {
		String text = "ITEMS" + System.lineSeparator();
		BillItem[] itemsArray = bill.getItems().toArray(new BillItem[0]);

		for (BillItem item : itemsArray) {
			text += item.getName() + " - " + item.getAmount() + System.lineSeparator();
		}

		double subtotal = BillCalculator.billSubtotal(itemsArray);
		double tax = BillCalculator.billTax(itemsArray);
		double tip = BillCalculator.billTip(itemsArray);
		double total = BillCalculator.billTotal(itemsArray);

		text += System.lineSeparator();
		text += "SUBTOTAL - $" + roundToNearestHundredth(subtotal) + System.lineSeparator();
		text += "TAX - $" + roundToNearestHundredth(tax) + System.lineSeparator();
		text += "TIP - $" + roundToNearestHundredth(tip) + System.lineSeparator();
		text += "TOTAL - $" + roundToNearestHundredth(total);

		return text;
	}

	private static double roundToNearestHundredth(double value) {
		return (int) (value * 100) / 100.0;
	}
}
