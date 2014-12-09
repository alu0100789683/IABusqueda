/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

/**
 *
 * @author eduardo
 */
public class MainFrame extends JFrame{
    public SimulatorPanel matrixPanel;
    public ControlPanel controlPanel;
    
    private JMenuBar menuBarPanel;
    
    private JMenu archiveMenu;
    private JMenu designMenu;
    private JMenu viewMenu;
    private JMenu algorithMenu;
    
    private JMenuItem newItemBar;
    private JMenuItem exitItemBar;
    private JMenuItem openItemBar;
    private JMenuItem saveItemBar;
    private JMenuItem saveAsItemBar;
    
    private JMenuItem randomItemBar;
    private JMenuItem openInsertItemBar;
    private JMenuItem movePj;
    
    private JMenuItem runItemBar;
    
    private JMenuItem showMatrixItemBar;
    
    public JScrollPane scrollPanel;

    
    
    public MainFrame(){
        super();
        initComponent();
        addComponent();
        showComponent();
        launchThread();
    }

    private void initComponent() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(300, 300));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        
        // Inicialize Simulator Panel
        Dimension size = new Dimension(0,0);
        int matrix_size = 32;
        matrixPanel = new SimulatorPanel(size,matrix_size);
        matrixPanel.init(
                "src/org/texture/bg.jpg", 
                "src/org/texture/rock.png",
                "src/org/texture/robot.png",
                "src/org/texture/finish.png",
                "src/org/texture/cross.png",
                3,4,1,matrix_size);
        // End simulator panel
        
        
        
        menuBarPanel = new JMenuBar();
        archiveMenu = new JMenu();
        designMenu = new JMenu();
        viewMenu = new JMenu();
        algorithMenu = new JMenu();
       
        newItemBar = new JMenuItem();
        openItemBar = new JMenuItem();
        saveItemBar = new JMenuItem();
        saveAsItemBar = new JMenuItem();
        exitItemBar = new JMenuItem();
        
        randomItemBar = new JMenuItem();
        openInsertItemBar = new JMenuItem();
        movePj = new JMenuItem();
        
        runItemBar=new JMenuItem();
        
        showMatrixItemBar = new JMenuItem();
        
        scrollPanel = new JScrollPane();
        
    }
    private void addComponent(){
        //MenuBar
        archiveMenu.setText("Archivo");
        designMenu.setText("Diseño");
        viewMenu.setText("Visualización");
        algorithMenu.setText("Heurística");
        
        //Archive
        newItemBar.setText("Nuevo");
        newItemBar.setName("NewItemBar");
        newItemBar.addMouseListener(new MouseController(this));
        
        openItemBar.setText("Abrir");
        
        saveItemBar.setText("Guardar");
        saveItemBar.setName("SaveItemBar");
        saveItemBar.addMouseListener(new MouseController(this));
        
        saveAsItemBar.setText("Guardar como ...");
        
        exitItemBar.setText("Salir");
        exitItemBar.setName("ExitItemBar");
        exitItemBar.addMouseListener(new MouseController(this));
        
        
        archiveMenu.add(newItemBar);
        archiveMenu.add(openItemBar);
        archiveMenu.add(saveItemBar);
        archiveMenu.add(saveAsItemBar);
        archiveMenu.add(exitItemBar);
        
        //design
        randomItemBar.setText("Generar obstaculos");
        openInsertItemBar.setText("Insertar meta");
        movePj.setText("Mover el astronauta");
        
        randomItemBar.setName("RandomItemBar");
        openInsertItemBar.setName("FinishInsertItemBar");
        movePj.setName("MovePjItemBar");
        
        randomItemBar.addMouseListener(new MouseController(this));
        openInsertItemBar.addMouseListener(new MouseController(this));
        movePj.addMouseListener(new MouseController(this));
        
        designMenu.add(randomItemBar);
        designMenu.add(openInsertItemBar);
        designMenu.add(movePj);
        //View
        
        showMatrixItemBar.setText("Mostrar/Ocultar area de trabajo");
        showMatrixItemBar.setName("ShowMatrixItemBar");
        showMatrixItemBar.addMouseListener(new MouseController(this));
        
        viewMenu.add(showMatrixItemBar);
        
        //Algorithm
        
        runItemBar.setText("Ejecutar/Parar búsqueda");
        runItemBar.setName("RunItemBar");
        runItemBar.addMouseListener(new MouseController(this));
        
        algorithMenu.add(runItemBar);
        
        //Menu
        menuBarPanel.add(archiveMenu);
        menuBarPanel.add(designMenu);
        menuBarPanel.add(viewMenu);
        menuBarPanel.add(algorithMenu);
        
        scrollPanel.setViewportView(matrixPanel);
        
        this.getContentPane().add(menuBarPanel, BorderLayout.PAGE_START);
        this.getContentPane().add(scrollPanel, BorderLayout.CENTER);
        
    }
    private void showComponent() {       
        this.pack();
        this.setVisible(true);
    }
    public void launchThread() {
        Thread animate = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        Thread.sleep(50);
                        matrixPanel.robot.run();
                        matrixPanel.repaint();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        animate.run();
    }
    public void destroy(){
        super.dispose();
    }
}
