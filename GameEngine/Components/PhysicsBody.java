package GameEngine.Components;

import GameEngine.Components.Definition.GameComponent;

public class PhysicsBody extends GameComponent {
    public int mass;
    public boolean useGravity;
    public boolean isKinematic;

    protected Transform transform;
    protected SpriteRenderer spriteRenderer;
    protected int y_pos;

    public PhysicsBody() {
        mass = 1;
        useGravity = true;
        isKinematic = false;
        createPanel();
    }

    @Override
    public void Start() {
        transform = (Transform) gameObject.properties.get("Transform");
        spriteRenderer = (SpriteRenderer) gameObject.properties.get("SpriteRenderer");
        y_pos = transform.pos_y;
    }

    @Override
    public void Update() {
        if (gameObject.canMove && useGravity && spriteRenderer != null) {
            y_pos += (2 + mass);
            spriteRenderer.spriteLabel.setBounds(transform.pos_x, y_pos, transform.width, transform.height);
        }
    }

    @Override
    public void Stop() {
        spriteRenderer.spriteLabel.setBounds(transform.pos_x, transform.pos_y, transform.width, transform.height);
    }

    @Override
    public GameComponent newInstance() {
        return new PhysicsBody();
    }
}
