package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.ResearchedColonies;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ResearchColonieModel extends AbstractTableModel {
    private ApplicationControler controler;
    private ArrayList<String> columnNames;
    private ArrayList<ResearchedColonies> contents;

    public ResearchColonieModel(String specie) throws AllEraException, NameException, ColonyException, DateException, ConnectionException {
        controler = new ApplicationControler();
        contents = controler.getColonies(specie);
        columnNames = new ArrayList<>();
        columnNames.add("Nom de l'époque");
        columnNames.add("Date de début");
        columnNames.add("Date de fin");
        columnNames.add("Nom de la planète");
        columnNames.add("Nom de tribu");
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount(){
        return contents.size();
    }

    public String getColumnName(int column){
        return columnNames.get(column);
    }

    public Object getValueAt(int row, int column){
        ResearchedColonies colony = contents.get(row);
        switch(column){
            case 0 : return colony.getEraName();
            case 1 : return colony.getEraBeginningInString();
            case 2 : return colony.getEraEndingInString();
            case 3 : return colony.getAstroName();
            case 4 : {if(colony.getTribalName() != null)
                return colony.getTribalName();
                else return "Inconnu";
            }
            default: return null;
        }
    }

    public Class getColumnClass(int column){
        Class c;
        switch(column){
            case 0 :
            case 1 :
            case 2:
            case 3 :
            case 4 : c = String.class;
            break;
            default: c = null;
        }
        return c;
    }

    public ArrayList<ResearchedColonies> getContents() {
        return contents;
    }

    public void removeRow(int row){
        contents.remove(row);
    }
}
