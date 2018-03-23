package kev.participle;

import org.ansj.domain.Term;
import org.ansj.recognition.impl.NatureRecognition;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import java.io.IOException;
import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Kevin
 * \* Date: 2018/3/22
 * \* Time: 16:40
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TextTowordSmartCh {
    public static List<Word> textToWord(String text) {
        List<Word> wordList = new ArrayList<Word>();
        Map<String, List<Integer>> wordMap = new HashMap<String, List<Integer>>();
        // 1、创建一个分析器对象
        Analyzer analyzer = new SmartChineseAnalyzer(); // 智能中文分析器
        // 2、从分析器对象中获得tokenStream对象
        // 参数1：域的名称，可以为null，或者是""
        // 参数2：要分析的文本
//        TokenStream tokenStream = analyzer.tokenStream("name01", "数据库习近平中存储的数据是结构化数据高富帅，即行数据java，可以用二维表结构来逻辑表达实现的数据。");
        TokenStream tokenStream = analyzer.tokenStream("name02", text);
        // 3、设置一个引用(相当于指针)，这个引用可以是多种类型，可以是关键词的引用，偏移量的引用等等
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class); // charTermAttribute对象代表当前的关键词
        // 偏移量(其实就是关键词在文档中出现的位置，拿到这个位置有什么用呢？因为我们将来可能要对该关键词进行高亮显示，进行高亮显示要知道这个关键词在哪？)
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        // 4、调用tokenStream的reset方法，不调用该方法，会抛出一个异常
        try {
            tokenStream.reset();
            // 5、使用while循环来遍历单词列表
            while (tokenStream.incrementToken()) {
                System.out.println("start→" + offsetAttribute.startOffset()); // 关键词起始位置
                // 6、打印单词
                System.out.println(charTermAttribute);
                System.out.println("end→" + offsetAttribute.endOffset()); // 关键词结束位置
                //起始位置添加到map中，完成遍历后再把指定词性的词添加封装到Word类对象中
                if (wordMap.containsKey(charTermAttribute.toString())) {
                    wordMap.get(charTermAttribute.toString()).add(offsetAttribute.startOffset());
                } else {
                    List<Integer> integerList = new ArrayList<Integer>();
                    integerList.add(offsetAttribute.startOffset());
                    wordMap.put(charTermAttribute.toString(), integerList);
                }
            }
            // 7、关闭tokenStream对象

            tokenStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (String key : wordMap.keySet()) {
//            System.out.println(key + "----" + wordMap.get(key)+"----"+wordMap.get(key).size());
//        }
        //词性筛选
        List<String> wordListWithoutCixing = new ArrayList<String>(wordMap.keySet());
        NatureRecognition natureRecognition = new NatureRecognition();
        List<Term> recognition = natureRecognition.recognition(wordListWithoutCixing, 0);


        return new ArrayList<Word>();

    }

    public static void NatureTagDemo() {
        String[] strs = {"对", "非", "ansj", "的", "分词", "结果", "进行", "词性", "标注","kong"};
        List<String> lists = Arrays.asList(strs);
        NatureRecognition natureRecognition = new NatureRecognition();
        List<Term> recognition = natureRecognition.recognition(lists, 0);
        System.out.println(recognition);
    }

    public static List<Word> textToWord(List<Sentence> sentenceList) {
        return new ArrayList<Word>();
    }
}
