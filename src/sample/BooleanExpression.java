package sample;

import java.util.LinkedList;

/**
 * Created by aaa on 6.12.2016.
 */
public class BooleanExpression {


    private void sort(BENode[] array) {
        int j, len = array.length;
        BENode element;

        for (int i = 1; i < len; i++) {
            element = array[i];
            j = i;
            while (j > 0 && element.getSum() < array[j - 1].getSum()) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = element;
        }
    }

    public LinkedList<String> simplify(BENode[] BENode) {
        sort(BENode); // sıralama

        int count;
        int lenI = BENode.length;
        BENode[] temp = new BENode[lenI];
        LinkedList<String> simplifiedExpression = new LinkedList<>();

        do {
            count = 0;
            for (int i = 0; i < lenI - 1; i++) { // node dizisini dönmek için
                for (int j = i + 1; j < lenI; j++) { // bir bit fark olanlar kıyaslanıyor
                    if (BENode[i] != null && BENode[j] != null && BENode[i].getSum() + 1 == BENode[j].getSum()) {
                        if (BENode[i].getA() != BENode[j].getA() && BENode[i].getB() == BENode[j].getB()
                                && BENode[i].getC() == BENode[j].getC() && BENode[i].getD() == BENode[j].getD()) {
                            temp[i] = new BENode(BENode[i].getA(), BENode[i].getB(), BENode[i].getC(), BENode[i].getD());
                            temp[i].removeA();
                            count++;
                        } else if (BENode[i].getA() == BENode[j].getA() && BENode[i].getB() != BENode[j].getB()
                                && BENode[i].getC() == BENode[j].getC() && BENode[i].getD() == BENode[j].getD()) {
                            temp[i] = new BENode(BENode[i].getA(), BENode[i].getB(), BENode[i].getC(), BENode[i].getD());
                            temp[i].removeB();
                            count++;
                        } else if (BENode[i].getA() == BENode[j].getA() && BENode[i].getB() == BENode[j].getB()
                                && BENode[i].getC() != BENode[j].getC() && BENode[i].getD() == BENode[j].getD()) {
                            temp[i] = new BENode(BENode[i].getA(), BENode[i].getB(), BENode[i].getC(), BENode[i].getD());
                            temp[i].removeC();
                            count++;
                        } else if (BENode[i].getA() == BENode[j].getA() && BENode[i].getB() == BENode[j].getB()
                                && BENode[i].getC() == BENode[j].getC() && BENode[i].getD() != BENode[j].getD()) {
                            temp[i] = new BENode(BENode[i].getA(), BENode[i].getB(), BENode[i].getC(), BENode[i].getD());
                            temp[i].removeD();
                            count++;
                        }
                    }
                }
            }
            if (count != 0)
                for (int i = 0; i < lenI; i++) {
                    BENode[i] = temp[i];
                    temp[i] = null;
                }
        } while (count > 0);

        for (int i = 0; i < lenI; i++) {
            if (BENode[i] != null) {
                simplifiedExpression.add((BENode[i].getA() == 0 ? "A'." : (BENode[i].getA() == 1 ? "A." : ""))
                        + (BENode[i].getB() == 0 ? "B'." : (BENode[i].getB() == 1 ? "B." : ""))
                        + (BENode[i].getC() == 0 ? "C'." : (BENode[i].getC() == 1 ? "C." : ""))
                        + (BENode[i].getD() == 0 ? "D'." : (BENode[i].getD() == 1 ? "D." : "")) + " ");
            }
        }
        return simplifiedExpression;
    }


}
