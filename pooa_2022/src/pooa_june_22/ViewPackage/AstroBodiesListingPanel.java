package pooa_june_22.ViewPackage;

import pooa_june_22.ExceptionPackage.*;

import javax.swing.*;
import java.awt.*;

public class AstroBodiesListingPanel extends JPanel {
    private TitlePanel title;
    private AstroBodiesListingModel model;
    private JTable table;

    public AstroBodiesListingPanel() throws GeneralException {
        //-----------------------------------Initialisation-----------------------------------
        this.setLayout(new BorderLayout(0,50));
        this.setBorder(BorderFactory.createEmptyBorder(20, 100, 50, 100));
        title = new TitlePanel("Liste des objets célestes connus de notre galaxie :");
        this.add(title, BorderLayout.NORTH);


        //-----------------------------------Création du modèle et affichage de la table-----------------------------------
        model = new AstroBodiesListingModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(scrollPane);
    }

}
