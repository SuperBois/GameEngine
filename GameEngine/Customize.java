package GameEngine;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;

public class Customize extends JFrame
{
    Customize()
    {
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Customize");
        setLayout(null);
        setVisible(true);

        JLabel fgLabel = new JLabel("ForeGround color  :  ");
        fgLabel.setBounds(20,20,130,30);
        fgLabel.setBackground(Test.main.fg_color);
        this.add(fgLabel);

        JButton fgButton = new JButton("Choose");
        fgButton.setBounds(150, 20, 100, 30);
        fgButton.setHorizontalTextPosition(JButton.CENTER);
        fgButton.addActionListener(e->Test.main.fg_color = changeColor());
        this.add(fgButton);

        JLabel bgLabel = new JLabel("BackGround color  :  ");
        bgLabel.setBounds(20,70,130,30);
        bgLabel.setBackground(Test.main.bg_color);
        this.add(bgLabel);

        JButton bgButton = new JButton("Choose");
        bgButton.setBounds(150, 70, 100, 30);
        bgButton.setHorizontalTextPosition(JButton.CENTER);
        bgButton.addActionListener(e->Test.main.bg_color = changeColor());
        this.add(bgButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(60, 120, 150, 30);
        updateButton.setHorizontalTextPosition(JButton.CENTER);
        updateButton.addActionListener(e -> {Test.main.refreshFrame();
                                            Test.main.updateColor(); });
        this.add(updateButton);
    }

    private Color changeColor() 
    {
        Color color = JColorChooser.showDialog(null, "Choose a color", Color.black);
        return color;
    }
    void refreshFrame()
    {
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
}
