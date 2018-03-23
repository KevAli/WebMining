package kev.participle;

import java.util.List;

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
        String textInputPath = "E:\\a.txt";
        String textOutputPath = "E:\\result2.html";
        int distanOfWordToBuildGraph = 10;
        int countOfTopT = 10;
        double threshold = 0.001;
        String text = TextReadAndWrite.textRead(textInputPath);
        List<Word> wordList = TextTowordAnsj.textToWord(text);
        List<Word> wordGraphList = Tools.buildGraphList(wordList, distanOfWordToBuildGraph);
        System.out.println(wordList.size() + "---" + wordGraphList.size());
        wordGraphList = PageRank.pageRank(wordGraphList, threshold);
        List<Word> topT = Tools.getTopT(wordGraphList, countOfTopT);
        String highLighttext = HighLighter.highLight(topT, text);
        TextReadAndWrite.textWrite(textOutputPath, highLighttext);
        List<String> phraseList = Tools.wordTOPhrase(topT);
        for (String phrase : phraseList) {
            System.out.println(phrase);
        }
    }
}
