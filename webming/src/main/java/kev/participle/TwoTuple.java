package kev.participle;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Kevin
 * \* Date: 2018/3/30
 * \* Time: 11:35
 * \* To change this template use File | Settings | File Templates.
 * \* Description:用元组的形式保存(句子1，句子2，cosine)关系
 * \
 */

public class TwoTuple<A, B> {
    public final A first;
    public final B second;
    public final double cosine;

    public TwoTuple(A a, B b, double cosine) {
        this.first = a;
        this.second = b;
        this.cosine = cosine;
    }

    @Override
    public String toString() {
        return "TwoTuple{" +
                "cosine=" + cosine +
                '}';
    }
}
