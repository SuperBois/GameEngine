package GameEngine;

import java.util.Enumeration;

import GameEngine.Components.Definition.GameComponent;

public class Render implements Runnable{
    // Renders all the sprites on their respective positions on the screen
    public static void RenderSprites(){
        // Calling the Start Method of every game Component
        for (int i =0; i<GameManager.objectsModel.getSize();i++){
            GameObject object = GameManager.objectsModel.getElementAt(i);
            Enumeration<GameComponent> components = object.properties.elements();
            while(components.hasMoreElements()){
                GameComponent gameComponent = components.nextElement();
                gameComponent.Start();
            }
        }
        // Calling the Update method of every game component
        while(!GameManager.stop){
            if (GameManager.running){
                for (int i =0; i<GameManager.objectsModel.getSize();i++){
                    GameObject object = GameManager.objectsModel.getElementAt(i);
                    Enumeration<GameComponent> components = object.properties.elements();
                    while(components.hasMoreElements()){
                        GameComponent gameComponent = components.nextElement();
                        gameComponent.Update();
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
        // Reset the objects to their position
        for (int i =0; i<GameManager.objectsModel.getSize();i++){
            GameObject object = GameManager.objectsModel.getElementAt(i);
            Enumeration<GameComponent> components = object.properties.elements();
            while(components.hasMoreElements()){
                GameComponent gameComponent = components.nextElement();
                gameComponent.Stop();
            }
        }
}

    @Override
    public void run() {
        RenderSprites();
    }
}