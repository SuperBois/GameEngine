package GameEngine.Renderer;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import GameEngine.GameObject;
import GameEngine.Components.Transform;

public class ObjectRenderer extends JLabel implements ListCellRenderer<GameObject> {
    public ObjectRenderer() {
        setOpaque(true);
    }
 
    @Override
    public Component getListCellRendererComponent(JList<? extends GameObject> list, GameObject gameObject, int index,
        boolean isSelected, boolean cellHasFocus) {
          
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("..\\Icons\\object.png"));
         
        setIcon(imageIcon);
        Transform transform = (Transform) gameObject.properties.get("Transform");
        setText(transform.getName());
         

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