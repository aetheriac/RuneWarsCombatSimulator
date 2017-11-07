package rwcsim.basicutils.dice;

import java.util.List;
import java.util.Map;

public class DieRollResultsAnalyzer {
    public static int countBlanks(Map<Die,List<DieFace>> results, List<DieRollResultsModifier> modifiers) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.BLANK).count();
        }

        for (DieRollResultsModifier mod : modifiers) {
            if (mod.getFace() == DieFace.BLANK) {
                cnt += mod.getCount();
            }
        }

        return cnt;
    }

    public static int countAccuracies(Map<Die,List<DieFace>> results, List<DieRollResultsModifier> modifiers) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.ACCURACY).count();
        }

        for (DieRollResultsModifier mod : modifiers) {
            if (mod.getFace() == DieFace.BLANK) {
                cnt += mod.getCount();
            }
        }
        return cnt;
    }

    public static int countMorale(Map<Die,List<DieFace>> results, List<DieRollResultsModifier> modifiers) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.MORALE).count();
        }

        for (DieRollResultsModifier mod : modifiers) {
            if (mod.getFace() == DieFace.BLANK) {
                cnt += mod.getCount();
            }
        }
        return cnt;
    }

    public static int countMortalStrikes(Map<Die,List<DieFace>> results, List<DieRollResultsModifier> modifiers) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.MORTAL_STRIKE).count();
        }

        for (DieRollResultsModifier mod : modifiers) {
            if (mod.getFace() == DieFace.MORTAL_STRIKE) {
                cnt += mod.getCount();
            }
        }
        return cnt;
    }


    public static int countHits(Map<Die,List<DieFace>> results, List<DieRollResultsModifier> modifiers) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.HIT).count();
        }

        for (DieRollResultsModifier mod : modifiers) {
            if (mod.getFace() == DieFace.BLANK) {
                cnt += mod.getCount();
            }
        }
        return cnt;
    }

    public static int countSurges(Map<Die,List<DieFace>> results, List<DieRollResultsModifier> modifiers) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.SURGE).count();
        }

        for (DieRollResultsModifier mod : modifiers) {
            if (mod.getFace() == DieFace.BLANK) {
                cnt += mod.getCount();
            }
        }
        return cnt;
    }


    public static int countDoubleHits(Map<Die,List<DieFace>> results) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.HIT_HIT).count();
        }
        return cnt;
    }



    public static int countHitSurge(Map<Die,List<DieFace>> results) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.HIT_SURGE).count();
        }
        return cnt;
    }


    public static int countHitMorale(Map<Die,List<DieFace>> results) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.HIT_MORALE).count();
        }
        return cnt;
    }


    public static int countHitAccuracy(Map<Die,List<DieFace>> results) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.HIT_ACCURACY).count();
        }
        return cnt;
    }


    public static int countDoubleSurge(Map<Die,List<DieFace>> results) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.SURGE_SURGE).count();
        }
        return cnt;
    }


    public static int countSurgeMorale(Map<Die,List<DieFace>> results) {
        int cnt = 0;
        for (List<DieFace> l : results.values()) {
            cnt += l.stream().filter(f -> f == DieFace.SURGE_MORALE).count();
        }
        return cnt;
    }

    public static int countAllSurges(Map<Die,List<DieFace>> results, List<DieRollResultsModifier> modifiers) {
        int cnt = 0;
        cnt += countSurges(results, modifiers);
        cnt += countHitSurge(results);
        cnt += countSurgeMorale(results);
        cnt += 2*countDoubleSurge(results);
        return cnt;
    }


    public static int countAllAccuracies(Map<Die,List<DieFace>> results, List<DieRollResultsModifier> modifiers) {
        int cnt = 0;
        cnt += countAccuracies(results, modifiers);
        cnt += countHitAccuracy(results);
        return cnt;
    }


    public static int countAllHits(Map<Die,List<DieFace>> results, List<DieRollResultsModifier> modifiers) {
        int cnt = 0;
        cnt += countHits(results, modifiers);
        cnt += countHitAccuracy(results);
        cnt += countHitSurge(results);
        cnt += countHitMorale(results);
        cnt += 2*countDoubleHits(results);
        return cnt;
    }


    public static int countAllMorale(Map<Die,List<DieFace>> results, List<DieRollResultsModifier> modifiers) {
        int cnt = 0;
        cnt += countMorale(results, modifiers);
        cnt += countHitMorale(results);
        cnt += countSurgeMorale(results);
        return cnt;
    }
}
