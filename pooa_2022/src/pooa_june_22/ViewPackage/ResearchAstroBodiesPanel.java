package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResearchAstroBodiesPanel extends JPanel {
    private TitlePanel title;
    private JLabel typeLabel;
    private JComboBox types;
    private JButton validate;
    private Container container;
    private TablePanel table;
    private ResearchAstroBodiesModel astroModel;
    private ApplicationControler controler;

    private ArrayList<AstroType> allTypes;

    public ResearchAstroBodiesPanel(){
        this.setLayout(new BorderLayout());
        title = new TitlePanel("Rechercher des planètes selon un type : ");
        this.add(title, BorderLayout.NORTH);

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
            container = new Container();
            container.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

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

            validate = new JButton("Rechercher");
            validate.addActionListener(new SearchAstroListener());
            c.gridx = 1;
            c.gridy = 2;
            c.ipadx = 100;
            c.anchor = GridBagConstraints.LAST_LINE_END;
            container.add(validate, c);

            this.add(container, BorderLayout.CENTER);
        } catch (NameException e) {
            e.printStackTrace();
        } catch (TypeException e) {
            e.printStackTrace();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }

    private class SearchAstroListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            container.removeAll();
            container.setLayout(new BorderLayout());
            try{
                astroModel = new ResearchAstroBodiesModel((String)types.getSelectedItem());
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
            } catch (IdException e) {
                e.printStackTrace();
            } catch (ConnectionException e) {
                e.printStackTrace();
            } catch (GravityException e) {
                e.printStackTrace();
            }
        }
    }
}
