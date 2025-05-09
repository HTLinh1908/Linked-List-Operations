package com.linkedlistoperations.view;

import com.linkedlistoperations.controller.ListController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel<T> extends JPanel {
    private final JTextField inputField;
    private final JButton insertFrontButton;
    private final JButton insertEndButton;
    private final JButton removeValueButton;
    private final JButton searchButton;
    private final JButton reverseButton;
    private final JButton middleButton;
    private final JButton detectCycleButton;
    private final JButton clearButton;
    private final JButton addElementButton;
    private final ListController<T> controller;  // Using generic T
    private final ListPanel<T> listPanel;  // Using generic T

    public ControlPanel(ListController<T> controller, ListPanel<T> listPanel) {  // Using generic T
        this.controller = controller;
        this.listPanel = listPanel;
        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        insertFrontButton = new JButton("Insert Front");
        insertEndButton = new JButton("Insert End");
        removeValueButton = new JButton("Remove Value");
        searchButton = new JButton("Search");
        reverseButton = new JButton("Reverse");
        middleButton = new JButton("Find Middle");
        detectCycleButton = new JButton("Detect Cycle");
        clearButton = new JButton("Clear");

        add(inputField);
        add(insertFrontButton);
        add(insertEndButton);
        add(removeValueButton);
        add(searchButton);
        add(reverseButton);
        add(middleButton);
        add(detectCycleButton);
        add(clearButton);

        insertFrontButton.addActionListener(this::handleInsertFront);
        insertEndButton.addActionListener(this::handleInsertEnd);
        removeValueButton.addActionListener(this::handleRemoveValue);
        searchButton.addActionListener(this::handleSearch);
        reverseButton.addActionListener(e -> controller.reverse());
        middleButton.addActionListener(e -> controller.findMiddle());
        detectCycleButton.addActionListener(e -> controller.detectCycle());
        clearButton.addActionListener(e -> controller.clear());
        addElementButton = new JButton("Add Element");
        addElementButton.addActionListener(this::handleInsertFront);  // You can set to another handler
        add(addElementButton);  // Adding Add Element button to the panel

        controller.setView(listPanel);
    }

    private void handleInsertFront(ActionEvent e) {
        T value = parseInput();
        if (value != null) {
            System.out.println("Inserting at front: " + value);  // Debugging output
            controller.insertFront(value);
        }
    }

    private void handleInsertEnd(ActionEvent e) {
        T value = parseInput();
        if (value != null) {
            System.out.println("Inserting at end: " + value);  // Debugging output
            controller.insertEnd(value);
        }
    }

    private void handleRemoveValue(ActionEvent e) {
        T value = parseInput();
        if (value != null) controller.removeValue(value);
    }

    private void handleSearch(ActionEvent e) {
        T value = parseInput();
        if (value != null) controller.search(value);
    }

    private T parseInput() {
        try {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                inputField.setText("");
                System.out.println("Parsed value: " + text);  // Debugging output
                return (T) text;  // Assuming you will cast it to appropriate type T
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid value.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;    
    }
}
