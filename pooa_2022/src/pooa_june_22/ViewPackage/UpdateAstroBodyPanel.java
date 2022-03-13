package pooa_june_22.ViewPackage;

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
    private TitlePanel title;
    private JLabel astroBodyLabel;
    private JComboBox astroBody;
    private JButton validate;
    private Container container;
    private ApplicationControler controller;
    private ArrayList<AstroBody> allAstroBodies;
    private String[] astroBodiesName;

    public UpdateAstroBodyPanel() throws GeneralException {
        this.setLayout(new BorderLayout());

        title = new TitlePanel("Modification d'un objet céleste existant :");
        this.add(title, BorderLayout.NORTH);

        container = new Container();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // --------------------------get all astro bodies--------------------------
        try {
            setController(new ApplicationControler());
            allAstroBodies = controller.getAllAstroBodies();
            astroBodiesName = new String[allAstroBodies.size()];
            int i = 0;
            for (AstroBody a : allAstroBodies) {
                astroBodiesName[i] = a.getName();
                i++;
            }

        } catch (AllAstroBodiesException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (IdException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (GravityException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (DateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (ClimateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (NameException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (TypeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        }

        // --------------------------Components--------------------------
        astroBodyLabel = new JLabel("Sélectionnez un Objet à modifier :");
        c.gridx = 0;
        c.gridy = 0;
        container.add(astroBodyLabel);

        astroBody = new JComboBox(astroBodiesName);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(30, 0, 0, 0);
        c.ipadx = 100;
        container.add(astroBody, c);

        validate = new JButton("Modifier");
        validate.addActionListener(new UpdateListener());
        c.gridx = 0;
        c.gridy = 2;
        c.ipadx = 100;
        c.insets = new Insets(100, 0, 200, 0);
        c.anchor = GridBagConstraints.SOUTH;
        container.add(validate, c);


        this.add(container, BorderLayout.CENTER);

    }

    private class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            container.removeAll();
            container.setLayout(new BorderLayout());

            // --------------------------retrieve id--------------------------
            Integer selectedId = null;
            for (AstroBody a : allAstroBodies)
                if (a.getName() == (String) astroBody.getSelectedItem())
                    selectedId = a.getAstroId();

            try {
                formPanel = new AstroBodyFormPanel(selectedId);
            } catch (NameException e) {
                e.printStackTrace();
            } catch (TypeException e) {
                e.printStackTrace();
            } catch (ConnectionException e) {
                e.printStackTrace();
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

