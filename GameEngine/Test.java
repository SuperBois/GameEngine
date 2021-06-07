package GameEngine;

import GameEngine.Components.*;
//import java.awt.*;


public class Test {
    public static MainProgram main;
    public static void main(String[] args) {
        GameManager.getFilesPaths();
        new Child1();
        new Child2();
        main = new MainProgram();
    }
}
