package GameEngine;

import java.util.Enumeration;

import javax.swing.JFrame;

import GameEngine.Components.Definition.GameComponent;

public class Render implements Runnable {
    // Renders all the sprites on their respective positions on the screen
    public static void RenderSprites() {
        JFrame frame = new JFrame("Game");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(Stop());
        // Calling the Start Method of every game Component
        for (int i = 0; i < GameManager.objectsModel.getSize(); i++) {
            GameObject object = GameManager.objectsModel.getElementAt(i);
            Enumeration<GameComponent> components = object.properties.elements();
            while (components.hasMoreElements()) {
                GameComponent gameComponent = components.nextElement();
                gameComponent.start();
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
                        gameComponent.update();
                    }
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Stop();
        frame.dispose();
    }

    static int Stop(){
         // Reset the objects to their position
         for (int i = 0; i < GameManager.objectsModel.getSize(); i++) {
            GameObject object = GameManager.objectsModel.getElementAt(i);
            Enumeration<GameComponent> components = object.properties.elements();
            while (components.hasMoreElements()) {
                GameComponent gameComponent = components.nextElement();
                gameComponent.stop();
            }
        }
        return JFrame.DISPOSE_ON_CLOSE;
    }

    @Override
    public void run() {
        RenderSprites();
    }
}