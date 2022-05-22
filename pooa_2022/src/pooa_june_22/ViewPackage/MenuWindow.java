package pooa_june_22.ViewPackage;

import pooa_june_22.DataAccessPackage.SingletonConnexion;
import pooa_june_22.ExceptionPackage.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;

public class MenuWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu welcomeMenu, editMenu, infosMenu, statMenu;
    private JMenuItem welcomeItem, addItem, searchItem, searchItem2, searchItem3, listItem, gravityAverageItem, updateItem, deleteItem, displayItem;
    private Container frameContainer;


    public MenuWindow() {

        // -----Initialization-----
        super("A.D.C.S - Solar system explorer");
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight() - 150;
        int width = (int) dimension.getWidth() - 500;
        setBounds(0, 0, width, height);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new ClosingListener());

        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        MainListener listener = new MainListener();


        // -----graphics-----
        Font defaultfont = new Font("Consolas", Font.TRUETYPE_FONT, 15);
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.put("Label.font", defaultfont);
        defaults.put("Label.foreground", Color.white);
        defaults.put("Panel.background", new Color(42, 39, 39));
        defaults.put("OptionPane.messageForeground", Color.white);


        // -----Welcome-----
        welcomeMenu = new JMenu("Accueil");
        menuBar.add(welcomeMenu);

        welcomeItem = new JMenuItem("Retourner sur la page d'accueil");
        welcomeItem.addActionListener(listener);
        welcomeMenu.add(welcomeItem);

        // -----edit-----
        editMenu = new JMenu("Editer");
        menuBar.add(editMenu);

        addItem = new JMenuItem("Ajouter un corps céleste");
        addItem.addActionListener(listener);
        editMenu.add(addItem);

        editMenu.addSeparator();

        updateItem = new JMenuItem("Modifier un corps céleste");
        updateItem.addActionListener(listener);
        editMenu.add(updateItem);

        editMenu.addSeparator();

        deleteItem = new JMenuItem("Supprimer un corps céleste");
        deleteItem.addActionListener(listener);
        editMenu.add(deleteItem);

        // -----list-----
        infosMenu = new JMenu("S'informer");
        menuBar.add(infosMenu);

        listItem = new JMenuItem("Lister les objets du système solaire");
        listItem.addActionListener(listener);
        infosMenu.add(listItem);

        infosMenu.addSeparator();

        // -----Research-----

        searchItem = new JMenuItem("Rechercher des colonies");
        searchItem.addActionListener(listener);
        infosMenu.add(searchItem);

        searchItem2 = new JMenuItem("Lister les planètes existant durant une période donnée");
        searchItem2.addActionListener(listener);
        infosMenu.add(searchItem2);

        searchItem3 = new JMenuItem("Rechercher des planètes en fonction de leur type");
        searchItem3.addActionListener(listener);
        infosMenu.add(searchItem3);

        // -----Business task-----
        statMenu = new JMenu("Statistiques");
        menuBar.add(statMenu);

        gravityAverageItem = new JMenuItem("Gravité moyenne pour un type de planète donné");
        gravityAverageItem.addActionListener(listener);
        statMenu.add(gravityAverageItem);

        displayItem = new JMenuItem("Comparer les différentes espèces");
        displayItem.addActionListener(listener);
        statMenu.add(displayItem);
        // default panels

        WelcomePanel welcomePanel = new WelcomePanel();
        frameContainer.add(welcomePanel);

        setVisible(true);
    }

    // -----------------------------------Action listener for buttons-----------------------------------
    private class MainListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            frameContainer.removeAll();
            try {
                if (event.getSource() == welcomeItem) {
                    WelcomePanel welcomePanel = new WelcomePanel();
                    frameContainer.add(welcomePanel);
                }
                if (event.getSource() == addItem) {
                    AddAstroBodyPanel addAstroBodyPanel = new AddAstroBodyPanel();
                    frameContainer.add(addAstroBodyPanel);
                }
                if (event.getSource() == updateItem) {
                    UpdateAstroBodyPanel updateAstroBodyPanel = null;
                    updateAstroBodyPanel = new UpdateAstroBodyPanel();

                    frameContainer.add(updateAstroBodyPanel);
                }

                if (event.getSource() == listItem) {
                    AstroBodiesListingPanel astroBodiesListingPanel = new AstroBodiesListingPanel();
                    frameContainer.add(astroBodiesListingPanel);

                }
                if (event.getSource() == deleteItem) {
                    DeleteAstroBodyPanel deletePanel = new DeleteAstroBodyPanel();
                    frameContainer.add(deletePanel);


                }
                if (event.getSource() == searchItem) {
                    ResearchColoniesPanel researchColoniesPanel = new ResearchColoniesPanel();
                    frameContainer.add(researchColoniesPanel);
                }
                if (event.getSource() == gravityAverageItem) {
                    BusinessPanel panel = new BusinessPanel();
                    frameContainer.add(panel);
                }
                if (event.getSource() == searchItem2) {
                    ResearchAstroBodiesForPeriodPanel panel = new ResearchAstroBodiesForPeriodPanel();
                    frameContainer.add(panel);
                }
                if (event.getSource() == searchItem3) {
                    ResearchAstroBodiesPanel panel = new ResearchAstroBodiesPanel();
                    frameContainer.add(panel);
                }
                if (event.getSource() == displayItem) {
                    CompareSpeciesPanel panel = new CompareSpeciesPanel();
                    frameContainer.add(panel);
                }
            } catch (GeneralException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        e.getTitle(), JOptionPane.ERROR_MESSAGE);
            }


            frameContainer.revalidate();
            frameContainer.repaint();
        }

    }

    // -----------------------------------Action for close-----------------------------------
    public class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            try {
                SingletonConnexion.close();
            } catch (GeneralException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        ex.getTitle(), JOptionPane.ERROR_MESSAGE);
            }

            System.exit(0);
        }
    }
}




