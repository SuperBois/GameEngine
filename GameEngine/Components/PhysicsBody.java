package GameEngine.Components;

import java.io.Serializable;

import GameEngine.Components.Definition.GameComponent;

public class PhysicsBody extends GameComponent implements Serializable{
    public int weight;
    public boolean useGravity;
    public boolean isStatic;

    protected int x_speed;
    protected int y_speed;

    protected Transform transform;
    protected SpriteRenderer spriteRenderer;
    protected int y_pos;

    public PhysicsBody() {
        weight = 1;
        useGravity = true;
        isStatic = false;
        createPanel();
    }

    public void addForce(int magnitude, int angle){
        
    }

    @Override
    public void start() {
        transform = (Transform) gameObject.properties.get("Transform");
        spriteRenderer = (SpriteRenderer) gameObject.properties.get("SpriteRenderer");
        y_pos = transform.pos_y;
    }

    @Override
    public void update() {
        if (gameObject.canMove && useGravity && spriteRenderer != null) {
            transform.pos_y += (2 + weight);
            spriteRenderer.spriteLabel.setBounds(transform.pos_x, transform.pos_y, transform.width, transform.height);
        }
    }

    @Override
    public void stop() {
        if (gameObject.canMove && useGravity && spriteRenderer != null) {
        spriteRenderer.spriteLabel.setBounds(transform.pos_x, transform.pos_y, transform.width, transform.height);
        }
    }

    @Override
    public GameComponent newInstance() {
        return new PhysicsBody();
    }
}
