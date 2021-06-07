package GameEngine.Renderer;
import java.awt.Component;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class FilesRenderer extends JLabel implements ListCellRenderer<Object> {
    public FilesRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        File f = (File) value;
        String name = f.getName();
        if (f.getName().contains("."))
        {
            if ((f.getName().contains(".zip")))
                setIcon(new ImageIcon("GameEngine/Icons/zip.png"));
            else if ((f.getName().contains(".java")))
                setIcon(new ImageIcon("GameEngine/Icons/java.png"));
            else
                setIcon(new ImageIcon("GameEngine/Icons/text.png"));
        }
        else
            setIcon(new ImageIcon("GameEngine/Icons/folder.png"));
        setText(name);
        setHorizontalAlignment(JLabel.CENTER);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);

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