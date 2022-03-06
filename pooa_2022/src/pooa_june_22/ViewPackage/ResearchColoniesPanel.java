package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;
import pooa_june_22.ModelPackage.Colony;
import pooa_june_22.ModelPackage.Era;
import pooa_june_22.ModelPackage.Specie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ResearchColoniesPanel extends JPanel {

    private TitlePanel title;
    private JLabel specieLabel, dateLabel, description;
    private JSpinner year;
    private JComboBox species;
    private JButton validate;
    private Container container;
    private TablePanel table;
    private String[] columnNames;
    private Object[][] datas;
    private ApplicationControler controller;

    private ArrayList<Specie> allSpecies;
    private ArrayList<Colony> selectedColonies;
    private ArrayList<AstroBody> allAstroBodies;

    public ResearchColoniesPanel() {
        this.setLayout(new BorderLayout());
        title = new TitlePanel("Rechercher des colonies :");
        this.add(title, BorderLayout.NORTH);

        // -----------------------------------get All species-----------------------------------
        String[] values = {};
        try {
            setController(new ApplicationControler());
            allSpecies = controller.getAllSpecies();
            values = new String[allSpecies.size()];

            int i = 0;
            for (Specie s : allSpecies) {
                values[i] = s.getVernacularName();
                i++;
            }
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (NameException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (AllSpeciesException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        }


        // -----------------------------------form-----------------------------------

        container = new Container();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        specieLabel = new JLabel("Selectionnez l'espèce désirée");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 25, 40, 0);
        c.anchor = GridBagConstraints.LINE_START;
        container.add(specieLabel, c);

        species = new JComboBox(values);
        species.setEditable(false);
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 100;
        c.anchor = GridBagConstraints.LINE_END;
        container.add(species, c);

        dateLabel = new JLabel("Entrez une année de référence");
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 0;
        c.anchor = GridBagConstraints.LINE_START;
        container.add(dateLabel, c);

        SpinnerModel model = new SpinnerNumberModel(1000, 1000, 9999, 100);
        year = new JSpinner(model);
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 70;
        c.anchor = GridBagConstraints.LINE_END;
        container.add(year, c);

        validate = new JButton("Rechercher");
        validate.addActionListener(new SearchListener());
        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 100;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        container.add(validate, c);

        this.add(container, BorderLayout.CENTER);
    }

    private class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            container.removeAll();
            container.setLayout(new BorderLayout());

            try {
                // -----------------------------------retrieve year-----------------------------------
                year.commitEdit();
                GregorianCalendar targetDate = new GregorianCalendar((Integer) year.getValue(), 1, 1);


                // -----------------------------------retrieve specie-----------------------------------
                String targetName = null;
                for (Specie specie : allSpecies) { //TODO inutile, soit utiliser les indices et acceder séquentiellement soit utiliser un Hashmap
                    if (specie.getVernacularName() == (String) species.getSelectedItem()) { //TODO comparer les strings avec la méthode adaptée
                        targetName = specie.getScientificName();
                    }
                }

                // -----------------------------------retrieve AstroBodies-----------------------------------
                allAstroBodies = controller.getAllAstroBodies(); //TODO inutile
                //TODO creer une classe selectedColonies dont la requete sql créé de nouvelles instances

                // -----------------------------------display table-----------------------------------
                selectedColonies = controller.getColonies(targetDate, targetName);

                columnNames = new String[]{"Planète", "Nom de la colonie"};
                datas = new Object[selectedColonies.size()][columnNames.length];

                int i = 0;
                for (Colony c : selectedColonies) {
                    for (AstroBody a : allAstroBodies) {
                        if (a.getAstroId() == c.getLocation())
                            datas[i][0] = a.getName();
                    }

                    if (c.getName() != null) {
                        datas[i][1] = c.getName();
                    } else {
                        datas[i][1] = "inconnu";
                    }
                    i++;
                }

                table = new TablePanel(columnNames, datas);
                container.add(table, BorderLayout.CENTER);

                // -----------------------------------description-----------------------------------
                Era era = controller.findEra(targetDate);

                if (selectedColonies.size() == 0) {
                    description = new JLabel(
                            "<html><p>L'année +" +
                                    targetDate.get(targetDate.YEAR) +
                                    " correspond à l' " +
                                    era.getName() + " ( " +
                                    era.getBeginning().get(era.getBeginning().YEAR) + " - " +
                                    era.getEnding().get(era.getEnding().YEAR) + " )</p> <p>En ce temps, les " +
                                    ((String) species.getSelectedItem()) + " ne possédaient aucunes colonies.</p>"
                    );

                } else {
                    description = new JLabel(
                            "<html><p>L'année +" +
                                    targetDate.get(targetDate.YEAR) +
                                    " correspond à l' " +
                                    era.getName() + " ( " +
                                    era.getBeginning().get(era.getBeginning().YEAR) + " - " +
                                    era.getEnding().get(era.getEnding().YEAR) + " )</p> <p>En ce temps, les " +
                                    ((String) species.getSelectedItem()) + " possédaient les colonies çi dessus.</p>"
                    );
                }

            } catch (DateException | ParseException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (ConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (NameException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (AllEraException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (ColonyException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (TypeException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (AllAstroBodiesException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (GravityException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (ClimateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (IdException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (GeneralException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }

            container.add(description, BorderLayout.SOUTH);

            container.revalidate();
            container.repaint();
        }
    }

    public void setController(ApplicationControler controller) {
        this.controller = controller;
    }
}
