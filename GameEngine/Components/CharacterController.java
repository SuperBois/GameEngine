package GameEngine.Components;

import java.awt.event.*; 
import java.io.Serializable;

import GameEngine.GameManager;
import GameEngine.Components.Definition.GameComponent;

public class CharacterController extends GameComponent implements Serializable {
    // moves the gameobject with the given speed

    protected Transform transform ;
    protected SpriteRenderer spriteRenderer;
    protected int x_pos, y_pos;
    public boolean up,down,left,right;
    
    public CharacterController(){
        up = true;
        down = true;
        right = true;
        left = true;
        createPanel();
    }

    @Override
    public void start() {
        transform  = (Transform) gameObject.properties.get("Transform");
        spriteRenderer = (SpriteRenderer) gameObject.properties.get("SpriteRenderer");
        x_pos = transform.pos_x;
        y_pos = transform.pos_y;
        this.gameObject.canMove = false;
        GameManager.frame.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                
                if ( left && key == KeyEvent.VK_LEFT) {
                    x_pos -= 5;
                    System.out.println("left key pressed");
                }
            
                if (right && key == KeyEvent.VK_RIGHT) {
                    x_pos += 5;
                    System.out.println("right key pressed");
                }
            
                if (up && key == KeyEvent.VK_UP) {
                    y_pos -= 5;
                    System.out.println("up key pressed");
                }
            
                if (down && key == KeyEvent.VK_DOWN) {
                    y_pos += 5;
                    System.out.println("down key pressed");
                }
                spriteRenderer.spriteLabel.setBounds(x_pos, y_pos, transform.width, transform.height);
                System.out.println("setposition");
            }

            @Override
            public void keyReleased(KeyEvent e) {
        }}
    );
    }

    @Override
    public void update() {
        if (gameObject.canMove){
            spriteRenderer.spriteLabel.setBounds(x_pos, y_pos, transform.width, transform.height);
    
        }
    }
    
    @Override
    public void stop(){
        if (spriteRenderer != null)
            spriteRenderer.spriteLabel.setBounds(transform.pos_x, transform.pos_y, transform.width, transform.height);
    }

    @Override
    public GameComponent newInstance() {
        return new CharacterController();
    }
}