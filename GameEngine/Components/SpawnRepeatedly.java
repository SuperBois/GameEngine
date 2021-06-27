package GameEngine.Components;

import java.io.Serializable;
import java.util.Random;

import GameEngine.GameManager;
import GameEngine.Components.Definition.GameComponent;

public class SpawnRepeatedly extends GameComponent implements Serializable{
    protected Transform transform;
    protected SpriteRenderer spriteRenderer;
    public boolean spawnRepeatdly;
    protected int x_pos, y_pos; // intital place of object
    public SpawnRepeatedly(){
        spawnRepeatdly = true;
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
        
        if (spawnRepeatdly && transform.pos_y > GameManager.gameWindowHeight)
        {
            GameManager.scores += 20;
            GameManager.scoreLabel.setText(String.valueOf(GameManager.scores));
            
            Random random = new Random();
            transform.pos_x = random.nextInt(GameManager.gameWindowWidth-100);
            System.out.println(transform.pos_x);
            transform.pos_y = 0;
            spriteRenderer.spriteLabel.setBounds(transform.pos_x, 0, transform.width, transform.height);
        }
        if (spawnRepeatdly && transform.pos_x > GameManager.gameWindowWidth)
        {
            spriteRenderer.spriteLabel.setBounds(x_pos, transform.pos_y, transform.width, transform.height);
        }
    }

    @Override
    public void stop() {
        
    }
}
