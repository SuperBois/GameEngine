package GameEngine.Components.Definition;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.PlainDocument;
import javax.swing.filechooser.*;

import GameEngine.GameManager;
import GameEngine.GameObject;
import GameEngine.MainProgram;
import GameEngine.Test;
import GameEngine.Components.SpriteRenderer;
import GameEngine.Components.Filters.MyFloatFilter;
import GameEngine.Components.Filters.MyIntFilter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;

public abstract class GameComponent {

    // x and y coordinates of the object
    protected JPanel panel;
    protected ArrayList<JLabel> labels;
    protected ArrayList<JTextField> textFieldlist; // ArrayList to store TextFields
    protected ArrayList<JCheckBox> checkBoxes; // ArrayList to store checkboxes
    protected ArrayList<JButton> buttons; // ArrayList to store all buttons
    protected Field[] fieldsID; // Field Array tos store fields references
    protected boolean removable;
    protected GameObject gameObject;

    public GameComponent() {
        // Initializing declared instances.
        panel = new JPanel();
        textFieldlist = new ArrayList<JTextField>();
        checkBoxes = new ArrayList<JCheckBox>();
        labels = new ArrayList<JLabel>();
        buttons = new ArrayList<JButton>();
        fieldsID = this.getClass().getFields();

        String className = this.getClass().getSimpleName();
        if (!(isInClassNameModel(className) || className.equals("AddComponent") || className.equals("Transform"))) {
            GameManager.classNameModel.addElement(this);
        }
        this.removable = true;
    }

    private boolean isInClassNameModel(String className) {
        for (int i = 0; i < GameManager.classNameModel.getSize(); i++) {
            if (className.equals(GameManager.classNameModel.elementAt(i).getClass().getSimpleName()))
                return true;
        }
        return false;
    }

