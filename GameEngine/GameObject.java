package GameEngine;


import java.io.Serializable;
import java.util.*;
import GameEngine.Components.AddComponent;
import GameEngine.Components.Transform;
import GameEngine.Components.Definition.GameComponent;

public class GameObject implements Serializable{
    public boolean canMove;
    public Dictionary<String , GameComponent> properties;
    // public DefaultListModel<GameComponent> properties;

    GameObject(){
        properties = new Hashtable<String , GameComponent>();
        canMove = true;

        Transform transform = new Transform();
        transform.setGameObject(this);

        AddComponent addComponent= new AddComponent();
        addComponent.setGameObject(this);

        this.properties.put("Transform", transform);
        this.properties.put("AddComponent", addComponent);
    }
}
