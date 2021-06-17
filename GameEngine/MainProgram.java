package GameEngine;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import GameEngine.Renderer.DebugRenderer;
import GameEngine.Renderer.FilesRenderer;
import GameEngine.Renderer.JTabbedPaneCloseButton;
import GameEngine.Renderer.ObjectRenderer;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.awt.event.*;
import java.awt.*;

import GameEngine.Components.Transform;
import GameEngine.Components.Definition.GameComponent;
import GameEngine.Panels.TextEditorPanel;

public class MainProgram extends JFrame {
    static JPanel ribbonPanel;
    static JPanel heirarchyPanel;
    public static  JPanel inspectorPanel;
    static JPanel infoPanel;
    static JPanel projectFilesPanel;
    public static JPanel displayPanel;
    static JMenuBar menuBar;

    // Record of the debug statements on the debug console
    static JList<String> debugList;

    // Record of the created objects
    static JList<GameObject> objectsList;

    // Record of the created objects
    static JList<Object> projectFilesList;

    // total number of objects created
    static int objectsCount;
    public static boolean clickedHeirarchyElement = false;
    // a reference to selected object
    // GameObject selectedObject;

    // colors for main program
    public Color fg_color;
    public Color bg_color;

    // JTabbedPane for different tabs in the window
    JTabbedPaneCloseButton tabbedPane;

    // stores a reference to the currently selected object
    public static GameObject selectedObject;

    String space;
    JPanel enginePanel;
    // contains everything in the inspector panel
    public JScrollPane scrollPane;
    private JPanel tempPanel;

    MainProgram() {
        tempPanel = new JPanel();
        tabbedPane = new JTabbedPaneCloseButton();
        // the list of all elements displayed in debugPanel
        if (GameManager.debugModel == null) {
            GameManager.debugModel = new DefaultListModel<String>(); // for test only
            System.out.println("Warning: The Debug model is null");
        }
        debugList = new JList<String>(GameManager.debugModel);
        debugList.setCellRenderer(new DebugRenderer());
        
        space = "    ";
        // creates a new jlist
        projectFilesList = new JList<Object>(GameManager.projectFilesModel);
        // sets the layout of the jlist and cell renderer
        projectFilesList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        projectFilesList.setVisibleRowCount(-1);
        projectFilesList.setCellRenderer(new FilesRenderer());
        // fixing the width and height of jlist items
        projectFilesList.setFixedCellHeight(80);
        projectFilesList.setFixedCellWidth(90);
        // debugList.setCellRenderer(new debugRenderer());

        // // the list of all objects stored in object List
        // if (objectsModel==null){
        // objectsModel = new DefaultListModel<GameObject>(); // for test only
        // System.out.println("Warning: The Object model is null");
        // }
        // creates a JList of all objects

        objectsList = new JList<GameObject>(GameManager.objectsModel);
        objectsList.setCellRenderer(new ObjectRenderer());

        menuBar = new JMenuBar();
        fg_color = Color.black;
        bg_color = Color.white;

        // Making the frame/ window
        this.setDefaultCloseOperation(close());
        this.setLayout(new BorderLayout(10, 10)); // ARGUMENTS FOR MARGINS BETWEEN COMPONENTS
        // this.setMinimumSize(new Dimension(1280,720));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setJMenuBar(menuBar);

        
        // JPanel componentPanel = new JPanel(new BorderLayout());
        // componentPanel.add(new JLayer<JTabbedPane>(tabbedPane, new CloseableTabbedPaneLayerUI()));
        // componentPanel.add(new JButton(new AbstractAction("add tab") {
        //   @Override
        //   public void actionPerformed(ActionEvent e) {
        //     tabbedPane.addTab("test", new JPanel());
        //   }
        // }), BorderLayout.SOUTH);
        
        CreateGUI();
        
        panelToTab("Engine",enginePanel);
        // UIManager.put("TabbedPane.tabInsets", new Insets(2, 2, 2, 50));
        // final JTabbedPane tabbedPane = new JTabbedPane();
        // tabbedPane.addTab("A", new JPanel());
        
        this.add(tabbedPane);
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setVisible(true);
    }

    private int close() {
        
        return JFrame.DISPOSE_ON_CLOSE;
    }

    private void CreateGUI() {

        enginePanel = new JPanel();
        enginePanel.setLayout(new BorderLayout(10, 10));
        // making the menu bar
        // making the file menu
        JMenu filemenu = new JMenu("File");
        // calls createProject method to create a new project
        JMenuItem newProject = new JMenuItem("New Project");
        newProject.addActionListener(e -> createProject());
        filemenu.add(newProject);
        // calls OpenProject method to open an existing Project
        JMenuItem openProject = new JMenuItem("Open Project");
        openProject.addActionListener(e -> openProject());
        filemenu.add(openProject);
        // calls saveProject methid to save the current Project
        JMenuItem saveProject = new JMenuItem("Save Project");
        saveProject.addActionListener(e -> saveProject());
        filemenu.add(saveProject);
        // calls buildProject method to build and export the current project
        JMenuItem exportBuild = new JMenuItem("Build/Export");
        exportBuild.addActionListener(e -> buildProject());
        filemenu.add(exportBuild);
        // calls exitProgram method to exit the program safely
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> exitProgram());
        filemenu.add(exit);
        menuBar.add(filemenu);

