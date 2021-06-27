package GameEngine.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GameEngine.GameManager;
import GameEngine.MainProgram;
import GameEngine.Test;
import GameEngine.Components.Definition.GameComponent;
import GameEngine.Renderer.ComponentListRenderer;

public class AddComponent extends GameComponent implements Serializable{

    JList<GameComponent> filteredClassList;
    JScrollPane scrollPane;
    public JTextField textField;
    JPanel componentPanel;
    JButton addComponentButton;
    DefaultListModel<GameComponent> filteredClassModel;
    JButton button;
    public static String enteredText = "newfilehai";

    public AddComponent() {
        // initializing the necessary fields
        textField = new JTextField();
        filteredClassModel = new DefaultListModel<GameComponent>();
        filteredClassList = new JList<GameComponent>(filteredClassModel);

        for (int i = 0; i < GameManager.classNameModel.getSize(); i++) {
            filteredClassModel.addElement(GameManager.classNameModel.elementAt(i));
        }

        textField.setSize(new Dimension(270, 30));
        ;
        textField.setText("");
        removable = false;

        filteredClassList.setVisibleRowCount(4);
        scrollPane = new JScrollPane(filteredClassList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        filteredClassList.setCellRenderer(new ComponentListRenderer());

        createPanel();
    }

    @Override
    public void createPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // Setting size (specially Height) to accomodate all fields.
        // setting panel border
        Border frameBorder = BorderFactory.createLineBorder(Color.black, 1);
        panel.setBorder(frameBorder);

        addComponentButton = new JButton("Add Component");
        addComponentButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        addComponentButton.addActionListener(e -> showClasses());
        panel.add(textField);
        panel.add(scrollPane);
        panel.add(addComponentButton);
        button = new JButton("Add");
        button.addActionListener(e -> addComponent());
        panel.add(button);
        button.setVisible(false);
        scrollPane.setVisible(false);
        textField.setVisible(false);
    }

    void showClasses() {

        addComponentButton.setVisible(false);
        textField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterComponents();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterComponents();
            }

        });
        textField.setVisible(true);
        scrollPane.setVisible(true);
        button.setVisible(true);
        filteredClassList.setSelectedIndex(0);
        // scrolls the panel to bottom
        Test.main.scrollToTop();
        // refreshes the frame
        refreshFrame();
        // requests focus for text field
        textField.requestFocus();
    }

    // filters the contenets of the classNameModels based on the entered text
    private void filterComponents() {
        NewScript.name = textField.getText().toLowerCase();
        System.out.println(NewScript.name);
        filteredClassModel.removeAllElements();

        for (int i = 0; i < GameManager.classNameModel.getSize(); i++) {

            String className = GameManager.classNameModel.elementAt(i).getClass().getSimpleName();
            // adds the element to list if it is in classnameList
            if (className.toLowerCase().contains(textField.getText().toLowerCase()))
                filteredClassModel.addElement(GameManager.classNameModel.elementAt(i));
            // removes the element from list if it is in properties
            if (MainProgram.selectedObject.properties
                    .get(GameManager.classNameModel.elementAt(i).getClass().getSimpleName()) != null)
                filteredClassModel.removeElement(GameManager.classNameModel.elementAt(i));

        }
        // if new script is not in the list then add it
        if (!filteredClassModel.contains(GameManager.classNameModel.lastElement()))
            filteredClassModel.addElement(GameManager.classNameModel.lastElement());
        // sets the selected index to 0
        filteredClassList.setSelectedIndex(0);
    }

    // adds selected class at the second last index of object's list model
    void addComponent() {
        GameComponent newComponent = (GameComponent) filteredClassModel.elementAt(filteredClassList.getSelectedIndex())
                .newInstance();
        newComponent.setGameObject(MainProgram.selectedObject);
        // add element at second last index
        if (!isInSelectedObjectProperties(newComponent.getClass().getSimpleName()))
            MainProgram.selectedObject.properties.put(newComponent.getClass().getSimpleName(), newComponent);
        Test.main.showPanelofSelected();
        button.setVisible(false);
        scrollPane.setVisible(false);
        textField.setVisible(false);
        addComponentButton.setVisible(true);
        Test.main.scrollToTop();
    }

    void refreshFrame() {
        Test.main.invalidate();
        Test.main.validate();
        Test.main.repaint();
    }

    private boolean isInSelectedObjectProperties(String name) {
        Set keys = MainProgram.selectedObject.properties.keySet();
        Iterator iterator = keys.iterator();

        while (iterator.hasNext()) {
            if (name.equals(iterator.next()))
                return true;
        }
        return false;
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public GameComponent newInstance() {
        return new AddComponent();
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        
    }

}
