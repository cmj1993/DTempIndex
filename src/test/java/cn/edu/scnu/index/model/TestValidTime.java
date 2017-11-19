package cn.edu.scnu.index.model;

import static cn.edu.scnu.index.model.ValidTime.intersect;
import static cn.edu.scnu.index.model.ValidTime.union;

public class TestValidTime {

    public static void main(String[] args) {
        System.out.println("-------------Test------------");
        ValidTime vt = new ValidTime(1234234, 1234234);
        System.out.println(vt.toString());
        System.out.println("-------------Test------------");
        ValidTime[] vtList = { new ValidTime(1, 3), new ValidTime(2, 4), new ValidTime(2, 5), new ValidTime(0, 3),
                new ValidTime(7, 8), new ValidTime(2, 6) };
        ValidTime R = intersect(vtList);
        if (R != null)
            System.out.println(R.getStart() + "," + R.getEnd());

        ValidTime[] Ru = union(vtList);
        System.out.println("-------------Test------------");
        for (int i = 0; i < Ru.length; i++) {
            System.out.println(Ru[i].getStart() + "," + Ru[i].getEnd());
        }
    }
}
