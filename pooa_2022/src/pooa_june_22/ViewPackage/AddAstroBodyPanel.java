package pooa_june_22.ViewPackage;

import pooa_june_22.ExceptionPackage.GeneralException;

import javax.swing.*;
import java.awt.*;

public class AddAstroBodyPanel extends JPanel {

    private TitlePanel title;
    private AstroBodyFormPanel formPanel;

    public AddAstroBodyPanel() {
        //-----------------------------------Initialisation-----------------------------------
        this.setLayout(new BorderLayout());

        title = new TitlePanel("Création d'un nouvel objet céleste :");
        this.add(title, BorderLayout.NORTH);

        //-----------------------------------Création du formulaire-----------------------------------
        try {
            formPanel = new AstroBodyFormPanel(null);
        } catch (GeneralException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    e.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
        this.add(formPanel, BorderLayout.CENTER);

    }
}
