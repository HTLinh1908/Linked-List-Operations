package com.linkedlistoperations.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.linkedlistoperations.model.AnimationStep;

public class ListPanel<T> extends JPanel {
    public ListPanel() {

    }
    public void setAnimationSteps(List<AnimationStep<T>> steps) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}