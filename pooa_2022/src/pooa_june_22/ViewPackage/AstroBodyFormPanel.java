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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AstroBodyFormPanel extends JPanel {
    private JLabel idLabel, nameLabel, explorerLabel, typeLabel, climateLabel, gravityLabel, lifeformLabel, dateLabel;
    private JTextField id, name;
    private JSpinner gravity, year;
    private JComboBox explorer, type, climate;
    private JButton validate;
    private JCheckBox noDate, lifeform;
    private Boolean checkBoxState, hasLifeForm;
    private ApplicationControler controller;
    private ArrayList<Explorer> allExplorers;
    private ArrayList<AstroType> allTypes;
    private AstroBody astroBody;

    public AstroBodyFormPanel(AstroBody astroBody) throws NameException, TypeException, ConnectionException {
        this.astroBody = astroBody;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // --------------------------get all explorers--------------------------
        String[] values = {};
        try {
            setController(new ApplicationControler());
            allExplorers = controller.getAllExplorers();
            allTypes = controller.getAllTypes();
            values = new String[allExplorers.size() + 1];
            values[10] = "inconnu";

            int i = 0;
            for (Explorer e : allExplorers) {
                values[i] = e.getName();
                i++;
            }

        } catch (GeneralException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    e.getTitle(), JOptionPane.ERROR_MESSAGE);
        }


        // --------------------------astroId--------------------------


        if (astroBody != null) {
            idLabel = new JLabel("Numero d'Id de l'objet :");
            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.LINE_START;
            this.add(idLabel, c);

            id = new JTextField(astroBody.getAstroId());
            id.setText(String.valueOf(astroBody.getAstroId()));
            id.setEditable(false);
            c.gridx = 4;
            c.gridy = 0;
            c.ipadx = 100;
            this.add(id, c);

        }

        // --------------------------name--------------------------

        nameLabel = new JLabel("*Nom de l'objet:");
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(20, 0, 0, 0);
        this.add(nameLabel, c);

        name = new JTextField(15);
        c.gridx = 4;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(name, c);


        // --------------------------firstKnownExplorer--------------------------

        explorerLabel = new JLabel("Premier explorateur connu:");
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(explorerLabel, c);

        explorer = new JComboBox(values);
        explorer.setEditable(false);
        c.gridx = 4;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(explorer, c);

        // --------------------------type--------------------------

        typeLabel = new JLabel("*Type d'objet :");
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(typeLabel, c);

        ArrayList<String> typesName = new ArrayList<>();
        for (AstroType a : allTypes) {
            typesName.add(a.getName());
        }


        type = new JComboBox(typesName.toArray());
        c.gridx = 4;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(type, c);


        // --------------------------climate--------------------------

        climateLabel = new JLabel("Climat :");
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(climateLabel, c);

        String[] climates = new String[]{"tempéré", "chaotique", "tempête", "aride", "glacé", "quantique", "volcanique", "torride", "inconnu"};
        climate = new JComboBox(climates);
        c.gridx = 4;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(climate, c);


        // --------------------------gravity--------------------------

        gravityLabel = new JLabel("Gravité relative (nulle ou postive) :");
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(gravityLabel, c);


        SpinnerModel gravityModel = new SpinnerNumberModel(1, null, null, 1);
        gravity = new JSpinner(gravityModel);
        c.gridx = 4;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(gravity, c);


        // --------------------------hasEndemicLifeform--------------------------

        lifeformLabel = new JLabel("*Forme de vie endémique :");
        c.gridx = 0;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(lifeformLabel, c);


        hasLifeForm = false;
        lifeform = new JCheckBox();
        lifeform.addItemListener(new CheckBoxListener());
        c.gridx = 4;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(lifeform, c);

        // --------------------------date + Pre-completed field for modification--------------------------

        dateLabel = new JLabel("Date de découverte :");
        c.gridx = 0;
        c.gridy = 7;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(dateLabel, c);

        Date startDate = new GregorianCalendar(1001, 00, 01).getTime();
        Date endDate = new GregorianCalendar(9999,11,31).getTime();

        SpinnerDateModel yearModel = new SpinnerDateModel();
        yearModel.setCalendarField(Calendar.YEAR);
        yearModel.setStart(startDate);
        yearModel.setEnd(endDate);

        if(astroBody != null){
            type.setSelectedItem(astroBody.getType().getName());
            name.setText(astroBody.getName());

            if(astroBody.getGravity() != null){
                gravity.setValue(astroBody.getGravity());
            }
            if(astroBody.getClimate() != null){
                climate.setSelectedItem(astroBody.getClimate());
            }
            if(astroBody.getFirstExplorer() != null){
                explorer.setSelectedItem(astroBody.getFirstExplorer());
            }
            if(astroBody.getFirstExploDate() != null){
                yearModel.setValue(astroBody.getFirstExploDate().getTime());
            }

        }else {
            yearModel.setValue(startDate);
        }

        SimpleDateFormat formatBeg = new SimpleDateFormat("dd.MM.yyyy");
        year = new JSpinner(yearModel);
        year.setEditor(new JSpinner.DateEditor(year, formatBeg.toPattern()));
        c.gridx = 4;
        c.gridy = 7;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(year, c);

        // --------------------------noDate checkbox--------------------------
        checkBoxState = false;
        noDate = new JCheckBox("Pas de date");
        noDate.addItemListener(new CheckBoxListener());
        c.gridx = 5;
        c.gridy = 7;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(noDate, c);

        // --------------------------Buttons--------------------------

        validate = new JButton("Valider");
        validate.addActionListener(new ValidateListener());
        c.gridx = 3;
        c.gridy = 8;
        c.anchor = GridBagConstraints.SOUTH;
        c.insets = new Insets(70, 0, 0, 0);
        this.add(validate, c);
    }

    private class ValidateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            try {
                year.commitEdit();
                gravity.commitEdit();

                // -----------------------------------retrieve name-----------------------------------

                if (name.getText().isEmpty())
                    throw new FormException("nom");

                String newName = name.getText();

                // -----------------------------------retrieve gravity-----------------------------------
                Integer newGravity = null;
                if((Integer) gravity.getValue() < 0) throw new GravityException();
                else if ((Integer) gravity.getValue() > 0) newGravity = (Integer) gravity.getValue();

                // -----------------------------------retrieve climate-----------------------------------
                String newClimate = null;
                if(((String)climate.getSelectedItem()).compareTo("inconnu") != 0){
                    newClimate = (String)climate.getSelectedItem();
                }

                // -----------------------------------retrieve date-----------------------------------
                GregorianCalendar newDate = null;
                if (!checkBoxState) {
                    Date beginDate = (Date) year.getValue();
                    newDate = new GregorianCalendar();
                    newDate.setTime(beginDate);
                }

                // -----------------------------------object creation-----------------------------------
                if (astroBody == null) {
                    AstroBody newBody = new AstroBody(controller.getMaxId() + 1, newName, (explorer.getSelectedIndex() == 10 ? null : allExplorers.get(explorer.getSelectedIndex())), allTypes.get(type.getSelectedIndex()), newClimate, newGravity, hasLifeForm, newDate);
                    controller.addAstroBody(newBody);
                    JOptionPane.showMessageDialog(null, "Nouvel objet céleste créé avec succès !", null, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    AstroBody newBody = new AstroBody(astroBody.getAstroId(), newName, (explorer.getSelectedIndex() == 10 ? null : allExplorers.get(explorer.getSelectedIndex())), allTypes.get(type.getSelectedIndex()), newClimate, newGravity, hasLifeForm, newDate);
                    controller.updateAstroBody(newBody);
                    JOptionPane.showMessageDialog(null, "objet céleste modifié avec succès !", null, JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (GeneralException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        e.getTitle(), JOptionPane.ERROR_MESSAGE);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Le parsing n'a pas pu s'effectuer", "Erreur de parsing", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class CheckBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent event) {
            if(event.getItem() == lifeform){
                if (event.getStateChange() == ItemEvent.SELECTED){
                    hasLifeForm = true;
                }else{
                    hasLifeForm = false;
                }
            }else{
                if (event.getStateChange() == ItemEvent.SELECTED)
                    checkBoxState = true;
                else {
                    checkBoxState = false;
                }
            }

        }
    }


    public void setController(ApplicationControler controller) {
        this.controller = controller;
    }

}
