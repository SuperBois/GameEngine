package GameEngine;

import GameEngine.Components.*;
//import java.awt.*;


public class Test {
    public static MainProgram main;
    public static void main(String[] args) {
        GameManager.getFilesPaths("D:\\");
        main = new MainProgram();
        // ---------------
        
        new Child1();
        new Child2();
        new NewScript();
        main.refreshFrame();
    }
}
