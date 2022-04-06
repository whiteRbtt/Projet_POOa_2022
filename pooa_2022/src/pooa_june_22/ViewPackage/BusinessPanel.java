package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.GeneralException;
import pooa_june_22.ModelPackage.AstroType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BusinessPanel extends JPanel {
    private TitlePanel title;
    private JLabel typeLabel;
    private JComboBox types;
    private JButton validate;
    private Container container;
    private ApplicationControler controler;

    private ArrayList<AstroType> allTypes;

    public BusinessPanel(){
        // -----------------------------------Initialization-----------------------------------
        this.setLayout(new BorderLayout());
        title = new TitlePanel("Gravité moyenne par type : ");
        this.add(title, BorderLayout.NORTH);

        // -----------------------------------Get datas for JCheckBox-----------------------------------
        String[] values = {};
        try{
            controler = new ApplicationControler();
            allTypes = controler.getAllTypes();
            values = new String[allTypes.size()];

            int i = 0;
            for(AstroType type : allTypes){
                values[i] = type.getName();
                i++;
            }

            // -----------------------------------Displays-----------------------------------
            container = new Container();
            container.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            // -----------------------------------Type-----------------------------------
            typeLabel = new JLabel("Selectionnez le type désirée");
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(0, 25, 40, 0);
            c.anchor = GridBagConstraints.LINE_START;
            container.add(typeLabel,c);

            types = new JComboBox(values);
            types.setEditable(false);
            c.gridx = 1;
            c.gridy = 0;
            c.ipadx = 100;
            c.anchor = GridBagConstraints.LINE_END;
            container.add(types, c);

            // -----------------------------------Validate Button-----------------------------------
            validate = new JButton("Rechercher");
            validate.addActionListener(new SearchAstroListener());
            c.gridx = 1;
            c.gridy = 2;
            c.ipadx = 100;
            c.anchor = GridBagConstraints.LAST_LINE_END;
            container.add(validate, c);

            this.add(container, BorderLayout.CENTER);
        } catch (GeneralException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    // -----------------------------------Action for button-----------------------------------
    private class SearchAstroListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            container.removeAll();
            container.setLayout(new BorderLayout());
            try{
                GridBagConstraints c = new GridBagConstraints();
                double average = controler.getGravityAverage((String)types.getSelectedItem());
                TitlePanel result = new TitlePanel("La gravité moyenne d’une planète de type \"" +((String)types.getSelectedItem())+ "\" est de "+ String.format("%.2f",average) + " ! ");
                container.add(result);
                container.revalidate();
                container.repaint();
                setVisible(true);
            } catch (GeneralException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
