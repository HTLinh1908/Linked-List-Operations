package com.linkedlistoperations.utils;

import javax.swing.*;
import java.util.List;
import com.linkedlistoperations.model.AnimationStep;

public class AnimationTimer {
    public AnimationTimer(int fps) {
        // Initialize the timer with the given frames per second (fps)
    }
    public void start(List<AnimationStep<?>> steps, Runnable onComplete) {
        // Start the animation with the provided steps and completion callback
    }
    public void stop() {
        // Stop the animation
    }
    public boolean isRunning() {
        return false;
    }
}
