package com.nanyang.app;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.nanyang.app.main.home.sport.additional.VsActivity;
import com.nanyang.app.main.home.sport.model.VsCellBean;
import com.nanyang.app.main.home.sport.model.VsTableRowBean;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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


    @Test
    public void testJsonMap()  {
        String string="{\n" +
                "    \"ModuleTitle\": \"日本职业联赛杯\",\n" +
                "    \"Home\": \"磐田山叶\",\n" +
                "    \"Away\": \"FC 东京\",\n" +
                "    \"Date\": \"06:00PM\",\n" +
                "    \"FT_1x2_N\": {\n" +
                "        \"\": \"\"\n" +
                "    },\n" +
                "    \"FT_DC\": {\n" +
                "        \"12\": \"0.00\",\n" +
                "        \"oid\": 12566436,\n" +
                "        \"1x\": \"0.00\",\n" +
                "        \"x2\": \"0.00\"\n" +
                "    },\n" +
                "    \"FT_OE\": {\n" +
                "        \"oid\": 12566436,\n" +
                "        \"ODD\": \"0.56\",\n" +
                "        \"EVEN\": \"1.51\"\n" +
                "    },\n" +
                "    \"FH_1x2_N\": {\n" +
                "        \"\": \"\"\n" +
                "    },\n" +
                "    \"FH_DC_N\": {\n" +
                "        \"\": \"\"\n" +
                "    },\n" +
                "    \"FH_OE_N\": {\n" +
                "        \"\": \"\"\n" +
                "    },\n" +
                "    \"FT_CS\": {\n" +
                "        \"oid\": 12566436,\n" +
                "        \"3:0\": \"2.05\",\n" +
                "        \"3:1\": \"4.1\",\n" +
                "        \"3:2\": \"26.6\",\n" +
                "        \"3:3\": \"87\",\n" +
                "        \"3:4\": \"115\",\n" +
                "        \"4:0\": \"3.65\",\n" +
                "        \"4:1\": \"11.8\",\n" +
                "        \"4:2\": \"49\",\n" +
                "        \"4:3\": \"104\",\n" +
                "        \"4:4\": \"117\",\n" +
                "        \"5:0\": \"17\",\n" +
                "        \"5:1\": \"41\",\n" +
                "        \"5:2\": \"88\",\n" +
                "        \"5:3\": \"114\",\n" +
                "        \"5:4\": \"0\",\n" +
                "        \"6:0\": \"65\",\n" +
                "        \"6:1\": \"96\",\n" +
                "        \"6:2\": \"113\",\n" +
                "        \"6:3\": \"0\",\n" +
                "        \"6:4\": \"0\",\n" +
                "        \"7:0\": \"109\",\n" +
                "        \"7:1\": \"116\",\n" +
                "        \"7:2\": \"0\",\n" +
                "        \"7:3\": \"0\",\n" +
                "        \"7:4\": \"0\",\n" +
                "        \"AOS\": \"117\"\n" +
                "    },\n" +
                "    \"FH_CS_N\": {\n" +
                "        \"\": \"\"\n" +
                "    },\n" +
                "    \"FGLG\": {\n" +
                "        \"oid\": 12566436,\n" +
                "        \"HF\": \"0\",\n" +
                "        \"HL\": \"0\",\n" +
                "        \"AF\": \"0\",\n" +
                "        \"AL\": \"0\",\n" +
                "        \"NO GOAL\": \"0\"\n" +
                "    },\n" +
                "    \"TG_N\": {\n" +
                "        \"\": \"\"\n" +
                "    },\n" +
                "    \"HOMETEAMTG\": {\n" +
                "        \"oid\": 12566438,\n" +
                "        \"oid_FH\": 0,\n" +
                "        \"FT_OU\": \"3.5\",\n" +
                "        \"FT_O\": \"1.66\",\n" +
                "        \"FT_U\": \"0.44\",\n" +
                "        \"FH_OU\": \"0\",\n" +
                "        \"FH_O\": \"100.00\",\n" +
                "        \"FH_U\": \"0.02\"\n" +
                "    },\n" +
                "    \"AWAYTEAMTG\": {\n" +
                "        \"oid\": 12566443,\n" +
                "        \"oid_FH\": 0,\n" +
                "        \"FT_OU\": \"0.5\",\n" +
                "        \"FT_O\": \"2.22\",\n" +
                "        \"FT_U\": \"0.29\",\n" +
                "        \"FH_OU\": \"0\",\n" +
                "        \"FH_O\": \"100.00\",\n" +
                "        \"FH_U\": \"0.02\"\n" +
                "    },\n" +
                "    \"FT15MINSHANDICAP_OVER_UNDER_0_N\": {\n" +
                "        \"\": \"\"\n" +
                "    },\n" +
                "    \"FT15MINSHANDICAP_OVER_UNDER_15_N\": {\n" +
                "        \"\": \"\"\n" +
                "    },\n" +
                "    \"FT15MINSHANDICAP_OVER_UNDER_30_N\": {\n" +
                "        \"\": \"\"\n" +
                "    },\n" +
                "    \"FT15MINSHANDICAP_OVER_UNDER_45_N\": {\n" +
                "        \"\": \"\"\n" +
                "    },\n" +
                "    \"FT15MINSHANDICAP_OVER_UNDER_60_N\": {\n" +
                "        \"\": \"\"\n" +
                "    }\n" +
                "}";


        testMap(string);
    }


    public void testMap(String str) {
        List<VsTableRowBean> rows = new ArrayList<VsTableRowBean>();
        Gson gson = new Gson();
        getRows(str, rows, gson);
        Log.d("Row",rows.toString());
    }

    private void getRows(String str, List<VsTableRowBean> rows, Gson gson) {
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(str, map.getClass());
        LinkedTreeMap<String, Object> dataMap = (LinkedTreeMap) map.get("FT_CS");
        parseRows(rows, dataMap);
    }

    private void parseRows(List<VsTableRowBean> rows, LinkedTreeMap<String, Object> dataMap) {
        Double sid = (Double) dataMap.get("oid");
        int oid=sid.intValue();
        Iterator<Map.Entry<String, Object>> iterator = dataMap.entrySet().iterator();
        int i = 0;
        Integer first = null;
        List<VsActivity.Csr> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            String score = next.getKey();
            String odds = next.getValue().toString();
            if (score.equals("oid") || odds == null)
                continue;
            String[] split = score.split(":");
            if (split.length < 2)
                continue;
            if (first == null) {
                first = Integer.valueOf(split[0]);
            }
            int pre = 0;
            if (first != null) {
                pre = Integer.valueOf(split[0]) - first;
            }
            String oddsType = "";
            if (pre > 0) {
                oddsType = pre + "" + split[1];
            } else {
                oddsType = split[1];
            }
            VsActivity.Csr csr = new VsActivity.Csr(false, score, odds, oddsType);
            list.add(csr);

        }

        int yu = list.size() % 3;
        int ce = list.size() / 3;
        rows.add(new VsTableRowBean("csr", Arrays.asList(
                new VsCellBean(list.get(0).getScore(), list.get(0).getOdds(), list.get(0).getType(), oid),
                new VsCellBean(list.get(1).getScore(), list.get(1).getOdds(), list.get(1).getType(), oid),
                new VsCellBean(list.get(2).getScore(), list.get(2).getOdds(), list.get(2).getType(), oid)), true, false, "", "", "", ""));
        if (yu == 0) {
            if (ce - 1 > 1)
                for (int c = 1; c < ce - 1; c++) {
                    rows.add(new VsTableRowBean("csr", Arrays.asList(
                            new VsCellBean(list.get(c * 3).getScore(), list.get(c * 3).getOdds(), list.get(c * 3).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 1).getScore(), list.get(c * 3 + 1).getOdds(), list.get(c * 3 + 1).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 2).getScore(), list.get(c * 3 + 2).getOdds(), list.get(c * 3 + 2).getType(), oid))));
                }
            rows.add(new VsTableRowBean("csr", Arrays.asList(
                    new VsCellBean(list.get(list.size() - 3).getScore(), list.get(list.size() - 3).getOdds(), list.get(list.size() - 3).getType(), oid),
                    new VsCellBean(list.get(list.size() - 2).getScore(), list.get(list.size() - 2).getOdds(), list.get(list.size() - 2).getType(), oid),
                    new VsCellBean(list.get(list.size() - 1).getScore(), list.get(list.size() - 1).getOdds(), list.get(list.size() - 1).getType(), oid)), true));
        } else if (yu == 1) {
            if (ce > 1)
                for (int c = 1; c < ce; c++) {
                    rows.add(new VsTableRowBean("csr", Arrays.asList(
                            new VsCellBean(list.get(c * 3).getScore(), list.get(c * 3).getOdds(), list.get(c * 3).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 1).getScore(), list.get(c * 3 + 1).getOdds(), list.get(c * 3 + 1).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 2).getScore(), list.get(c * 3 + 2).getOdds(), list.get(c * 3 + 2).getType(), oid))));
                }
            rows.add(new VsTableRowBean("csr", Arrays.asList(
                    new VsCellBean(list.get(list.size() - 1).getScore(), list.get(list.size() - 1).getOdds(), list.get(list.size() - 1).getType(), oid),
                    new VsCellBean("", "", "", oid),
                    new VsCellBean("", "", "", oid)), true));
        } else if (yu == 2) {
            if (ce > 1)
                for (int c = 1; c < ce; c++) {
                    rows.add(new VsTableRowBean("csr", Arrays.asList(
                            new VsCellBean(list.get(c * 3).getScore(), list.get(c * 3).getOdds(), list.get(c * 3).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 1).getScore(), list.get(c * 3 + 1).getOdds(), list.get(c * 3 + 1).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 2).getScore(), list.get(c * 3 + 2).getOdds(), list.get(c * 3 + 2).getType(), oid))));
                }
            rows.add(new VsTableRowBean("csr", Arrays.asList(
                    new VsCellBean(list.get(list.size() - 2).getScore(), list.get(list.size() - 2).getOdds(), list.get(list.size() - 2).getType(), oid),
                    new VsCellBean(list.get(list.size() - 1).getScore(), list.get(list.size() - 1).getOdds(), list.get(list.size() - 1).getType(), oid),
                    new VsCellBean("", "", "", oid)), true));
        }
    }
}
