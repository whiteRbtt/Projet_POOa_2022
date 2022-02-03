package pooa_june_22.ViewPackage;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private CMatrixPanel cMatrix;
    private JLabel riddle, status;
    private TitlePanel t1, t2;

    public WelcomePanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        t1 = new TitlePanel("Bienvenus dans le programme d'apprentissage de l'A.D.C.S,");
        c.gridx = 0;
        c.gridy = 0;
        this.add(t1, c);

        t2 = new TitlePanel("explorez cette mystérieuse galaxie et percez ses secrets !");
        c.gridx = 0;
        c.gridy = 1;
        this.add(t2, c);

        riddle = new JLabel("Enigme : Comment les Nomais se sont ils éteints ?");
        c.gridx = 0;
        c.gridy = 2;
        this.add(riddle, c);

        status = new JLabel("Chargement :");
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(150, 0, 0, 40);
        this.add(status, c);

        cMatrix = new CMatrixPanel();
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(0, 0, 0, 40);
        this.add(cMatrix, c);

    }
}
