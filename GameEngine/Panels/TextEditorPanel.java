package GameEngine.Panels;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class TextEditorPanel extends JPanel{

    JTextArea textArea;
    JScrollPane scrollPane;
    JLabel fontLabel;
    JSpinner fontSizeSpinner;
    JButton fontColorButton;
    JComboBox<String> fontBox;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;

    public String filePath;

    public TextEditorPanel() {
        makePanel();
    }
    
    void makePanel(){
        
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(700, 700));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fontLabel = new JLabel("Font: ");
        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
        fontSizeSpinner.setValue(16);
        fontSizeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                textArea.setFont(
                        new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));
            }

        });

        fontColorButton = new JButton("Color");
        fontColorButton.addActionListener(e -> changeFontColor());

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        fontBox = new JComboBox<String>(fonts);
        fontBox.addActionListener(e-> changeFont());
        fontBox.setSelectedItem("Consolas");

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e->save());

        JPanel ribbon = new JPanel();

        ribbon.add(saveButton);
        ribbon.add(fontLabel);
        ribbon.add(fontSizeSpinner);
        ribbon.add(fontColorButton);
        ribbon.add(fontBox);

        this.add(ribbon, BorderLayout.NORTH);
        this.add(scrollPane);
    }

    public JPanel open(String Path){
        filePath = Path;
        File file = new File(Path);
        Scanner fileIn = null;
        makePanel();
        try {
            fileIn = new Scanner(file);
            if (file.isFile()) {
                while (fileIn.hasNextLine()) {
                    String line = fileIn.nextLine() + "\n";
                        textArea.append(line);
                }
            }
            
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            fileIn.close();
            }
            return this;
        }


        public JPanel open(File file){
            Scanner fileIn = null;
            makePanel();
            try {
                fileIn = new Scanner(file);
                if (file.isFile()) {
                    while (fileIn.hasNextLine()) {
                        String line = fileIn.nextLine() + "\n";
                            textArea.append(line);
                    }
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } finally {
                fileIn.close();
                }
            return this;
            }


        void save(){
            
        File file = new File(filePath);
        PrintWriter fileOut = null;

        try {
            fileOut = new PrintWriter(file);
            fileOut.println(textArea.getText());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            fileOut.close();
            }
        }
        
        void changeFontColor() {
            Color color = JColorChooser.showDialog(null, "Choose a color", Color.black);

            textArea.setForeground(color);
        }

        void changeFont() {
            textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
        }
}