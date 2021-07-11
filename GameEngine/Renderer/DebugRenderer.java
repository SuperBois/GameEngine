package GameEngine.Renderer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import GameEngine.NewWindow;

import java.awt.Component;
import java.awt.Font;


public class DebugRenderer extends JLabel implements ListCellRenderer<String> {
    public DebugRenderer() {
        setOpaque(true);
    }

@Override
public Component getListCellRendererComponent(JList<? extends String> list, String string, int index, boolean isSelected,       
            boolean cellHasFocus) {
            
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("..\\Icons\\info_small.png"));
         
        setIcon(imageIcon);
        setText(string.toString());
        setFont(new Font("Courier", Font.BOLD, 15)); 
        
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
            NewWindow.main.refreshFrame();
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
 
        return this;
}
}