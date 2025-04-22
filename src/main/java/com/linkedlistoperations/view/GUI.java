import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class LinkedListGUI extends JFrame {
    private LinkedList<String> linkedList = new LinkedList<>();
    private JPanel Panel;
    private JTextField inputField;

    public LinkedListGUI() {
        setTitle("Linked List GUI");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Half - Linked List Display
        Panel = new JPanel();
        Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Panel.setBorder(BorderFactory.createTitledBorder("Linked List"));
        JScrollPane scrollPane = new JScrollPane(Panel);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Half - Controls & Functions
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBorder(BorderFactory.createTitledBorder("Add Element"));

        inputField = new JTextField(20);
        JButton addButton = new JButton("Add to List");

        controlPanel.add(inputField);
        controlPanel.add(addButton);

        add(controlPanel, BorderLayout.SOUTH);

        // Button Action
        addButton.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                linkedList.add(text);
                inputField.setText("");
                updateListDisplay();
            }
        });

        setVisible(true);
    }

    private void updateListDisplay() {
        Panel.removeAll();
        for (String value : linkedList) {
            JLabel label = new JLabel(value);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            label.setPreferredSize(new Dimension(60, 30));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            Panel.add(label);
        }
        Panel.revalidate();
        Panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LinkedListGUI::new);
    }
}
