package GameEngine;

import GameEngine.Components.*;
//import java.awt.*;


public class Test {
    public static MainProgram main;
    public static void main(String[] args) {
        GameManager.getFilesPaths("/home/MuhammadMustafa/Music/TestProject");
        // Initializing the components
        // ---------------
        new Child1();
        new Child2();
        new NewScript();
        main = new MainProgram();
    }
}
