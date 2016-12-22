package sample;

/**
 * Created by Hasan Hüseyin Pay on 20.12.2016.
 */
public class KMNode {

    private String key;
    private boolean flag;


    public KMNode(String key) {
        this.key = key;
        flag = false;
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public String getKey() {
        return key;
    }



    public boolean equal(String s) {
        return key.equals(s);
    }
}
