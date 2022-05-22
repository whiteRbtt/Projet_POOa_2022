package pooa_june_22.ViewPackage;

import org.w3c.dom.ls.LSOutput;
import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateAstroBodyPanel extends JPanel {
    private AstroBodyFormPanel formPanel;
    private TitlePanel t1;
    private JLabel astroBodyLabel;
    private JComboBox astroBody;
    private JButton validate;
    private Container container;
    private ApplicationControler controller;
    private ArrayList<AstroBody> allAstroBodies;
    private String[] astroBodiesName;

    public UpdateAstroBodyPanel() throws GeneralException {
        // -----------------------------------Initialization-----------------------------------
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
        t1 = new TitlePanel("Modification d'un corps céleste existant :");
        this.add(t1, BorderLayout.NORTH);

        container = new Container();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // --------------------------get datas--------------------------
        try {
            setController(new ApplicationControler());
            allAstroBodies = controller.getAllAstroBodies();

            astroBodiesName = new String[allAstroBodies.size()];
            int i = 0;
            for (AstroBody a : allAstroBodies) {
                astroBodiesName[i] = a.getName();
                i++;
            }

        } catch (GeneralException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
        }

        // --------------------------Displays--------------------------
        // -----------------------------------AstroBody-----------------------------------
        astroBodyLabel = new JLabel("Sélectionnez un corps céleste à modifier :");
        c.gridx = 0;
        c.gridy = 0;
        container.add(astroBodyLabel);

        astroBody = new JComboBox(astroBodiesName);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(30, 0, 0, 0);
        c.ipadx = 100;
        container.add(astroBody, c);

        // -----------------------------------Validate button-----------------------------------
        validate = new JButton("Modifier");
        validate.addActionListener(new UpdateListener());
        c.gridx = 0;
        c.gridy = 2;
        c.ipadx = 100;
        c.insets = new Insets(50, 0, 200, 0);
        c.anchor = GridBagConstraints.SOUTH;
        container.add(validate, c);


        this.add(container, BorderLayout.CENTER);

    }

    // -----------------------------------Action for button-----------------------------------
    private class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            container.removeAll();
            container.setLayout(new BorderLayout());
            try {
                // --------------------------retrieve id--------------------------
                AstroBody astro = null;
                for (AstroBody a : allAstroBodies) {
                    if (a.getName().compareTo((String) astroBody.getSelectedItem()) == 0) {

                        astro = new AstroBody(a.getAstroId(), a.getName(), a.getFirstExplorer(), a.getType(), a.getClimate(), a.getGravity(), a.getHasLifeform(), a.getFirstExploDate());
                        controller.updateAstroBody(astro);
                    }
                }

                formPanel = new AstroBodyFormPanel(astro);
            }catch (GeneralException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
            }

            container.add(formPanel, BorderLayout.CENTER);

            container.revalidate();
            container.repaint();
        }
    }

    public void setController(ApplicationControler controller) {
        this.controller = controller;
    }
}

