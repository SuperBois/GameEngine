package GameEngine.Components;

import GameEngine.GameManager;
import GameEngine.Components.Definition.GameComponent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NewScript extends GameComponent {
    public static String name;
    // No arg constructor
    public NewScript(){
        //writes a new file 
        if ((name!= null)&&(!isInClassNameModel()))
            try {
                writeNewFile(name);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        System.out.println("name is null");
    }

    private boolean isInClassNameModel() {
        for (int i=0; i<GameManager.classNameModel.getSize(); i++){
            String className = GameManager.classNameModel.elementAt(i).getClass().getSimpleName();
            if (className.equals(name))
                return true;
        }
        return false;
    }

    private static void writeNewFile(String name) throws IOException {
        File file = new File("GameEngine\\Components\\"+name+".java");
        FileWriter filewriter = null;
        filewriter = new FileWriter(file);
        filewriter.write("package GameEngine.Components;\n"+
            "import GameEngine.Components.Definition.GameComponent;\n"+
            "\n"+
            "public class "+name+" extends GameComponent {\n"+
            "public "+name+"() {\n"+
            "   // TODO initialize all the fields to avoid any errors\n"+
            "\n"+
            "    createPanel(); // create panel should be the last statement in the constructor\n"+
            "}\n"+
            "\n"+
            "@Override\n"+
            "public void Start() {\n"+
            "// TODO Auto-generated method stub\n"+
                "\n"+
                "}\n"+
            "\n"+
            "@Override\n"+
            "public void Update() {\n"+
            "// TODO Auto-generated method stub\n"+
            "\n"+
            "}\n"+
            "@Override\n"+
            "public GameComponent newInstance() {\n"+
             "   // TODO Auto-generated method stub\n"+
             "   return new "+name+"();\n"+
           " }\n"+
            "\n"+
            "}\n");
        filewriter.close();
        // read the contents of file Test.java
        FileReader gameManagerContents = new FileReader("GameEngine\\Test.java");
          BufferedReader bufferedReader=new BufferedReader(gameManagerContents);    
            String testContentString = "";
          int i;    
          while((i=bufferedReader.read())!=-1){
            testContentString += (char)i;
          }  
          bufferedReader.close();    
          gameManagerContents.close();
          //splits the given string to insert new constructor

          String[] parts = testContentString.split("// ---------------");

        file = new File("GameEngine\\Test.java");
        filewriter = new FileWriter(file);
        filewriter.write(parts[0]+
        "        // ---------------\n"+
        "        new "+name+"();\n"+
        parts[1]);
        filewriter.close();

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
        return new NewScript();
    }
    

}
