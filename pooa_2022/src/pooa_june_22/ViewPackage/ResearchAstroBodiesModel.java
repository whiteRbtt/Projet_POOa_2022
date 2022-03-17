package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.ResearchedAstroBodies;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ResearchAstroBodiesModel extends AbstractTableModel {
    private ApplicationControler controler;
    private ArrayList<String> columnNames;
    private ArrayList<ResearchedAstroBodies> contents;

    public ResearchAstroBodiesModel(String type) throws ClimateException, NameException, DateException, IdException, ConnectionException, GravityException, TypeException {
        controler = new ApplicationControler();
        contents = controler.getAstroBodiesForType(type);
        columnNames = new ArrayList<>();
        columnNames.add("Espèce");
        columnNames.add("Explorateur");
        columnNames.add("Date de découverte");
        columnNames.add("Gravité");
        columnNames.add("Climat");
        columnNames.add("Identifiant");
        columnNames.add("Nom de la planète");
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public int getRowCount() {
        return contents.size();
    }

    public String getColumnName(int column){
        return columnNames.get(column);
    }

    public Object getValueAt(int row, int column) {
        ResearchedAstroBodies astroBody = contents.get(row);
        switch (column) {
            case 0:
                return astroBody.getSpecie();
            case 1:
                return astroBody.getExplorer();
            case 2:
                return astroBody.getFirstExploDateInString();
            case 3:
                return astroBody.getGravity();
            case 4: {
                if (astroBody.getClimate() != null) return astroBody.getClimate();
                else return "Inconnu";
            }
            case 5:
                return astroBody.getAstroId();
            case 6:
                return astroBody.getAstroName();

            default:return null;
        }

    }
        public Class getColumnClass(int column){
            Class c;
            switch(column){
                case 0 :
                case 1:
                case 2:
                case 4:
                case 6:
                    c = String.class;
                    break;
                case 3:
                case 5:
                    c = Integer.class;
                    break;
                default: c = null;
            }
            return c;
        }

    public ArrayList<ResearchedAstroBodies> getContents() {
        return contents;
    }

    public void removeRow(int row){
        contents.remove(row);
    }
}

