package GameEngine.Panels;
import javax.swing.*;

import java.awt.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Path;
import java.util.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;

public class TextEditorPanel extends JPanel {

    JTextPane textArea;
    JScrollPane scrollPane;
    JLabel fontLabel;
    JSpinner fontSizeSpinner;
    JButton fontColorButton;
    JComboBox fontBox;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;


    public String filePath;


    public TextEditorPanel() {

        this.setLayout(new BorderLayout());
        this.setSize(800, 800);
        this.setVisible(true);
        

        final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet attrMAGENTA = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.MAGENTA);
        final AttributeSet attrRED = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
        final AttributeSet attrBLUE = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(36, 172, 242));
        final AttributeSet attrWHITE = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(220, 217, 235));
        final AttributeSet attrYellow = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.YELLOW);

        DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);

                if (before < 0)
                    before = 0;

                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if (text.substring(wordL, wordR).matches("(\\W)*(private|public|protected)"))
                            setCharacterAttributes(wordL, wordR - wordL, attrMAGENTA, false);
                        else if (text.substring(wordL, wordR).matches("(\\W)*(byte|short|out|int|long|boolean|float|double|this|print|printf|println|)"))
                            setCharacterAttributes(wordL, wordR - wordL, attrBLUE, false);
                        else if (text.substring(wordL, wordR).matches("(\\W)*(break|continue|static|return|System)"))
                            setCharacterAttributes(wordL, wordR - wordL, attrRED, false);
                        else if (text.substring(wordL, wordR).matches("(\\W)*(import|while|for|package|new|if|else|true|false|try|case|catch|finally)"))
                            setCharacterAttributes(wordL, wordR - wordL, attrYellow, false);
                        else
                            setCharacterAttributes(wordL, wordR - wordL, attrWHITE, false);
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0)
                    before = 0;
                int after = findFirstNonWordChar(text, offs);

                if (text.substring(before, after).matches("(\\W)*(private|public|protected)"))
                    setCharacterAttributes(before, after - before, attrMAGENTA, false);
                else if (text.substring(before, after).matches("(\\W)*(try|catch|finally)"))
                    setCharacterAttributes(before, after - before, attrBLUE, false);
                else if (text.substring(before, after).matches("(\\W)*()"))
                    setCharacterAttributes(before, after - before, attrRED, false);
                else
                    setCharacterAttributes(before, after - before, attrWHITE, false);

            }
        };
        
        textArea = new JTextPane(doc);
        textArea.setCaretColor(Color.WHITE);
        textArea.setBackground(Color.darkGray);
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

        fontBox = new JComboBox(fonts);
        fontBox.addActionListener(e -> changeFont());
        fontBox.setSelectedItem("Consolas");

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> save());

        this.add(saveButton);
        this.add(fontLabel);
        this.add(fontSizeSpinner);
        this.add(fontColorButton);
        this.add(fontBox);
        this.add(scrollPane);
        
        JPanel ribbon = new JPanel();

        ribbon.add(saveButton);
        ribbon.add(fontLabel);
        ribbon.add(fontSizeSpinner);
        ribbon.add(fontColorButton);
        ribbon.add(fontBox);

        this.add(ribbon, BorderLayout.NORTH);
        this.add(scrollPane);
        setVisible(true);
    }

    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) 
                break;
        }
        return index;
    }

    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) 
                break;
            index++;
        }
        return index;
    }

    public JPanel open(File file) {
        Scanner fileIn = null;

        try {
            fileIn = new Scanner(file);
            if (file.isFile()) {
                while (fileIn.hasNextLine()) {
                    String line = fileIn.nextLine() + "\n";
                    textArea.setText(textArea.getText().concat(line));
                    
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            fileIn.close();
        }
        return this;
    }

    void save() {

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
        JColorChooser colorChooser = new JColorChooser();

        Color color = colorChooser.showDialog(null, "Choose a color", Color.black);

        textArea.setForeground(color);
    }

    void changeFont() {
        textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
    }
}