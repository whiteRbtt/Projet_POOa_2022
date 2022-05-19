package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Specie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResearchColoniesPanel extends JPanel {

    private TitlePanel title;
    private JLabel specieLabel;
    private JComboBox species;
    private JButton validate;
    private Container container;
    private ResearchColonieModel colonieModel;
    private ApplicationControler controller;

    private ArrayList<Specie> allSpecies;

    public ResearchColoniesPanel() {
        // -----------------------------------Initialization-----------------------------------
        this.setLayout(new BorderLayout());
        title = new TitlePanel("Rechercher des colonies :");
        this.setLayout(new BorderLayout(0, 50));
        this.setBorder(BorderFactory.createEmptyBorder(20, 100, 100, 100));
        this.add(title, BorderLayout.NORTH);

        // -----------------------------------Get datas-----------------------------------
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
        } catch (GeneralException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
        }

        // -----------------------------------Displays-----------------------------------
        container = new Container();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // -----------------------------------Species-----------------------------------
        specieLabel = new JLabel("Sélectionnez l'espèce cible");
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

        // -----------------------------------Validate button-----------------------------------
        validate = new JButton("Recherche");
        validate.addActionListener(new SearchListener());
        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 100;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        container.add(validate, c);

        this.add(container, BorderLayout.CENTER);
    }

    // -----------------------------------Action for button-----------------------------------
    class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            container.removeAll();
            container.setLayout(new BorderLayout());

            try {
                colonieModel = new ResearchColonieModel((String)species.getSelectedItem());
                JTable table = new JTable(colonieModel);
                JScrollPane scrollPane = new JScrollPane(table);
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                container.add(scrollPane);

                container.revalidate();
                container.repaint();
                setVisible(true);
            } catch (GeneralException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void setController(ApplicationControler controller) {
        this.controller = controller;
    }
}
