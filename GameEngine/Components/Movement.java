package GameEngine.Components;

import java.io.Serializable;

import GameEngine.Components.Definition.GameComponent;

public class Movement extends GameComponent implements Serializable{

    public Movement(){
        createPanel();
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
        return new Movement();
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        
    }
    
}
