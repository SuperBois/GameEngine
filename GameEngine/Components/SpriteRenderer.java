package GameEngine.Components;

import javax.swing.JLabel;
import java.awt.Color;

import GameEngine.MainProgram;
import GameEngine.Components.Definition.GameComponent;

public class SpriteRenderer extends GameComponent {

    public JLabel spriteLabel;
    public Color color; 

    public SpriteRenderer(){
        this.spriteLabel = new JLabel();
        this.color = Color.white;
        spriteLabel.setSize(50,50);
        MainProgram.displayPanel.add(spriteLabel);
        createPanel();
    }

    @Override
    public void Start() {
        // TODO Auto-generated method stub
    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub
    }

    @Override
    public GameComponent newInstance() {
        return new SpriteRenderer();
    }
    
}
