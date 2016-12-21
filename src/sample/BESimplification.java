package sample;

import java.util.LinkedList;

/**
 * Created by aaa on 6.12.2016.
 */
public class BESimplification {
    private BANode[] baNode;


    public BESimplification(String[] expression) {
        baNode = new BANode[expression.length];
        int len = expression.length;
        for (int i = 0; i < len; i++) {
            String[] splited = expression[i].split("\\.");
            switch (splited.length) {
                case 2:
                    baNode[i] = new BANode(splited[0].length() == 1 ? 1 : 0, splited[1].length() == 1 ? 1 : 0);
                    break;
                case 3:
                    baNode[i] = new BANode(splited[0].length() == 1 ? 1 : 0, splited[1].length() == 1 ? 1 : 0,
                            splited[2].length() == 1 ? 1 : 0);
                    break;
                case 4:
                    baNode[i] = new BANode(splited[0].length() == 1 ? 1 : 0, splited[1].length() == 1 ? 1 : 0,
                            splited[2].length() == 1 ? 1 : 0, splited[3].length() == 1 ? 1 : 0);
                    break;
                default:
                    break;
            }
        }
    }


    private void sort(BANode[] array) {
        int j, len = array.length;
        BANode element;

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


    public LinkedList<String> simplify() {
        sort(baNode); // sıralama
        LinkedList<String> simplifiedExpression = new LinkedList<>();
        int lenI = baNode.length;
        BANode[] temp = new BANode[lenI];
        int count;
        // 1. tur kontrol
        do {
            count = 0;
            for (int i = 0; i < lenI - 1; i++) { // node dizisini dönmek için
                for (int j = i + 1; j < lenI; j++) { // bir bit fark olanlar kıyaslanıyor
                    if (baNode[i] != null && baNode[j] != null && baNode[i].getSum() + 1 == baNode[j].getSum()) {
                        if (baNode[i].getA() != baNode[j].getA() && baNode[i].getB() == baNode[j].getB()
                                && baNode[i].getC() == baNode[j].getC() && baNode[i].getD() == baNode[j].getD()) {
                            temp[i] = new BANode(baNode[i].getA(), baNode[i].getB(), baNode[i].getC(), baNode[i].getD());
                            temp[i].removeA();
                            count++;
                        } else if (baNode[i].getA() == baNode[j].getA() && baNode[i].getB() != baNode[j].getB()
                                && baNode[i].getC() == baNode[j].getC() && baNode[i].getD() == baNode[j].getD()) {
                            temp[i] = new BANode(baNode[i].getA(), baNode[i].getB(), baNode[i].getC(), baNode[i].getD());
                            temp[i].removeB();
                            count++;
                        } else if (baNode[i].getA() == baNode[j].getA() && baNode[i].getB() == baNode[j].getB()
                                && baNode[i].getC() != baNode[j].getC() && baNode[i].getD() == baNode[j].getD()) {
                            temp[i] = new BANode(baNode[i].getA(), baNode[i].getB(), baNode[i].getC(), baNode[i].getD());
                            temp[i].removeC();
                            count++;
                        } else if (baNode[i].getA() == baNode[j].getA() && baNode[i].getB() == baNode[j].getB()
                                && baNode[i].getC() == baNode[j].getC() && baNode[i].getD() != baNode[j].getD()) {
                            temp[i] = new BANode(baNode[i].getA(), baNode[i].getB(), baNode[i].getC(), baNode[i].getD());
                            temp[i].removeD();
                            count++;
                        }
                    }
                }
            }
            if (count != 0)
                for (int i = 0; i < lenI; i++) {
                    baNode[i] = temp[i];
                    temp[i] = null;
                }


        } while (count > 0);

        for (int i = 0; i < lenI; i++) {
            if (baNode[i] != null) {
                simplifiedExpression.add((baNode[i].getA() == 0 ? "A'." : (baNode[i].getA() == 1 ? "A." : ""))
                        +  (baNode[i].getB() == 0 ? "B'." : (baNode[i].getB() == 1 ? "B." : ""))
                        +  (baNode[i].getC() == 0 ? "C'." : (baNode[i].getC() == 1 ? "C." : ""))
                        + (baNode[i].getD() == 0 ? "D'." : (baNode[i].getD() == 1 ? "D." : "")) + " ");
            }
        }

        return simplifiedExpression;
    }


}
