package GameEngine;

import java.io.File;

import javax.swing.DefaultListModel;
import GameEngine.Components.Definition.GameComponent;

public class GameManager {
    public static DefaultListModel<GameObject>  objectsModel = new DefaultListModel<GameObject>();
    static DefaultListModel<Object> projectFilesModel = new DefaultListModel<Object>();
    public static DefaultListModel<GameComponent>  classNameModel = new DefaultListModel<GameComponent>();
    public static DefaultListModel<String> debugModel = new DefaultListModel<String>();
    
    public static boolean running = true, stop = false;
    public static void getFilesPaths(String projectPath)
    {
        File Directory = new File(projectPath);
        File[] list = Directory.listFiles();
        for (File file : list)
            projectFilesModel.addElement(file);
    }

    public static void Play() {
            running = true; stop = false;

            Runnable runnable1 = new Physics();
            Thread physicsThread = new Thread(runnable1);
            physicsThread.start();

            Runnable runnable2 = new Render();
            Thread renderThread = new Thread(runnable2);
            renderThread.start();
    }

    public static void stop(){
        stop = true;
        
    }
}
