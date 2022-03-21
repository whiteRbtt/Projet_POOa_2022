package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class AstroBodiesListingPanel extends JPanel {
    private TitlePanel title;
    private AstroBodiesListingModel model;
    private JTable table;

    public AstroBodiesListingPanel() throws GeneralException {
        this.setLayout(new BorderLayout());
        title = new TitlePanel("Liste des objets célestes connus de notre galaxie :");
        this.add(title, BorderLayout.NORTH);
        model = new AstroBodiesListingModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(scrollPane);

    }

}
