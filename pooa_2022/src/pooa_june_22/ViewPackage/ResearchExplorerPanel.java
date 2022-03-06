package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;

import javax.swing.*;
import java.awt.*;

public class ResearchExplorerPanel extends JPanel {
    private TitlePanel title;
    private JLabel eraBeginLabel, eraEndingLabel;
    private JComboBox eraBegin, eraEnding;
    private JButton validate;
    private ApplicationControler controler;
    private JPanel gridPanel;

    public ResearchExplorerPanel(){
        this.setLayout(new BorderLayout());
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2,2));
        validate = new JButton("Rechercher");
        eraBeginLabel = new JLabel("Epoque 1");
        eraEndingLabel = new JLabel("Epoque 2");
        eraBegin = new JComboBox();
        eraEnding = new JComboBox();
        gridPanel.add(eraBeginLabel);
        gridPanel.add(eraBegin);
        gridPanel.add(eraEndingLabel);
        gridPanel.add(eraEnding);
        this.add(gridPanel, BorderLayout.CENTER);
        this.add(validate, BorderLayout.SOUTH);
    }
}
