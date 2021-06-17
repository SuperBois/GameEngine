package GameEngine.Components;

import java.io.Serializable;

import GameEngine.Components.Definition.GameComponent;

public class Transform extends GameComponent implements Serializable {
    public String name;
    public int pos_x,pos_y;
    public int width, height;
    public float scale_x, scale_y;
    // default constructor
    public Transform(){
        setName("GameObject");
        this.pos_x = 0;
        this.pos_y = 0;
        this.width = 100;
        this.height = 100;
        this.scale_x = (float) 1.0;
        this.scale_y = (float) 1.0;
        removable = false;
        createPanel();
    }
    // sets the name 
    public void setName(String name) {
        this.name = name;
    }
    // sets the position
    void setPosition(int pos_x, int pos_y){
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
    // returns the name
    public String getName() {
        return name;
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
        return new Transform();
    }
    @Override
    public void stop() {
        // TODO Auto-generated method stub
        
    }
}
