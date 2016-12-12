package sample;

/**
 * Created by aaa on 12.12.2016.
 */
public class Converter {

    public String ttTObe(String[] tt) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String temp = "";
            int len = (int)(Math.log((double)tt.length) / Math.log(2));

            int rows = (int) Math.pow(2, len);

            for (int i = 0; i < rows; i++) {
                if (tt[i].equals("1")) {
                    for (int j = len - 1; j >= 0; j--) {
                        temp += ((i / (int) Math.pow(2, j)) % 2);
                    }
                    switch (len) {
                        case 2:
                            stringBuilder.append(temp.charAt(0) == '0' ? "A'." : "A.");
                            stringBuilder.append(temp.charAt(1) == '0' ? "B'+" : "B+");
                            break;
                        case 3:
                            stringBuilder.append(temp.charAt(0) == '0' ? "A'." : "A.");
                            stringBuilder.append(temp.charAt(1) == '0' ? "B'." : "B.");
                            stringBuilder.append(temp.charAt(2) == '0' ? "C'+" : "C+");
                            break;
                        case 4:
                            stringBuilder.append(temp.charAt(0) == '0' ? "A'." : "A.");
                            stringBuilder.append(temp.charAt(1) == '0' ? "B'." : "B.");
                            stringBuilder.append(temp.charAt(2) == '0' ? "C'." : "C.");
                            stringBuilder.append(temp.charAt(3) == '0' ? "D'+" : "D+");
                            break;
                        default:
                            return null;
                    }
                }
                temp = "";
            }
            return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error: Converter > ttTObe()");
            return null;
        }
    }


    public String[] beTOtt(String be){

        return null;
    }
}
