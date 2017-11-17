package com.nanyang.app.main.home.keno.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */

public class KenoDataBean {

    private List<PublicDataBean> PublicData;

    public List<PublicDataBean> getPublicData() {
        return PublicData;
    }

    public void setPublicData(List<PublicDataBean> PublicData) {
        this.PublicData = PublicData;
    }

    public static class PublicDataBean {
        /**
         * RefreshTime : 10
         * ServerTime : 2017-11-17|11:35:25
         * CompanyData : [{"company_id":1,"company_name":"中国","closing_date":"2017-08-10|11:35:20","odds_id":"","draw_value":"","weburl_value":"http://www.bjfcdt.gov.cn/happy8/v1/affiche.aspx","status_value":"D","MatchDate_value":"","draw2_value":"856627","MatchDate2_value":"17/11 11:35AM","bet_id":[{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""}],"result_id":[{"id":"856626","value":"1 3 10 20 30 31 32 34 38 48 49 50 51 55 57 60 63 68 70 72|B D E E W"},{"id":"856625","value":"2 8 11 18 19 21 28 30 31 33 36 39 40 41 44 51 54 57 72 79|S U T E A"},{"id":"856624","value":"2 6 10 13 14 21 23 35 40 41 43 45 47 59 63 66 69 73 74 75|B D O O W"},{"id":"856623","value":"1 4 16 20 22 25 28 31 33 38 39 40 44 48 51 54 56 67 68 74|S U E O A"},{"id":"856622","value":"3 5 14 17 21 23 26 32 37 42 45 50 56 61 70 71 75 76 77 80|B D O O F"},{"id":"856621","value":"1 6 14 15 18 19 21 22 27 28 35 42 60 66 68 72 74 76 79 80|B U E O W"},{"id":"856620","value":"1 4 5 6 13 16 23 35 44 45 47 53 56 60 62 63 65 67 69 72|S D O E W"},{"id":"856619","value":"11 13 17 18 21 25 27 31 37 38 45 46 54 65 69 70 73 74 79 80|B T O O F"},{"id":"856618","value":"3 5 9 14 19 25 26 30 31 34 35 42 46 49 51 52 55 58 59 65|S U O E A"},{"id":"856617","value":"3 4 10 11 14 15 16 17 22 23 28 37 46 48 49 52 53 62 65 72|S U E O G"},{"id":"856616","value":"5 6 8 9 10 13 14 26 38 44 48 50 52 53 60 61 65 67 77 79|S D E O W"},{"id":"856615","value":"4 13 17 19 20 24 28 30 35 38 39 40 41 53 55 56 57 59 73 80|S U O O W"},{"id":"856614","value":"7 9 14 20 24 28 35 40 44 55 60 61 63 67 68 70 71 75 76 79|B D T E E"},{"id":"856613","value":"5 6 7 14 20 22 23 25 31 34 35 40 41 45 55 59 68 75 76 79|S U O E A"},{"id":"856612","value":"2 3 15 16 20 22 27 29 36 38 39 45 46 56 62 69 74 76 77 78|B U E E W"},{"id":"856611","value":"4 11 13 15 17 31 34 37 42 44 45 56 58 62 65 68 73 74 75 80|B D T E F"},{"id":"856610","value":"3 5 9 17 19 21 22 23 33 34 39 50 51 52 60 63 69 72 75 76|S U O O W"},{"id":"856609","value":"7 15 16 18 19 20 25 27 30 31 33 36 37 40 45 51 55 56 63 66|S U O E G"},{"id":"856608","value":"6 11 13 19 20 21 26 29 32 41 47 48 49 54 58 65 66 69 76 78|B D T E W"},{"id":"856607","value":"3 8 18 29 36 38 41 44 48 49 55 59 61 66 71 73 75 76 79 80|B D O O E"},{"id":"856606","value":"7 8 10 11 12 17 19 21 25 27 34 36 42 44 48 52 57 59 73 79|S U O O G"},{"id":"856605","value":"7 8 11 17 21 24 29 30 34 37 40 47 51 52 59 62 64 68 75 80|B U T E W"},{"id":"856604","value":"2 5 19 21 22 29 30 38 41 43 45 49 50 58 59 63 66 69 73 74|B D O E F"},{"id":"856603","value":"15 16 23 24 31 37 39 41 42 49 54 57 58 60 63 66 69 73 74 77|B D O E E"},{"id":"856602","value":"3 5 9 11 14 18 21 25 32 34 37 41 44 52 61 63 66 67 74 77|S U O E A"},{"id":"856601","value":"1 5 6 7 9 17 20 21 23 30 31 35 41 46 48 55 57 68 71 78|S U O O G"},{"id":"856600","value":"1 3 4 9 10 15 30 33 40 43 48 49 54 58 59 62 72 74 75 80|B D E O W"},{"id":"856599","value":"2 4 5 8 12 19 21 23 28 33 35 38 41 48 51 54 59 63 66 80|S U T E G"},{"id":"856598","value":"1 5 6 9 13 15 17 24 29 31 32 36 39 40 42 53 54 61 74 75|S U O E G"},{"id":"856597","value":"2 7 11 15 18 22 36 37 40 45 48 49 55 57 65 67 69 72 76 77|B D O E F"},{"id":"856595","value":"1 4 9 10 12 17 18 23 28 29 35 36 43 44 50 53 56 62 67 79|S U T E G"},{"id":"856594","value":"3 4 5 10 15 32 34 38 39 43 50 54 55 57 58 62 67 69 70 76|B D E O W"},{"id":"856593","value":"3 4 7 9 10 11 18 22 26 30 32 37 39 40 43 46 51 54 56 77|S U E O G"},{"id":"856592","value":"1 19 20 22 27 29 35 46 50 52 55 57 58 64 66 67 70 73 74 77|B D T E E"},{"id":"856591","value":"12 13 23 26 31 37 38 44 47 48 49 53 57 61 62 63 65 68 72 80|B D O O E"}],"history_id_1":"BSBSBBSBSSSSBSBBSSBBSBBBSSBSSBSBSBB","history_id_2":"DUDUDUDTUUDUDUUDUUDDUUDDUUDUUDUDUDD","history_id_3":"ETOEOEOOOEEOTOETOOTOOTOOOOETOOTEETO","history_id_4":"EEOOOOEOEOOOEEEEOEEOOEEEEOOEEEEOOEO","history_id_5":"WAWAFWWFAGWWEAWFWGWEGWFEAGWGGFGWGEE"},{"company_id":"2","company_name":"加拿大 1","closing_date":"2017-11-17|11:35:30","odds_id":"14118011","draw_value":"2213239","weburl_value":"http://www.bclc.com/app/DidYouWin/WinningNumbers/Keno.asp","status_value":"D","MatchDate_value":"17/11 11:36AM","draw2_value":"2213238","MatchDate2_value":"17/11 11:32AM","bet_id":[{"id":"1","value":"1.95"},{"id":"2","value":"1.95"},{"id":"15","value":"104.00"},{"id":"7","value":"2.30"},{"id":"9","value":"2.30"},{"id":"8","value":"4.30"},{"id":"3","value":"2.30"},{"id":"4","value":"2.30"},{"id":"16","value":"4.30"},{"id":"5","value":"1.95"},{"id":"6","value":"1.95"},{"id":"10","value":"9.20"},{"id":"11","value":"4.60"},{"id":"12","value":"2.40"},{"id":"13","value":"4.60"},{"id":"14","value":"9.20"}],"result_id":[{"id":"2213238","value":"9 14 17 19 22 25 26 36 40 43 47 48 50 56 61 62 69 70 73 78|B D E O F"},{"id":"2213237","value":"6 9 21 30 33 34 35 37 46 50 53 55 60 61 62 64 68 72 73 77|B D T E E"},{"id":"2213236","value":"10 16 21 31 32 34 38 41 42 51 55 56 58 61 63 66 67 68 71 72|B D E O E"},{"id":"2213235","value":"2 3 5 6 11 14 15 19 20 21 45 50 52 55 58 61 74 75 76 79|S T O O A"},{"id":"2213234","value":"1 4 7 17 18 21 36 37 41 46 49 50 52 58 59 61 62 69 76 79|B D O O W"},{"id":"2213233","value":"1 3 4 6 17 19 22 32 37 38 45 60 62 65 67 70 73 74 76 77|B T T E W"},{"id":"2213232","value":"8 10 14 16 17 21 30 31 47 48 49 50 52 54 55 60 63 64 71 73|B D E O W"},{"id":"2213231","value":"3 10 14 20 22 29 40 44 52 56 58 65 66 70 71 72 73 75 77 80|B D E O E"},{"id":"2213230","value":"7 10 11 13 16 17 26 27 30 37 38 46 51 52 54 57 60 66 67 68|S U E O A"},{"id":"2213229","value":"2 4 16 17 18 19 24 25 27 31 36 39 49 57 62 63 64 71 75 78|S U O O W"},{"id":"2213228","value":"3 7 9 12 17 19 20 32 33 34 35 37 39 41 44 46 56 58 64 79|S U O O G"},{"id":"2213227","value":"7 8 14 15 19 21 32 42 45 52 56 59 65 68 69 73 74 78 79 80|B D T E E"},{"id":"2213226","value":"7 12 22 23 26 27 29 32 35 36 38 41 51 53 62 68 70 73 77 78|B U T E F"},{"id":"2213225","value":"6 11 12 17 19 23 26 28 32 42 43 50 53 58 59 61 66 67 71 76|B D T E W"},{"id":"2213224","value":"2 9 10 12 13 15 20 25 26 33 38 39 41 50 54 71 72 74 77 80|S U E O A"},{"id":"2213223","value":"1 3 9 10 15 21 24 26 27 35 42 46 54 55 58 62 66 67 72 80|S T E O W"},{"id":"2213222","value":"10 14 15 18 19 23 28 31 32 40 43 45 46 48 59 61 64 71 75 79|B T O O W"},{"id":"2213221","value":"8 12 15 31 40 42 47 50 51 55 56 59 64 66 67 72 77 78 79 80|B D E O E"},{"id":"2213220","value":"1 5 6 7 10 14 16 21 30 35 40 42 44 46 51 56 69 75 78 80|S U E E A"},{"id":"2213219","value":"1 5 7 13 16 19 21 27 28 29 32 39 50 56 69 71 72 74 77 78|S U O E W"},{"id":"2213218","value":"3 13 15 17 20 26 30 35 37 42 43 47 58 65 66 68 70 71 75 78|B D O O F"},{"id":"2213217","value":"4 5 7 8 14 16 30 32 34 36 40 47 50 57 60 64 65 66 69 72|S U E E W"},{"id":"2213216","value":"6 7 9 15 16 24 25 29 30 39 41 42 45 49 52 55 62 67 75 79|S T O O W"},{"id":"2213215","value":"9 13 15 18 22 26 27 29 30 36 40 42 49 61 63 66 68 71 77 78|B U T E W"},{"id":"2213214","value":"16 17 20 21 22 28 33 35 37 40 41 45 47 56 58 61 63 71 74 79|B T O E F"},{"id":"2213213","value":"2 6 9 11 12 15 17 20 22 26 36 38 47 57 62 65 67 72 75 76|S U E O A"},{"id":"2213212","value":"8 10 16 18 20 21 22 26 31 34 39 42 44 53 59 60 72 75 77 79|S U E E W"},{"id":"2213211","value":"2 7 8 9 13 14 21 30 32 34 40 42 47 49 55 58 60 63 65 74|S U E O A"},{"id":"2213210","value":"4 5 8 11 13 16 21 22 31 32 40 46 50 54 65 66 73 75 77 80|S U E O W"},{"id":"2213209","value":"3 4 7 11 19 20 26 27 34 35 40 42 44 45 47 50 53 58 59 65|S U O O G"},{"id":"2213208","value":"7 9 13 14 29 31 35 40 45 47 50 52 55 57 61 66 73 74 77 79|B D O E F"},{"id":"2213207","value":"4 8 11 12 15 16 31 32 34 35 41 47 52 54 60 66 71 74 75 79|B T E O W"},{"id":"2213206","value":"7 13 15 16 17 24 31 32 34 36 40 42 48 52 53 57 58 59 61 78|S U E O W"},{"id":"2213205","value":"1 2 3 6 9 24 34 40 42 44 48 55 56 61 62 65 71 75 76 80|B D E E W"},{"id":"2213204","value":"2 3 11 14 18 20 25 34 36 37 38 39 41 42 48 60 63 76 77 78|S U E E A"}],"history_id_1":"BBBSBBBBSSSBBBSSBBSSBSSBBSSSSSBBSBS","history_id_2":"DDDTDTDDUUUDUDUTTDUUDUTUTUUUUUDTUDU","history_id_3":"ETEOOTEEEOOTTTEEOEEOOEOTOEEEEOOEEEE","history_id_4":"OEOOOEOOOOOEEEOOOOEEOEOEEOEOOOEOOEE","history_id_5":"FEEAWWWEAWGEFWAWWEAWFWWWFAWAWGFWWWA"},{"company_id":3,"company_name":"加拿大 2","closing_date":"2017-08-10|11:35:20","odds_id":"","draw_value":"","weburl_value":"http://www.wclc.com/app/winning_numbers/keno.html","status_value":"D","MatchDate_value":"","draw2_value":"218882","MatchDate2_value":"17/11 11:35AM","bet_id":[{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""}],"result_id":[{"id":"218881","value":"4 6 11 24 28 32 35 38 39 42 43 47 54 60 64 70 73 76 78 80|B D E E F"},{"id":"218880","value":"8 17 20 21 24 30 37 38 40 41 47 49 52 60 62 64 65 68 69 76|B D E E F"},{"id":"218879","value":"1 6 23 25 30 45 48 49 50 53 54 56 60 61 64 71 73 74 78 79|B D T E E"},{"id":"218878","value":"5 12 13 16 18 22 28 35 38 39 48 54 60 61 62 65 67 70 76 78|B T E O F"},{"id":"218877","value":"3 4 7 8 13 17 18 30 31 42 48 53 54 55 56 59 71 72 74 76|S D E O W"},{"id":"218876","value":"2 5 15 16 18 19 21 27 31 35 44 47 54 55 57 63 67 68 75 80|S T O O W"},{"id":"218875","value":"1 2 3 5 10 11 19 21 22 25 29 36 41 42 51 53 61 64 71 75|S U O E G"},{"id":"218874","value":"1 4 5 17 18 27 29 43 49 51 54 55 61 65 66 69 75 77 78 79|B D O O F"},{"id":"218873","value":"1 2 4 7 9 13 18 20 23 24 27 31 35 44 55 58 60 67 72 74|S U T E G"},{"id":"218872","value":"2 4 5 12 15 19 27 28 33 39 40 43 50 51 54 63 64 68 77 79|S U O O W"},{"id":"218871","value":"5 9 12 20 24 25 26 30 36 42 44 45 46 51 57 62 63 72 73 80|B D E E W"},{"id":"218870","value":"5 6 17 20 23 31 33 37 38 42 43 47 48 52 56 58 65 67 70 79|B D O O W"},{"id":"218869","value":"1 2 9 17 27 34 37 39 41 54 56 57 63 64 67 69 73 75 77 78|B D O E E"},{"id":"218868","value":"4 6 11 14 22 23 24 25 33 34 44 46 48 51 53 56 63 71 72 78|S T E E W"},{"id":"218867","value":"1 5 6 7 24 25 28 31 40 43 46 55 58 61 64 66 73 75 77 80|B D O O F"},{"id":"218866","value":"3 12 15 18 19 24 30 34 36 40 43 44 45 48 50 60 66 71 74 80|B T E E W"},{"id":"218865","value":"2 10 11 13 16 34 35 37 39 40 41 44 53 54 64 66 70 72 74 75|B T E E W"},{"id":"218864","value":"4 10 12 19 20 24 27 28 30 32 34 37 38 45 47 53 62 65 70 78|S U E O A"},{"id":"218863","value":"4 6 7 9 10 19 21 24 25 31 33 34 35 43 44 47 62 65 66 74|S U O O G"},{"id":"218862","value":"5 6 7 15 16 18 19 20 22 28 29 32 38 39 41 51 56 58 68 74|S U E E G"},{"id":"218861","value":"2 6 7 14 15 16 18 20 29 34 35 41 47 50 51 53 54 62 74 78|S U E E A"},{"id":"218860","value":"2 4 16 21 23 27 28 31 33 34 41 44 45 48 50 51 68 77 78 79|S T T E W"},{"id":"218859","value":"8 15 16 22 30 34 38 42 43 53 56 60 63 65 66 67 73 75 76 78|B D E E E"},{"id":"218858","value":"5 7 9 17 18 29 32 33 36 39 42 48 51 55 57 60 68 71 76 80|B T O O W"},{"id":"218857","value":"1 4 6 9 12 13 16 19 22 23 25 36 38 47 48 53 54 59 73 78|S U T E G"},{"id":"218856","value":"1 9 11 12 13 17 18 20 27 41 45 47 48 57 62 65 72 75 76 77|S D O O W"},{"id":"218855","value":"8 11 23 24 26 31 32 36 44 47 48 53 57 58 62 74 75 76 78 79|B D E E E"},{"id":"218854","value":"1 4 7 18 20 25 26 31 35 36 37 43 44 57 59 62 65 74 76 80|S U T E W"},{"id":"218853","value":"1 4 7 15 19 26 31 36 43 45 48 49 55 58 60 62 66 69 75 79|B D O E W"},{"id":"218852","value":"5 6 7 14 15 18 22 24 26 27 30 36 42 51 52 58 61 63 64 68|S U E O G"},{"id":"218851","value":"8 11 13 15 18 23 25 26 27 31 39 41 48 49 54 61 63 67 72 80|S U O O W"},{"id":"218850","value":"1 2 9 12 16 20 29 32 35 36 46 52 55 57 61 66 68 72 73 74|B T E E W"},{"id":"218849","value":"2 9 12 13 14 16 19 25 29 30 36 44 46 50 54 73 75 77 78 79|S U E O W"},{"id":"218848","value":"5 10 13 20 21 29 33 34 41 43 44 47 48 59 61 64 66 70 78 79|B D O O F"},{"id":"218847","value":"1 2 11 12 14 23 27 29 31 38 39 40 44 49 62 63 64 70 75 80|S U T E W"}],"history_id_1":"BBBBSSSBSSBBBSBBBSSSSSBBSSBSBSSBSBS","history_id_2":"DDDTDTUDUUDDDTDTTUUUUTDTUDDUDUUTUDU","history_id_3":"EETEEOOOTOEOOEOEEEOEETEOTOETOEOEEOT","history_id_4":"EEEOOOEOEOEOEEOEEOOEEEEOEOEEEOOEOOE","history_id_5":"FFEFWWGFGWWWEWFWWAGGAWEWGWEWWGWWWFW"},{"company_id":4,"company_name":"斯洛伐克","closing_date":"2017-08-10|11:35:20","odds_id":"","draw_value":"","weburl_value":"http://eklubkeno.etip.sk/archive.aspx","status_value":"C","MatchDate_value":"","draw2_value":"","MatchDate2_value":"","bet_id":[{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""}],"result_id":[{"id":"2194554","value":"2 3 4 16 19 27 32 42 43 47 49 50 53 55 66 67 72 75 79 80|B D O O F"},{"id":"2194553","value":"4 14 18 20 21 28 30 31 38 39 49 50 51 58 60 63 65 66 72 75|B T E E W"},{"id":"2194552","value":"4 6 12 20 21 25 27 31 35 40 43 46 47 51 55 67 68 70 73 78|B T O O W"},{"id":"2194551","value":"7 8 9 11 12 16 17 18 20 29 35 40 45 51 52 55 65 68 73 76|S U O O A"},{"id":"2194550","value":"1 2 6 11 17 20 23 26 29 33 34 39 40 51 54 63 66 67 68 71|S U O O A"},{"id":"2194549","value":"1 5 8 11 13 15 16 24 27 28 47 48 49 53 56 60 71 75 78 80|S T O O W"},{"id":"2194548","value":"3 7 14 22 30 31 32 36 39 41 43 44 45 47 50 52 54 59 65 67|S D O O W"},{"id":"2194547","value":"2 3 7 12 14 15 21 27 28 32 34 39 43 48 50 55 58 60 69 79|S U T E A"},{"id":"2194546","value":"2 5 10 16 18 21 28 29 33 39 41 42 44 45 53 54 62 70 78 80|S T E E W"},{"id":"2194545","value":"3 5 6 10 11 12 16 20 22 26 27 35 48 49 51 52 63 64 66 68|S U E E G"},{"id":"2194544","value":"4 5 7 18 20 21 26 30 31 32 34 43 52 55 58 61 63 64 70 73|S U E O W"},{"id":"2194543","value":"2 5 7 10 13 20 32 35 37 42 44 49 52 57 64 65 69 72 76 78|B D E O W"},{"id":"2194542","value":"1 8 11 12 16 20 23 28 29 31 32 35 37 39 41 49 54 66 75 77|S U O E G"},{"id":"2194541","value":"9 10 19 20 21 23 28 29 30 35 40 46 48 61 63 65 66 67 75 79|B U O E W"},{"id":"2194540","value":"8 10 12 17 26 30 31 35 37 43 44 45 47 54 58 68 71 73 76 79|B D T E F"},{"id":"2194539","value":"1 3 8 10 13 16 17 23 25 27 33 45 46 50 52 53 59 66 77 78|S U O E A"},{"id":"2194538","value":"11 18 22 23 27 28 36 40 48 49 50 52 54 57 59 63 64 68 69 71|B D E O F"},{"id":"2194537","value":"4 6 16 17 19 26 27 31 36 43 45 47 55 59 60 69 70 71 72 77|B D O E W"},{"id":"2194536","value":"3 4 5 6 15 17 20 26 36 39 41 42 45 46 58 63 65 74 76 79|S T T E A"},{"id":"2194535","value":"4 5 7 14 16 17 19 21 25 31 35 36 44 45 47 65 69 72 75 78|S U O O A"},{"id":"2194534","value":"3 4 5 7 8 20 22 23 29 38 39 40 48 50 53 55 70 75 78 79|S U T E A"},{"id":"2194533","value":"2 8 13 16 17 19 22 30 34 39 43 44 54 66 67 68 69 70 71 80|B T E E W"},{"id":"2194532","value":"6 9 13 15 28 30 35 43 50 53 59 60 61 63 64 66 67 73 77 80|B D O E E"},{"id":"2194531","value":"1 5 10 15 17 21 29 31 32 35 40 41 43 46 48 62 65 66 72 73|S U O E A"},{"id":"2194530","value":"4 8 12 21 30 32 35 37 42 46 48 51 53 59 62 63 65 71 75 79|B D O O F"},{"id":"2194529","value":"5 12 14 16 17 23 24 25 26 34 36 39 42 44 46 47 63 66 68 75|S U E E A"},{"id":"2194528","value":"11 20 23 24 29 35 36 37 39 44 45 50 59 61 64 67 68 69 71 74|B D O E E"},{"id":"2194527","value":"6 9 12 14 15 16 17 27 30 33 45 57 65 67 70 72 73 75 76 78|B T O O F"},{"id":"2194526","value":"12 14 18 21 22 25 30 37 38 39 40 45 48 49 55 64 67 69 71 80|B U T E W"},{"id":"2194525","value":"6 7 10 18 24 31 41 42 48 50 51 54 61 63 65 67 68 72 74 79|B D E O E"},{"id":"2194524","value":"1 5 9 12 14 16 18 19 22 26 27 29 30 40 47 55 62 66 70 75|S U E O G"},{"id":"2194523","value":"3 6 16 23 25 29 30 34 41 48 53 54 55 57 58 63 66 69 77 78|B D O O F"},{"id":"2194522","value":"2 4 7 11 12 16 17 24 26 27 33 36 42 45 47 56 62 64 67 80|S U E E G"},{"id":"2194521","value":"6 7 8 10 19 24 26 31 32 33 35 36 45 48 56 58 65 67 71 74|S U E O A"},{"id":"2194520","value":"1 2 3 4 5 6 8 12 13 24 25 31 35 37 49 51 55 65 79 80|S U O O G"}],"history_id_1":"BBBSSSSSSSSBSBBSBBSSSBBSBSBBBBSBSSS","history_id_2":"DTTUUTDUTUUDUUDUDDTUUTDUDUDTUDUDUUU","history_id_3":"OEOOOOOTEEEEOOTOEOTOTEOOOEOOTEEOEEO","history_id_4":"OEOOOOOEEEOOEEEEOEEOEEEEOEEOEOOOEOO","history_id_5":"FWWAAWWAWGWWGWFAFWAAAWEAFAEFWEGFGAG"},{"company_id":5,"company_name":"澳洲","closing_date":"2017-08-10|11:35:20","odds_id":"","draw_value":"","weburl_value":"https://www.acttab.com.au/interbet/kenoreswin","status_value":"C","MatchDate_value":"","draw2_value":"","MatchDate2_value":"","bet_id":[{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""}],"result_id":[],"history_id_1":"","history_id_2":"","history_id_3":"","history_id_4":"","history_id_5":""}]
         */

