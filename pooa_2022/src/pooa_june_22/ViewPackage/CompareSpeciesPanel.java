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

        title = new TitlePanel("Quelle est l'espèce alien la plus puissante ?");
        this.add(title, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        information = new JLabel(
                "<html>La comparaison s'effectue sur base d'une formule considérant nombre de colonie, <br>" +
                "la durée de vie moyenne des colonies, ainsi que de la gravité moyenne des planètes colonisées. <br><br>" +
                "La variable ayant le plus de poids est la gravité car elle impacte directement la force physique</html>"
        );
        this.add(information, BorderLayout.PAGE_END);
        try{
            ArrayList<Specie> allSpecies = controler.getAllSpecies();
            ArrayList<String> speciesNames = new ArrayList<>();
            for(Specie specie : allSpecies){
                speciesNames.add(specie.getVernacularName());
            }

            specieLabel1 = new JLabel("Selectionnez les espèces à comparer");
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 50, 0);
            c.anchor = GridBagConstraints.CENTER;
            secondPanel.add(specieLabel1, c);

            species1 = new JList(speciesNames.toArray());
            species1.setVisibleRowCount(5);
            species1.setFixedCellWidth(150);
            species1.setFixedCellHeight(20);
            species1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            c.gridx = 0;
            c.gridy = 1;
            secondPanel.add(new JScrollPane(species1), c);

            species2 = new JList(speciesNames.toArray());
            species2.setVisibleRowCount(5);
            species2.setFixedCellWidth(150);
            species2.setFixedCellHeight(20);
            species2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            c.gridx = 2;
            c.gridy = 1;
            secondPanel.add(new JScrollPane(species2), c);

            validate = new JButton("Comparer");
            c.gridx = 1;
            c.gridy = 2;
            c.insets = new Insets(50, 0, 0, 0);
            c.anchor = GridBagConstraints.CENTER;
            secondPanel.add(validate, c);

            validate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    try{
                            if(species1.getSelectedValue() == null || species2.getSelectedValue() == null) {
                                throw new SelectionException("2 espèces.") ;
                            }

                            if((String)species1.getSelectedValue() == (String)species2.getSelectedValue()) {
                                throw new SelectionException("2 espèces différentes.") ;
                            }

                            secondPanel.removeAll();
                            secondPanel.setLayout(new BorderLayout());


                            GridBagConstraints c = new GridBagConstraints();
                            String winner = controler.bestSpecie((String)species1.getSelectedValue(), (String)species2.getSelectedValue());
                            TitlePanel resultat = new TitlePanel("L'espèce la plus forte est : " + winner);
                            resultat.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

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

