package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Specie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompareSpeciesPanel extends JPanel {
    private TitlePanel title;
    private JLabel specieLabel1, specieLabel2, information;
    private JList species1, species2;
    private JButton validate;
    private JPanel secondPanel;

    public CompareSpeciesPanel(){
        this.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        secondPanel = new JPanel(new GridBagLayout());
        ApplicationControler controler = new ApplicationControler();

        title = new TitlePanel("Quelle est la meilleure espèce enregistré ?");
        this.add(title, BorderLayout.NORTH);
        information = new JLabel("(La comparaison s'effectue sur base d'une formule autour du nombre de colonie, de la durée de vie moyenne de ses colonies, ainsi que de la gravité moyenne des planètes colonisées. La variable ayant le plus de poids étant la gravité car révélateur de la force physique)");
        this.add(information, BorderLayout.PAGE_END);
        try{
            ArrayList<Specie> allSpecies = controler.getAllSpecies();
            ArrayList<String> speciesNames = new ArrayList<>();
            for(Specie specie : allSpecies){
                speciesNames.add(specie.getVernacularName());
            }

            specieLabel1 = new JLabel("Selectionnez l'espèce désirée");
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(0, 25, 40, 0);
            c.anchor = GridBagConstraints.LINE_START;
            secondPanel.add(specieLabel1, c);

            species1 = new JList(speciesNames.toArray());
            species1.setVisibleRowCount(5);
            species1.setFixedCellWidth(90);
            species1.setFixedCellHeight(20);
            species1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            c.gridx = 1;
            c.gridy = 0;
            c.ipadx = 100;
            secondPanel.add(new JScrollPane(species1), c);

            specieLabel2 = new JLabel("Selectionnez la deuxième espèce désirée");
            c.gridx = 0;
            c.gridy = 1;
            c.ipadx = 100;
            secondPanel.add(specieLabel2, c);

            species2 = new JList(speciesNames.toArray());
            species2.setVisibleRowCount(5);
            species2.setFixedCellWidth(90);
            species2.setFixedCellHeight(20);
            species2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            c.gridx = 1;
            c.gridy = 1;
            c.ipadx = 100;
            c.anchor = GridBagConstraints.LINE_END;
            secondPanel.add(new JScrollPane(species2), c);

            validate = new JButton("Comparer");
            c.gridx = 0;
            c.gridy = 2;
            c.ipadx = 100;
            c.anchor = GridBagConstraints.LAST_LINE_END;
            secondPanel.add(validate, c);

            validate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    secondPanel.removeAll();
                    secondPanel.setLayout(new BorderLayout());
                    try{
                        GridBagConstraints c = new GridBagConstraints();
                        String winner = controler.bestSpecie((String)species1.getSelectedValue(), (String)species2.getSelectedValue());
                        TitlePanel resultat = new TitlePanel("L'espèce la plus forte est : " + winner);
                        secondPanel.add(resultat);
                        secondPanel.revalidate();
                        secondPanel.repaint();
                        setVisible(true);
                    } catch(GeneralException exception){
                        JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getTitle(),JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            this.add(secondPanel, BorderLayout.CENTER);

        } catch (GeneralException e) {
            e.printStackTrace();
        }
    }

}

