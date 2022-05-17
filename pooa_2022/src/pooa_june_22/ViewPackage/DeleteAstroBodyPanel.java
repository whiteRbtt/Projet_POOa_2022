package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAstroBodyPanel extends JPanel{
    private ApplicationControler controller;
    private AstroBodiesListingModel model;
    private JButton deleteBut;
    private TitlePanel title;
    private JTable table;

    public DeleteAstroBodyPanel() throws GeneralException {
        // -----------------------------------Initilization-----------------------------------
        this.setLayout(new BorderLayout());
        title = new TitlePanel("Liste des objets célestes connus de notre galaxie :");
        this.add(title, BorderLayout.NORTH);
        controller = new ApplicationControler();

        // -----------------------------------Displays and model creation-----------------------------------
        deleteBut = new JButton("Supprimer");
        model = new AstroBodiesListingModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel listSelect = table.getSelectionModel();

        // -----------------------------------ActionListener for button-----------------------------------
        deleteBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    if(table.getSelectedRow() != -1){
                        int astroID = Integer.parseInt(table.getValueAt(table.getSelectedRow(),0).toString());
                        int answer = JOptionPane.showConfirmDialog(null, "La suppression de cette planète entraînera également la suppression de ces colonies... êtes-vous sur de votre choix?", "Attention", JOptionPane.WARNING_MESSAGE);
                        if(answer == 0){
                            controller.deleteAstroBody(astroID);
                            model.removeRow(table.getSelectedRow());
                            DeleteAstroBodyPanel.this.removeAll();
                            DeleteAstroBodyPanel.this.add(scrollPane);
                            DeleteAstroBodyPanel.this.add(deleteBut, BorderLayout.SOUTH);
                            DeleteAstroBodyPanel.this.add(title, BorderLayout.NORTH);
                            DeleteAstroBodyPanel.this.validate();
                            DeleteAstroBodyPanel.this.repaint();
                        }else{
                            JOptionPane.showMessageDialog(null,"Suppression annulé ! Choisissez une ligne.", "Consigne", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (GeneralException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),e.getTitle(), JOptionPane.ERROR_MESSAGE );
                }
            }
        });
        this.add(scrollPane);
        this.add(deleteBut, BorderLayout.SOUTH);
    }
}
