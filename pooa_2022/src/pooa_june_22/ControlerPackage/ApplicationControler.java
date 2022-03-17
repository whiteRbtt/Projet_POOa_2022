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
    private TypeManager typeManager;

    public ApplicationControler() {
        setAstroBodyManager(new AstroBodyManager());
        setSpecieManager(new SpecieManager());
        setColonyManager(new ColonyManager());
        setExplorerManager(new ExplorerManager());
        setEraManager(new EraManager());
        setTypeManager(new TypeManager());
    }


    //------Setters------

    public void setTypeManager(TypeManager typeManager) {
        this.typeManager = typeManager;
    }

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
    public void deleteAstroBody(int astroID) throws ConnectionException, DeleteAstroBodyException, GeneralException {
        astroBodyManager.deleteAstroBody(astroID);
    }
    public void addAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException {
        astroBodyManager.addAstroBody(astroBody);
    }

    public void updateAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException, UpdateAstroBodyException {
        astroBodyManager.updateAstroBody(astroBody);
    }

    public ArrayList<AstroBody> getAllAstroBodies() throws GravityException, ConnectionException, ClimateException, TypeException, AllAstroBodiesException, NameException, DateException, IdException, GeneralException {
        return astroBodyManager.getAllAstroBodies();
    }

    public ArrayList<ResearchedAstroBodies> getAstroBodiesForType(String type) throws ConnectionException, ClimateException, NameException, DateException, IdException, GravityException, TypeException {
        return astroBodyManager.getAstroBodiesForType(type);
    }

    public ArrayList<ResearchedAstroBodiesDate> getAstroBodiesForPeriod(GregorianCalendar beginning, GregorianCalendar ending) throws ClimateException, NameException, DateException, ConnectionException{
        return astroBodyManager.getAstroBodiesForPeriod(beginning, ending);
    }

    public int getMaxId() throws ConnectionException, IdException{
        return astroBodyManager.getMaxId();
    }

    public double getGravityAverage(String type) throws ClimateException, NameException, DateException, TypeException, IdException, ConnectionException, GravityException, GravityAverageException {
        return astroBodyManager.getGravityAverage(type);
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
    public ArrayList<ResearchedColonies> getColonies( String specie) throws ConnectionException, DateException, ColonyException, AllEraException, NameException, AllColoniesException {
        return colonyManager.getColonies(specie);
    }

    //-----------------------------------TypeManager-----------------------------------
    public ArrayList<AstroType> getAllTypes() throws NameException, TypeException, ConnectionException, AllTypesException, TypeIDException {
        return typeManager.getAllTypes();
    }
}
