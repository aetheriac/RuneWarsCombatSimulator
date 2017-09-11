package rwcsim.factions.base;

import rwcsim.base.Formation;
import rwcsim.factions.base.upgrades.UpgradeType;
import rwcsim.factions.daqan.DaqanUnit;
import rwcsim.factions.latari.LatariUnit;
import rwcsim.factions.uthuk.UthukUnit;
import rwcsim.factions.waiqar.WaiqarUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsayles on 8/17/17.
 */
public interface Unit {
    List<Formation> legalFormations = new ArrayList<>();
    List<UpgradeType> legalUpgrades = new ArrayList<>();

    void initializeUnit();

    Siege getAsSiege();

    Infantry getAsInfantry();

    Cavalry getAsCavalry();

    Hero getAsHero();

    DaqanUnit getAsDaqanUnit();
    LatariUnit getAsLatariUnit();
    UthukUnit getAsUthukUnit();
    WaiqarUnit getAsWaiqarUnit();

    void populateFormations();
    void populateUpgrades(Formation formation);

}
