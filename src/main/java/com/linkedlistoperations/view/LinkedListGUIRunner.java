// File: src/main/java/com/linkedlistoperations/LinkedListGUIRunner.java
// In LinkedListGUIRunner.java:
package com.linkedlistoperations.view;
import com.linkedlistoperations.model.LinkedList;
import com.linkedlistoperations.controller.ListController;
import com.linkedlistoperations.view.MainFrame;

import javax.swing.*;

public class LinkedListGUIRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LinkedList<Integer> model = new LinkedList<>();
            ListController<Integer> controller = new ListController<>(model);
            new MainFrame<>(controller); // cleanly launch GUI
        });
    }
}