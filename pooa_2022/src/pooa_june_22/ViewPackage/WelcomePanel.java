package pooa_june_22.ViewPackage;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private CMatrixPanel cMatrix;
    private JLabel icon, riddle, status;
    private TitlePanel t1;

    public WelcomePanel() {
        // -----------------------------------Initilizations-----------------------------------
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        // -----------------------------------Displays-----------------------------------
        Image newImg = new ImageIcon(this.getClass().getResource("../Ressources/logo.png")).getImage().getScaledInstance(150, 130, Image.SCALE_SMOOTH
        );;
        icon = new JLabel(new ImageIcon(newImg), JLabel.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 20, 0);
        this.add(icon, c);

        t1 = new TitlePanel("Bienvenus dans le programme d'apprentissage de l'A.D.C.S !");
        c.gridy = 1;
        this.add(t1, c);


        riddle = new JLabel("Explorez une mystérieuse galaxie et percez le secret de l'extinction des Nomais.");
        riddle.setFont(new Font("Consolas",Font.TRUETYPE_FONT,20));
        c.gridy = 2;
        c.insets = new Insets(20, 0, 110, 0);
        this.add(riddle, c);

        status = new JLabel(">> launch_sequence()");
        status.setFont(new Font("Consolas",Font.TRUETYPE_FONT,15));
        c.gridy = 3;
        c.insets = new Insets(0, 0, 5, 40);
        this.add(status, c);

        // -----------------------------------Thread-----------------------------------
        cMatrix = new CMatrixPanel();
        cMatrix.setBackground(new Color(42,39,39));
        c.gridy = 4;
        c.insets = new Insets(0, 0, 0, 40);
        this.add(cMatrix, c);

    }
}
