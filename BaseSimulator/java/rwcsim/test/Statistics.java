package rwcsim.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.managers.UnitStateManager;

/**
 * Created by dsayles on 8/17/17.
 */
public class Statistics {
    Logger log = LogManager.getLogger(Statistics.class);
    UnitStateManager first;
    UnitStateManager second;
    int rounds;
    public Statistics(int r, UnitStateManager d, UnitStateManager w) {
        first = d;
        second = w;
        rounds = r;
    }

    public void showStats() {
//        log.info(first.getDsc());



//        System.out.println("R: "+ rounds + " " + first.unit.getName() + ":"+first.isAlive+ " "+ second.unit.getName() +":"+second.isAlive);
//        System.out.println("\nLiving Data");


//        daqan.showStats();
//
//        System.out.println("\nWaiqar Stats");
//        waiqar.showStats();
    }
}
