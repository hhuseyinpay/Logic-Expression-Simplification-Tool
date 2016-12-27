package sample;

import java.util.LinkedList;

/**
 * Created by Hasan Hüseyin Pay on 20.12.2016.
 */
public class KarnaughMap {


    public LinkedList<String> simplify(KMNode[][] kMap) {// dont carelı için, converter içine fonksiyon yazılacak.
        LinkedList<String> simplifiedExpression = new LinkedList<>();
        boolean flag = true;
        int size = kMap[0].length;
        String temp;

        if (kMap.length == 4 && kMap[0].length == 4) {
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
            for (int i = 0; i < 4 && flag; i++) {
                for (int j = 0; j < 4 && flag; j++) {
                    if (kMap[i][j].equal("0"))
                        flag = false;
                }
            }
            if (flag)//16 tane bir varsa geriye null döner.
                return null;
            //************

            // -------v8 h8-------
            for (int j = 0; j < 4; j++) {
                if (v8Checker(kMap, j))
                    simplifiedExpression.add((j == 0 ? "C'" : (j == 1 ? "D" : (j == 2 ? "C" : "D'"))));
            }

            for (int j = 0; j < 4; j++) {
                if (h8Checker(kMap, j))
                    simplifiedExpression.add((j == 0 ? "A'" : (j == 1 ? "B" : (j == 2 ? "A" : "B'"))));
            }
            //************

            // -------v4 h4 s4-------

            for (int i = 0; i < 4; i++) {//vertical
                for (int j = 0; j < 4; j++) {
                    if (!kMap[i][j].isFlag() && v4Checker(kMap, j))
                        simplifiedExpression.add((i == 0 ? "C'.D'" : (i == 1 ? "C'.D" : (i == 2 ? "C.D" : "C.D'"))));
                }
            }

            for (int i = 0; i < 4; i++) {//horizontal
                for (int j = 0; j < 4; j++) {
                    if (!kMap[i][j].isFlag() && h4Checker(kMap, i))
                        simplifiedExpression.add((i == 0 ? "A'.B'" : (i == 1 ? "A'.B" : (i == 2 ? "A.B" : "A.B'"))));
                }
            }

            for (int i = 0; i < 4; i++) {//square
                for (int j = 0; j < 4; j++) {
                    if ((!kMap[i][j].isFlag() || !kMap[i][add(j, 1, 4)].isFlag()
                            || !kMap[add(i, 1, 4)][j].isFlag() || !kMap[add(i, 1, 4)][add(j, 1, 4)].isFlag()) && s4Checker(kMap, i, j)) {
                        temp = "";
                        temp += (i == 0 ? "A'" : (i == 1 ? "B" : (i == 2 ? "A" : "B'")));
                        temp += "." + (j == 0 ? "C'" : (j == 1 ? "D" : (j == 2 ? "C" : "D'")));
                        simplifiedExpression.add(temp);
                    }
                }
            }
            //************

            // -------v2 h2-------
            for (int i = 0; i < 4; i++) { // vertical
                for (int j = 0; j < 4; j++) {
                    if ((!kMap[i][j].isFlag() || !kMap[add(i, 1, 4)][j].isFlag()) && v2Checker(kMap, i, j, size)) {//kendisi veya bir sağı true ise çalışacak.
                        temp = "";
                        temp += (i == 0 ? "A'" : (i == 1 ? "B" : (i == 2 ? "A" : "B'")));
                        temp += "." + (j == 0 ? "C'.D'" : (j == 1 ? "C'.D" : (j == 2 ? "C.D" : "C.D'")));
                        simplifiedExpression.add(temp);
                    }
                }
            }

            for (int i = 0; i < 4; i++) { // horizontal
                for (int j = 0; j < 4; j++) {
                    if ((!kMap[i][j].isFlag() || !kMap[i][add(j, 1, 4)].isFlag()) && h2Checker(kMap, i, j, size)) {
                        temp = "";
                        temp += (i == 0 ? "A'.B'" : (i == 1 ? "A'.B" : (i == 2 ? "A.B" : "A.B'")));
                        temp += "." + (j == 0 ? "C'" : (j == 1 ? "D" : (j == 2 ? "C" : "D'")));
                        simplifiedExpression.add(temp);
                    }
                }
            }
            //************

            // tekli kontrolü
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (kMap[i][j].equal("1") && !kMap[i][j].isFlag()) {
                        temp = "";
                        temp += (i == 0 ? "A'.B'" : (i == 1 ? "A'.B" : (i == 2 ? "A.B" : "A.B'")));
                        temp += "." + (j == 0 ? "C'.D'" : (j == 1 ? "C'.D" : (j == 2 ? "C.D" : "C.D'")));
                        simplifiedExpression.add(temp);
                    }
                }
            }

//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
        } else if (kMap.length == 4 && kMap[0].length == 2) {

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    if (kMap[i][j].equal("0"))
                        flag = false;
                }
            }
            if (flag)//8 tane bir varsa geriye null döner.
                return null;


            // -------v4 s4-------
            for (int j = 0; j < 2; j++) { // vertical
                if (v4Checker(kMap, j)) {
                    simplifiedExpression.add(j == 0 ? "C'" : "C");

                }
            }
            for (int i = 0; i < 4; i++) { //square
                for (int j = 0; j < 2; j++) {
                    if (!kMap[i][j].isFlag() && s4Checker(kMap, i, 0)) {
                        simplifiedExpression.add(i == 0 ? "A'" : (i == 1 ? "B" : (i == 2 ? "A" : "B'")));
                    }
                }
            }
            //************
            // -------v2 h2-------
            for (int i = 0; i < 4; i++) {//vertical
                for (int j = 0; j < 2; j++) {
                    if ((!kMap[i][j].isFlag() || !kMap[add(i, 1, 4)][j].isFlag()) && v2Checker(kMap, i, j, 4)) {
                        temp = "";
                        temp += (i == 0 ? "A'" : (i == 1 ? "B" : (i == 2 ? "A" : "B'")));
                        temp += ((j == 0) ? "C'" : "C");
                        simplifiedExpression.add(temp);
                    }
                }
            }

            for (int i = 0; i < 4; i++) {//horizontal
                for (int j = 0; j < 2; j++) {
                    if (!kMap[i][j].isFlag() && h2Checker(kMap, i, 0, 2))
                        simplifiedExpression.add((i == 0 ? "A'.B'" : (i == 1 ? "A'.B" : (i == 2 ? "A.B" : "A.B'"))));
                }

            }

            //************

            // tekli kontrolü
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    if (kMap[i][j].equal("1") && !kMap[i][j].isFlag()) {
                        temp = "";
                        temp += (i == 0 ? "A'.B'" : (i == 1 ? "A'.B" : (i == 2 ? "A.B" : "A.B'")));
                        temp += "." + (j == 0 ? "C'" : "C");
                        simplifiedExpression.add(temp);
                    }
                }
            }


        } else {
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (kMap[i][j].equal("1"))
                        flag = false;
                }
            }

            if (flag)//4 tane bir varsa geriye null döner.
                return null;

            for (int i = 0; i < 2; i++) {
                if (!kMap[0][i].isFlag() && v2Checker(kMap, 0, i, 2))
                    simplifiedExpression.add((i == 0 ? "B'" : "B"));

                if (!kMap[i][0].isFlag() && h2Checker(kMap, i, 0, 2))
                    simplifiedExpression.add((i == 0 ? "A'" : "A"));
            }

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (kMap[i][j].equal("1") && !kMap[i][j].isFlag()) {
                        simplifiedExpression.add((i == 0 ? (j == 0 ? "A'.B'" : "A'.B") : (j == 0 ? "A.B'" : "A.B")));
                    }

                }
            }
        }

        return simplifiedExpression;
    }

    private int add(int n1, int n2, int size) {
        return (n1 + n2 < 0 ? size : ((n1 + n2) >= size) ? (n1 + n2 - size) : (n1 + n2));
    }

    private boolean v8Checker(KMNode[][] kMap, int j) {
        for (int under = 0; under < 4; under++) {//bi altına bakma durumu
            for (int k = 0; k < 2; k++) {
                if (kMap[under][add(j, k, 4)].equal("0"))
                    return false;
            }
        }

        for (int under = 0; under < 4; under++) {//bi altına bakma durumu
            for (int k = 0; k < 2; k++) {
                kMap[under][add(j, k, 4)].setFlag(true);
            }
        }

        return true;
    }

    private boolean h8Checker(KMNode[][] kMap, int i) {
        for (int k = 0; k < 2; k++) {
            for (int side = 0; side < 4; side++) {
                if (kMap[add(k, i, 4)][side].equal("0"))
                    return false;
            }
        }

        for (int k = 0; k < 2; k++) {
            for (int side = 0; side < 4; side++) {
                kMap[add(k, i, 4)][side].setFlag(true);
            }
        }

        return true;
    }

    private boolean s4Checker(KMNode[][] kMap, int i, int j) {
        for (int k = 0; k < 2; k++) {
            for (int l = 0; l < 2; l++) {
                if (kMap[add(i, k, 4)][add(j, l, 4)].equal("0"))
                    return false;
            }
        }

        for (int k = 0; k < 2; k++) {
            for (int l = 0; l < 2; l++) {
                kMap[add(i, k, 4)][add(j, l, 4)].setFlag(true);
            }
        }

        return true;
    }

    private boolean h4Checker(KMNode[][] kMap, int i) {
        for (int k = 0; k < 4; k++) {
            if (kMap[i][k].equal("0"))
                return false;
        }

        for (int k = 0; k < 4; k++)
            kMap[i][k].setFlag(true);

        return true;
    }

    private boolean v4Checker(KMNode[][] kMap, int j) {
        for (int k = 0; k < 4; k++) {
            if (kMap[k][j].equal("0"))
                return false;
        }

        for (int k = 0; k < 4; k++)
            kMap[k][j].setFlag(true);

        return true;
    }

    private boolean v2Checker(KMNode[][] kMap, int i, int j, int size) {
        if (kMap[i][j].equal("0") || kMap[add(i, 1, size)][j].equal("0"))
            return false;

        kMap[i][j].setFlag(true);
        kMap[add(i, 1, size)][j].setFlag(true);

        return true;
    }

    private boolean h2Checker(KMNode[][] kMap, int i, int j, int size) {
        if (kMap[i][j].equal("0") || kMap[i][add(j, 1, size)].equal("0"))
            return false;

        kMap[i][j].setFlag(true);
        kMap[i][add(j, 1, size)].setFlag(true);

        return true;
    }


}
