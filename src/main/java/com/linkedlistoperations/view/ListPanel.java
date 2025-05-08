// File: src/main/java/com/linkedlistoperations/view/ListPanel.java
package com.linkedlistoperations.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.linkedlistoperations.model.Node;

public class ListPanel<T> extends JPanel {
    private List<Node<T>> nodes;

    public void setNodes(List<Node<T>> nodes) {
        this.nodes = nodes;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (nodes == null || nodes.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.setStroke(new BasicStroke(2));

        int x = 50, y = 100;

        for (int i = 0; i < nodes.size(); i++) {
            Node<T> node = nodes.get(i);

            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, 60, 30);
            g2.drawString(node.getData().toString(), x + 20, y + 20);

            Node<T> next = node.getNext();
            if (next != null) {
                g2.drawLine(x + 60, y + 15, x + 90, y + 15);
                g2.drawLine(x + 85, y + 10, x + 90, y + 15);
                g2.drawLine(x + 85, y + 20, x + 90, y + 15);
            }

            x += 90;
        }
    }
}