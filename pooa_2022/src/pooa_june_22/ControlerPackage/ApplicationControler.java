package pooa_june_22.ControlerPackage;

import pooa_june_22.BusinessPackage.*;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ApplicationControler {
    private AstroBodyManager astroBodyManager;
    private ColonyManager colonyManager;
    private ExplorerManager explorerManager;
    private SpecieManager specieManager;
    private EraManager eraManager;

    public ApplicationControler() {
        setAstroBodyManager(new AstroBodyManager());
        setSpecieManager(new SpecieManager());
        setColonyManager(new ColonyManager());
        setExplorerManager(new ExplorerManager());
        setEraManager(new EraManager());
    }


    //------Setters------
    public void setAstroBodyManager(AstroBodyManager astroBodyManager) {
        this.astroBodyManager = astroBodyManager;
    }

    public void setColonyManager(ColonyManager colonyManager) {
        this.colonyManager = colonyManager;
    }

    public void setExplorerManager(ExplorerManager explorerManager) {
        this.explorerManager = explorerManager;
    }

    public void setSpecieManager(SpecieManager specieManager) {
        this.specieManager = specieManager;
    }

    public void setEraManager(EraManager eraManager) {
        this.eraManager = eraManager;
    }

    //-----------------------------------AstroBodyManager-----------------------------------
    public void addAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException {
        astroBodyManager.addAstroBody(astroBody);
    }

    public void updateAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException {
        astroBodyManager.updateAstroBody(astroBody);
    }

    public ArrayList<AstroBody> getAllAstroBodies() throws GravityException, ConnectionException, ClimateException, TypeException, AllAstroBodiesException, NameException, DateException, IdException {
        return astroBodyManager.getAllAstroBodies();
    }

    //-----------------------------------SpecieManager-----------------------------------
    public ArrayList<Specie> getAllSpecies() throws ConnectionException, AllSpeciesException, NameException {
        return specieManager.getAllSpecies();
    }

    //-----------------------------------ExplorerManager-----------------------------------
    public ArrayList<Explorer> getAllExplorers() throws ConnectionException, AllExplorersException, IdException, DateException, NameException {
        return explorerManager.getAllExplorers();
    }

    //-----------------------------------ErasManager-----------------------------------
    public Era findEra(GregorianCalendar date) throws ConnectionException, DateException, AllEraException, NameException {
        return eraManager.findEra(date);
    }

    //-----------------------------------ColonyManager-----------------------------------
    public ArrayList<Colony> getColonies(GregorianCalendar date, String specie) throws ConnectionException, DateException, ColonyException, AllEraException, NameException {
        return colonyManager.getColonies(date, specie);
    }

}