        private String RefreshTime;
        private String ServerTime;
        private List<CompanyDataBean> CompanyData;

        public String getRefreshTime() {
            return RefreshTime;
        }

        public void setRefreshTime(String RefreshTime) {
            this.RefreshTime = RefreshTime;
        }

        public String getServerTime() {
            return ServerTime;
        }

        public void setServerTime(String ServerTime) {
            this.ServerTime = ServerTime;
        }

        public List<CompanyDataBean> getCompanyData() {
            return CompanyData;
        }

        public void setCompanyData(List<CompanyDataBean> CompanyData) {
            this.CompanyData = CompanyData;
        }

        public static class CompanyDataBean {
            /**
             * company_id : 1
             * company_name : 中国
             * closing_date : 2017-08-10|11:35:20
             * odds_id :
             * draw_value :
             * weburl_value : http://www.bjfcdt.gov.cn/happy8/v1/affiche.aspx
             * status_value : D
             * MatchDate_value :
             * draw2_value : 856627
             * MatchDate2_value : 17/11 11:35AM
             * bet_id : [{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""},{"id":"","value":""}]
             * result_id : [{"id":"856626","value":"1 3 10 20 30 31 32 34 38 48 49 50 51 55 57 60 63 68 70 72|B D E E W"},{"id":"856625","value":"2 8 11 18 19 21 28 30 31 33 36 39 40 41 44 51 54 57 72 79|S U T E A"},{"id":"856624","value":"2 6 10 13 14 21 23 35 40 41 43 45 47 59 63 66 69 73 74 75|B D O O W"},{"id":"856623","value":"1 4 16 20 22 25 28 31 33 38 39 40 44 48 51 54 56 67 68 74|S U E O A"},{"id":"856622","value":"3 5 14 17 21 23 26 32 37 42 45 50 56 61 70 71 75 76 77 80|B D O O F"},{"id":"856621","value":"1 6 14 15 18 19 21 22 27 28 35 42 60 66 68 72 74 76 79 80|B U E O W"},{"id":"856620","value":"1 4 5 6 13 16 23 35 44 45 47 53 56 60 62 63 65 67 69 72|S D O E W"},{"id":"856619","value":"11 13 17 18 21 25 27 31 37 38 45 46 54 65 69 70 73 74 79 80|B T O O F"},{"id":"856618","value":"3 5 9 14 19 25 26 30 31 34 35 42 46 49 51 52 55 58 59 65|S U O E A"},{"id":"856617","value":"3 4 10 11 14 15 16 17 22 23 28 37 46 48 49 52 53 62 65 72|S U E O G"},{"id":"856616","value":"5 6 8 9 10 13 14 26 38 44 48 50 52 53 60 61 65 67 77 79|S D E O W"},{"id":"856615","value":"4 13 17 19 20 24 28 30 35 38 39 40 41 53 55 56 57 59 73 80|S U O O W"},{"id":"856614","value":"7 9 14 20 24 28 35 40 44 55 60 61 63 67 68 70 71 75 76 79|B D T E E"},{"id":"856613","value":"5 6 7 14 20 22 23 25 31 34 35 40 41 45 55 59 68 75 76 79|S U O E A"},{"id":"856612","value":"2 3 15 16 20 22 27 29 36 38 39 45 46 56 62 69 74 76 77 78|B U E E W"},{"id":"856611","value":"4 11 13 15 17 31 34 37 42 44 45 56 58 62 65 68 73 74 75 80|B D T E F"},{"id":"856610","value":"3 5 9 17 19 21 22 23 33 34 39 50 51 52 60 63 69 72 75 76|S U O O W"},{"id":"856609","value":"7 15 16 18 19 20 25 27 30 31 33 36 37 40 45 51 55 56 63 66|S U O E G"},{"id":"856608","value":"6 11 13 19 20 21 26 29 32 41 47 48 49 54 58 65 66 69 76 78|B D T E W"},{"id":"856607","value":"3 8 18 29 36 38 41 44 48 49 55 59 61 66 71 73 75 76 79 80|B D O O E"},{"id":"856606","value":"7 8 10 11 12 17 19 21 25 27 34 36 42 44 48 52 57 59 73 79|S U O O G"},{"id":"856605","value":"7 8 11 17 21 24 29 30 34 37 40 47 51 52 59 62 64 68 75 80|B U T E W"},{"id":"856604","value":"2 5 19 21 22 29 30 38 41 43 45 49 50 58 59 63 66 69 73 74|B D O E F"},{"id":"856603","value":"15 16 23 24 31 37 39 41 42 49 54 57 58 60 63 66 69 73 74 77|B D O E E"},{"id":"856602","value":"3 5 9 11 14 18 21 25 32 34 37 41 44 52 61 63 66 67 74 77|S U O E A"},{"id":"856601","value":"1 5 6 7 9 17 20 21 23 30 31 35 41 46 48 55 57 68 71 78|S U O O G"},{"id":"856600","value":"1 3 4 9 10 15 30 33 40 43 48 49 54 58 59 62 72 74 75 80|B D E O W"},{"id":"856599","value":"2 4 5 8 12 19 21 23 28 33 35 38 41 48 51 54 59 63 66 80|S U T E G"},{"id":"856598","value":"1 5 6 9 13 15 17 24 29 31 32 36 39 40 42 53 54 61 74 75|S U O E G"},{"id":"856597","value":"2 7 11 15 18 22 36 37 40 45 48 49 55 57 65 67 69 72 76 77|B D O E F"},{"id":"856595","value":"1 4 9 10 12 17 18 23 28 29 35 36 43 44 50 53 56 62 67 79|S U T E G"},{"id":"856594","value":"3 4 5 10 15 32 34 38 39 43 50 54 55 57 58 62 67 69 70 76|B D E O W"},{"id":"856593","value":"3 4 7 9 10 11 18 22 26 30 32 37 39 40 43 46 51 54 56 77|S U E O G"},{"id":"856592","value":"1 19 20 22 27 29 35 46 50 52 55 57 58 64 66 67 70 73 74 77|B D T E E"},{"id":"856591","value":"12 13 23 26 31 37 38 44 47 48 49 53 57 61 62 63 65 68 72 80|B D O O E"}]
             * history_id_1 : BSBSBBSBSSSSBSBBSSBBSBBBSSBSSBSBSBB
             * history_id_2 : DUDUDUDTUUDUDUUDUUDDUUDDUUDUUDUDUDD
             * history_id_3 : ETOEOEOOOEEOTOETOOTOOTOOOOETOOTEETO
             * history_id_4 : EEOOOOEOEOOOEEEEOEEOOEEEEOOEEEEOOEO
             * history_id_5 : WAWAFWWFAGWWEAWFWGWEGWFEAGWGGFGWGEE
             */

