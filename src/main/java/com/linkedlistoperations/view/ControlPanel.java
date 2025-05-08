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
    private final ListController<T> controller;
    private final ListPanel<T> listPanel;

    public ControlPanel(ListController<T> controller, ListPanel<T> listPanel) {
        this.controller = controller;
        this.listPanel = listPanel;

        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        insertFrontButton = new JButton("Insert Front");
        insertEndButton = new JButton("Insert End");

        add(inputField);
        add(insertFrontButton);
        add(insertEndButton);

        insertFrontButton.addActionListener(this::handleInsertFront);
        insertEndButton.addActionListener(this::handleInsertEnd);

        controller.setView(listPanel);
    }

    private void handleInsertFront(ActionEvent e) {
        try {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                T value = (T) Integer.valueOf(text);
                controller.insertFront(value);
                inputField.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleInsertEnd(ActionEvent e) {
        try {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                T value = (T) Integer.valueOf(text);
                controller.insertEnd(value);
                inputField.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

