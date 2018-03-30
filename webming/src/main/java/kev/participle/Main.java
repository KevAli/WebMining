package kev.participle;

import java.util.List;
import java.util.Set;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Kevin
 * \* Date: 2018/3/23
 * \* Time: 12:14
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Main {
    public static void main(String[] args) {
        String textInputPath = System.getProperty("user.dir") + "\\src\\data\\a.txt";    //文档路径
        String textOutputPath = System.getProperty("user.dir") + "\\src\\data\\result.html";     //高亮文本输出路径
        String stopWordsPath = System.getProperty("user.dir") + "\\src\\data\\stopwords.txt";    //停用词读入路径
        int distanOfWordToBuildGraph = 10;      //相邻为K以内的词有有边
        int countOfTopT = 10;                   //前T个PR值最高的词
        double threshold = 0.001;               //使用PageRank算法的精度阈值
        String text = TextReadAndWrite.textRead(textInputPath);     //读入文本
        List<Word> wordList = TextTowordAnsj.textToWord(text);      //使用ansj的分词器把文档分词
        Set<String> stopWords = TextTowordAnsj.getStopWord(stopWordsPath);                      //读入停用词
        List<Word> wordListWithoutStopWord = TextTowordAnsj.detachStopWord(wordList, stopWords);//删除停用词
        List<Word> wordGraphList = Tools.buildGraphList(wordListWithoutStopWord, distanOfWordToBuildGraph);    //建立图结构
        System.out.println(wordListWithoutStopWord.size() + "---" + wordGraphList.size());
        wordGraphList = PageRank.pageRank(wordGraphList, threshold);    //调用PageRank
        List<Word> topT = Tools.getTopT(wordGraphList, countOfTopT);    //提取前T个关键词
        String highLighttext = HighLighter.highLight(topT, text);       //在文中给前T个关键词高亮为红色，html的格式
        TextReadAndWrite.textWrite(textOutputPath, highLighttext);      //高亮文本以html的格式写入到磁盘
        List<String> phraseList = Tools.wordTOPhrase(topT);             //前T个关键词中，如果有相邻的则组合成短语
        for (Word word : topT) {
            System.out.println(word.getValue());//显示前T个关键词
        }
        System.out.println("------------------------------------");
        for (String phrase : phraseList) {
            System.out.println(phrase);//显示关键词短语
        }

        List<Sentence> sentenceList = TextToSentence.textToSentence(text);  //获取text分割成句子
        for (Sentence sentence : sentenceList) {
            sentence.setFeatureVec(new double[wordGraphList.size()]);  //设置句子特征向量的长度
        }
        for (int i = 0; i < wordGraphList.size(); i++) {
            for (Sentence sentence : sentenceList) {
                Word word = wordGraphList.get(i);
                if (sentence.getValue().contains(word.getValue())) {
                    sentence.getFeatureVec()[i]++;
                    sentence.setPRvalue(sentence.getPRvalue() + word.getPrValue());
                }
            }
        }
        //计算句子的特征向量长度，显示句子的特征向量
        for (Sentence sentence : sentenceList) {
            sentence.setVecLength();
//            System.out.println(sentence.getValue());
//            System.out.println(sentence.getPRvalue());
//            System.out.println(sentence.getVecLength());
//            System.out.println(sentence.getCosOfTwoSentence(sentence));
//            System.out.println("----------------------");
        }
        //计算不同句子的相似性
        List<TwoTuple<Sentence, Sentence>> twoTupleList = Sentence.culCosine(sentenceList);
        List<TwoTuple<Sentence, Sentence>> EdgeList = Sentence.getEdge(twoTupleList, 0.2);
//        sentenceList = Sentence.sentencePageRankOnce(sentenceList, EdgeList);
        sentenceList = Sentence.pageRank(sentenceList, EdgeList, 0.01);
        List<Sentence> TopNList = Sentence.getTopNSentence(sentenceList, 20);
        for (Sentence sentence : TopNList) {
            System.out.println(sentence.getValue());
        }
    }
}
