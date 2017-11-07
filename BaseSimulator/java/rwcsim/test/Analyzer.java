package rwcsim.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.utils.statistics.DieStatisticCounter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dsayles on 8/17/17.
 */
public class  Analyzer {
    private static Logger log = LogManager.getLogger(Analyzer.class);
    public static void analyze(List<Statistics> stats) {
        AtomicInteger totrounds = new AtomicInteger(0);
        stats.stream().forEach(s -> {totrounds.getAndAdd(s.rounds);});

        System.out.println("Total Rounds: "+ totrounds.get());
        System.out.println("Avg Rounds: "+ totrounds.get()/stats.size());

        Map<String, Long> unitLife = new HashMap<>();
        AtomicInteger fmaxregencount = new AtomicInteger(0);
        AtomicInteger fusedregencount = new AtomicInteger(0);
        AtomicInteger smaxregencount = new AtomicInteger(0);
        AtomicInteger susedregencount = new AtomicInteger(0);
        AtomicInteger fusedtemperedsteel = new AtomicInteger(0);
        AtomicInteger susedtemperedsteel = new AtomicInteger(0);


        AtomicInteger[] firstDice = new AtomicInteger[DieStatisticCounter.STAT_SIZE];
        AtomicInteger[] secondDice = new AtomicInteger[DieStatisticCounter.STAT_SIZE];

        for (int i = 0; i<DieStatisticCounter.STAT_SIZE; i++) {
            firstDice[i] = new AtomicInteger();
            secondDice[i] = new AtomicInteger();
        }


        stats.stream().forEach( s -> {
            if (!unitLife.containsKey(s.first.unit.getName())) {
                unitLife.put(s.first.unit.getName(), new Long(0));
            }
            if (s.first.isAlive) {
                unitLife.put(s.first.unit.getName(), unitLife.get(s.first.unit.getName()) + 1);
            }

            if (!unitLife.containsKey(s.second.unit.getName())) {
                unitLife.put(s.second.unit.getName(), new Long(0));
            }
            if (s.second.isAlive) {
                unitLife.put(s.second.unit.getName(), unitLife.get(s.second.unit.getName()) + 1);
            }

            if (!unitLife.containsKey("Both Alive")) {
                unitLife.put("Both Alive", new Long(0));
            }
            if (s.first.isAlive && s.second.isAlive) {
                unitLife.put("Both Alive", unitLife.get("Both Alive") + 1);
            }

            if (!unitLife.containsKey("First ("+s.first.unit.getName()+") kills Second ("+s.second.unit.getName()+")")) {
                unitLife.put("First ("+s.first.unit.getName()+") kills Second ("+s.second.unit.getName()+")", new Long(0));
            }
            if (s.first.isAlive && !s.second.isAlive) {
                unitLife.put("First ("+s.first.unit.getName()+") kills Second ("+s.second.unit.getName()+")", unitLife.get("First ("+s.first.unit.getName()+") kills Second ("+s.second.unit.getName()+")") + 1);
            }

            if (!unitLife.containsKey("Second ("+s.second.unit.getName()+") kills First ("+s.first.unit.getName()+")")) {
                unitLife.put("Second ("+s.second.unit.getName()+") kills First ("+s.first.unit.getName()+")", new Long(0));
            }
            if (!s.first.isAlive && s.second.isAlive) {
                unitLife.put("Second ("+s.second.unit.getName()+") kills First ("+s.first.unit.getName()+")", unitLife.get("Second ("+s.second.unit.getName()+") kills First ("+s.first.unit.getName()+")") + 1);
            }

            int[] fregen = s.first.getRegen();
            int[] sregen = s.second.getRegen();
            for (int i=0; i<8; i++) {
                fmaxregencount.getAndAdd(fregen[i]);
                fusedregencount.getAndAdd(fregen[i+8]);

                smaxregencount.getAndAdd(sregen[i]);
                susedregencount.getAndAdd(sregen[i+8]);
            }

            int[] fts = s.first.getTemperedSteel();
            int[] sts = s.second.getTemperedSteel();
            fusedtemperedsteel.getAndAdd(fts[1]);
            susedtemperedsteel.getAndAdd(sts[1]);

            for ( int i = 0; i<DieStatisticCounter.STAT_SIZE; i++) {
                firstDice[i].getAndAdd(s.first.getDsc().getStats()[i]);
                secondDice[i].getAndAdd(s.second.getDsc().getStats()[i]);
            }
        });

        for (Map.Entry<String,Long> ul : unitLife.entrySet()) {
            int value = ul.getValue().intValue();
            log.info(ul.getKey() + " (isAlive): " + value+ " (delta): " + ((double)value)/stats.size()*100);
        }

        log.info("First regen: Max("+fmaxregencount.get()+") Used("+fusedregencount.get()+")");
        log.info("Second regen: Max("+smaxregencount.get()+") Used("+susedregencount.get()+")");
        log.info("First  tsteel: Used("+fusedtemperedsteel.get()+")");
        log.info("Second tsteel: Used("+susedtemperedsteel.get()+")");

        StringBuilder firstDiceString = new StringBuilder();
        StringBuilder secondDiceString = new StringBuilder();

        for (int i = 0; i < DieStatisticCounter.STAT_SIZE; i++) {
            if (i>0) {
                firstDiceString.append(" , ");
                secondDiceString.append(" , ");
            }
            firstDiceString.append(firstDice[i].get());
            secondDiceString.append(secondDice[i].get());
        }

        log.info(DieStatisticCounter.statLine());
        log.info("First Dice: " + firstDiceString.toString());
        log.info("Second Dice: " + secondDiceString.toString());


        // used regeneration
    }
}
