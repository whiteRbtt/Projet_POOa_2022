package pooa_june_22.ViewPackage;

import javax.swing.*;
import java.awt.*;

public class AddAstroBodyPanel extends JPanel {

    private TitlePanel title;
    private AstroBodyFormPanel formPanel;

    public AddAstroBodyPanel() {
        this.setLayout(new BorderLayout());

        title = new TitlePanel("Création d'un nouvel objet céleste :");
        this.add(title, BorderLayout.NORTH);

        formPanel = new AstroBodyFormPanel(null);
        this.add(formPanel, BorderLayout.CENTER);

    }
}
