package com.linkedlistoperations.view;

import com.linkedlistoperations.model.AnimationStep;
import com.linkedlistoperations.model.AnimationType;
import com.linkedlistoperations.model.Node;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListPanel<T> extends JPanel {
    private List<AnimationStep<T>> animationSteps = new ArrayList<>();
    private String message = "";
    private T highlightValue = null;

    public ListPanel() {
        setPreferredSize(new Dimension(800, 200));
        setBackground(Color.WHITE);
    }

    public void setAnimationSteps(List<AnimationStep<T>> steps) {
        this.animationSteps = steps;
        repaint();
    }

    public void updateList(List<Node<T>> nodes) {
        List<AnimationStep<T>> steps = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            Node<T> current = nodes.get(i);
            Node<T> next = (i + 1 < nodes.size()) ? nodes.get(i + 1) : null;
            steps.add(new AnimationStep<>(AnimationType.VISIT, current, next));
        }
        setAnimationSteps(steps);
    }

    public void showMessage(String message) {
        this.message = message;
        repaint();
    }

    public void highlightValue(T value) {
        this.highlightValue = value;
        repaint();
    }

    public void highlightNode(Node<T> node) {
        if (node != null) {
            node.setHighlighted(true);
        } else {
            this.highlightValue = null;
        }
        repaint();
    }

    public void clearHighlight() {
        this.highlightValue = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (animationSteps == null || animationSteps.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 20;
        int y = getHeight() / 2 - 20;

        for (AnimationStep<T> step : animationSteps) {
            T value = step.node().getData();
            boolean isHighlighted = (value != null && value.equals(highlightValue)) || step.node().isHighlighted();

            g2.setColor(isHighlighted ? Color.YELLOW : Color.CYAN);
            g2.fillRoundRect(x, y, 80, 40, 10, 10);

            g2.setColor(Color.BLACK);
            g2.drawRoundRect(x, y, 80, 40, 10, 10);

            String text = String.valueOf(value);
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textX = x + (80 - textWidth) / 2;
            int textY = y + 25;
            g2.drawString(text, textX, textY);

            if (step.otherNode() != null) {
                int arrowX = x + 80;
                int arrowY = y + 20;
                g2.drawLine(arrowX, arrowY, arrowX + 20, arrowY);
                g2.drawLine(arrowX + 15, arrowY - 5, arrowX + 20, arrowY);
                g2.drawLine(arrowX + 15, arrowY + 5, arrowX + 20, arrowY);
            }

            x += 110;
        }

        g2.setColor(Color.BLACK);
        g2.drawString(message, 10, getHeight() - 10);
    }
}