package com.linkedlistoperations.view;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.linkedlistoperations.model.AnimationStep;

public class ListPanel<T> extends JPanel {
    private List<AnimationStep<T>> animationSteps;

    public ListPanel() {
        setPreferredSize(new Dimension(800, 200));
        setBackground(Color.WHITE);
    }

    public void setAnimationSteps(List<AnimationStep<T>> steps) {
        this.animationSteps = steps;
        repaint();  // Trigger a redraw
    }

    // @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (animationSteps == null || animationSteps.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 20;
        int y = getHeight() / 2 - 20;

        for (AnimationStep<T> step : animationSteps) {
            // Draw node rectangle
            g2.setColor(Color.CYAN);
            g2.fillRoundRect(x, y, 80, 40, 10, 10);

            g2.setColor(Color.BLACK);
            g2.drawRoundRect(x, y, 80, 40, 10, 10);

            // Draw data
            String text = String.valueOf(step.node().getData());
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textX = x + (80 - textWidth) / 2;
            int textY = y + 25;
            g2.drawString(text, textX, textY);

            // Draw arrow to next node
            if (step.otherNode() != null) {
                int arrowX = x + 80;
                int arrowY = y + 20;
                g2.drawLine(arrowX, arrowY, arrowX + 20, arrowY);
                g2.drawLine(arrowX + 15, arrowY - 5, arrowX + 20, arrowY);
                g2.drawLine(arrowX + 15, arrowY + 5, arrowX + 20, arrowY);
            }

            x += 110; // Space between nodes
        }
    }
}
