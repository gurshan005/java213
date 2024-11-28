package cp213;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author Gurshan Bhogal 
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-10-15
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {

	    // your code here
		
		try {
            // Create a PrinterJob instance
            PrinterJob job = PrinterJob.getPrinterJob();

            // Set the Order object as the printable content
            job.setPrintable(order);

            // Show print dialog to the user
            if (job.printDialog()) {
                // Start the print process
                job.print();
                JOptionPane.showMessageDialog(null, "Order printed successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Print job cancelled.");
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, "Failed to print the order: " + ex.getMessage());
        }
    }
}
		
	

	

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {
    	private final MenuItem item;

        QuantityListener(final MenuItem item) {
            this.item = item;
        }

        @Override
        public void focusGained(FocusEvent e) {
            // No action required on focus gained
        }

        @Override
        public void focusLost(FocusEvent e) {
            JTextField source = (JTextField) e.getSource();
            try {
                int quantity = Integer.parseInt(source.getText());
                if (quantity < 0) {
                    throw new NumberFormatException();
                }
                order.update(item, quantity); // Update order with valid quantity
            } catch (NumberFormatException ex) {
                source.setText("0"); // Reset invalid input to 0
                order.update(item, 0); // Remove the item from the order
            }
            // Update totals after quantity change
            refreshDisplay();
        }
    }
    

    // Attributes
    private Menu menu = null;
    private final Order order = new Order();
    private final DecimalFormat priceFormat = new DecimalFormat("$##0.00");
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     * @return 
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Uses the GridLayout to place the labels and buttons.
     */
    private void layoutView() {

	// your code here
    	this.setLayout(new GridBagLayout()); // Use GridBagLayout for precise control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Column Titles
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
        this.add(new JLabel("Item", JLabel.CENTER), gbc);

        gbc.gridx = 1;
        this.add(new JLabel("Price", JLabel.CENTER), gbc);

        gbc.gridx = 2;
        this.add(new JLabel("Quantity", JLabel.CENTER), gbc);

        // Menu Items
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);

            // Item Name
            gbc.gridx = 0; gbc.gridy = i + 1; gbc.weightx = 1.0;
            nameLabels[i] = new JLabel(item.getListing(), JLabel.LEFT);
            this.add(nameLabels[i], gbc);

            // Item Price
            gbc.gridx = 1;
            priceLabels[i] = new JLabel(priceFormat.format(item.getPrice()), JLabel.RIGHT);
            this.add(priceLabels[i], gbc);

            // Quantity Field
            gbc.gridx = 2;
            quantityFields[i] = new JTextField("0", 5);
            quantityFields[i].setHorizontalAlignment(JTextField.RIGHT); // Align text to the right
            this.add(quantityFields[i], gbc);
        }

        // Subtotal, Taxes, and Total Labels
        gbc.gridy = menu.size() + 1;
        gbc.gridx = 0; gbc.gridwidth = 2;
        this.add(new JLabel("Subtotal:"), gbc);

        gbc.gridx = 2; gbc.gridwidth = 1;
        this.add(subtotalLabel, gbc);

        gbc.gridy++;
        gbc.gridx = 0; gbc.gridwidth = 2;
        this.add(new JLabel("Taxes:"), gbc);

        gbc.gridx = 2; gbc.gridwidth = 1;
        this.add(taxLabel, gbc);

        gbc.gridy++;
        gbc.gridx = 0; gbc.gridwidth = 2;
        this.add(new JLabel("Total:"), gbc);

        gbc.gridx = 2; gbc.gridwidth = 1;
        this.add(totalLabel, gbc);

        // Print Button
        gbc.gridy++;
        gbc.gridx = 0; gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(printButton, gbc);
    }

    private void refreshDisplay() {
        subtotalLabel.setText("Subtotal: " + priceFormat.format(order.getSubTotal()));
        taxLabel.setText("Taxes: " + priceFormat.format(order.getTaxes()));
        totalLabel.setText("Total: " + priceFormat.format(order.getTotal()));
    }
    

    


	/**
     * Register the widget listeners with the widgets.
     */
    private void registerListeners() {
	// Register the PrinterListener with the print button.
	this.printButton.addActionListener(new PrintListener());

	// your code here
	this.printButton.addActionListener(new PrintListener());

    // Register QuantityListener with each quantity field
    for (int i = 0; i < quantityFields.length; i++) {
        quantityFields[i].addFocusListener(new QuantityListener(menu.getItem(i)));
    }

    
}
}