package GameEngine.Components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.io.Serializable;

import GameEngine.GameManager;
import GameEngine.MainProgram;
import GameEngine.NewWindow;

import GameEngine.Components.Definition.GameComponent;

public class SpriteRenderer extends GameComponent implements Serializable{

    public JLabel spriteLabel;
    public JLabel displayLabel;
    public Color color; 
    private ImageIcon image;

    public SpriteRenderer(){
        this.spriteLabel = new JLabel();
        
        this.color = Color.white;
        spriteLabel.setSize(100,100);
        

        GameManager.frame.add(spriteLabel);
        createPanel();
        
        this.displayLabel = new JLabel();
        displayLabel.setSize(100,100);
        MainProgram.displayPanel.add(displayLabel);

        if (this.gameObject!=null){
            Transform transform = (Transform) gameObject.properties.get("Transform");
            spriteLabel.setBounds(transform.pos_x,transform.pos_y, transform.width,transform.height);
        }
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public GameComponent newInstance() {
        return new SpriteRenderer();
    }
    
    @Override
    protected void remove(GameComponent gameComponent) {
        MainProgram.selectedObject.properties.remove(this.getClass().getSimpleName());
        MainProgram.displayPanel.remove(this.spriteLabel);
        NewWindow.main.showPanelofSelected();
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        
    }
}
