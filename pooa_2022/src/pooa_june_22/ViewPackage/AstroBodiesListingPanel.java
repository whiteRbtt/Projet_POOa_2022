package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AstroBodiesListingPanel extends JPanel {
    private TitlePanel title;
    private TablePanel table;
    private String[] columnNames;
    private Object[][] datas;
    private ApplicationControler controller;


    public AstroBodiesListingPanel() {
        this.setLayout(new BorderLayout());

        title = new TitlePanel("Liste des Objets célestes connus de notre galaxie :");
        this.add(title, BorderLayout.NORTH);

        try {
            setController(new ApplicationControler());
            ArrayList<AstroBody> allBodies = controller.getAllAstroBodies();
            columnNames = new String[]{"ID", "Nom", "Type", "Climat", "Gravité relative", "Propice à la vie", "Date d'exploration"};
            //TODO ajouter colonne explorateurs, changer bd pour faire de gravité un entier non null (0 = 0), ajouter un "inconnus" dans la colonne propice à la vie

            datas = new Object[allBodies.size()][columnNames.length];
            // TODO utiliser le template de jList du cours (chap séparation en couches)
            int i = 0;
            for (AstroBody a : allBodies) {
                datas[i][0] = a.getAstroId();
                datas[i][1] = a.getName();
                datas[i][2] = a.getType();

                if (a.getClimate() != null)
                    datas[i][3] = a.getClimate();
                else {
                    datas[i][3] = new String("vide spatial");
                }

                if (a.getGravity() != null)
                    datas[i][4] = a.getGravity();
                else {
                    datas[i][4] = 0;
                }

                datas[i][5] = a.getHasLifeform();

                String date;
                if (a.getFirstExploDate() != null) {

                    date = a.getFirstExploDate().get(a.getFirstExploDate().DAY_OF_MONTH) + "/" +
                            a.getFirstExploDate().get(a.getFirstExploDate().MONTH) + "/" +
                            a.getFirstExploDate().get(a.getFirstExploDate().YEAR);

                    datas[i][6] = date;
                } else {
                    date = "Inconnue";
                    datas[i][6] = date;
                }
                i++;
            }

            table = new TablePanel(columnNames, datas);
            this.add(table, BorderLayout.CENTER);

        } catch (AllAstroBodiesException e) {
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
        }
    }

    public void setController(ApplicationControler controller) {
        this.controller = controller;
    }
}
