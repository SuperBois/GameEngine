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
    // @Override
    // public void Update() 
    // {
    //     class1 = textFieldlist.get(0).getText();
    //     class2 = textFieldlist.get(1).getText();
    //     class3 = textFieldlist.get(2).getText();
    //     class4 = checkBoxes.get(0).isSelected();
    //     class5 = Integer.parseInt(textFieldlist.get(3).getText());
    //     System.out.println(this);
    // }
    @Override
    public String toString() 
    {
        return class1 + "\n" + class2 + "\n" + class3 + "\n" + class4 + "\n" + class5 ;
    }
}
