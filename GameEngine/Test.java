package GameEngine;

import GameEngine.Components.*;
//import java.awt.*;


public class Test {
    public static MainProgram main;
    public static void main(String[] args) {
        GameManager.getFilesPaths("D:\\");
        main = new MainProgram();
        // ---------------
        new SpriteRenderer();
        new NewScript();
        new PhysicsBody();
        new CharacterController();
        
        main.refreshFrame();
    }
}
