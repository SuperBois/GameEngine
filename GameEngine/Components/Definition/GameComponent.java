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

public abstract class GameComponent {

    // x and y coordinates of the object
    protected int x_pos, y_pos;
    public JPanel panel;
    protected ArrayList<JTextField> textFieldlist;
    protected ArrayList<JCheckBox> checkBoxes;
    protected Field[] fieldsID;
    public boolean removable;

    public GameComponent() {
        panel = new JPanel();
        textFieldlist = new ArrayList<JTextField>();
        checkBoxes = new ArrayList<JCheckBox>();
        String className = this.getClass().getSimpleName();
        if (!(GameManager.classNameModel.contains(className) || className.equals("AddComponent")
                || className.equals("Transform"))){
            GameManager.classNameModel.addElement(this);

        }
        this.removable = true;
    }

    public void createPanel() {
        fieldsID = this.getClass().getDeclaredFields();

        panel.setLayout(null);
        // Setting size (specially Height) to accomodate all fields.
        panel.setBounds(0, 0, 270, 50 * fieldsID.length);
        panel.setPreferredSize(new Dimension(270, 50 * fieldsID.length));
        // setting panel border
        Border frameBorder = BorderFactory.createLineBorder(Color.black, 1);
        panel.setBorder(frameBorder);

        if (this.removable) {
            // setting closeButton
            JButton closeButton = new JButton();
            closeButton.setIcon(new ImageIcon("GameEngine/Icons/cross.png"));
            closeButton.addActionListener(e->remove(this));
            closeButton.setBounds(300 - 50, 5, 20, 20);
            closeButton.setBackground(Color.darkGray);
            panel.add(closeButton);

            // Setting Jlabel to display class name.
            JLabel className = new JLabel(this.getClass().getSimpleName());
            className.setBounds(80, 5, 160, 20);
            className.setFont(new Font("Times New Roman", Font.BOLD, 15));
            panel.add(className);
        }

        for (int i = 0; i < fieldsID.length; i++) {
            JLabel label = new JLabel(fieldsID[i].getName());
            label.setBounds(30, 30 * (i + 1) + 10, 100, 20);
            panel.add(label);

            try {

                if (fieldsID[i].get(this) instanceof String) {
                    JTextField fieldValue = new JTextField((String) fieldsID[i].get(this));
                    fieldValue.setBounds(130, 30 * (i + 1) + 10, 100, 20);
                    fieldValue.addActionListener(e -> UpdateValues(this.getClass().getSimpleName()));

                    textFieldlist.add(fieldValue);
                    panel.add(fieldValue);
                } else if (fieldsID[i].get(this) instanceof Integer) {
                    JTextField fieldValue = new JTextField();
                    fieldValue.setBounds(130, 30 * (i + 1) + 10, 100, 20);
                    fieldValue.setText(fieldsID[i].get(this).toString());
                    fieldValue.addActionListener(e -> UpdateValues(this.getClass().getSimpleName()));

                    PlainDocument doc = (PlainDocument) fieldValue.getDocument();
                    doc.setDocumentFilter(new MyIntFilter());

                    textFieldlist.add(fieldValue);
                    panel.add(fieldValue);
                } else if (fieldsID[i].get(this) instanceof Boolean) {
                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected((boolean) fieldsID[i].get(this));
                    checkBox.setBounds(126, 30 * (i + 1) + 10, 50, 20);
                    checkBox.addActionListener(e -> UpdateValues(this.getClass().getSimpleName()));

                    checkBoxes.add(checkBox);
                    panel.add(checkBox);
                }

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
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

    public void Start() {

    }

    public void UpdateValues(String classString) {
        int propIndex = 0;
        int checkBoxFieldCount = 0;
        int textFieldCount = 0;

        for (int i = 0; i < MainProgram.selectedObject.properties.getSize(); i++) {
            if (MainProgram.selectedObject.properties.getElementAt(i).getClass().getSimpleName() == classString) {
                propIndex = i;
                break;
            }
        }

        try {
            for (int i = 0; i < fieldsID.length; i++) {
                if (fieldsID[i].get(this) instanceof String) {
                    fieldsID[i].set(MainProgram.selectedObject.properties.getElementAt(propIndex),
                            textFieldlist.get(textFieldCount).getText());
                    textFieldCount++;
                } else if (fieldsID[i].get(this) instanceof Integer) {
                    int value;
                    try {
                        value = (textFieldlist.get(textFieldCount).getText() != "")
                                ? Integer.parseInt(textFieldlist.get(textFieldCount).getText())
                                : 0;
                    } catch (Exception e) {
                        System.out.println("No input... Setting to 0.");
                        value = 0;
                    }
                    fieldsID[i].set(MainProgram.selectedObject.properties.getElementAt(propIndex), value);
                    textFieldCount++;
                } else if (fieldsID[i].get(this) instanceof Boolean) {
                    fieldsID[i].set(MainProgram.selectedObject.properties.getElementAt(propIndex),
                            checkBoxes.get(checkBoxFieldCount).isSelected());
                    checkBoxFieldCount++;
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(MainProgram.selectedObject.properties.getElementAt(propIndex).toString());
        RereshFrame();
    }

    void RereshFrame() {
        Test.main.invalidate();
        Test.main.validate();
        Test.main.repaint();
    }
}
