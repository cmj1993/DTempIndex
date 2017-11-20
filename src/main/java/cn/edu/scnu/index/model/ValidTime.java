package cn.edu.scnu.index.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @author CMJ 有效时间，时态信息类
 */
public class ValidTime implements Comparable<ValidTime> {

    private long start;// 开始时间
    private long end;// 结束时间

    public ValidTime() {
    }

    public ValidTime(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public ValidTime(String start, String end) {
        this.start = parse(start);
        this.end = parse(end);
    }

    /**
     * 字符串时间解析成long型
     *
     * @param strTime
     * @return
     */
    public static long parse(String strTime) {
        Long longTime = null;
        try {
            longTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strTime).getTime();
        } catch (ParseException e1) {
            try {
                longTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(strTime).getTime();
            } catch (ParseException e2) {
                try {
                    longTime = new SimpleDateFormat("yyyy-MM-dd").parse(strTime).getTime();
                } catch (ParseException e3) {
                    e1.printStackTrace();
                    e2.printStackTrace();
                    e3.printStackTrace();
                }
            }
        }
        return longTime;
    }

    public String getStartString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(this.getStart()));
    }

    public void setStartString(String left) {
        this.start = parse(left);
    }

    public String getEndString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(this.getEnd()));
    }

    public void setEndString(String right) {
        this.end = parse(right);
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }


    /**
     * //返回-1，this在前或包含o；返回0，相等区间；返回1，this在后或o包含this
     */
    @Override
    public int compareTo(ValidTime o) {
        if (this.start < o.start) {
            return -1;
        } else if (this.start > o.start) {
            return 1;
        } else {
            if (this.end == o.end)
                return 0;
            else if (this.end > o.end)
                return -1;
            else
                return 1;
        }
    }

    /**
     * 返回true，this包含point；返回false，this不包含point；
     *
     * @param point
     * @return
     */
    public boolean isContain(long point) {
        if (this.start - point <= 0 && (this.end - point) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 逗号间隔开
     */
    @Override
    public String toString() {
        return getStartString() + "," + getEndString();
    }

    // 交集

    /**
     * 求时间区间数组的交集区间
     * @param o
     * @return
     */
    public static ValidTime intersect(ValidTime[] o) {
        Arrays.sort(o);// 按开始时间由小到大排
        ValidTime R = o[0];
        for (int i = 1; i < o.length; i++) {
            if (R.end < o[i].start)
                R = null;
            else {
                R.start = R.start > o[i].start ? R.start : o[i].start;
                R.end = R.end < o[i].end ? R.end : o[i].end;
            }
        }
        return R;
    }

    // 并集
    // 当Rie<R(i+1)s时，将Ri放入已排数组Q中，i++转Step3。
    // 否则，令R(i+1)s=Ris，i++转Step3。直到i=R.size()，转Step4。
    public static ValidTime[] union(ValidTime[] o) {
        Arrays.sort(o);// 按开始时间由小到大排
        List<ValidTime> R = new ArrayList<ValidTime>();
        ValidTime T = o[0];
        for (int i = 1; i < o.length; i++) {
            if (T.end < o[i].start) {
                R.add(T);
                T = o[i];
            } else {
                T.start = T.start < o[i].start ? T.start : o[i].start;
                T.end = T.end > o[i].end ? T.end : o[i].end;
            }
        }
        R.add(T);
        return (ValidTime[]) R.toArray(new ValidTime[R.size()]);
    }


}