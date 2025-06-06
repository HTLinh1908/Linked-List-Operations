package com.linkedlistoperations.view;
import com.linkedlistoperations.controller.ListController;
import com.linkedlistoperations.model.LinkedList;
import static com.linkedlistoperations.model.SoundEffect.playSound;
import javax.swing.*;
import java.awt.*;

public class MainFrame<T> extends JFrame {
    private final ListController<T> controller;
    private final ControlPanel<T> controlPanel;
    private final ListPanel<T> listPanel;

    public MainFrame(ListController<T> controller) {
        this.controller = controller;
        this.listPanel = new ListPanel<>();
        this.controlPanel = new ControlPanel<>(controller, listPanel);
        initUI();
    }

    private void initUI() {
        setTitle("Linked List Operations");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        add(listPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            LinkedList<Integer> model = new LinkedList<>();
            ListController<Integer> controller = new ListController<>(model);
            new MainFrame<>(controller);
        });
        playSound("src/main/resources/Jack In The Box - Sound Effect (HD).wav", true);

    }
}
