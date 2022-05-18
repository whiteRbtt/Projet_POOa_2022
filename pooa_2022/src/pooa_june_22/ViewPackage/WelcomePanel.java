package pooa_june_22.ViewPackage;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private CMatrixPanel cMatrix;
    private JLabel riddle, status;
    private TitlePanel t1, t2;

    public WelcomePanel() {
        // -----------------------------------Initilizations-----------------------------------
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setBackground(new Color(42,39,39));


        // -----------------------------------Displays-----------------------------------
        t1 = new TitlePanel("Bienvenus dans le programme d'apprentissage de l'A.D.C.S !", true);
        t1.setBackground(new Color(42,39,39));
        c.gridx = 0;
        c.gridy = 0;
        this.add(t1, c);


        riddle = new JLabel("Explorez une mystérieuse galaxie et percez le secret de l'extinction des Nomais.");
        riddle.setFont(new Font("Consolas",Font.TRUETYPE_FONT,20));
        riddle.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20, 0, 150, 0);
        this.add(riddle, c);

        status = new JLabel(">> launch_sequence()");
        status.setFont(new Font("Consolas",Font.TRUETYPE_FONT,15));
        status.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 5, 40);
        this.add(status, c);

        // -----------------------------------Thread-----------------------------------
        cMatrix = new CMatrixPanel();
        cMatrix.setBackground(new Color(42,39,39));
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 0, 40);
        this.add(cMatrix, c);

    }
}
