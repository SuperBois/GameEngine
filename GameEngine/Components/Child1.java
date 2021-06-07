package GameEngine.Components;

import GameEngine.Components.Definition.GameComponent;

public class Child1 extends GameComponent {
    public String class1;
	public String class2;
	public String class3;
    public boolean class4;
    public int class5;
    
    public Child1(){
        this.class1 = "default";
        this.class2 = "default";
        this.class3 = "default";
        this.class4 = false;
        this.class5 = 0;
        createPanel();
    }

    public Child1(String arg1, String arg2, String arg3){
        this.class1 = arg1;
        this.class2 = arg2;
        this.class3 = arg3;
        this.class4 = false;
        this.class5 = 5;
        createPanel();
    }
    
    @Override
    public String toString() 
    {
        return class1 + "\n" + class2 + "\n" + class3 + "\n" + class4 + "\n" + class5 ;
    }

    @Override
    public void Start() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public GameComponent newInstance() {
        return new Child1();
    }

}
