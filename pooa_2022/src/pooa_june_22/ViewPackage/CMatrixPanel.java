package pooa_june_22.ViewPackage;

import pooa_june_22.ThreadPackage.CMatrixThread;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CMatrixPanel extends JPanel {
    private JTextArea cMatrix;
    private String[] fakeCmd = {
            "Démarrage du vaisseau ...",
            "Atterrissage sur la lune quantique ...",
            "Vérification de la gravité artificielle ...",
            "Chargement des canons à particules ...",
            "Initialisation ...",
            "... Error",
            "Localisation de l'oeil de l'univers ...",
            "Observation des coelcantes ...",
            "Débarquement des Nomais ...",
            "Capture des méduses ...",
            "réservation des popcorns pour la supernova ...",
            "Apprentissage de la méditation ...",
            "Découverte des secrets de l'univers ...",
            "Accordage du banjo ..."
    };

    public CMatrixPanel() {
        this.setLayout(new FlowLayout());
        cMatrix = new JTextArea(1, 25);
        cMatrix.setEditable(false);
        this.add(cMatrix);

        CMatrixThread cMatrixThread = new CMatrixThread(this);
        cMatrixThread.start();
    }

    public void textScrolling() {
        cMatrix.setText(null);
        Random random = new Random();
        int r = random.nextInt(fakeCmd.length);
        cMatrix.append(fakeCmd[r]);
        cMatrix.append(" ");
    }
}
