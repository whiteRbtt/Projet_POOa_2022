package pooa_june_22.ViewPackage;

import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ExceptionPackage.TypeException;

import javax.swing.*;
import java.awt.*;

public class AddAstroBodyPanel extends JPanel {

    private TitlePanel title;
    private AstroBodyFormPanel formPanel;

    public AddAstroBodyPanel() {
        this.setLayout(new BorderLayout());

        title = new TitlePanel("Création d'un nouvel objet céleste :");
        this.add(title, BorderLayout.NORTH);

        try {
            formPanel = new AstroBodyFormPanel(null);
        } catch (NameException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (TypeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        }
        this.add(formPanel, BorderLayout.CENTER);

    }
}
