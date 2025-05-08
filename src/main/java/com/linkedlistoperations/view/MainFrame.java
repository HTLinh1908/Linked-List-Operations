// File: src/main/java/com/linkedlistoperations/view/MainFrame.java
package com.linkedlistoperations.view;
import com.linkedlistoperations.controller.ListController;
import com.linkedlistoperations.view.ControlPanel;
import javax.swing.*;
import java.awt.*;
import com.linkedlistoperations.controller.ListController;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Linked List GUI");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ListPanel<String> listPanel = new ListPanel<>();
        listPanel.setBackground(Color.WHITE);
        listPanel.setBorder(BorderFactory.createTitledBorder("Linked List View"));

        ListController<String> controller = new ListController<>(listPanel);
        ControlPanel<String> controlPanel = new ControlPanel<>(controller);
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        controlPanel.setListPanel(listPanel);

        add(listPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}