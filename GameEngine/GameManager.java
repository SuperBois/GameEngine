package GameEngine;

import java.io.File;

import javax.swing.DefaultListModel;

import GameEngine.Components.Definition.GameComponent;

public class GameManager {
    public static DefaultListModel<GameObject>  objectsModel = new DefaultListModel<GameObject>();
    static DefaultListModel<Object> projectFilesModel = new DefaultListModel<Object>();
    public static DefaultListModel<GameComponent>  classNameModel = new DefaultListModel<GameComponent>();
    
    public static void getFilesPaths(String projectPath)
    {
        File Directory = new File(projectPath);
        File[] list = Directory.listFiles();
        for (File file : list)
            projectFilesModel.addElement(file);
 
    }
}
