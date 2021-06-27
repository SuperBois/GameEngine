package GameEngine;

import java.awt.geom.*;

import GameEngine.Components.SpriteRenderer;

public class Physics implements Runnable {

    void PhysicsCalculations() {
        // keep running until the user stops the game
        while (!GameManager.stop) {
            if (GameManager.running) {
                // looping through every gameObject in objects model
                for (int i = 0; i < GameManager.objectsModel.getSize(); i++) {
                    GameObject object = GameManager.objectsModel.getElementAt(i);
                    
                    // checking if the object contains a physicsBody component and canMove?
                    if (object.properties.get("PhysicsBody") != null
                            && object.properties.get("SpriteRenderer") != null) {

                        for (int index = 0; index < GameManager.objectsModel.getSize(); index++) {
                            GameObject otherObject = GameManager.objectsModel.getElementAt(index);

                            if (object != otherObject && otherObject.properties.get("PhysicsBody") != null
                                    && otherObject.properties.get("SpriteRenderer") != null) {

                                SpriteRenderer otherRenderer = (SpriteRenderer) otherObject.properties
                                        .get("SpriteRenderer");
                                SpriteRenderer thisRenderer = (SpriteRenderer) object.properties.get("SpriteRenderer");
                                
                                Area areaA = new Area(thisRenderer.spriteLabel.getBounds());
                                Area areaB = new Area(otherRenderer.spriteLabel.getBounds());

                                if (areaA.intersects(areaB.getBounds2D())){
                                    object.canMove = false;
                                    Render.Stop();
                                    GameManager.frame.dispose();
                                }
                                else
                                    object.canMove = true;
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        PhysicsCalculations();

    }

}
