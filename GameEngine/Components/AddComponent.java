package GameEngine.Components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import GameEngine.GameManager;
import GameEngine.MainProgram;
import GameEngine.Test;
import GameEngine.Components.Definition.GameComponent;
import GameEngine.Renderer.ComponentListRenderer;

public class AddComponent extends GameComponent {

    JList<GameComponent> classesListBox;
    JScrollPane scrollPane;
    JTextField textField;
    JPanel componentPanel;
    JButton addComponentButton;
    DefaultListModel<GameComponent> filteredClassList;
    JButton button;

    public AddComponent() {
        // initializing the necessary fields
        textField = new JTextField();
        filteredClassList = new DefaultListModel<GameComponent>();
        classesListBox = new JList<GameComponent>(filteredClassList);
        
        for (int i=0; i<GameManager.classNameModel.getSize(); i++)
        {
            filteredClassList.addElement(GameManager.classNameModel.elementAt(i));
        }

        textField.setSize(new Dimension(270, 30));;
        textField.setText("");
        removable = false;

        classesListBox.setVisibleRowCount(4);
        scrollPane = new JScrollPane(classesListBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        classesListBox.setCellRenderer(new ComponentListRenderer());

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
        textField.getDocument().addDocumentListener(new DocumentListener(){

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
        // scrolls the panel to bottom 
        Test.main.scrollToBottom();
        // refreshes the frame
        refreshFrame();
        // requests focus for text field
        textField.requestFocus();
    }

    // filters the contenets of the classNameModels based on the entered text
    private void filterComponents() {
        filteredClassList.removeAllElements();
        for (int i=0; i<GameManager.classNameModel.getSize(); i++){
            
            String className = GameManager.classNameModel.elementAt(i).getClass().getSimpleName();
            if (className.toLowerCase().contains(textField.getText().toLowerCase()))
                filteredClassList.addElement(GameManager.classNameModel.elementAt(i));
        }
        classesListBox.setSelectedIndex(0);
    }

    // adds selected class at the second last index of object's list model
    void addComponent(){
        GameComponent selectedClass = GameManager.classNameModel.elementAt(classesListBox.getSelectedIndex());
        // add element at second last index
        MainProgram.selectedObject.properties.add(MainProgram.selectedObject.properties.getSize()-1, selectedClass);
        Test.main.showPanelofSelected();
        button.setVisible(false);
        scrollPane.setVisible(false);
        textField.setVisible(false);
        addComponentButton.setVisible(true);
        Test.main.updateColor();
        Test.main.scrollToBottom();
    }
    void refreshFrame(){
        Test.main.invalidate();
        Test.main.validate();
        Test.main.repaint();
    }

    
}
