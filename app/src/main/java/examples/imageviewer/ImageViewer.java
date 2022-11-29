/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2000 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package examples.imageviewer;

/** This class is an entry point of the simple image viewer.
 * It creates and shows the main application frame.
 */
public class ImageViewer extends javax.swing.JFrame {

    /** Image Viewer constructor.
     * It initializes all GUI components [menu bar, menu items, desktop pane, etc.].
     */
    public ImageViewer() {
        initComponents();
        pack();
        setBounds( 100, 100, 400, 400 );
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        desktop = new javax.swing.JDesktopPane();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        exitMenuItem = new javax.swing.JMenuItem();

        setTitle("Image Viewer");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        getAccessibleContext().setAccessibleName("Image Viewer Frame");
        getContentPane().add(desktop, java.awt.BorderLayout.CENTER);
        desktop.getAccessibleContext().setAccessibleName("Image Desktop");
        desktop.getAccessibleContext().setAccessibleDescription("Image desktop");

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");
        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(openMenuItem);
        openMenuItem.getAccessibleContext().setAccessibleName("Open Menu Item");
        openMenuItem.getAccessibleContext().setAccessibleDescription("Open menu item.");

        fileMenu.add(jSeparator1);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(exitMenuItem);
        exitMenuItem.getAccessibleContext().setAccessibleName("Exit Menu Item");
        exitMenuItem.getAccessibleContext().setAccessibleDescription("Exit menu item.");

        mainMenuBar.add(fileMenu);
        fileMenu.getAccessibleContext().setAccessibleName("File Menu");
        fileMenu.getAccessibleContext().setAccessibleDescription("File menu.");

        setJMenuBar(mainMenuBar);
        mainMenuBar.getAccessibleContext().setAccessibleName("Main Menu Bar");
        mainMenuBar.getAccessibleContext().setAccessibleDescription("Main menu bar.");

    }//GEN-END:initComponents

    /** This method is called when File -> Exit menu item is invoked.
     * It closes the application.
     * @param evt ActionEvent instance passed from actionPerformed event.
     */
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit( 0 );
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /** This method is called when File -> Open menu item is invoked.
     * It displays a dialog to choose the image file to be opened and displayed.
     * @param evt ActionEvent instance passed from actionPerformed event.
     */
    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.addChoosableFileFilter(new ImageFileFilter());
        int option = chooser.showOpenDialog(this);
        if (option == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = chooser.getSelectedFile();
            if (file == null) return;
            ImageFrame ifr = new ImageFrame(file.getAbsolutePath());
            desktop.add(ifr, javax.swing.JLayeredPane.DEFAULT_LAYER);
            
            ifr.setVisible( true );
            ifr.setSize(200, 200);
            ifr.setLocation(0, 0);
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    /** This method is called when the application frame is closed.
     * @param evt WindowEvent instance passed from windowClosing event.
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /** Define custom file filter for acceptable image files.
     */
    private static class ImageFileFilter extends javax.swing.filechooser.FileFilter {
        
        public boolean accept(java.io.File file) {
            if (file == null)
                return false;
            return file.isDirectory() || file.getName().toLowerCase().endsWith(".gif") || file.getName().toLowerCase().endsWith(".jpg");
        }
        
        public String getDescription() {
            return "Image files (*.gif, *.jpg)";
        }
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem openMenuItem;
    // End of variables declaration//GEN-END:variables


    /** Starts the application.
     * @param args Application arguments.
     */    
    public static void main(String args[]) {
        new ImageViewer().show();
    }

}