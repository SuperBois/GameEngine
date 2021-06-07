package GameEngine.Renderer;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import GameEngine.Components.Definition.GameComponent;

public class ComponentListRenderer extends JLabel implements ListCellRenderer<GameComponent> {
    public ComponentListRenderer() {
        setOpaque(true);
    }
 
    @Override
    public Component getListCellRendererComponent(JList<? extends GameComponent> list, GameComponent classObject, int index,
        boolean isSelected, boolean cellHasFocus) {
          
        ImageIcon imageIcon = new ImageIcon("GameEngine\\Icons\\java.png");
        setIcon(imageIcon);

        setText(classObject.getClass().getSimpleName());
         

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
 
        return this;
    }
     
}