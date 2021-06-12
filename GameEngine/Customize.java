package GameEngine;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;

public class Customize extends JFrame
{
    Customize()
    {
        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Customize");
        setLayout(null);
        setVisible(true);
        Border frameBorder = BorderFactory.createLineBorder(Color.black, 1);

        JLabel fgLabel = new JLabel("ForeGround color  :  ");
        fgLabel.setBounds(20,20,130,30);
        this.add(fgLabel);

        JLabel fgColorTeller = new JLabel();
        fgColorTeller.setOpaque(true);
        fgColorTeller.setBounds(270,20,30,30);
        fgColorTeller.setBorder(frameBorder);
        fgColorTeller.setBackground(Test.main.fg_color);
        this.add(fgColorTeller);

        JButton fgButton = new JButton("Choose");
        fgButton.setBounds(150, 20, 100, 30);
        fgButton.setHorizontalTextPosition(JButton.CENTER);
        fgButton.addActionListener(e->{Test.main.fg_color = changeColor();
                                       fgColorTeller.setBackground(Test.main.fg_color);
                                       refreshFrame();
                                       Test.main.updateColor();
                                       Test.main.refreshFrame(); });
        
        this.add(fgButton);

        JLabel bgLabel = new JLabel("BackGround color  :  ");
        bgLabel.setBounds(20,70,130,30);
        bgLabel.setBackground(Test.main.bg_color);
        this.add(bgLabel);

        JLabel bgColorTeller = new JLabel();
        bgColorTeller.setOpaque(true);
        bgColorTeller.setBounds(270,70,30,30);
        bgColorTeller.setBorder(frameBorder);
        bgColorTeller.setBackground(Test.main.bg_color);
        this.add(bgColorTeller);

        JButton bgButton = new JButton("Choose");
        bgButton.setBounds(150, 70, 100, 30);
        bgButton.setHorizontalTextPosition(JButton.CENTER);
        bgButton.addActionListener(e->{Test.main.bg_color = changeColor();
                                       bgColorTeller.setBackground(Test.main.bg_color);
                                       refreshFrame();
                                       Test.main.updateColor();
                                       Test.main.refreshFrame(); });
        this.add(bgButton);
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