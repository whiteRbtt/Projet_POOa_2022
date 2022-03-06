package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AstroBodiesListingModel extends AbstractTableModel {
    ApplicationControler controller;
    private ArrayList<String> columnNames;
    private ArrayList<AstroBody> datas;

    public AstroBodiesListingModel() throws ClimateException, NameException, DateException, TypeException, AllAstroBodiesException, IdException, ConnectionException, GravityException, GeneralException {
        controller = new ApplicationControler();
        datas = controller.getAllAstroBodies();
        columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("Nom");
        columnNames.add("Type");
        columnNames.add("Climat");
        columnNames.add("Gravité relative");
        columnNames.add("Propice à la vie");
        columnNames.add("Date d'exploration");
        columnNames.add("Premier explorateur connus");;
    }
    public int getColumnCount(){
        return columnNames.size();
    }
    public int getRowCount(){
        return datas.size();
    }
    public String getColumnName(int column){
        return columnNames.get(column);
    }
    public Object getValueAt(int row, int column){
        AstroBody astroBody = datas.get(row);
        switch (column){
            case 0 : return astroBody.getAstroId();
            case 1 : return astroBody.getName();
            case 2 : return astroBody.getType();
            case 3 : return astroBody.getClimate();
            case 4 : return astroBody.getGravity();
            case 5 : {if(astroBody.getHasLifeform())return "Oui";
            else return "Non";}
            case 6 : return astroBody.getFirstExploDateInString();
            case 7 : {if(astroBody.getFirstExplorer()!=null)return astroBody.getFirstExplorer().getName();
            else return "inconnu";}
            default: return null;
        }
    }
    public Class getColumnClass(int column){
        Class c;
        switch(column){
            case 0 :
            case 4 :
                c = Integer.class;
            break;
            case 1 :
            case 2 :
            case 3 :
            case 5 :
            case 6 :
            case 7 :
                c = String.class;
            break;
            default: c=null;
        }
        return c;
    }

    public ArrayList<AstroBody> getDatas(){return datas;}
    public void removeRow(int row){
        datas.remove(row);
    }
}