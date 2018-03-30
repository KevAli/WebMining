package kev.participle;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Kevin
 * \* Date: 2018/3/21
 * \* Time: 16:37
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 利用ansj封装好的包，把一整篇文章分割成词语，并判断词性，
 * 把符合词性要求的词语封装成一个List<Word>对象返回
 * \
 */
public class TextTowordAnsj {
    public static List<Word> textToWord(String text) {
        //添加筛选的词性
        Set<String> expectedNature = new HashSet<String>() {{
            add("n");
            add("v");
            add("vd");
            add("vn");
            add("vf");
            add("vx");
            add("vi");
            add("vl");
            add("vg");
            add("nt");
            add("nz");
            add("nw");
            add("nl");
            add("ng");
            add("wh");
        }};
//        FilterModifWord.insertStopWord("并且") ;
//        FilterModifWord.insertStopWord("但是") ;
//        FilterModifWord.setUpdateDic(strHashMap);
        //分词结果的一个封装，主要是一个List<Term>的terms
        Result result = ToAnalysis.parse(text);
//        System.out.println(result.getTerms());

        List<Term> terms = result.getTerms(); //拿到terms
//        System.out.println(terms.size());
        Map<String, List<Integer>> wordIndexMap = new HashMap<String, List<Integer>>();
        Map<String, String> wordCixingMap = new HashMap<String, String>();
        for (int i = 0; i < terms.size(); i++) {
            String word = terms.get(i).getName(); //拿到词
            String natureStr = terms.get(i).getNatureStr(); //拿到词性
            if (expectedNature.contains(natureStr)) {
//                System.out.println(word + "---" + natureStr);
                wordCixingMap.put(word, natureStr);//添加词性
                if (wordIndexMap.containsKey(word)) {
                    wordIndexMap.get(word).add(terms.get(i).getOffe());
                } else {
                    List<Integer> indexList = new ArrayList<Integer>();
                    indexList.add(terms.get(i).getOffe());
                    wordIndexMap.put(word, indexList);
                }
            }
        }
        List<Word> wordList = new ArrayList<Word>();
        //遍历Map封装成Word对象并返回一个List
        for (String key : wordIndexMap.keySet()) {
//            System.out.println(key + "---" + wordIndexMap.get(key) + "----" + wordCixingMap.get(key));
            wordList.add(new Word(key, wordIndexMap.get(key), wordCixingMap.get(key)));

        }
        return wordList;
    }

    public static List<Word> textToWord(List<Sentence> sentenceList) {

        return new ArrayList<Word>();
    }

    public static Set<String> getStopWord(String stopWordPath) {
        Set<String> stopWord = new HashSet<String>();
        File textFile = new File(stopWordPath);
        BufferedReader reader = null;
        String tempString = null;
        try {
            reader = new BufferedReader(new FileReader(textFile));
            while ((tempString = reader.readLine()) != null) {
                stopWord.add(tempString);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stopWord;
    }

    public static List<Word> detachStopWord(List<Word> wordList, Set<String> stopWords) {
        Iterator<Word> wordIterator = wordList.iterator();
        while (wordIterator.hasNext()) {
            //Word word = wordIterator.next();
            if (stopWords.contains(wordIterator.next().getValue())) {
                wordIterator.remove();
            }
        }
//        删除要改变结构，需要使用迭代器
//        for (Word word : wordList) {
//            if (stopWords.contains(word.getValue())) {
//                wordList.remove(word);
//            }
//        }
        return wordList;
    }
}
