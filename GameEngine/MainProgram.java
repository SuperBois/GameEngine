package GameEngine;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GameEngine.Renderer.FilesRenderer;
import GameEngine.Renderer.JTabbedPaneCloseButton;
import GameEngine.Renderer.ObjectRenderer;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.awt.event.*;
import java.awt.*;

import GameEngine.Panels.TextEditorPanel;

public class MainProgram extends JFrame {
    static JPanel ribbonPanel;
    static JPanel heirarchyPanel;
    static public JPanel inspectorPanel;
    static JPanel infoPanel;
    static JPanel projectFilesPanel;
    static JPanel displayPanel;
    static JMenuBar menuBar;

    // Record of the debug statements on the debug console
    static JList<String> debugList;
    public DefaultListModel<String> debugModel;

    // Record of the created objects
    static JList<GameObject> objectsList;
    public DefaultListModel<GameObject> objectsModel;

    // Record of the created objects
    static JList<Object> projectFilesList;

    // total number of objects created
    static int objectsCount;
    public static boolean clickedHeirarchyElement = false;
    // a reference to selected object
    // GameObject selectedObject;

    // colors for main program
    protected Color fg_color;
    protected Color bg_color;

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
        if (debugModel == null) {
            debugModel = new DefaultListModel<String>(); // for test only
            System.out.println("Warning: The Debug model is null");
        }
        debugList = new JList<String>(debugModel);
        
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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        this.setVisible(true);
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
        ribbonPanel.setPreferredSize(new Dimension(100, 30));
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
        debugList.setBackground(Color.yellow);
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
        projectFilesList.setBackground(Color.orange);
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
        JPanel ribbon = new JPanel();
        ribbon.setLayout(new GridLayout(1, 10));
        // ribbon.setBackground(Color.cyan);
        // Button to test the other function
        JButton addButton = new JButton();
        addButton.setSize(50, 30);
        addButton.setText("Add Object");
        addButton.addActionListener(e -> addObject());
        ribbon.add(addButton);
        // Label to add space
        ribbon.add(new JLabel());
        ribbon.add(new JLabel());
        // Button to play the game
        JButton play = new JButton();
        play.setSize(50, 30);
        play.setText("Play");
        // play.addActionListener(e -> GameManager.Play());
        ribbon.add(play);
        // Button to pause the game
        JButton pause = new JButton();
        pause.setSize(50, 30);
        pause.setText("Pause");
        // pause.addActionListener(e -> addObject());
        ribbon.add(pause);
        // Button to stop the game
        JButton stop = new JButton();
        stop.setSize(50, 30);
        stop.setText("Stop");
        // stop.addActionListener(e -> addObject());
        ribbon.add(stop);
        // Label to add space
        ribbon.add(new JLabel());
        ribbon.add(new JLabel());
        // Button to stop the game
        JButton CustomizeEditor = new JButton();
        CustomizeEditor.setSize(50, 30);
        CustomizeEditor.setText("Customize");
        CustomizeEditor.addActionListener(e -> {
            // new Customize(this);

        });
        ribbon.add(CustomizeEditor);
        // ----------------------------------------------------
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
        for (int i = 0; i < selectedObject.properties.getSize(); i++) {
            JPanel componentPanel = selectedObject.properties.elementAt(i).panel;
            tempPanel.add(componentPanel);
        }

        scrollPane = new JScrollPane(tempPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        inspectorPanel.add(scrollPane);
        refreshFrame();
    }

    public void scrollToBottom() {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    void panelToTab(String name, JPanel panel){
        tabbedPane.add(name+space, panel);
    }
    public static void displayFiles(){   
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
        // TODO: Add the code to save the project
    }
    private void openProject() {
        // TODO: Add the code to open an existing project
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
}