package GameEngine;

import javax.swing.*;

import GameEngine.Components.AddComponent;
import GameEngine.Components.Transform;
import GameEngine.Components.Definition.GameComponent;

public class GameObject{
    public DefaultListModel<GameComponent> properties;

    GameObject(){
        properties = new DefaultListModel<GameComponent>();
        this.properties.addElement(new Transform());
        this.properties.addElement(new AddComponent());
    }
}
