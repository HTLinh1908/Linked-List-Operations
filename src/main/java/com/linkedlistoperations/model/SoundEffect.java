package com.linkedlistoperations.model;

import javax.sound.sampled.*;
import java.io.File;

public class SoundEffect {
    public static void playSound(String filePath, boolean loopIndefinitely) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            if (loopIndefinitely) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.loop(5);
            }
            clip.start();
            System.out.println("Playing sound... Press Ctrl+C to stop.");
            Thread.sleep(60000);
            clip.stop();
            clip.close();
            audioStream.close();
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }
}