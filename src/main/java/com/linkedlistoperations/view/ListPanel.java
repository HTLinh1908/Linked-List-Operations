package com.linkedlistoperations.view;

import com.linkedlistoperations.model.Node;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListPanel<T> extends JPanel {
    private List<Node<T>> nodeList;
    private final JTextArea messageArea;

    public ListPanel() {
        setPreferredSize(new Dimension(800, 200));
        setBackground(Color.WHITE);
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setPreferredSize(new Dimension(800, 50));
        add(messageArea, BorderLayout.SOUTH);
    }

    public void updateList(List<Node<T>> nodeList) {
        this.nodeList = nodeList;
        repaint();  // Trigger the panel to be re-drawn
    }

    public void showMessage(String message) {
        messageArea.setText(message);  // Display the message in the text area
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (nodeList == null || nodeList.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 20;
        int y = getHeight() / 2 - 20;

        for (Node<T> node : nodeList) {
            // Draw node rectangle
            g2.setColor(Color.CYAN);
            g2.fillRoundRect(x, y, 80, 40, 10, 10);
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(x, y, 80, 40, 10, 10);

            // Draw data
            String text = String.valueOf(node.getData());
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textX = x + (80 - textWidth) / 2;
            int textY = y + 25;
            g2.drawString(text, textX, textY);

            // Draw arrow to next node
            if (node.getNext() != null) {
                int arrowX = x + 80;
                int arrowY = y + 20;
                g2.drawLine(arrowX, arrowY, arrowX + 20, arrowY);
                g2.drawLine(arrowX + 15, arrowY - 5, arrowX + 20, arrowY);
                g2.drawLine(arrowX + 15, arrowY + 5, arrowX + 20, arrowY);
            }

            x += 110;  // Space between nodes
        }
    }
}
