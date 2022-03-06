package pooa_june_22.ViewPackage;

import pooa_june_22.DataAccessPackage.SingletonConnexion;
import pooa_june_22.ExceptionPackage.*;


import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException; // TODO Rien a faire ici


public class MenuWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu welcomeMenu, addMenu, updateMenu, deleteMenu, listMenu, searchMenu, searchMenu2, searchMenu3, editMenu, infosMenu, statMenu, lifeSpanMenu;
    private Container frameContainer;

    public MenuWindow() {
        super("A.D.C.S - Solar system explorer");
        setBounds(0, 0, 700, 700);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new ClosingListener());

        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // -----accueil-----
        welcomeMenu = new JMenu("Accueil");
        welcomeMenu.addMenuListener(new MainListener());
        menuBar.add(welcomeMenu);

        // -----edit-----
        editMenu = new JMenu("Editer");
        menuBar.add(editMenu);

        addMenu = new JMenu("Ajouter un corps céleste");
        addMenu.addMenuListener(new MainListener());
        editMenu.add(addMenu);

        editMenu.addSeparator();

        updateMenu = new JMenu("Modifier un corps céleste");
        updateMenu.addMenuListener(new MainListener());
        editMenu.add(updateMenu);

        editMenu.addSeparator();

        deleteMenu = new JMenu("Supprimer un corps céleste");
        deleteMenu.addMenuListener(new MainListener());
        editMenu.add(deleteMenu);

        // -----list-----
        infosMenu = new JMenu("S'informer");
        menuBar.add(infosMenu);

        listMenu = new JMenu("Lister les objets de notre système");
        listMenu.addMenuListener(new MainListener());
        infosMenu.add(listMenu);

        infosMenu.addSeparator();

        searchMenu = new JMenu("Rechercher des colonies");
        searchMenu.addMenuListener(new MainListener());
        infosMenu.add(searchMenu);

        searchMenu2 = new JMenu("Rechercher un explorateur");
        searchMenu2.addMenuListener(new MainListener());
        infosMenu.add(searchMenu2);

        searchMenu3 = new JMenu("Rechercher explorateurs entre 2 époques");
        searchMenu3.addMenuListener(new MainListener());
        infosMenu.add(searchMenu3);

        // -----Tâche métier-----
        statMenu = new JMenu("Statistiques");
        menuBar.add(statMenu);

        lifeSpanMenu = new JMenu("Durée de vie moyenne entre 2 ères");
        lifeSpanMenu.addMenuListener(new MainListener());
        statMenu.add(lifeSpanMenu);
        // default panels

        WelcomePanel welcomePanel = new WelcomePanel();
        frameContainer.add(welcomePanel);


        setVisible(true);
    }

    private class MainListener implements MenuListener {
        public void menuSelected(MenuEvent event) {

            frameContainer.removeAll();

            if (event.getSource() == welcomeMenu) {
                WelcomePanel welcomePanel = new WelcomePanel();
                frameContainer.add(welcomePanel);
            }
            if (event.getSource() == addMenu) {
                AddAstroBodyPanel addAstroBodyPanel = new AddAstroBodyPanel();
                frameContainer.add(addAstroBodyPanel);
            }
            if (event.getSource() == updateMenu) {
                UpdateAstroBodyPanel updateAstroBodyPanel = null;
                try {
                    updateAstroBodyPanel = new UpdateAstroBodyPanel();
                } catch (GeneralException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                            "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                }
                frameContainer.add(updateAstroBodyPanel);
            }
            if (event.getSource() == listMenu) {// TODO : optimiser les try catchs
                try{
                    AstroBodiesListingPanel astroBodiesListingPanel = new AstroBodiesListingPanel();
                    frameContainer.add(astroBodiesListingPanel);
                }catch (AllAstroBodiesException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (IdException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (GravityException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (DateException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (ClimateException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (NameException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (ConnectionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (TypeException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (GeneralException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                            "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(event.getSource() == deleteMenu){
                try{
                    DeleteAstroBodyPanel deletePanel = new DeleteAstroBodyPanel();
                    frameContainer.add(deletePanel);
                }catch (AllAstroBodiesException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (IdException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (GravityException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (DateException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (ClimateException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (NameException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (ConnectionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (TypeException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                } catch (GeneralException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                            "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                }

            }
            if (event.getSource() == searchMenu) {
                ResearchColoniesPanel researchColoniesPanel = new ResearchColoniesPanel();
                frameContainer.add(researchColoniesPanel);
            }
            if(event.getSource() == lifeSpanMenu){
                BusinessPanel panel = new BusinessPanel();
                frameContainer.add(panel);
            }
            if(event.getSource() == searchMenu2){
                ResearchExplorerPanel panel = new ResearchExplorerPanel();
                frameContainer.add(panel);
            }

            frameContainer.revalidate();
            frameContainer.repaint();
        }

        public void menuDeselected(MenuEvent e) {

        }

        public void menuCanceled(MenuEvent e) {
        }
    }

    public class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) { //TODO faire de la fermeture une fonction de classe singleton et ici seulement un appel de fct
            try {
                Connection connection = SingletonConnexion.getInstance();
                connection.close();
            } catch (ConnectionException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la cloture de la connection",
                        "Erreur :", JOptionPane.ERROR_MESSAGE);
            }

            System.exit(0);
        }
    }
}




