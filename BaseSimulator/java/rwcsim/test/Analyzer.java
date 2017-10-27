package rwcsim.test;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dsayles on 8/17/17.
 */
public class  Analyzer {
    private static Logger log = Logger.getLogger(Analyzer.class);
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
        });

        for (Map.Entry<String,Long> ul : unitLife.entrySet()) {
            int value = ul.getValue().intValue();
            log.info(ul.getKey() + " (isAlive): " + value+ " (delta): " + ((double)value)/stats.size()*100);
        }

        log.info("First regen: Max("+fmaxregencount.get()+") Used("+fusedregencount.get()+")");
        log.info("Second regen: Max("+smaxregencount.get()+") Used("+susedregencount.get()+")");

        // used regeneration
    }
}
