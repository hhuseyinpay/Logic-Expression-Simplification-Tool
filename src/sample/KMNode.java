package sample;

/**
 * Created by Hasan Hüseyin Pay on 20.12.2016.
 */
public class KMNode {

    private String key;
    private int vertical;
    private int horizontal;
    private int direction;
    private boolean flag;


    public KMNode(String key) {
        this.key = key;
        vertical = 0;
        horizontal = 0;

        direction = 0;
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

    public void setKey(String key) {
        this.key = key;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void reset() {
        vertical = 0;
        horizontal = 0;
        direction = 0;
    }

    public boolean equal(String s) {
        return key.equals(s);
    }
}
