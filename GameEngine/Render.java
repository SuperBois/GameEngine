package GameEngine;

import java.util.Enumeration;

import GameEngine.Components.Definition.GameComponent;

public class Render implements Runnable {
    // Renders all the sprites on their respective positions on the screen
    public static void RenderSprites() {
        // Calling the Start Method of every game Component
        for (int i = 0; i < GameManager.objectsModel.getSize(); i++) {
            GameObject object = GameManager.objectsModel.getElementAt(i);
            Enumeration<GameComponent> components = object.properties.elements();
            while (components.hasMoreElements()) {
                GameComponent gameComponent = components.nextElement();
                gameComponent.Start();
            }
        }

        // keep running until the user stops the game
        while (!GameManager.stop) {
            if (GameManager.running) {
                // looping through every gameObject in objects model
                for (int i = 0; i < GameManager.objectsModel.getSize(); i++) {
                    GameObject object = GameManager.objectsModel.getElementAt(i);
                    // Calling the Update method of every game component
                    Enumeration<GameComponent> components = object.properties.elements();
                    while (components.hasMoreElements()) {
                        GameComponent gameComponent = components.nextElement();
                        gameComponent.Update();
                    }
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // Reset the objects to their position
        for (int i = 0; i < GameManager.objectsModel.getSize(); i++) {
            GameObject object = GameManager.objectsModel.getElementAt(i);
            Enumeration<GameComponent> components = object.properties.elements();
            while (components.hasMoreElements()) {
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