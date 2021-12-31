/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
/**
 *
 * @author mdani
 */



public class Main extends JFrame {
        MainProgram main;

        NewWindow newWindow = new NewWindow();

    /**
     * Creates new form MustafaBestCoder
     */
    public Main() {
        initComponents();
    }

    private void initComponents() {

        background = new JPanel();
        sidePanel = new JPanel();
        btn_help = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        btn_newProject = new JPanel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jSeparator1 = new JSeparator();
        btn_openProject = new JPanel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        btn_about = new JPanel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jPanel1 = new JPanel();
        jLabel10 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jLabel11 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);

        background.setBackground(new Color(255, 255, 255));
        background.addFocusListener(new java.awt.event.FocusAdapter() {
        });

        sidePanel.setBackground(new Color(54, 38, 90));

        btn_help.setBackground(new Color(64, 43, 100));

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setIcon(new ImageIcon(getClass().getResource("Icons\\icons8_help_24px.png"))); // NOI18N

        jLabel2.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new Color(204, 204, 204));
        jLabel2.setText("Help");

        GroupLayout btn_helpLayout = new GroupLayout(btn_help);
        btn_help.setLayout(btn_helpLayout);
        btn_helpLayout
                .setHorizontalGroup(
                        btn_helpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(btn_helpLayout.createSequentialGroup().addGap(60, 60, 60)
                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 39,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 56,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(139, Short.MAX_VALUE)));
        btn_helpLayout.setVerticalGroup(btn_helpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(btn_helpLayout.createSequentialGroup().addContainerGap()
                        .addGroup(btn_helpLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel1, GroupLayout.Alignment.LEADING,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(jLabel2, GroupLayout.Alignment.LEADING,
                                        GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addContainerGap()));

        btn_newProject.setBackground(new Color(64, 43, 100));
        btn_newProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_newProjectMousePressed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setIcon(new ImageIcon(getClass().getResource("Icons\\icons8_plus_24px.png"))); // NOI18N

        jLabel4.setFont(new Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new Color(204, 204, 204));
        jLabel4.setText("New Project..");

        GroupLayout btn_newProjectLayout = new GroupLayout(btn_newProject);
        btn_newProject.setLayout(btn_newProjectLayout);
        btn_newProjectLayout
                .setHorizontalGroup(btn_newProjectLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(btn_newProjectLayout.createSequentialGroup().addGap(18, 18, 18)
                                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 41,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18).addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 145,
                                        GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(78, Short.MAX_VALUE)));
        btn_newProjectLayout.setVerticalGroup(btn_newProjectLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(btn_newProjectLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(btn_newProjectLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel4).addComponent(jLabel3, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)));

        jLabel5.setFont(new Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new Color(204, 204, 204));
        jLabel5.setText("Project MFG9");

        jSeparator1.setPreferredSize(new Dimension(50, 5));

        btn_openProject.setBackground(new Color(64, 43, 100));
        btn_openProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_openProjectMousePressed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setIcon(new ImageIcon(getClass().getResource("Icons\\icons8_open_in_browser_24px.png"))); // NOI18N

        jLabel7.setFont(new Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new Color(204, 204, 204));
        jLabel7.setText("Open Project");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {

        });

        GroupLayout btn_openProjectLayout = new GroupLayout(btn_openProject);
        btn_openProject.setLayout(btn_openProjectLayout);
        btn_openProjectLayout
                .setHorizontalGroup(btn_openProjectLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(btn_openProjectLayout.createSequentialGroup().addGap(18, 18, 18)
                                .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 41,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18).addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 145,
                                        GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(78, Short.MAX_VALUE)));
        btn_openProjectLayout.setVerticalGroup(btn_openProjectLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(btn_openProjectLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(btn_openProjectLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel7).addComponent(jLabel6, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)));

        btn_about.setBackground(new Color(64, 43, 100));

        jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel8.setIcon(new ImageIcon(getClass().getResource("Icons\\icons8_info_24px_5.png"))); // NOI18N

        jLabel9.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new Color(204, 204, 204));
        jLabel9.setText("About");

        GroupLayout btn_aboutLayout = new GroupLayout(btn_about);
        btn_about.setLayout(btn_aboutLayout);
        btn_aboutLayout
                .setHorizontalGroup(
                        btn_aboutLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(btn_aboutLayout.createSequentialGroup().addGap(64, 64, 64)
                                        .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 35,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 56,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(139, Short.MAX_VALUE)));
        btn_aboutLayout.setVerticalGroup(btn_aboutLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(btn_aboutLayout.createSequentialGroup().addContainerGap()
                        .addGroup(btn_aboutLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel8, GroupLayout.Alignment.LEADING,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(jLabel9, GroupLayout.Alignment.LEADING,
                                        GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addContainerGap()));

        GroupLayout sidePanelLayout = new GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout
                .setHorizontalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(sidePanelLayout.createSequentialGroup().addGap(50, 50, 50).addComponent(jLabel5,
                                GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
                        .addComponent(btn_newProject, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_openProject, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_about, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_help, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(sidePanelLayout.createSequentialGroup().addGap(31, 31, 31).addComponent(jSeparator1,
                                GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)));
        sidePanelLayout.setVerticalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(sidePanelLayout.createSequentialGroup().addGap(20, 20, 20)
                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 60,
                                GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10,
                                GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btn_newProject, GroupLayout.PREFERRED_SIZE, 50,
                                GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_openProject, GroupLayout.PREFERRED_SIZE, 50,
                                GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(btn_about, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0).addComponent(btn_help, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

        jPanel1.setBackground(new Color(120, 132, 239));

        jLabel10.setBackground(new Color(255, 255, 255));
        jLabel10.setFont(new Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new Color(255, 255, 255));
        jLabel10.setText("Recently Opened Projects");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout
                        .createSequentialGroup().addGap(37, 37, 37).addComponent(jLabel10,
                                GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(425, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout
                        .createSequentialGroup().addGap(53, 53, 53).addComponent(jLabel10,
                                GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(57, Short.MAX_VALUE)));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTable1.setFont(new Font("Segoe UI", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] { { null, null, null }, { "TestProject", "5/06/2021 12:00AM", "Project File" },
                        { "Game Demo", "5/06/2021 3:00AM", "Project File" },
                        { "Test2", "27/06/2021 5:00PM", "Project File" },
                        { "PresentationDemo", "27/06/2021 7:00PM", "Project File" } },
                new String[] { "File Name", "Last Modified", "Type" }) {
            Class<?>[] types = new Class[] { java.lang.String.class, java.lang.String.class, java.lang.Object.class };

            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jTable1.setGridColor(new Color(255, 255, 255));
        jTable1.setIntercellSpacing(new Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new Color(120, 132, 239));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);
        }

        jLabel11.setFont(new Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("X");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });

        GroupLayout backgroundLayout = new GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(backgroundLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(sidePanel, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(backgroundLayout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 570,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING,
                                        backgroundLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel11).addContainerGap())))
                .addGroup(backgroundLayout.createSequentialGroup().addGap(298, 298, 298).addComponent(jPanel1,
                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)));
        backgroundLayout
                .setVerticalGroup(backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addComponent(sidePanel, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(backgroundLayout.createSequentialGroup().addComponent(jLabel11).addGap(29, 29, 29)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 197,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                background, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(background, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>


    //EVENTS

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        System.exit(0);
    }


    private void btn_newProjectMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        setColor(btn_newProject);
        resetColor(btn_openProject);
        newWindow.setVisible(true);     //Opens the NewWindow instance after clicking on New Project button
    }

    private File btn_openProjectMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        setColor(btn_openProject);
        resetColor(btn_newProject);
        File openDirectory = getFilePath().toFile();
        openDirectory = openDirectory.toPath().toFile();
        GameManager.projectPath = String.copyValueOf(openDirectory.toString().toCharArray());
        GameManager.getFilesPaths(openDirectory.toString());
        

        NewWindow.createWindow();
        this.dispose();
        // ---------------


        return openDirectory;
    }

    java.nio.file.Path getFilePath() {          //Method for getting file path from user.
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int response = fileChooser.showOpenDialog(null);

        if(response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            return file.toPath();
        }
       return null;
    }

    void setColor(JPanel panel) {
        panel.setBackground(new Color(85, 65, 118));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(64, 43, 100));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private JPanel background;
    private JPanel btn_about;
    private JPanel btn_help;
    private JPanel btn_newProject;
    private JPanel btn_openProject;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator1;
    private JTable jTable1;
    private JPanel sidePanel;
    // End of variables declaration
}