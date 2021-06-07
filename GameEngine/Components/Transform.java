package GameEngine.Components;

import GameEngine.Components.Definition.GameComponent;

public class Transform extends GameComponent{
    public String name;
    public int pos_x,pos_y;
    // default constructor
    public Transform(){
        setName("GameObject");
        setPosition(0, 0);
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
    public String toString() {
        return name + "\n" + pos_x + "\n" + pos_y;
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
        return new Transform();
    }
}
