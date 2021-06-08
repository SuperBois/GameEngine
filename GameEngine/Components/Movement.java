package GameEngine.Components;

import javax.swing.JLabel;
import java.awt.Color;

import GameEngine.MainProgram;
import GameEngine.Components.Definition.GameComponent;

public class Movement extends GameComponent {

    public Movement(){
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
        return new Movement();
    }
    
}
