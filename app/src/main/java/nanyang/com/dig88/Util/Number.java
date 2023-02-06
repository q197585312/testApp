package nanyang.com.dig88.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Number {
    static List<String> listData1 = new ArrayList<>();
    static List<Integer> listData = new ArrayList<>();

    /*
     * 递归函数的输入函数有两个，第一个表示输入数组中第几个数字，第二个表示输入数组
     * 由于动态数组可以灵活添加元素，因此使用了动态数组来存储结果
     */
    public static ArrayList<Integer> foo(int i, int[] vec) {
        //递归结束的条件是读到第一个元素之后返回该元素
        if (i == 0) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(vec[0]);
            return list;
        } else {
            //得到上一步的结果
            ArrayList<Integer> nlist = foo(i - 1, vec);
            int n = nlist.size();
            int num = vec[i];
            //把输入数组中此处的元素添加进动态数组
            nlist.add(num);
            //动态数组中的每一个元素将输入数组中当前元素放在个位之后追加到动态数组
            for (int k = 0; k < n; k++) {
                nlist.add(nlist.get(k) * 10 + num);
            }
            return nlist;
        }

    }

    public static List<Integer> getAllCombine(int[] Str) {
        listData.clear();
        int l = Str.length;
        ArrayList<Integer> list = foo(l - 1, Str);
//        Collections.sort(list);
        for (int i : list) {
            allPermutation(i + "");
        }
//        Comparator<Integer> cm = new MyComparator();
//        Collections.sort(listData, cm);
        List<Integer> listData1 = new ArrayList<>();
        for (int i = 0; i < listData.size(); i++) {
            Integer integer = listData.get(i);
            if (!listData1.contains(integer)) {
                listData1.add(integer);
            }
        }
        return listData1;
    }

    public static List<String> Permutation(String str) {
        listData1.clear();
        char[] arrays = str.toCharArray();
        permutation(arrays, 0, arrays.length);
        return listData1;
    }

    // 方法参数 1数组  2起始位置  3数据长度
    private static void permutation(char[] arrays, int start, int length) {

        //数据长度为1时，数组输出
        if (length == 1) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < arrays.length; i++) {
                stringBuffer.append(arrays[i]);
            }
            String result = stringBuffer.toString();
            if (!listData1.contains(result)) {
                listData1.add(result);
            }
        } else {
            for (int i = start; i < start + length; i++) {
                swap1(arrays, start, i);
                permutation(arrays, start + 1, length - 1);
                swap1(arrays, start, i);//切记一定要换回，否则影响下次的循环交换。
            }
        }
    }

    private static void swap1(char[] arrays, int start, int i) {
        // TODO Auto-generated method stub
        char temp = arrays[start];
        arrays[start] = arrays[i];
        arrays[i] = temp;
    }

    public static void main(String[] args) {
        Permutation("0009");
//        int[] vec = {1, 2, 3, 4};
//        int l = vec.length;
//        ArrayList<Integer> list = foo(l - 1, vec);
//        Collections.sort(list);
//        for (int i : list) {
//            allPermutation(i + "");
//        }
//        Comparator<Integer> cm = new MyComparator();
//        Collections.sort(listData, cm);
//        for (int i = 0; i < listData.size(); i++) {
//            System.out.println(listData.get(i));
//        }
    }

    public static void allPermutation(String str) {
        if (str == null || str.length() == 0)
            return;
        //保存所有的全排列
        LinkedList<String> listStr = new LinkedList<String>();
        allPermutation(str.toCharArray(), listStr, 0);
        for (int i = 0; i < listStr.size(); i++) {
            String s = listStr.get(i);
            if (!listData.contains(s) && s.length() > 1) {
                listData.add(Integer.valueOf(s));
            }
        }
    }


    private static void allPermutation(char[] c, LinkedList<String> listStr, int start) {


        if (start == c.length - 1)
            listStr.add(String.valueOf(c));
        else {
            for (int i = start; i <= c.length - 1; i++) {
                swap(c, i, start);//相当于: 固定第 i 个字符
                allPermutation(c, listStr, start + 1);//求出这种情形下的所有排列
                swap(c, start, i);//复位
            }
        }
    }

    private static void swap(char[] c, int i, int j) {
        char tmp;
        tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }

    private static void print(LinkedList<String> listStr) {
        Collections.sort(listStr);//使字符串按照'字典顺序'输出
        for (String str : listStr) {
            System.out.println(str);
        }
    }

    public static class MyComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            if (i1 > i2) return 1;
            else if (i1 == i2) return 0;
            else return -1;
        }
    }
}