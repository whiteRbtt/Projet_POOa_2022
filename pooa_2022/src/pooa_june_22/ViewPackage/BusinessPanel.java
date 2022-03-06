package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class BusinessPanel extends JPanel {
    private ApplicationControler controller;
    private JSpinner beginDate, endingDate;
    private JButton validate;
    private JLabel beginDateText, endingDateText;
    public BusinessPanel(){
        this.controller = new ApplicationControler();
        this.setLayout(new BorderLayout());
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(2,2));
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        SpinnerModel model = new SpinnerNumberModel(1000, 1000, 9999,1);
        beginDate = new JSpinner(model);
        endingDate = new JSpinner(model);
        beginDateText = new JLabel("Date de commencement");
        endingDateText = new JLabel("Date de fin");
        validate = new JButton("Calculé");
        panelForm.add(beginDateText);
        panelForm.add(beginDate);
        panelForm.add(endingDateText);
        panelForm.add(endingDate);
        this.add(panelForm, BorderLayout.CENTER);
        this.add(validate, BorderLayout.SOUTH);
    }
}
