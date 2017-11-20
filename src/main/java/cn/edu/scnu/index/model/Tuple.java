package cn.edu.scnu.index.model;

/**
 * @author CMJ  基本数据条目类
 */
public class Tuple implements Comparable<Tuple> {
    private NoTime nt;// 非时态元组
    private ValidTime vt;// 有效时间

    public Tuple() {
        // TODO Auto-generated constructor stub
    }
    // obj包含时态和非时态
    public Tuple(Object[] obj) {
        if (obj.length < 2) {
            return;
        }
        Object[] ntObj = new Object[obj.length - 2];
        Object[] vtObj = new Object[2];
        System.arraycopy(obj, 0, ntObj, 0, ntObj.length);
        System.arraycopy(obj, obj.length - 2, vtObj, 0, vtObj.length);
        nt = new NoTime(ntObj);
        vt = new ValidTime(vtObj[0] + "", vtObj[1] + "");
    }

    // vtObj时态和ntObj非时态分开
    public Tuple(Object[] ntObj, long[] vtObj) {
        if (ntObj.length < 0 || vtObj.length != 2) {
            return;
        }
        nt = new NoTime(ntObj);
        vt = new ValidTime(vtObj[0], vtObj[1]);
    }

    /**
     * 获取元组
     *
     * @param i
     * @return
     */
    public String get(int i) {
        if (i < 0 && i > nt.getObj().length + 1) {
            return null;
        } else if (i == nt.getObj().length + 1) {
            return vt.getEndString();
        } else if (i == nt.getObj().length) {
            return vt.getStartString();
        } else {
            return nt.getObj()[i] + "";
        }
    }

    public long getStart() {
        return vt.getStart();
    }

    public long getEnd() {
        return vt.getEnd();
    }

    public NoTime getNt() {
        return nt;
    }

    public void setNt(NoTime nt) {
        this.nt = nt;
    }

    public ValidTime getVt() {
        return vt;
    }

    public void setVt(ValidTime vt) {
        this.vt = vt;
    }

    @Override
    public String toString() {
        return nt.toString() + vt.toString();
    }

    @Override
    public int compareTo(Tuple o) {
        return this.vt.compareTo(o.vt);
    }
}