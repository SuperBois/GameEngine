package GameEngine;

import GameEngine.Components.*;
//import java.awt.*;


public class Test {
    public static MainProgram main;
    public static void main(String[] args) {
        GameManager.getFilesPaths("C:\\Users\\MuhammadMustafa\\Music\\Example");
        main = new MainProgram();
        // ---------------
        new SpriteRenderer();
        new NewScript();
        new PhysicsBody();
        new Move();
        
        main.refreshFrame();
    }
}
