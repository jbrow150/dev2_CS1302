package edu.westga.cs1302.Lab3.views;

import edu.westga.cs1302.Lab3.model.Bill;
import edu.westga.cs1302.Lab3.model.BillItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

	@FXML
	private TextField amount;

	@FXML
	private TextField name;

	@FXML
	private TextArea output;

	private Bill billTotal;

	public void initialize() {
		this.billTotal = new Bill();	
		}

	@FXML
	void addItem(ActionEvent event) {
		String itemName = name.getText().trim();
		String amountText = amount.getText().trim();

		if (itemName.isEmpty()) {
			this.output.setText("Item name cannot be empty.");
			return;
		}

		if (!amountText.matches("\\d+(\\.\\d+)?")) {
			this.output.setText("Amount must be a valid positive number.");
			return;
		}

		double itemAmount = Double.parseDouble(amountText);
		if (itemAmount <= 0) {
			this.output.setText("Amount must be greater than zero.");
			return;
		}

		BillItem item = new BillItem(itemName, itemAmount);
		this.billTotal.addItem(item);

		String billText = new BillView().getText(this.billTotal);
		this.output.setText(billText);

		name.clear();
		amount.clear();
}
}
