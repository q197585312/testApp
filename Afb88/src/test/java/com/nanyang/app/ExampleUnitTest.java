package com.nanyang.app;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String id= "|1|50|452265||";
        String[] split = id.split("\\|");
        if (split.length > 3) {
            String a = split[2];;
        }
       /* String s = "伯恩利 (X)\\n @ 3.10\\n||r=1968463123|5|13";
        String[] split = s.split("\\|");
        System.out.println(split.length);
        if (split.length == 5) {
            System.out.println(split.length);
        }*/
    }


}