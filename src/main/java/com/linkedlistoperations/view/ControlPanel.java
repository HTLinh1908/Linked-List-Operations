// File: src/main/java/com/linkedlistoperations/view/ControlPanel.java
package com.linkedlistoperations.view;

import javax.swing.*;
import java.awt.*;
import com.linkedlistoperations.controller.ListController;

public class ControlPanel<T> extends JPanel {
    private final JTextField inputField;
    private ListPanel<T> listPanel;

    public ControlPanel(ListController<T> controller) {
        setLayout(new FlowLayout());

        inputField = new JTextField(15);
        JButton addFrontBtn = new JButton("Add Front");
        JButton addEndBtn = new JButton("Add End");
        JButton reverseBtn = new JButton("Reverse");
        JButton clearBtn = new JButton("Clear");

        add(inputField);
        add(addFrontBtn);
        add(addEndBtn);
        add(reverseBtn);
        add(clearBtn);

        addFrontBtn.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                controller.insertFront((T) text);
                inputField.setText("");
            }
        });

        addEndBtn.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                controller.insertEnd((T) text);
                inputField.setText("");
            }
        });

        reverseBtn.addActionListener(e -> controller.reverse());
        clearBtn.addActionListener(e -> controller.clear());
    }

    public void setListPanel(ListPanel<T> listPanel) {
        this.listPanel = listPanel;
    }
}