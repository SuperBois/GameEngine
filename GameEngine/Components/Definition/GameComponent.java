package GameEngine.Components.Definition;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.PlainDocument;

import GameEngine.GameManager;
import GameEngine.MainProgram;
import GameEngine.Test;
import GameEngine.Components.Filters.MyIntFilter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class GameComponent implements Cloneable{

    // x and y coordinates of the object
    protected int x_pos, y_pos;
    public JPanel panel;
    public ArrayList<JLabel> labels;
    protected ArrayList<JTextField> textFieldlist;  //ArrayList to store TextFields
    protected ArrayList<JCheckBox> checkBoxes;      //ArrayList to store checkboxes
    protected Field[] fieldsID;   //Field Array tos store fields references
    public boolean removable;

    public GameComponent() 
    {
        //Initializing declared instances.
        panel = new JPanel();
        textFieldlist = new ArrayList<JTextField>();
        checkBoxes = new ArrayList<JCheckBox>();
        labels = new ArrayList<JLabel>();
        fieldsID = this.getClass().getDeclaredFields();

        String className = this.getClass().getSimpleName();
        if (!(isInClassNameModel(className) || className.equals("AddComponent")
                || className.equals("Transform"))) {
            GameManager.classNameModel.addElement(this);
        }
        this.removable = true;
    }

    private boolean isInClassNameModel(String className) {
        for (int i =0; i<GameManager.classNameModel.getSize(); i++){
            if (className.equals(GameManager.classNameModel.elementAt(i).getClass().getSimpleName()))
                return true;
        }
        return false;
    }

    public void createPanel() 
    {
        panel.setLayout(null);
        panel.setBackground(Test.main.bg_color);
        // Setting size (specially Height) to accomodate all fields.
        panel.setBounds(0, 0, 270, 50 * fieldsID.length);
        panel.setPreferredSize(new Dimension(270, 50 * fieldsID.length));
        // setting panel border
        Border frameBorder = BorderFactory.createLineBorder(Color.black, 1);
        panel.setBorder(frameBorder);

        //close button functionality
        if (this.removable) {
            // setting closeButton
            JButton closeButton = new JButton();
            closeButton.setIcon(new ImageIcon(getClass().getResource("..\\Icons\\cross.png")));
            closeButton.addActionListener(e->remove(this));
            closeButton.setBounds(300 - 50, 5, 20, 20);
            closeButton.setBackground(Color.darkGray);
            panel.add(closeButton);

            // Setting Jlabel to display class name.
            JLabel className = new JLabel(this.getClass().getSimpleName());
            className.setBounds(80, 5, 160, 20);
            className.setForeground(Test.main.fg_color);
            className.setFont(new Font("Times New Roman", Font.BOLD, 15));
            labels.add(className);
            panel.add(className);
        }
        

        //looping through all the fields to create respective textboxes and checkboxes.
        for (int i = 0; i < fieldsID.length; i++) 
        {
            //Field label to display field name
            JLabel label = new JLabel(fieldsID[i].getName());
            label.setBounds(30, 30 * (i + 1) + 10, 100, 20);
            label.setForeground(Test.main.fg_color);
            labels.add(label);
            panel.add(label);

            try 
            {
                if (fieldsID[i].get(this) instanceof String) 
                {
                    //If the returned field value is String, then a textfield will be created
                    JTextField fieldValue = new JTextField((String) fieldsID[i].get(this));
                    fieldValue.setBounds(130, 30 * (i + 1) + 10, 100, 20);
                    fieldValue.setForeground(Color.black);
                    fieldValue.addActionListener(e -> UpdateValues(this.getClass().getSimpleName()));

                    //adding it to array list and panel
                    textFieldlist.add(fieldValue);
                    panel.add(fieldValue);
                } else if (fieldsID[i].get(this) instanceof Integer) 
                {
                    //If the returned field value is String,
                    //then a textfield with only integers entrance will be created
                    JTextField fieldValue = new JTextField();
                    fieldValue.setBounds(130, 30 * (i + 1) + 10, 100, 20);
                    fieldValue.setForeground(Color.black);
                    fieldValue.setText(fieldsID[i].get(this).toString());
                    fieldValue.addActionListener(e -> UpdateValues(this.getClass().getSimpleName()));

                    PlainDocument doc = (PlainDocument) fieldValue.getDocument();
                    doc.setDocumentFilter(new MyIntFilter());
                    
                    //adding it to array list and panel
                    textFieldlist.add(fieldValue);
                    panel.add(fieldValue);
                } else if (fieldsID[i].get(this) instanceof Boolean) 
                {
                    //If the returned field value is Boolean,
                    //then a checkBox will be created.
                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected((boolean) fieldsID[i].get(this));
                    checkBox.setBounds(130, 30 * (i + 1) + 10, 20, 15);
                    checkBox.addActionListener(e -> UpdateValues(this.getClass().getSimpleName()));

                    //adding it to array list and panel
                    checkBoxes.add(checkBox);
                    panel.add(checkBox);
                }

            } catch (IllegalArgumentException e) {
                GameManager.debugModel.addElement("Field not found...");
            } catch (IllegalAccessException e) {
                GameManager.debugModel.addElement("Access to this field is not possible...");
            }
        }
    }

    private void remove(GameComponent gameComponent) {
        MainProgram.selectedObject.properties.removeElement(this);
        Test.main.showPanelofSelected();
    }

    public GameComponent newObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        return this.getClass().getConstructor().newInstance();
    }

    public abstract void Start();
    public abstract void Update();

    public void UpdateValues(String classString) 
    {
        //To get property index of certain gameobject
        int propIndex = 0;  
        //Counters
        int checkBoxFieldCount = 0;   
        int textFieldCount = 0;

        //looping through all the properties of gameobject to get property index.
        for (int i = 0; i < MainProgram.selectedObject.properties.getSize(); i++) {
            if (MainProgram.selectedObject.properties.getElementAt(i).getClass().getSimpleName() == classString) {
                propIndex = i;
                break;
            }
        }

        try 
        {
            //looping through the fieldID's and arraylists to update the fields.
            for (int i = 0; i < fieldsID.length; i++) 
            {
                
                if (fieldsID[i].get(this) instanceof String) 
                {
                    fieldsID[i].set(MainProgram.selectedObject.properties.getElementAt(propIndex),
                                    textFieldlist.get(textFieldCount).getText());
                    textFieldCount++;
                } else if (fieldsID[i].get(this) instanceof Integer) 
                {
                    int value;
                    try {
                        value = (textFieldlist.get(textFieldCount).getText() != "")
                                ? Integer.parseInt(textFieldlist.get(textFieldCount).getText())
                                : 0;
                    } catch (Exception e) {
                        GameManager.debugModel.addElement("No input in integer field... Setting to 0.");
                        value = 0;
                    }
                    fieldsID[i].set(MainProgram.selectedObject.properties.getElementAt(propIndex), value);
                    textFieldCount++;
                } else if (fieldsID[i].get(this) instanceof Boolean) 
                {
                    fieldsID[i].set(MainProgram.selectedObject.properties.getElementAt(propIndex),
                            checkBoxes.get(checkBoxFieldCount).isSelected());
                    checkBoxFieldCount++;
                }
            }
        } catch (IllegalArgumentException e) {
            GameManager.debugModel.addElement("Field not found...");
        } catch (IllegalAccessException e) {
            GameManager.debugModel.addElement("Access to this field is not possible...");
        }
        System.out.println(MainProgram.selectedObject.properties.getElementAt(propIndex).toString());
        Test.main.refreshFrame();
    }

    public abstract GameComponent newInstance();
}
