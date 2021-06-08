package GameEngine;

import javax.swing.JLabel;

import GameEngine.Components.SpriteRenderer;
import GameEngine.Components.Transform;
import GameEngine.Components.Definition.GameComponent;

public class Render implements Runnable{
    // Renders all the sprites on their respective positions on the screen
    public static void RenderSprites(){
        int x_pos = 0,y_pos = 0;
        while(!GameManager.stop)
            if (GameManager.running){
            for (int i =0; i<GameManager.objectsModel.getSize();i++){
                GameObject object = GameManager.objectsModel.getElementAt(i);
                
                JLabel label = null;
                for(int j=0; j<object.properties.getSize();j++){
                    GameComponent component = object.properties.getElementAt(j);
                    if (component.getClass().getSimpleName().equals("Transform")){
                        Transform transformComponent = (Transform) component;
                        // x_pos = transformComponent.pos_x;
                        // y_pos = transformComponent.pos_y;
                        x_pos += 3;
                        y_pos += 3;
                    } else if(component.getClass().getSimpleName().equals("SpriteRenderer")){
                        SpriteRenderer spriteRenderer = (SpriteRenderer) component;
                        label = spriteRenderer.spriteLabel;
                    }
                    // since every object contains transform component so we only check if label == null
                    if (label != null){
                        label.setBounds(x_pos, y_pos, label.getWidth(), label.getHeight());
                    }
                }
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
}

    @Override
    public void run() {
        RenderSprites();
    }
}