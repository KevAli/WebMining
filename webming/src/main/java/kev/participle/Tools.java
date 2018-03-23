package kev.participle;

import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Kevin
 * \* Date: 2018/3/22
 * \* Wordime: 18:12
 * \* Wordo change this template use File | Settings | File Wordemplates.
 * \* Description:
 * \
 */
public class Tools {
    //把map封装成一个List返回，因为要先判断词性，这个函数用不上
    public static List<Word> mapWordoList(Map<String, List<Integer>> map, String type) {
        List<Word> list = new ArrayList<Word>();
        for (String key : map.keySet()) {
            Word word = new Word(key, map.get(key), type);
            list.add(word);
        }
        return list;
    }

    //计算两个Word之间的距离
    public static int distanceOfTwoWords(Word word1, Word word2) {
        int mixDistance = 10000;
        for (int i : word1.getIndexList()) {
            for (int j : word2.getIndexList()) {
                int dis = Math.abs(i + word1.getValue().length() / 2 - j + word2.getValue().length() / 2);
                mixDistance = dis < mixDistance ? dis : mixDistance;
            }
        }
        return mixDistance;
    }

    //建图，用邻接表的方式记录每一条边，无向边的形式存储,边记录在一个新的map
    public static Map<Word, Set<Word>> buildGraph(List<Word> wordList, int distance) {
        Map<Word, Set<Word>> wordGraph = new HashMap<Word, Set<Word>>();
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < wordList.size(); j++) {
                Word word1 = wordList.get(i);
                Word word2 = wordList.get(j);
                int dis = distanceOfTwoWords(word1, word2);
                if (i != j && dis < distance) {
                    if (wordGraph.containsKey(word1)) {
                        wordGraph.get(word1).add(word2);
                        System.out.println(word1.getValue() + "---" + wordGraph.get(word1).size());
                    } else {
                        Set<Word> words = new HashSet<Word>();
                        words.add(word2);
                        wordGraph.put(word1, words);
                        System.out.println(wordGraph.get(word1).size());
                    }
                }
            }
        }
        System.out.println("----------------------------------");
        for (Word word : wordGraph.keySet()) {
            //此处不知为何会出现Null值
            System.out.println(wordGraph.get(word).size());
            word.setNeighborCount(wordGraph.get(word).size());
        }
        return wordGraph;
    }

    //建图，用邻接表的方式记录每一条边，无向边的形式存储，边记录在Word的neighbors属性中
    public static List<Word> buildGraphList(List<Word> wordList, int distance) {
        Set<Word> wordSet = new HashSet<Word>();
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = i; j < wordList.size(); j++) {
                Word word1 = wordList.get(i);
                Word word2 = wordList.get(j);
                int dis = distanceOfTwoWords(word1, word2);
                if (i != j && dis < distance) {
                    word1.getNeighbors().add(word2);
                    word2.getNeighbors().add(word1);
                    wordSet.add(word1);
                    wordSet.add(word2);
                }
            }
        }
        return new ArrayList<Word>(wordSet);

    }

    //返回最高RP值最高的的T个词语
    public static List<Word> getTopT(List<Word> wordList, int t) {
        Collections.sort(wordList, new Comparator<Word>() {
            public int compare(Word o1, Word o2) {
                double diff = o2.getPrValue() - o1.getPrValue();
                if (diff > 0) {
                    return 1;
                } else if (diff == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        List<Word> topTList = wordList.subList(0, t);
        return topTList;
    }

}
