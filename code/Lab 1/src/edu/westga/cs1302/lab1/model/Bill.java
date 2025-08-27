package edu.westga.cs1302.lab1.model;

import java.util.ArrayList;

/** Stores information for a bill.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Bill {
	private ArrayList<BillItem> items;

	/**
	 * Create a new empty Bill
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public Bill() {
		this.items = new ArrayList<BillItem>();
	}

	/**
	 * Adds the item to the bill
	 * 
	 * @precondition item != null
	 * @postcondition item is added to the list of items in the bill
	 * 
	 * @param item the item to be added to the bill
	 */
	public void addItem(BillItem item) {
		if (item == null) {
			throw new IllegalArgumentException("item must not be null.");
		}
		this.items.add(item);
	}

	/**
	 * Returns an unmodifiable list of the bill's items
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a list of bill items
	 */
	public List<BillItem> getItems() {
		return Collections.unmodifiableList(this.items);
	}

}
