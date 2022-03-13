package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;
import pooa_june_22.ModelPackage.AstroType;
import pooa_june_22.ModelPackage.Explorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AstroBodyFormPanel extends JPanel {
    private JLabel idLabel, nameLabel, explorerLabel, typeLabel, climateLabel, gravityLabel, lifeformLabel, dateLabel;
    private JTextField id, name;
    private JSpinner gravity, year;
    private JComboBox day, month, explorer, lifeform, type, climate;
    private JButton validate;
    private JCheckBox noDate;
    private Boolean checkBoxState;
    private ApplicationControler controller;
    private ArrayList<Explorer> allExplorers;
    private ArrayList<AstroType> allTypes;
    private Integer fixedId;

    public AstroBodyFormPanel(Integer fixedId) throws NameException, TypeException, ConnectionException {
        this.fixedId = fixedId;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // --------------------------get all explorers--------------------------
        String[] values = {};
        try {
            setController(new ApplicationControler());
            allExplorers = controller.getAllExplorers();
            allTypes = controller.getAllTypes();
            values = new String[allExplorers.size() + 1];
            values[0] = "inconnu";

            int i = 1;
            for (Explorer e : allExplorers) {
                values[i] = e.getName();
                i++;
            }

        } catch (AllExplorersException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (NameException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (DateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        } catch (IdException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        }


        // --------------------------astroId--------------------------
        idLabel = new JLabel("Numero d'Id de l'objet :");
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(idLabel, c);

        if (fixedId != null) {
            id = new JTextField(fixedId);
            id.setText(String.valueOf(fixedId));
            id.setEditable(false);
            c.gridx = 4;
            c.gridy = 0;
            c.ipadx = 100;
            this.add(id, c);
        } else {
            id = new JTextField();
            c.gridx = 4;
            c.gridy = 0;
            c.ipadx = 100;
            this.add(id, c);
        }


        // --------------------------name--------------------------

        nameLabel = new JLabel("Nom de l'objet:");
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 0;
        c.insets = new Insets(20, 0, 0, 0);

        this.add(nameLabel, c);

        name = new JTextField();
        c.gridx = 4; //TODO faire de ces c. une fonction qui renvoie le grid bag constraint avec en param les diff valeurs
        c.gridy = 1;
        c.ipadx = 100;
        this.add(name, c);


        // --------------------------firstKnownExplorer--------------------------

        explorerLabel = new JLabel("Premier explorateur connu:");
        c.gridx = 0;
        c.gridy = 2;
        c.ipadx = 0;
        this.add(explorerLabel, c);

        explorer = new JComboBox(values);
        explorer.setEditable(false);
        c.gridx = 4;
        c.gridy = 2;
        c.ipadx = 0;
        this.add(explorer, c);


        // --------------------------type--------------------------

        typeLabel = new JLabel("Type d'objet :");
        c.gridx = 0;
        c.gridy = 3;
        c.ipadx = 0;
        this.add(typeLabel, c);

        ArrayList<String> typesName = new ArrayList<>();
        for (AstroType a : allTypes) {
            typesName.add(a.getName());
        }
        type = new JComboBox(typesName.toArray());
        c.gridx = 4;
        c.gridy = 3;
        c.ipadx = 0;
        c.anchor = GridBagConstraints.LINE_END;
        this.add(type, c);


        // --------------------------climate--------------------------

        climateLabel = new JLabel("Climat :");
        c.gridx = 0;
        c.gridy = 4;
        c.ipadx = 0;
        this.add(climateLabel, c);

        String[] climates = new String[]{"tempéré", "chaotique", "tempête", "aride", "glacé", "quantique", "volcanique", "torride"};
        climate = new JComboBox(climates);
        c.gridx = 4;
        c.gridy = 4;
        c.ipadx = 100;
        this.add(climate, c);


        // --------------------------gravity--------------------------

        gravityLabel = new JLabel("Gravité relative :");
        c.gridx = 0;
        c.gridy = 5;
        c.ipadx = 0;
        this.add(gravityLabel, c);

        SpinnerModel gravityModel = new SpinnerNumberModel(1, 0, null, 1);
        gravity = new JSpinner(gravityModel);
        c.gridx = 4;
        c.gridy = 5;
        c.ipadx = 25;
        this.add(gravity, c);


        // --------------------------hasEndemicLifeform--------------------------

        lifeformLabel = new JLabel("Forme de vie endémique :");
        c.gridx = 0;
        c.gridy = 6;
        c.ipadx = 0;
        this.add(lifeformLabel, c);

        String[] answer = new String[]{"oui", "non", "aucunes données"};
        lifeform = new JComboBox(answer);
        lifeform.setEditable(false);
        c.gridx = 4;
        c.gridy = 6;
        c.ipadx = 0;
        this.add(lifeform, c);


        // --------------------------date--------------------------

        dateLabel = new JLabel("Date de découverte :");
        c.gridx = 0;
        c.gridy = 7;
        c.ipadx = 0;
        c.insets = new Insets(20, 0, 50, 0);
        this.add(dateLabel, c);

        SpinnerModel yearModel = new SpinnerNumberModel(1000, 1000, 9999, 100);
        year = new JSpinner(yearModel);
        c.gridx = 4;
        c.gridy = 7;
        c.ipadx = 0;
        this.add(year, c);

        String[] months = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        month = new JComboBox(months);
        month.setEditable(false);
        c.gridx = 3;
        c.gridy = 7;
        c.ipadx = 25;
        c.anchor = GridBagConstraints.LINE_END;
        this.add(month, c);

        String[] days = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",};
        day = new JComboBox(days);
        day.setEditable(false);
        c.gridx = 2;
        c.gridy = 7;
        this.add(day, c);

        // --------------------------noDate checkbox--------------------------
        checkBoxState = false;
        noDate = new JCheckBox("Pas de date");
        noDate.addItemListener(new CheckBoxListener());
        c.gridx = 5;
        c.gridy = 7;
        c.ipadx = 0;
        this.add(noDate, c);


        // --------------------------Butons--------------------------

        validate = new JButton("Valider");
        validate.addActionListener(new ValidateListener());
        c.gridx = 3;
        c.gridy = 8;
        c.ipadx = 100;
        c.anchor = GridBagConstraints.SOUTH;
        this.add(validate, c);
    }

    private class ValidateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            try {
                year.commitEdit();
                gravity.commitEdit();

                // -----------------------------------retrieve id, name, type, climate, gravity-----------------------------------

                if (name.getText().isEmpty() || id.getText().isEmpty())
                    throw new FormException("Nom et Id");

                Integer newId = Integer.parseInt(id.getText());
                String newName = name.getText();
                String newType = (String)type.getSelectedItem();

                // -----------------------------------retrieve gravity-----------------------------------
                Integer newGravity = null;
                if ((Integer) gravity.getValue() != 0)
                    newGravity = (Integer) gravity.getValue();

                // -----------------------------------retrieve climate-----------------------------------
                String newClimate = (String)climate.getSelectedItem();

                // -----------------------------------retrieve explorer-----------------------------------
                Integer newExplorerId = null;
                for (Explorer e : allExplorers) {
                    if (e.getName() == (String) explorer.getSelectedItem())
                        newExplorerId = e.getExplorerId();
                }

                // -----------------------------------retrieve date-----------------------------------
                GregorianCalendar newDate = null; //TODO gerer le calendrier genre 31 fev devrait etre impossible
                if (!checkBoxState)
                    newDate = new GregorianCalendar((Integer) year.getValue(), (Integer) Integer.parseInt((String) month.getSelectedItem()), Integer.parseInt((String) day.getSelectedItem()));

                // -----------------------------------retrieve lifeform-----------------------------------
                Boolean newLifeform = null;
                if ((String) lifeform.getSelectedItem() != "aucunes données")
                    if ((String) lifeform.getSelectedItem() == "oui")
                        newLifeform = true;
                    else {
                        newLifeform = false;
                    }

                // -----------------------------------object creation-----------------------------------
                AstroBody newBody = new AstroBody(newId, newName, allExplorers.get(explorer.getSelectedIndex()), allTypes.get(type.getSelectedIndex()), newClimate, newGravity, newLifeform, newDate);

                if (fixedId == null) {
                    controller.addAstroBody(newBody);
                    JOptionPane.showMessageDialog(null, "Nouvel objet céleste créé avec succès !", null, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    controller.updateAstroBody(newBody);
                    JOptionPane.showMessageDialog(null, "objet céleste modifié avec succès !", null, JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (TypeException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (NameException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (DateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (IdException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (ClimateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (GravityException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (ConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (AddAstroBodyException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            } catch (FormException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Oups, une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class CheckBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED)
                checkBoxState = true;
            else {
                checkBoxState = false;
            }
        }
    }

    public void setController(ApplicationControler controller) {
        this.controller = controller;
    }
}
