import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

// Node class representing each element in the linked list
class Node<T> {
    T data;
    Node<T> next;
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
    public T getData() { return data; }
    public Node<T> getNext() { return next; }
    public void setNext(Node<T> next) { this.next = next; }
}

// Custom singly linked list implementation
class LinkedList<T> {
    private Node<T> head;
    private final List<ListChangeListener<T>> listeners = new ArrayList<>();

    public void addListChangeListener(ListChangeListener<T> listener) {
        listeners.add(listener);
    }

    private void notifyChange() {
        for (ListChangeListener<T> listener : listeners) {
            listener.onChange(toList());
        }
    }

    public void insertFront(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(head);
        head = newNode;
        notifyChange();
    }

    public void insertEnd(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(newNode);
        }
        notifyChange();
    }

    public void insertAt(int index, T data) {
        if (index < 0) return;
        if (index == 0) {
            insertFront(data);
            return;
        }
        Node<T> newNode = new Node<>(data);
        Node<T> curr = head;
        for (int i = 0; i < index - 1 && curr != null; i++) {
            curr = curr.getNext();
        }
        if (curr == null) return;
        newNode.setNext(curr.getNext());
        curr.setNext(newNode);
        notifyChange();
    }

    public T removeAt(int index) {
        if (index < 0 || head == null) return null;
        if (index == 0) {
            T data = head.getData();
            head = head.getNext();
            notifyChange();
            return data;
        }
        Node<T> curr = head;
        for (int i = 0; i < index - 1 && curr.getNext() != null; i++) {
            curr = curr.getNext();
        }
        if (curr.getNext() == null) return null;
        T data = curr.getNext().getData();
        curr.setNext(curr.getNext().getNext());
        notifyChange();
        return data;
    }

    public boolean removeValue(T value) {
        if (head == null) return false;
        if (head.getData().equals(value)) {
            head = head.getNext();
            notifyChange();
            return true;
        }
        Node<T> curr = head;
        while (curr.getNext() != null && !curr.getNext().getData().equals(value)) {
            curr = curr.getNext();
        }
        if (curr.getNext() == null) return false;
        curr.setNext(curr.getNext().getNext());
        notifyChange();
        return true;
    }

    public void reverse() {
        Node<T> prev = null;
        Node<T> curr = head;
        while (curr != null) {
            Node<T> next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
        notifyChange();
    }

    public List<Node<T>> search(T value) {
        List<Node<T>> result = new ArrayList<>();
        Node<T> curr = head;
        while (curr != null) {
            if (curr.getData().equals(value)) {
                result.add(curr);
            }
            curr = curr.getNext();
        }
        return result;
    }

    public boolean detectCycle() {
        Node<T> slow = head;
        Node<T> fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) return true;
        }
        return false;
    }

    public Node<T> findMiddle() {
        if (head == null) return null;
        Node<T> slow = head;
        Node<T> fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    public List<Node<T>> toList() {
        List<Node<T>> list = new ArrayList<>();
        Node<T> curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.getNext();
        }
        return list;
    }
}

// Listener interface for list changes
interface ListChangeListener<T> {
    void onChange(List<Node<T>> nodes);
}

// Panel to display the linked list
class ListPanel<T> extends JPanel implements ListChangeListener<T> {
    private List<Node<T>> nodes = new ArrayList<>();
    private String message = "";
    private T highlightValue = null;

    public ListPanel() {
        setPreferredSize(new Dimension(800, 200));
        setBackground(Color.WHITE);
    }

    public void showMessage(String message) {
        this.message = message;
        repaint();
    }

    public void highlightValue(T value) {
        this.highlightValue = value;
        repaint();
    }

    @Override
    public void onChange(List<Node<T>> nodes) {
        this.nodes = nodes;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (nodes == null || nodes.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 20;
        int y = getHeight() / 2 - 20;

        for (Node<T> node : nodes) {
            T value = node.getData();
            boolean isHighlighted = value != null && value.equals(highlightValue);

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

            x += 110;
        }

        g2.setColor(Color.BLACK);
        g2.drawString(message, 10, getHeight() - 10);
    }
}

// Controller to handle operations between the model and the view
class ListController<T> {
    private final LinkedList<T> model;
    private final ListPanel<T> view;

    public ListController(LinkedList<T> model, ListPanel<T> view) {
        this.model = model;
        this.view = view;
        this.model.addListChangeListener(view);
    }

    public void insertFront(T data) {
        model.insertFront(data);
        view.showMessage("Inserted at front: " + data);
    }

    public void insertEnd(T data) {
        model.insertEnd(data);
        view.showMessage("Inserted at end: " + data);
    }

    public void insertAt(int index, T data) {
        model.insertAt(index, data);
        view.showMessage("Inserted at index " + index + ": " + data);
    }

