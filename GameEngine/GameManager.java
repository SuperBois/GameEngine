package GameEngine;

import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import GameEngine.Components.Definition.GameComponent;

public class GameManager {
    public static DefaultListModel<GameObject>  objectsModel = new DefaultListModel<GameObject>();
    static DefaultListModel<Object> projectFilesModel = new DefaultListModel<Object>();
    public static DefaultListModel<GameComponent>  classNameModel = new DefaultListModel<GameComponent>();
    public static DefaultListModel<String> debugModel = new DefaultListModel<String>();
    public static int scores = 0;
    
    public static JLabel scoreLabel = new JLabel("0");
    public static boolean running = true, stop = false;

    public static String projectPath;
    public static int gameWindowWidth = 800, gameWindowHeight=600;

    public static JFrame frame = new JFrame();

    public static void getFilesPaths(String location)
    {   
        projectPath = String.copyValueOf(location.toCharArray());
        File Directory = new File(location);
        File[] list = Directory.listFiles();
        for (File file : list)
            projectFilesModel.addElement(file);
        // this method is called when the program is opened so we set default operation in it 
        frame.setDefaultCloseOperation(closeOperation());
    }

    public static void Play() {
            running = true; stop = false;

            frame.setSize(gameWindowWidth,gameWindowHeight);
            frame.setLayout(null);
            frame.setVisible(true);
            frame.setResizable(false);
            scoreLabel.setBounds(gameWindowWidth-100, 20, 80, 30);
            frame.add(scoreLabel);
            
            Runnable runnable1 = new Physics();
            Thread physicsThread = new Thread(runnable1);
            physicsThread.start();

            Runnable runnable2 = new Render();
            Thread renderThread = new Thread(runnable2);
            renderThread.start();
    }

    private static int closeOperation() {
        stop = true;
        return JFrame.DISPOSE_ON_CLOSE;
    }

    public static void stop(){
        stop = true;
    }
}