        // ---- Layout Menu
        
        JMenu layoutMenu = new JMenu("Layout");
        // calls method showInstructions which shows instruction about using the program
        JMenuItem addEnginePanel = new JMenuItem("Add Engine Panel");
        addEnginePanel.addActionListener(e -> panelToTab("Engine", enginePanel));
        layoutMenu.add(addEnginePanel);
        // adds help menu to menu Bar
        menuBar.add(layoutMenu);

        // --- Help Menu
        
        JMenu helpMenu = new JMenu("Help");
        // calls method showInstructions which shows instruction about using the program
        JMenuItem instruction = new JMenuItem("How To Use");
        instruction.addActionListener(e -> showInstructions());
        helpMenu.add(instruction);
        // calls method showAbout that shows the details about the program
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> showAbout());
        helpMenu.add(about);
        // adds help menu to menu Bar
        menuBar.add(helpMenu);

        // tabs for switching between debugPanel and projectFilesPanel
        JTabbedPane bottomPane = new JTabbedPane();
        // Button to test the function of displayPanel
        ribbonPanel = new JPanel(); // at the top of the window
        heirarchyPanel = new JPanel(); // at the left of the window
        inspectorPanel = new JPanel(); // at the right of the window
        infoPanel = new JPanel(); // at the bottom of the window
        projectFilesPanel = new JPanel();
        displayPanel = new JPanel(); // at the center of the window
        // SETTING THE COLOURS OF PANELS
        ribbonPanel.setBackground(bg_color);
        heirarchyPanel.setBackground(bg_color);
        inspectorPanel.setBackground(bg_color);
        infoPanel.setBackground(bg_color);
        projectFilesPanel.setBackground(bg_color);
        displayPanel.setBackground(Color.lightGray);
        // setting the layouts as required
        displayPanel.setLayout(null);
        infoPanel.setLayout(new GridLayout(1, 1));
        projectFilesPanel.setLayout(new GridLayout(1, 1));
        inspectorPanel.setLayout(new GridLayout(1, 1));
        heirarchyPanel.setLayout(new GridLayout(1, 1));
        ribbonPanel.setLayout(new GridLayout(1, 1));
        // SETTING THE SIZE OF PANELS
        ribbonPanel.setPreferredSize(new Dimension(100, 40));
        heirarchyPanel.setPreferredSize(new Dimension(300, 100));
        inspectorPanel.setPreferredSize(new Dimension(300, 100));
        infoPanel.setPreferredSize(new Dimension(100, 200));
        projectFilesPanel.setPreferredSize(new Dimension(100, 200));
        // ---------------------- PREPARES THE infoPanel FOR DISPLAYING DEBUG MESSAGES
        // creates a JList from the given debug Strings

        // sets the selection mode and adds the JList to a scroll pane
        debugList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane debugScrollPane = new JScrollPane(debugList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // changing the appearance of the text displayed in infopanel
        debugList.setBackground(bg_color);
        debugList.setForeground(fg_color);
        infoPanel.add(debugScrollPane);
        // ---------------------- PREPARES THE projectFilesPanel FOR DISPLAYING Project
        // Files
        // creates a JList from the given debug Strings

        // sets the selection mode
        projectFilesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // adding a double click listener
        projectFilesList.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
        
                    // Double-click detected
                    int index = projectFilesList.locationToIndex(evt.getPoint());
                    File file = (File) GameManager.projectFilesModel.elementAt(index);
                    // the index of current panel in tabbed pane
                    int panelIndex = findTabByName(file.getName()+space, tabbedPane);
                    if (panelIndex == -1){
                        // if the tab of same name does not exist
                        TextEditorPanel textEditorPanel =  new TextEditorPanel();
                        textEditorPanel.filePath = file.getPath();
                        System.out.println(file.getPath());
                        panelToTab(file.getName(), textEditorPanel.open(file));
                        tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(file.getName()+space));
                    } else {
                        // if it exists then switch to it 
                        tabbedPane.setSelectedIndex(panelIndex);
                    }
                }
            }
        });
        // adding the jlist to scrollpane
        JScrollPane filesScrollPane = new JScrollPane(projectFilesList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // changing the appearance of the text displayed in infopanel
        projectFilesList.setBackground(bg_color);
        projectFilesList.setForeground(fg_color);
        projectFilesPanel.add(filesScrollPane);

        // --------------------------- Adds two tabs on the bottom tabbed pane
        bottomPane.add("Files", projectFilesPanel);
        bottomPane.add("Console", infoPanel);
        
        // -------------------------------------------------------------------------------

        // ---------------------- PREPARES THE HEIRARCHY FOR DISPLAYING ALL OBJECTS

        // sets the selection mode and adds the JList to a scroll pane
        objectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane objectsScrollPane = new JScrollPane(objectsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                JPopupMenu objectPopupMenu = new JPopupMenu();
        JMenuItem deleteOption = new JMenuItem("Delete");
        deleteOption.addActionListener(e->{GameManager.objectsModel.removeElement(selectedObject);
                                           refreshFrame();
                                          }
                                      );
        objectPopupMenu.add(deleteOption);

        objectsList.addMouseListener(new MouseAdapter()
        {
                public void mousePressed(MouseEvent evt) {
                    if (evt.isPopupTrigger() && !(selectedObject == null)) {
                        showMenu(evt);
                    }
                }
                public void mouseReleased(MouseEvent evt) {
                    if ((evt.isPopupTrigger() && !(selectedObject == null))) {
                        showMenu(evt);
                    }
                }
                private void showMenu(MouseEvent evt) {
                    objectPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());    
            }
        });

        // selection listener for objects list
        // displays the panel of currently selected object
        objectsList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    showPanelofSelected();
                }
            }
        });

        // changing the appearance of the text displayed in infopanel
        objectsList.setBackground(bg_color);
        objectsList.setForeground(fg_color);
        heirarchyPanel.add(objectsScrollPane);
        // -------------------------------------------------------------------------------

        // ----------------------PREPARES THE RIBBON PANEL AND THE MENU BAR

        // ---------------Preparing the ribbon Panel
        JPanel centerPanel = new JPanel();
        JButton play = new JButton();
          play.setIcon(new ImageIcon(getClass().getResource("Icons\\play.png")));
          play.setBackground(Color.black);
          play.setFocusable(false);
          play.addActionListener(e -> {
              play.setEnabled(false);
              GameManager.Play();});
          centerPanel.add(play);
          // Button to pause the game
          JButton pause = new JButton();
          pause.setIcon(new ImageIcon(getClass().getResource("Icons\\pause.png")));
          pause.setBackground(Color.BLACK);
          pause.setFocusable(false);
        //   pause.addActionListener(e -> {
        //     // play.setEnabled(true);  
        //     GameManager.running =false;});
          centerPanel.add(pause);
          // Button to stop the game
          JButton stop = new JButton();
          stop.setIcon(new ImageIcon(getClass().getResource("Icons\\stop.png")));
          stop.setBackground(Color.black);
          stop.setFocusable(false);
          stop.addActionListener(e -> {
            play.setEnabled(true); 
            GameManager.stop();});
          centerPanel.add(stop);
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.setPreferredSize(new Dimension(400, 50));
        centerPanel.setMaximumSize(new Dimension(400, 50));
        // Label to add space
        JPanel leftPanel = new JPanel();
        leftPanel.setSize(new Dimension(100,30));
        leftPanel.setMaximumSize(new Dimension(100,30));
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton addButton = new JButton("Add Object");
        addButton.addActionListener(e -> addObject());
        
        leftPanel.add(addButton);

        JPanel leftspacePanel = new JPanel();
        leftspacePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel rightspacePanel = new JPanel();
        rightspacePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setSize(new Dimension(100,30));
        rightPanel.setMaximumSize(new Dimension(100,30));
        rightPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JButton CustomizeEditor = new JButton("Customize");
        CustomizeEditor.addActionListener(e -> {
            new Customize();
        });

        rightPanel.add(CustomizeEditor);
        JPanel ribbon = new JPanel();
        ribbon.setLayout(new BoxLayout(ribbon, BoxLayout.X_AXIS));
        // ----------------------------------------------------
        ribbon.add(leftPanel);
        ribbon.add(leftspacePanel);
        ribbon.add(centerPanel);
        ribbon.add(rightspacePanel);
        ribbon.add(rightPanel);
        ribbonPanel.add(ribbon);
        // ---------------------------------------------------
        // ------------------Adding Dragging option to display Panel

        // ADDING PANELS TO THE FRAME
        enginePanel.add(ribbonPanel, BorderLayout.NORTH);
        enginePanel.add(heirarchyPanel, BorderLayout.WEST);
        enginePanel.add(bottomPane, BorderLayout.SOUTH);
        enginePanel.add(inspectorPanel, BorderLayout.EAST);
        enginePanel.add(displayPanel, BorderLayout.CENTER);
    }

    private void addObject() {
        GameManager.objectsModel.addElement(new GameObject());
    }

    public void showPanelofSelected() {
        inspectorPanel.removeAll();
        tempPanel.removeAll();

        // changing the layout of inspector panel
        tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));

        selectedObject = objectsList.getSelectedValue();

        if (!(selectedObject == null))
        {
            Transform transform = (Transform) selectedObjectproperties.get("Transform");
            System.out.println(transform.name);
            Enumeration<String> keys = MainProgram.selectedObject.properties.keys();
            while (keys.hasMoreElements()){
                JPanel componentPanel = selectedObject.properties.get(keys.nextElement()).getPanel();
                componentPanel.setBackground(bg_color);
                componentPanel.setForeground(fg_color);
                tempPanel.add(componentPanel);
            }
        }
        tempPanel.setBackground(bg_color);
        tempPanel.setForeground(fg_color);
        scrollPane = new JScrollPane(tempPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        inspectorPanel.add(scrollPane);
        refreshFrame();
    }

    public void scrollToTop() {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMinimum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    void panelToTab(String name, JPanel panel){
        tabbedPane.add(name+space, panel);
    }

    private void exitProgram() {
        // TODO: Add the code to exit the program safely
    }
    private void showAbout() {
        // TODO: Add the code to show the about of program
    }
    private void showInstructions() {
        // TODO: Add the code to show Instructions of a program
    }
    private void buildProject() {
        // TODO: Add the code to build and export the current project
    }
    private void saveProject() {
        try {
            // opening a file for writing output
            FileOutputStream fileOutputStream = new FileOutputStream(GameManager.projectPath+"\\object.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // writing "array" to file
            objectOutputStream.writeObject(GameManager.objectsModel);
            objectOutputStream.close();

            // opening a file for writing output
            fileOutputStream = new FileOutputStream(GameManager.projectPath+"\\classes.ser");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // writing "array" to file
            objectOutputStream.writeObject(GameManager.classNameModel);
            objectOutputStream.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void openProject() {
        try {

           // opening a file for writing output
           FileInputStream fileInputStream = new FileInputStream(GameManager.projectPath+"\\classes.ser");
           ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

           DefaultListModel classes = (DefaultListModel) objectInputStream.readObject();
           // writing "array" to file
           GameManager.classNameModel.removeAllElements();
           for (int i =0; i<classes.size();i++){
               GameComponent gameComponent = (GameComponent)classes.elementAt(i);
               GameManager.classNameModel.addElement(gameComponent);
           }
           objectInputStream.close();


            // opening a file for writing output
            fileInputStream = new FileInputStream(GameManager.projectPath+"\\object.ser");
            objectInputStream = new ObjectInputStream(fileInputStream);

            DefaultListModel objects = (DefaultListModel) objectInputStream.readObject();
            // writing "array" to file
            GameManager.objectsModel.removeAllElements();
            for (int i =0; i<objects.size();i++){
                GameObject gameObject = (GameObject)objects.elementAt(i);
                GameManager.objectsModel.addElement(gameObject);
                Transform transform = (Transform) gameObject.properties.get("Transform");
                System.out.println(transform.name);
            }
            objectInputStream.close();
           refreshFrame();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
    }
    private void createProject() {
        // TODO: Add the code to create a new Project
    }
    public void refreshFrame() {
        // refreshes and updates the gui of the current frame
        this.invalidate();
        this.validate();
        this.repaint();
    }
    public int findTabByName(String title, JTabbedPane tab)  
    {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++) 
        {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }
        return -1;
    }
    
    public void updateColor(){
        // updating the colors of objects list
        objectsList.setBackground(bg_color);
        objectsList.setForeground(fg_color);
        // updating the colors of debug Panel
        debugList.setBackground(bg_color);
        debugList.setForeground(fg_color);
        // updating the colors of files Panel
        projectFilesList.setBackground(bg_color);
        projectFilesList.setForeground(fg_color);

        inspectorPanel.setBackground(bg_color);
        inspectorPanel.setForeground(fg_color);

        tempPanel.setBackground(bg_color);
        tempPanel.setForeground(fg_color);

        for (int i = 0; i < GameManager.objectsModel.getSize(); i++ )
        {
            Enumeration<String> keys = GameManager.objectsModel.getElementAt(i).properties.keys();
            while (keys.hasMoreElements())
            {
                String propertyKey = keys.nextElement();
                GameManager.objectsModel.getElementAt(i).properties.get(propertyKey).getPanel().setBackground(bg_color);
                GameManager.objectsModel.getElementAt(i).properties.get(propertyKey).getPanel().setForeground(fg_color);
                for (int k = 0; k < GameManager.objectsModel.getElementAt(i).properties.get(propertyKey).getLabels().size(); k++) 
                {
                    GameManager.objectsModel.getElementAt(i).properties.get(propertyKey).getLabels().get(k).setForeground(fg_color);
                }
            }
        }
    }
}