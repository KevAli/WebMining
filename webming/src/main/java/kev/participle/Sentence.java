package kev.participle;

import lombok.Data;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Kevin
 * \* Date: 2018/3/21
 * \* Time: 14:30
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Data
public class Sentence {
    String value;
    List<Word> wordList = new ArrayList<Word>();
    double[] featureVec;
    double PRvalue = 0;
    double vecLength = 0;
    RealVector vector;
    int neiborCount = 0;

    public Sentence(String value) {
        this.value = value;
    }

    //取向量模
    public void setVecLength() {
        this.vector = new ArrayRealVector(featureVec);
        this.vecLength = Math.sqrt(this.vector.dotProduct(this.vector));
    }

    //计算两个向量的conine
    public double getCosOfTwoSentence(Sentence sentence) {
        if (this.vecLength != 0 && sentence.getVecLength() != 0) {
            return this.vector.cosine(sentence.getVector());
        } else {
            return 0;
        }
    }

    //返回一个带有Cosine值的元组List
    public static List<TwoTuple<Sentence, Sentence>> culCosine(List<Sentence> sentenceList) {
        List<TwoTuple<Sentence, Sentence>> twoTupleList = new ArrayList<TwoTuple<Sentence, Sentence>>();
        //把两个句子的关系和cosine值封装成一个元组放到List中。
        for (int i = 0; i < sentenceList.size(); i++) {
            for (int j = i; j < sentenceList.size(); j++) {
                //自己不与自己相连
                if (i != j) {
                    Sentence sentence1 = sentenceList.get(i);
                    Sentence sentence2 = sentenceList.get(j);
                    TwoTuple<Sentence, Sentence> tuple = new TwoTuple<Sentence, Sentence>(sentence1, sentence2,
                            sentence1.getCosOfTwoSentence(sentence2));
                    //相似度在0.2以下的就不要了
                    if (tuple.cosine > 0.2) {
                        twoTupleList.add(tuple);
                    }
                }
            }
        }
        //根据cosine相似度排序
        Collections.sort(twoTupleList, new Comparator<TwoTuple>() {
            public int compare(TwoTuple o1, TwoTuple o2) {
                if (o1.cosine < o2.cosine) {
                    return 1;
                } else if (o1.cosine == o2.cosine) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
//        System.out.println(twoTupleList);
//        System.out.println(twoTupleList.size());

        return twoTupleList;
    }

    //根据指定的比例，把cosine值的前百分之n视为存在边，构造句子的图结构
    public static List<TwoTuple<Sentence, Sentence>> getEdge(List<TwoTuple<Sentence, Sentence>> twoTupleList, double percent) {
        int N = (int) (twoTupleList.size() * percent);
        List<TwoTuple<Sentence, Sentence>> EdgeList = twoTupleList.subList(0, N);
        for (TwoTuple<Sentence, Sentence> twoTuple : EdgeList) {
            twoTuple.first.setNeiborCount(twoTuple.first.getNeiborCount() + 1);
            twoTuple.second.setNeiborCount(twoTuple.second.getNeiborCount() + 1);
        }

        return EdgeList;
    }

    //单词运行的PageRank算法
    public static List<Sentence> sentencePageRankOnce(List<Sentence> sentenceList, List<TwoTuple<Sentence, Sentence>> EdgeList) {
//        List<TwoTuple<Sentence, Sentence>> EdgeList = getEdge(twoTupleList, percent);
        Map<Sentence, Double> prMap = new HashMap<Sentence, Double>();
        double totalPR = 0;
        int prCount = 0;
        for (Sentence sentence : sentenceList) {
            prMap.put(sentence, 0.0);
            totalPR += sentence.getPRvalue();
            prCount++;
        }
        for (int i = 0; i < sentenceList.size(); i++) {
            for (int j = i + 1; j < sentenceList.size(); j++) {
                Sentence sentence1 = sentenceList.get(i);
                Sentence sentence2 = sentenceList.get(j);
                for (TwoTuple<Sentence, Sentence> twoTuple : EdgeList) {
                    if (twoTuple.first == sentence1 && twoTuple.second == sentence2) {
                        double newPR = prMap.get(sentence2) + sentence1.getPRvalue() / sentence1.getNeiborCount();
                        prMap.put(sentence2, newPR);
                        newPR = prMap.get(sentence1) + sentence2.getPRvalue() / sentence2.getNeiborCount();
                        prMap.put(sentence1, newPR);
                    }
                }
            }
        }
        for (Sentence sentence : sentenceList) {
            double newPR = prMap.get(sentence) * 0.85 + 0.15;//* totalPR / prCount;
            sentence.setPRvalue(newPR);
        }
        return sentenceList;
    }

    //递归调用PageRank直到误差小于阈值
    public static List<Sentence> pageRank(List<Sentence> sentenceList, List<TwoTuple<Sentence, Sentence>> EdgeList, double threshold) {
        Map<String, Double> prMap = new HashMap<String, Double>();
        for (Sentence sentence : sentenceList) {
            prMap.put(sentence.getValue(), sentence.getPRvalue());
        }
        sentenceList = sentencePageRankOnce(sentenceList, EdgeList);
//        System.out.println(prMap.size()+"------"+sentenceList.size());
        double error = culThreshold(prMap, sentenceList);
//        System.out.println(error);
        if (error <= threshold) {
            return sentenceList;
        } else {
            return pageRank(sentenceList, EdgeList, threshold);
        }
    }

    //计算变化误差
    private static double culThreshold(Map<String, Double> prMap, List<Sentence> sentenceList) {
        double prMapTotol = 0;
        double threshold = 0;
//        System.out.println(prMap.size() + "------" + sentenceList.size());
        for (Sentence sentence : sentenceList) {
//            System.out.println(prMap.get(sentence.getValue()));
            threshold += Math.abs(prMap.get(sentence.getValue()) - sentence.getPRvalue());
            prMapTotol += sentence.getPRvalue();
        }
        double error = threshold / prMapTotol;
        return error;
    }

    public static List<Sentence> getTopNSentence(List<Sentence> sentenceList, int N) {
        Collections.sort(sentenceList, new Comparator<Sentence>() {
            public int compare(Sentence o1, Sentence o2) {
                if (o1.getPRvalue() - o2.getPRvalue() < 0) {
                    return 1;
                } else if (o1.getPRvalue() - o2.getPRvalue() == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return sentenceList.subList(0, N);

    }

}
