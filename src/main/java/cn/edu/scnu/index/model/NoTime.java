package cn.edu.scnu.index.model;

/**
 * @author CMJ 非时态信息元组
 */
public class NoTime implements Comparable<NoTime> {
    private Object[] obj;

    public NoTime(Object[] obj) {
        this.setObj(obj);
    }

    @Override
    public boolean equals(Object obj) {
        return this.compareTo((NoTime) obj) == 0 ? true : false;
    }

    @Override
    public int hashCode() {
        String s = "";
        for (int i = 0; i < obj.length; i++) {
            s += obj[i] + "";
        }
        char[] chars = s.toCharArray();
        int n = 0;
        for (int i = 0; i < chars.length; i++) {
            n += chars[i];
        }
        return n;
    }

    /**
     * 返回0，字符相同；
     */
    @Override
    public int compareTo(NoTime noTime) {
        if (this.obj.length != noTime.obj.length) {
            return -1;
        } else {
            for (int i = 0; i < obj.length; i++)
                if (("" + this.obj[i]).compareTo("" + noTime.obj[i]) != 0) return -1;
        }
        return 0;
    }

    public Object[] getObj() {
        return obj;
    }

    public void setObj(Object[] obj) {
        this.obj = obj;
    }

    /**
     * 逗号间隔开
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < obj.length; i++) {
            str += obj[i] + ",";
        }
        return str;
    }
}
