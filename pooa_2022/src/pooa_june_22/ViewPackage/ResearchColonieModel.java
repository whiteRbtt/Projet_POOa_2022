package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Colony;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ResearchColonieModel extends AbstractTableModel {
    private ApplicationControler controler;
    private ArrayList<String> columnNames;
    private ArrayList<Colony> contents;

    public ResearchColonieModel(String specie) throws AllEraException, NameException, ColonyException, DateException, ConnectionException, AllColoniesException {
        controler = new ApplicationControler();
        contents = controler.getColonies(specie);
        columnNames = new ArrayList<>();
        columnNames.add("Nom de l'époque");
        columnNames.add("Date de début");
        columnNames.add("Date de fin");
        columnNames.add("Nom de la planète");
        columnNames.add("Nom de la colonie");
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return contents.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt(int row, int column) {
        Colony colony = contents.get(row);
        switch (column) {
            case 0:
                return colony.getEra().getName();
            case 1:
                return colony.getEra().getBeginningInString();
            case 2:
                return colony.getEra().getEndingInString();
            case 3:
                return colony.getLocation();
            case 4: {
                if (colony.getName() != null)
                    return colony.getName();
                else return "Inconnu";
            }
            default:
                return null;
        }
    }

    public Class getColumnClass(int column) {
        Class c;
        switch (column) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                c = String.class;
                break;
            default:
                c = null;
        }
        return c;
    }

    public ArrayList<Colony> getContents() {
        return contents;
    }

    public void removeRow(int row) {
        contents.remove(row);
    }
}
