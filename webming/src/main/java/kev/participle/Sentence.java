package kev.participle;

import lombok.Data;

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
    String sentenceNumber;
    String value;

    public Sentence(String sentenceNumber, String value) {
        this.sentenceNumber = sentenceNumber;
        this.value = value;
    }
}
