package edu.westga.cs1302.bill.model;

/**
 * Stores information for a bill.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class BillCalculator {

	/**
	 * Calculate a new Bill
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */

	/**
	 * Calculates the subtotal for the given list of BillItem objects.
	 * 
	 * @precondition items != null && no element in items is null
	 * @postcondition none
	 * 
	 * @param items the array of BillItem objects
	 * @return the subtotal
	 */
	public static double billSubtotal(BillItem[] items) {
		if (items == null) {
			throw new IllegalArgumentException("Items array must not be null.");
		}

		double subtotal = 0;
		for (BillItem item : items) {
			if (item == null) {
				throw new IllegalArgumentException("Item in items must not be null.");
			}
			subtotal += item.getAmount();
		}
		return subtotal;
	}

	/**
	 * Calculates the tax for the given items.
	 * 
	 * @precondition items != null && no element in items is null
	 * @postcondition none
	 * 
	 * @param items the array of BillItem objects
	 * @return the tax amount
	 */
	public static double billTax(BillItem[] items) {
		return billSubtotal(items) * Bill.TAX_RATE;
	}

	/**
	 * Calculates the tip for the given items.
	 * 
	 * @precondition items != null && no element in items is null
	 * @postcondition none
	 * 
	 * @param items the array of BillItem objects
	 * @return the tip amount
	 */
	public static double billTip(BillItem[] items) {
		return billSubtotal(items) * Bill.TIP_RATE;
	}

	/**
	 * Calculates the total (subtotal + tax + tip) for the given items.
	 * 
	 * @precondition items != null && no element in items is null
	 * @postcondition none
	 * 
	 * @param items the array of BillItem objects
	 * @return the total amount
	 */
	public static double billTotal(BillItem[] items) {
		return billSubtotal(items) + billTax(items) + billTip(items);
	}

}
