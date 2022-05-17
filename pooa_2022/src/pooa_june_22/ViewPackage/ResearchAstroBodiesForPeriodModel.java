package pooa_june_22.ViewPackage;

import pooa_june_22.ControlerPackage.ApplicationControler;
import pooa_june_22.ExceptionPackage.ClimateException;
import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.DateException;
import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ModelPackage.ResearchedAstroBodiesDate;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ResearchAstroBodiesForPeriodModel extends AbstractTableModel {
    private ApplicationControler controler;
    private ArrayList<String> columnNames;
    private ArrayList<ResearchedAstroBodiesDate> contents;

    public ResearchAstroBodiesForPeriodModel(GregorianCalendar beginningDate, GregorianCalendar endingDate) throws ClimateException, NameException, DateException, ConnectionException {
        controler = new ApplicationControler();
        columnNames = new ArrayList<>();
        contents = controler.getAstroBodiesForPeriod(beginningDate, endingDate);
        columnNames.add("Nom de la planète");
        columnNames.add("Climat de la planète");
        columnNames.add("Type de la planète");
        columnNames.add("Explorateur");
        columnNames.add("Née le");
        columnNames.add("Appartient à l'espèce");
        columnNames.add("Nom vernaculaire de l'espèce");
        columnNames.add("Est éteinte");
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public int getRowCount() {
        return contents.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt(int row, int column){
        ResearchedAstroBodiesDate astroBodiesDate = contents.get(row);
        switch(column){
            case 0 :
                return astroBodiesDate.getAstroName();
            case 1 :
                if(astroBodiesDate.getAstroClimate() != null){
                    return astroBodiesDate.getAstroClimate();
                }else{
                    return "inconnu";
                }
            case 2 :
                return astroBodiesDate.getTypeName();
            case 3 :
                if(astroBodiesDate.getExploName() != null){
                    return astroBodiesDate.getExploName();
                }else{
                    return "inconnu";
                }
            case 4 :
                if(astroBodiesDate.getExploBirthInString() != null){
                    return astroBodiesDate.getExploBirthInString();
                }else{
                    return "inconnu";
                }
            case 5 :
                if(astroBodiesDate.getSpecieSName() != null){
                    return astroBodiesDate.getSpecieSName();
                }else{
                    return "inconnu";
                }
            case 6 :
                if(astroBodiesDate.getSpecieVName() != null){
                    return astroBodiesDate.getSpecieVName();
                }else{
                    return "inconnu";
                }
            case 7 :
                if(astroBodiesDate.isExtinct() != null){
                    if(astroBodiesDate.isExtinct()){
                        return "Oui";
                    }else{
                        return "Non";
                    }
                }else{
                    return "inconnu";
                }
            default:return null;
        }
    }

    public Class getColumnClass(int column){
        Class c;
        switch (column){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                c = String.class;
                break;
            default: c = null;
        }
        return c;
    }

    public ArrayList<ResearchedAstroBodiesDate> getContents(){
        return contents;
    }

    public void removeRow(int row){
        contents.remove(row);
    }
}
