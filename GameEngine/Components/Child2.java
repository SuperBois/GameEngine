package GameEngine.Components;

import GameEngine.Components.Definition.GameComponent;

public class Child2 extends GameComponent {
    public String class5;
	public String test;
	public String class7;

    public Child2(){
        this.class5 = "default";
        this.test = "default";
        this.class7 = "default";
        createPanel();
    }

    public Child2(String arg1, String arg2, String arg3){
        this.class5 = arg1;
        this.test = arg2;
        this.class7 = arg3;
        createPanel();
    }
    @Override
    public String toString() {
        return class5 + "\n" + test + "\n" + class7;
    }
}
