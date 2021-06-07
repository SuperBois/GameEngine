package GameEngine.Components;

import java.awt.Color;
import java.awt.Dimension;

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

public class AddComponent extends GameComponent {

    JList<GameComponent> filteredClassList;
    JScrollPane scrollPane;
    JTextField textField;
    JPanel componentPanel;
    JButton addComponentButton;
    DefaultListModel<GameComponent> filteredClassModel;
    JButton button;

    public AddComponent() {
        // initializing the necessary fields
        textField = new JTextField();
        filteredClassModel = new DefaultListModel<GameComponent>();
        filteredClassList = new JList<GameComponent>(filteredClassModel);
        
        for (int i=0; i<GameManager.classNameModel.getSize(); i++){
            filteredClassModel.addElement(GameManager.classNameModel.elementAt(i));
        }

        textField.setSize(new Dimension(270, 30));;
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
        filteredClassList.setSelectedIndex(0);
        // scrolls the panel to bottom 
        Test.main.scrollToBottom();
        // refreshes the frame
        refreshFrame();
        // requests focus for text field
        textField.requestFocus();
    }

    // filters the contenets of the classNameModels based on the entered text
    private void filterComponents() {
        filteredClassModel.removeAllElements();
        for (int i=0; i<GameManager.classNameModel.getSize(); i++){
            
            String className = GameManager.classNameModel.elementAt(i).getClass().getSimpleName();
            if (className.toLowerCase().contains(textField.getText().toLowerCase()))
                filteredClassModel.addElement(GameManager.classNameModel.elementAt(i));
        }
        // if new script is not in the list then add it
        if (!filteredClassModel.contains(GameManager.classNameModel.lastElement()))
            filteredClassModel.addElement(GameManager.classNameModel.lastElement());
        // sets the selected index to 0
        filteredClassList.setSelectedIndex(0);
    }

    // adds selected class at the second last index of object's list model
    void addComponent(){
        GameComponent selectedClass = GameManager.classNameModel.elementAt(filteredClassList.getSelectedIndex());
        // add element at second last index
        MainProgram.selectedObject.properties.add(MainProgram.selectedObject.properties.getSize()-1, selectedClass);
        Test.main.showPanelofSelected();
        button.setVisible(false);
        scrollPane.setVisible(false);
        textField.setVisible(false);
        addComponentButton.setVisible(true);
        Test.main.scrollToBottom();
    }
    void refreshFrame(){
        Test.main.invalidate();
        Test.main.validate();
        Test.main.repaint();
    }

    @Override
    public void Start() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub
        
    }

    
}
