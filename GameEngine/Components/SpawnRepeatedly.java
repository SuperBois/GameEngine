package GameEngine.Components;

import GameEngine.GameManager;
import GameEngine.Components.Definition.GameComponent;

public class SpawnRepeatedly extends GameComponent{
    protected Transform transform;
    protected SpriteRenderer spriteRenderer;
    protected int x_pos, y_pos; // intital place of object
    public SpawnRepeatedly(){
        createPanel();
    }

    @Override
    public GameComponent newInstance() {
        return new SpawnRepeatedly();
    }

    @Override
    public void start() {
        transform = (Transform) gameObject.properties.get("Transform");
        spriteRenderer = (SpriteRenderer) gameObject.properties.get("SpriteRenderer");
        x_pos = transform.pos_x;
        y_pos = transform.pos_y;
    }

    @Override
    public void update() {
        if (transform.pos_y > GameManager.gameWindowHeight){
        spriteRenderer.spriteLabel.setBounds(transform.pos_x, y_pos, transform.width, transform.height);
        }
        if (transform.pos_x > GameManager.gameWindowWidth){
        spriteRenderer.spriteLabel.setBounds(x_pos, transform.pos_y, transform.width, transform.height);
        }
    }

    @Override
    public void stop() {
        
    }
}