    public void removeAt(int index) {
        T removed = model.removeAt(index);
        if (removed != null) {
            view.showMessage("Removed item at index " + index + ": " + removed);
        } else {
            view.showMessage("Invalid index: " + index);
        }
    }

    public void removeValue(T value) {
        boolean success = model.removeValue(value);
        if (success) {
            view.showMessage("Removed value: " + value);
        } else {
            view.showMessage("Value not found: " + value);
        }
    }

    public void reverse() {
        model.reverse();
        view.showMessage("List reversed.");
    }

    public void search(T value) {
        List<Node<T>> found = model.search(value);
        if (found.isEmpty()) {
            view.showMessage("Value not found: " + value);
        } else {
            view.showMessage("Found " + found.size() + " occurrence(s) of: " + value);
            view.highlightValue(value);
        }
    }

    public void detectCycle() {
        boolean hasCycle = model.detectCycle();
        view.showMessage(hasCycle ? "Cycle detected!" : "No cycle detected.");
    }

    public void findMiddle() {
        Node<T> mid = model.findMiddle();
        if (mid != null) {
            view.showMessage("Middle element: " + mid.getData());
        } else {
            view.showMessage("List is empty.");
        }
    }
}

public class merch_test extends JFrame {
    private final LinkedList<String> linkedList = new LinkedList<>();
    private final ListPanel<String> listPanel = new ListPanel<>();
    private final ListController<String> controller = new ListController<>(linkedList, listPanel);

    public merch_test() {
        setTitle("Linked List Operations");
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel to display the linked list
        add(listPanel, BorderLayout.CENTER);

        // Control panel with buttons and input
        JPanel controlPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        // Row 1: Insertion controls
        JPanel insertPanel = new JPanel();
        JTextField inputField = new JTextField(10);
        JTextField indexField = new JTextField(5);
        JButton addFrontBtn = new JButton("Insert Front");
        JButton addEndBtn = new JButton("Insert End");
        JButton insertAtBtn = new JButton("Insert at Index");

        insertPanel.add(new JLabel("Value:"));
        insertPanel.add(inputField);
        insertPanel.add(new JLabel("Index:"));
        insertPanel.add(indexField);
        insertPanel.add(addFrontBtn);
        insertPanel.add(addEndBtn);
        insertPanel.add(insertAtBtn);

        // Row 2: Removal/Search controls
        JPanel operationPanel = new JPanel();
        JTextField searchField = new JTextField(10);
        JTextField removeIndexField = new JTextField(5);
        JButton removeAtBtn = new JButton("Remove at Index");
        JButton removeValueBtn = new JButton("Remove Value");
        JButton searchBtn = new JButton("Search");

        operationPanel.add(new JLabel("Search/Remove Value:"));
        operationPanel.add(searchField);
        operationPanel.add(removeValueBtn);
        operationPanel.add(new JLabel("Remove Index:"));
        operationPanel.add(removeIndexField);
        operationPanel.add(removeAtBtn);
        operationPanel.add(searchBtn);

        // Row 3: Other operations
        JPanel actionPanel = new JPanel();
        JButton reverseBtn = new JButton("Reverse");
        JButton middleBtn = new JButton("Find Middle");
        JButton cycleBtn = new JButton("Detect Cycle");

        actionPanel.add(reverseBtn);
        actionPanel.add(middleBtn);
        actionPanel.add(cycleBtn);

        // Add all rows to control panel
        controlPanel.add(insertPanel);
        controlPanel.add(operationPanel);
        controlPanel.add(actionPanel);

        add(controlPanel, BorderLayout.SOUTH);

        // Button Actions
        addFrontBtn.addActionListener(e -> {
            String val = inputField.getText().trim();
            if (!val.isEmpty()) controller.insertFront(val);
        });

        addEndBtn.addActionListener(e -> {
            String val = inputField.getText().trim();
            if (!val.isEmpty()) controller.insertEnd(val);
        });

        insertAtBtn.addActionListener(e -> {
            String val = inputField.getText().trim();
            try {
                int index = Integer.parseInt(indexField.getText().trim());
                controller.insertAt(index, val);
            } catch (NumberFormatException ex) {
                listPanel.showMessage("Invalid index.");
            }
        });

        removeAtBtn.addActionListener(e -> {
            try {
                int index = Integer.parseInt(removeIndexField.getText().trim());
                controller.removeAt(index);
            } catch (NumberFormatException ex) {
                listPanel.showMessage("Invalid index.");
            }
        });

        removeValueBtn.addActionListener(e -> {
            String val = searchField.getText().trim();
            if (!val.isEmpty()) controller.removeValue(val);
        });

        searchBtn.addActionListener(e -> {
            String val = searchField.getText().trim();
            if (!val.isEmpty()) controller.search(val);
        });

        reverseBtn.addActionListener(e -> controller.reverse());
        middleBtn.addActionListener(e -> controller.findMiddle());
        cycleBtn.addActionListener(e -> controller.detectCycle());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(merch_test::new);
    }
}

