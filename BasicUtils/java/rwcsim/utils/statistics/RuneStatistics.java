package rwcsim.utils.statistics;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RuneStatistics {
    private static Logger log = LogManager.getLogger(RuneStatistics.class);
    private static boolean showRecordings = false;
    private static List<String> recording = new ArrayList<>();

    public static void record(String value) {
        recording.add(value);
        if (showRecordings) {
            log.debug("RuneStatistics("+ System.nanoTime() +"): "+value);
        }
    }


}
