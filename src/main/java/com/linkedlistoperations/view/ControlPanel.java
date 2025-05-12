// File: src/main/java/com/linkedlistoperations/view/ControlPanel.java
package com.linkedlistoperations.view;
import com.linkedlistoperations.controller.ListController;
import com.linkedlistoperations.model.AnimationStep;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

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
    private final JButton SortButton;
    private final JButton CheckPalidromeButton;
    private final JButton removeDuplicatedValuesButton;

    private final ListController<T> controller;
    private final ListPanel<T> listPanel;

    public ControlPanel(ListController<T> controller, ListPanel<T> listPanel) {
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
        SortButton = new JButton("Sort");
        CheckPalidromeButton = new JButton("Check Palindrome");
        removeDuplicatedValuesButton = new JButton("Remove Value");

        add(inputField);
        add(insertFrontButton);
        add(insertEndButton);
        // add(removeValueButton);
        add(removeDuplicatedValuesButton);
        add(searchButton);
        add(reverseButton);
        add(middleButton);
        // add(detectCycleButton);
        add(clearButton);
        add(SortButton);
        add(CheckPalidromeButton);

        insertFrontButton.addActionListener(this::handleInsertFront);
        insertEndButton.addActionListener(this::handleInsertEnd);
        removeValueButton.addActionListener(this::handleRemoveValue);
        searchButton.addActionListener(this::handleSearch);
        reverseButton.addActionListener(e -> controller.reverse());
        middleButton.addActionListener(e -> controller.findMiddle());
        detectCycleButton.addActionListener(e -> controller.detectCycle());
        clearButton.addActionListener(e -> controller.clear());
        SortButton.addActionListener(e -> controller.sort());
        CheckPalidromeButton.addActionListener(e -> controller.checkPalindrome());
        removeDuplicatedValuesButton.addActionListener(this::handleRemoveAllValues);

        controller.setView(listPanel);
    }

    private void handleInsertFront(ActionEvent e) {
        T value = parseInput();
        if (value != null) controller.insertFront(value);
    }

    private void handleInsertEnd(ActionEvent e) {
        T value = parseInput();
        if (value != null) controller.insertEnd(value);
    }

    private void handleRemoveValue(ActionEvent e) {
        T value = parseInput();
        if (value != null) controller.removeValue(value);
    }

    private void handleRemoveAllValues(ActionEvent e) {
        T value = parseInput();
        if (value != null) controller.removeDuplicatedValues(value);
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
                return (T) Integer.valueOf(text);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter an integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}


