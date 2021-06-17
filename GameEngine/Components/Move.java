package GameEngine.Components;

import java.io.Serializable;

import GameEngine.Components.Definition.GameComponent;

public class Move extends GameComponent implements Serializable {
    // moves the gameobject with the given speed

    public int x_speed;
    public int y_speed;
    protected Transform transform ;
    protected SpriteRenderer spriteRenderer;
    protected int x_pos, y_pos;
    
    public Move(){
        x_speed = 0;
        y_speed = 0;
        createPanel();
    }

    @Override
    public void start() {
        transform  = (Transform) gameObject.properties.get("Transform");
        spriteRenderer = (SpriteRenderer) gameObject.properties.get("SpriteRenderer");
        x_pos = transform.pos_x;
        y_pos = transform.pos_y;

    }

    @Override
    public void update() {
        if (gameObject.canMove){
            x_pos += x_speed;
            y_pos += y_speed;
            spriteRenderer.spriteLabel.setBounds(x_pos, y_pos, transform.width, transform.height);
    
        }
    }
    
    @Override
    public void stop(){
        spriteRenderer.spriteLabel.setBounds(transform.pos_x, transform.pos_y, transform.width, transform.height);
    }

    @Override
    public GameComponent newInstance() {
        return new Move();
    }
    
}
