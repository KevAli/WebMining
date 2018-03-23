package kev.participle;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Kevin
 * \* Date: 2018/3/20
 * \* Time: 15:25
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Getter
@Setter
public class Word {
    private String value = "";
    private List<Integer> indexList = new ArrayList<Integer>();
    private double prValue = 0;
    private String type = "";
    private int neighborCount = 0;
    private Set<Word> neighbors = new HashSet<Word>();


    public enum Type {
        VERB, NOUN, ADJECTIVE, ADVERB
    }

    public Word() {
    }

    public Word(String value, List<Integer> indexList, String type) {
        this.value = value;
        this.indexList = indexList;
        this.type = type;
        this.prValue = indexList.size();
    }
}

