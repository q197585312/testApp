package com.nanyang.app;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.finalteam.toolsfinal.logger.Logger;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.

 /*       String s="<font color=red>M817</font>";
        String ss= Html.fromHtml(s).toString();
        String[] channels = ss.split("\n");
        channels.toString();
*/
String ul=" http://a8197c.a36588.com/_bet/JRecPanel.aspx?g=2&b=away_par&oId=12219288&odds=19";
       int i= ul.indexOf("&b=");
        String s=ul.substring(i+3);
        int last = s.indexOf("&");
        if(last>0)
            s=s.substring(0,last);
        s.toString();

    }
    @Test
    public void handleOddsUpdate() {
        String s = "CHG|Odds has changed to 1.69!|1.69|1";
        String substring = s.substring(s.indexOf("!|") + 2);
        String odds = substring.substring(0, substring.indexOf("|"));
        Logger.getDefaultLogger().d(odds);
    }
    /**
     * [
     [
     0,
     'a04b29a7ccffa83',
     'r',
     0,
     0,
     1,
     0,
     1,
     -1,
     'eng'
     ],
     [

     ],
     [

     ],
     [

     ],
     [

     ],
     [
     [
     12328178,
     [
     50,
     52
     ],
     [
     2.99,
     1.84
     ]
     ],
     [
     12327656,
     [
     29,
     30,
     31
     ],
     [
     9.7,
     8.5,
     -8.5
     ]
     ],
     [
     12328455,
     [
     29,
     30,
     31
     ],
     [
     14.7,
     5,
     -5
     ]
     ],
     [
     12327658,
     [
     29,
     30,
     31
     ],
     [
     34.4,
     1.3,
     -1.3
     ]
     ],
     [
     12328196,
     [
     29,
     30,
     31,
     60
     ],
     [
     11.3,
     7,
     -7,
     1
     ]
     ],
     [
     12328431,
     [
     29,
     30,
     31,
     59
     ],
     [
     17.2,
     4,
     -4,
     0
     ]
     ],
     [
     12327923,
     [
     60
     ],
     [
     0
     ]
     ],
     [
     12328437,
     [
     59,
     60
     ],
     [
     0,
     0
     ]
     ]
     ]
     ]*/
}