    public void createPanel() {
        panel.setLayout(null);
        panel.setBackground(Test.main.bg_color);
        // Setting size (specially Height) to accomodate all fields.
        panel.setBounds(0, 0, 270, 50 * fieldsID.length);
        panel.setPreferredSize(new Dimension(270, 50 * fieldsID.length));
        // setting panel border
        Border frameBorder = BorderFactory.createLineBorder(Color.black, 1);
        panel.setBorder(frameBorder);

        // close button functionality
        if (this.removable) {
            // setting closeButton
            JButton closeButton = new JButton();
            closeButton.setIcon(new ImageIcon(getClass().getResource("..\\Icons\\cross.png")));
            closeButton.addActionListener(e -> remove(this));
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

        // looping through all the fields to create respective textboxes and checkboxes.
        for (int i = 0; i < fieldsID.length; i++) {
            // Field label to display field name
            JLabel label = new JLabel(fieldsID[i].getName());
            label.setBounds(30, 30 * (i + 1) + 10, 100, 20);
            label.setForeground(Test.main.fg_color);
            labels.add(label);
            panel.add(label);

            try {
                if (fieldsID[i].get(this) instanceof String) {
                    // If the returned field value is String, then a textfield will be created
                    JTextField fieldValue = new JTextField((String) fieldsID[i].get(this));
                    fieldValue.setBounds(130, 30 * (i + 1) + 10, 100, 20);
                    fieldValue.setForeground(Color.black);
                    fieldValue.addActionListener(e -> UpdateValues(this.getClass().getSimpleName()));

                    // adding it to array list and panel
                    textFieldlist.add(fieldValue);
                    panel.add(fieldValue);
                } else if (fieldsID[i].get(this) instanceof Integer) {
                    // If the returned field value is String,
                    // then a textfield with only integers entrance will be created
                    JTextField fieldValue = new JTextField();
                    fieldValue.setBounds(130, 30 * (i + 1) + 10, 100, 20);
                    fieldValue.setForeground(Color.black);
                    fieldValue.setText(fieldsID[i].get(this).toString());
                    fieldValue.addActionListener(e -> UpdateValues(this.getClass().getSimpleName()));

                    PlainDocument doc = (PlainDocument) fieldValue.getDocument();
                    doc.setDocumentFilter(new MyIntFilter());

                    // adding it to array list and panel
                    textFieldlist.add(fieldValue);
                    panel.add(fieldValue);
                } else if (fieldsID[i].get(this) instanceof Boolean) {
                    // If the returned field value is Boolean,
                    // then a checkBox will be created.
                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected((boolean) fieldsID[i].get(this));
                    checkBox.setBounds(130, 30 * (i + 1) + 10, 20, 15);
                    checkBox.addActionListener(e -> UpdateValues(this.getClass().getSimpleName()));

                    // adding it to array list and panel
                    checkBoxes.add(checkBox);
                    panel.add(checkBox);
                } else if (fieldsID[i].get(this) instanceof JLabel) {
                    // If the returned field value is String, then a textfield will be created
                    JButton selectImageButton = new JButton("Select Image");
                    selectImageButton.setBounds(130, 30 * (i + 1) + 10, 130, 20);
                    selectImageButton.setForeground(Color.black);
                    selectImageButton.addActionListener(e -> {
                        chooseImage();
                        UpdateValues(this.getClass().getSimpleName());
                    });

                    // adding it to array list and panel
                    buttons.add(selectImageButton);
                    panel.add(selectImageButton);
                } else if (fieldsID[i].get(this) instanceof Color) {
                    // If the returned field value is String, then a textfield will be created
                    JButton selectColorButton = new JButton("Select Color");
                    selectColorButton.setBounds(130, 30 * (i + 1) + 10, 130, 20);
                    selectColorButton.setForeground(Color.black);
                    selectColorButton.addActionListener(e -> chooseColor());

                    // adding it to array list and panel
                    buttons.add(selectColorButton);
                    panel.add(selectColorButton);
                } else if (fieldsID[i].get(this) instanceof Float) {
                    // If the returned field value is String,
                    // then a textfield with only floats entrance will be created
                    JTextField fieldValue = new JTextField();
                    fieldValue.setBounds(130, 30 * (i + 1) + 10, 100, 20);
                    fieldValue.setForeground(Color.black);
                    fieldValue.setText(fieldsID[i].get(this).toString());
                    fieldValue.addActionListener(e -> UpdateValues(this.getClass().getSimpleName()));

                    PlainDocument doc = (PlainDocument) fieldValue.getDocument();
                    doc.setDocumentFilter(new MyFloatFilter());

                    // adding it to array list and panel
                    textFieldlist.add(fieldValue);
                    panel.add(fieldValue);
                }

            } catch (IllegalArgumentException e) {
                GameManager.debugModel.addElement("Field not found...");
            } catch (IllegalAccessException e) {
                GameManager.debugModel.addElement("Access to this field is not possible...");
            }
        }
    }

    private void chooseImage() {
        Enumeration<String> keys = MainProgram.selectedObject.properties.keys();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg", "bmp"));
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            while (keys.hasMoreElements()) {
                if (keys.nextElement().equals("SpriteRenderer")) {
                    // sets the image as icon of the label of the elementRF
                    SpriteRenderer spriteRenderer = (SpriteRenderer) MainProgram.selectedObject.properties
                            .get("SpriteRenderer");
                    spriteRenderer.setImage(new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath()));
                    spriteRenderer.spriteLabel.setIcon(spriteRenderer.getImage());
                }
            }
        }
    }

    private void chooseColor() {

    }

    protected void remove(GameComponent gameComponent) {
        MainProgram.selectedObject.properties.remove(this.getClass().getSimpleName());
        Test.main.showPanelofSelected();
    }

    public GameComponent newObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        return this.getClass().getConstructor().newInstance();
    }

    public void UpdateValues(String classString) {
        // To get property index of certain gameobject

        // Counters
        int checkBoxFieldCount = 0;
        int textFieldCount = 0;

        try {
            // looping through the fieldID's and arraylists to update the fields.
            for (int i = 0; i < fieldsID.length; i++) {

                if (fieldsID[i].get(this) instanceof String) {
                    fieldsID[i].set(MainProgram.selectedObject.properties.get(classString),
                            textFieldlist.get(textFieldCount).getText());
                    textFieldCount++;
                } else if (fieldsID[i].get(this) instanceof Integer) {
                    int value;
                    try {
                        value = (textFieldlist.get(textFieldCount).getText() != "")
                                ? Integer.parseInt(textFieldlist.get(textFieldCount).getText())
                                : 0;
                    } catch (Exception e) {
                        GameManager.debugModel.addElement("No input in integer field... Setting to 0.");
                        value = 0;
                    }

                    if (MainProgram.selectedObject.properties.get("SpriteRenderer") != null) {
                        SpriteRenderer spriteRenderer = (SpriteRenderer) MainProgram.selectedObject.properties
                                .get("SpriteRenderer");

                        if (fieldsID[i].getName().equals("pos_x")) {
                            spriteRenderer.spriteLabel.setLocation(value, spriteRenderer.spriteLabel.getY());

                        } else if (fieldsID[i].getName().equals("pos_y")) {
                            spriteRenderer.spriteLabel.setLocation(spriteRenderer.spriteLabel.getX(), value);

                        } else if (fieldsID[i].getName().equals("width")) {
                            spriteRenderer.spriteLabel.setSize(value, spriteRenderer.spriteLabel.getHeight());
                            spriteRenderer.spriteLabel.setIcon(spriteRenderer.getImage());

                        } else if (fieldsID[i].getName().equals("height")) {
                            spriteRenderer.spriteLabel.setSize(spriteRenderer.spriteLabel.getWidth(), value);
                            spriteRenderer.spriteLabel.setIcon(spriteRenderer.getImage());
                        
                        }
                    }

                    fieldsID[i].set(MainProgram.selectedObject.properties.get(classString), value);
                    textFieldCount++;

                } else if (fieldsID[i].get(this) instanceof Float) {
                    float value;
                    try {
                        value = (textFieldlist.get(textFieldCount).getText() != "")
                                ? Float.parseFloat(textFieldlist.get(textFieldCount).getText())
                                : 0;
                    } catch (Exception e) {
                        GameManager.debugModel.addElement("No input in float field... Setting to 0.");
                        value = 0;
                    }

                    if (MainProgram.selectedObject.properties.get("SpriteRenderer") != null) {
                        SpriteRenderer spriteRenderer = (SpriteRenderer) MainProgram.selectedObject.properties
                                .get("SpriteRenderer");

                        if (fieldsID[i].getName().equals("scale_x")) {
                            spriteRenderer.spriteLabel.setSize( (int) (spriteRenderer.spriteLabel.getWidth() * value),
                                    spriteRenderer.spriteLabel.getHeight());
                            spriteRenderer.spriteLabel.setIcon(spriteRenderer.getImage());

                        } else if (fieldsID[i].getName().equals("scale_y")) {
                            spriteRenderer.spriteLabel.setSize(spriteRenderer.spriteLabel.getWidth(),
                                    (int)(spriteRenderer.spriteLabel.getHeight() * value));
                            spriteRenderer.spriteLabel.setIcon(spriteRenderer.getImage());
                        }
                    }

                    fieldsID[i].set(MainProgram.selectedObject.properties.get(classString), value);
                    textFieldCount++;

                } else if (fieldsID[i].get(this) instanceof Boolean) {
                    fieldsID[i].set(MainProgram.selectedObject.properties.get(classString),
                            checkBoxes.get(checkBoxFieldCount).isSelected());
                    checkBoxFieldCount++;
                }
            }
        } catch (IllegalArgumentException e) {
            GameManager.debugModel.addElement("Field not found...");
        } catch (IllegalAccessException e) {
            GameManager.debugModel.addElement("Access to this field is not possible...");
        }
        Test.main.refreshFrame();
    }

    // Abstract methods
    public abstract GameComponent newInstance();

    public abstract void Start();

    public abstract void Update();

    public abstract void Stop();
    // -- Getter Setters
    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public ArrayList<JLabel> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<JLabel> labels) {
        this.labels = labels;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
