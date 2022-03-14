package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.ClimateException;
import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.DateException;
import pooa_june_22.ExceptionPackage.NameException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ResearchAstroBodiesForPeriodPanel extends JPanel {
    private TitlePanel title;
    private JLabel beginLabel, endingLabel;
    private JSpinner dateBegin, dateEnding;
    private JButton validate;
    private Container container;
    private ApplicationControler controler;
    private ResearchAstroBodiesForPeriodModel astroModel;


    public ResearchAstroBodiesForPeriodPanel(){
        this.controler = new ApplicationControler();
        this.setLayout(new BorderLayout());
        title = new TitlePanel("Rechercher des planètes en fonction d'une période");

        container = new Container();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.add(title, BorderLayout.NORTH);

        beginLabel = new JLabel("Date de commencement");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 25, 40, 0);
        c.anchor = GridBagConstraints.LINE_START;
        container.add(beginLabel, c);

        Date startDate = new GregorianCalendar(1000,00,01).getTime();
        Date endDate = new GregorianCalendar(9999, 11,31).getTime();
        SpinnerDateModel startModel = new SpinnerDateModel();
        startModel.setCalendarField(Calendar.YEAR);
        startModel.setStart(startDate);
        startModel.setEnd(endDate);
        startModel.setValue(startDate);
        SimpleDateFormat formatBeg = new SimpleDateFormat("dd.MM.yyyy");
        dateBegin = new JSpinner(startModel);
        dateBegin.setEditor(new JSpinner.DateEditor(dateBegin, formatBeg.toPattern()));
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 100;
        c.anchor = GridBagConstraints.LINE_END;
        container.add(dateBegin, c);

        endingLabel = new JLabel("Date de fin");
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 0;
        c.anchor = GridBagConstraints.LINE_START;
        container.add(endingLabel, c);


        SpinnerDateModel endModel = new SpinnerDateModel();
        endModel.setCalendarField(Calendar.YEAR);
        endModel.setStart(startDate);
        endModel.setEnd(endDate);
        endModel.setValue(endDate);
        dateEnding = new JSpinner(endModel);// TODO : Documentation pour min et max
        dateEnding.setEditor(new JSpinner.DateEditor(dateEnding, formatBeg.toPattern()));
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 70;
        c.anchor = GridBagConstraints.LINE_END;
        container.add(dateEnding, c);

        validate = new JButton("Rechercher");
        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 100;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        container.add(validate, c);
        this.add(container, BorderLayout.CENTER);

        validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                container.removeAll();
                container.setLayout(new BorderLayout());
                try{
                    Date beginDate = (Date) dateBegin.getValue();
                    Date endingDate = (Date) dateEnding.getValue();
                    GregorianCalendar beginCalendar = new GregorianCalendar();
                    GregorianCalendar endingCalendar = new GregorianCalendar();
                    beginCalendar.setTime(beginDate);
                    endingCalendar.setTime(endingDate);
                    astroModel = new ResearchAstroBodiesForPeriodModel(beginCalendar, endingCalendar);
                    JTable table = new JTable(astroModel);
                    JScrollPane scrollPane = new JScrollPane(table);
                    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    container.add(scrollPane);
                    container.revalidate();
                    container.repaint();
                    setVisible(true);

                } catch (ClimateException e) {
                    e.printStackTrace();
                } catch (NameException e) {
                    e.printStackTrace();
                } catch (DateException e) {
                    e.printStackTrace();
                } catch (ConnectionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}