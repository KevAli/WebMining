package kev.participle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Kevin
 * \* Date: 2018/3/23
 * \* Time: 0:15
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class PageRank {
    public static List<Word> pageRankOnce(List<Word> wordGraph) {
        Map<Word, Double> prMap = new HashMap<Word, Double>();
        double totalPR = 0;
        int prCount = 0;
        for (Word word : wordGraph) {
            prMap.put(word, 0.0);
            totalPR += word.getPrValue();
            prCount++;

        }
        for (Word key : wordGraph) {
//            System.out.println(key);
            for (Word value : key.getNeighbors()) {
//                System.out.println(key);
                double newPR = prMap.get(value) + (key.getPrValue() / key.getNeighbors().size());
//                System.out.println(newPR);
                prMap.put(value, newPR);
            }
        }
        for (Word word : wordGraph) {
            double newPR = prMap.get(word) * 0.85 + 0.15;//* totalPR / prCount;
            word.setPrValue(newPR);
        }
        return wordGraph;
    }

    public static List<Word> pageRank(List<Word> wordGraph, double threshold) {
        Map<Word, Double> prMap = new HashMap<Word, Double>();
        for (Word word : wordGraph) {
            prMap.put(word, word.getPrValue());
        }
        wordGraph = pageRankOnce(wordGraph);
        if (culThreshold(prMap, wordGraph) <= threshold) {
            return wordGraph;
        } else {
            return pageRank(wordGraph, threshold);
        }
    }

    private static double culThreshold(Map<Word, Double> prMap, List<Word> wordGraph) {
        double prMapTotol = 0;
        double threshold = 0;
        for (Word word : wordGraph) {
            threshold += Math.abs(prMap.get(word) - word.getPrValue());
            prMapTotol += word.getPrValue();
        }
        double error = threshold / prMapTotol;
        return error;
    }


}
