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
        
            Runnable runnable = new Render();
            Thread renderThread = new Thread(runnable);
            System.out.println("I am running");
            renderThread.start();
    }
}