            private int company_id;
            private String company_name;
            private String closing_date;
            private String odds_id;
            private String draw_value;
            private String weburl_value;
            private String status_value;
            private String MatchDate_value;
            private String draw2_value;
            private String MatchDate2_value;
            private String history_id_1;
            private String history_id_2;
            private String history_id_3;
            private String history_id_4;
            private String history_id_5;
            private List<BetIdBean> bet_id;
            private List<ResultIdBean> result_id;

            public int getCompany_id() {
                return company_id;
            }

            public void setCompany_id(int company_id) {
                this.company_id = company_id;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getClosing_date() {
                return closing_date;
            }

            public void setClosing_date(String closing_date) {
                this.closing_date = closing_date;
            }

            public String getOdds_id() {
                return odds_id;
            }

            public void setOdds_id(String odds_id) {
                this.odds_id = odds_id;
            }

            public String getDraw_value() {
                return draw_value;
            }

            public void setDraw_value(String draw_value) {
                this.draw_value = draw_value;
            }

            public String getWeburl_value() {
                return weburl_value;
            }

            public void setWeburl_value(String weburl_value) {
                this.weburl_value = weburl_value;
            }

            public String getStatus_value() {
                return status_value;
            }

            public void setStatus_value(String status_value) {
                this.status_value = status_value;
            }

            public String getMatchDate_value() {
                return MatchDate_value;
            }

            public void setMatchDate_value(String MatchDate_value) {
                this.MatchDate_value = MatchDate_value;
            }

            public String getDraw2_value() {
                return draw2_value;
            }

            public void setDraw2_value(String draw2_value) {
                this.draw2_value = draw2_value;
            }

            public String getMatchDate2_value() {
                return MatchDate2_value;
            }

            public void setMatchDate2_value(String MatchDate2_value) {
                this.MatchDate2_value = MatchDate2_value;
            }

            public String getHistory_id_1() {
                return history_id_1;
            }

            public void setHistory_id_1(String history_id_1) {
                this.history_id_1 = history_id_1;
            }

            public String getHistory_id_2() {
                return history_id_2;
            }

            public void setHistory_id_2(String history_id_2) {
                this.history_id_2 = history_id_2;
            }

            public String getHistory_id_3() {
                return history_id_3;
            }

            public void setHistory_id_3(String history_id_3) {
                this.history_id_3 = history_id_3;
            }

            public String getHistory_id_4() {
                return history_id_4;
            }

            public void setHistory_id_4(String history_id_4) {
                this.history_id_4 = history_id_4;
            }

            public String getHistory_id_5() {
                return history_id_5;
            }

            public void setHistory_id_5(String history_id_5) {
                this.history_id_5 = history_id_5;
            }

            public List<BetIdBean> getBet_id() {
                return bet_id;
            }

            public void setBet_id(List<BetIdBean> bet_id) {
                this.bet_id = bet_id;
            }

            public List<ResultIdBean> getResult_id() {
                return result_id;
            }

            public void setResult_id(List<ResultIdBean> result_id) {
                this.result_id = result_id;
            }

            public static class BetIdBean {
                /**
                 * id :
                 * value :
                 */

                private String id;
                private String value;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class ResultIdBean {
                /**
                 * id : 856626
                 * value : 1 3 10 20 30 31 32 34 38 48 49 50 51 55 57 60 63 68 70 72|B D E E W
                 */

                private String id;
                private String value;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
