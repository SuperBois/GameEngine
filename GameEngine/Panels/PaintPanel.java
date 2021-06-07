package GameEngine.Panels;

import javax.swing.*;
import java.awt.*;

public class PaintPanel extends JPanel{

    public PaintPanel(){
        this.setBackground(Color.red);
        this.add(new JLabel("Paint Panel"));
    }
}
