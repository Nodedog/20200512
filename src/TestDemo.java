import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
*
*                           Java对象的比较
* 比较大小  Comparable
* 比较对象
*
*
* */


//Comparable 默认是Object的可以加个泛型参数<Card>
class Card implements Comparable<Card> {
    public String rank;
    public String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    //前四部都是固定的，不管给哪个类实现equals方法前四步是必须的

    //因为在自己定义的类中如果不重写 equals，默认的 equals 逻辑就是引用比较
    //可以通过重写 自定义“什么叫相等”
    //可以按照身份，花色，点数 都能自定义为相等
    @Override
    public boolean equals(Object obj) {
        //1.看this和obj身份是否相同
        if (this == obj){
            return true;
        }
        //2.如果obj为null，一定不相等
        if (obj == null){
            return false;
        }
        //3.判断obj类型能否强转成 Card
        if (!(obj instanceof Card)){
            return false;
        }
        //4.类型转换，把参数强转成Card
        Card card = (Card) obj;
        //5.真正按照自定义规则比较
        return this.rank.equals(card.rank);
    }

    @Override
    //给Comparable加了泛型参数<Card>之后就把Object改为Card
    //public int compareTo(Object o) {
    public int compareTo(Card o) {
        //传进来的参数 o   this
        // this < o ,返回 <0的数
        // this > o ,返回 >0的数
        // this = o ,返回 0
        //自定义
        int rank1 = this.convertRank();
        int rank2 = o.convertRank();
        //升序
        //return rank1 - rank2;
        //降序
        return rank2 - rank1;
    }

    private  int convertRank(){
        //把String类型的 rank 转成int 值
        //2-10 => 2-10
        //J => 11  Q => 12  K => 13  A => 14
        if ("A".equals(rank)){
            return 14;
        }
        if ("J".equals(rank)){
            return 11;
        }
        if ("Q".equals(rank)){
            return 12;
        }
        if ("K".equals(rank)){
            return 13;
        }
        return Integer.parseInt(rank);
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank='" + rank + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }
}

public class TestDemo {
    public static void main(String[] args) {
        Card card1 = new Card("10","♠");
        Card card2 = new Card("10","♥");
        Card card3 = card2;
        // == 始终比较的是两个对象的身份，比较的是两个引用中地址的值
        System.out.println(card1);
        System.out.println(card2);
        //这里 == 比较的是两个地址的值 所以false
        System.out.println(card1 == card2);

        //这里是两个引用指向一个地址 所以true
        System.out.println(card2 == card3);

        System.out.println("==============================");

        //因为在自己定义的类中如果不重写 equals，默认的 equals 逻辑就是引用比较
        //没重写之前是false  true
        //重写之后是true true
        System.out.println(card1.equals(card2));
        System.out.println(card2.equals(card3));


        //比较值大小 重写Comparable方法
        System.out.println(card1.compareTo(card2));

        List<Card> cards =  new ArrayList<>();
        cards.add(new Card("A","红桃"));
        cards.add(new Card("J","红桃"));
        cards.add(new Card("Q","红桃"));
        cards.add(new Card("K","红桃"));
        cards.add(new Card("10","红桃"));
        Collections.sort(cards);
        System.out.println(cards);

    }
}
