package com.nanyang.app.main.BetCenter;

import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import com.unkonw.testapp.libs.utils.LogUtil;

import org.xml.sax.XMLReader;

import java.lang.reflect.Field;
import java.util.HashMap;

public class HtmlTagHandler implements Html.TagHandler {
    // 自定义标签名称
    private String tagName;

    // 标签开始索引
    private int startIndex = 0;
    // 标签结束索引
    private int endIndex = 0;
    // 存放标签所有属性键值对
    final HashMap<String, String> attributes = new HashMap<>();

    public HtmlTagHandler(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        // 判断是否是当前需要的tag
        if (tag.equalsIgnoreCase(tagName)) {
            // 解析所有属性值
            parseAttributes(xmlReader);

            if (opening) {
                startHandleTag(tag, output, xmlReader);
            } else {
                endEndHandleTag(tag, output, xmlReader);
            }
        }
    }

    public void startHandleTag(String tag, Editable output, XMLReader xmlReader) {
        startIndex = output.length();
    }

    public void endEndHandleTag(String tag, Editable output, XMLReader xmlReader) {
        endIndex = output.length();

        // 获取对应的属性值
        String color = attributes.get("color");
        String size = attributes.get("size");
        size = size.split("px")[0];

        // 设置颜色
        if (!TextUtils.isEmpty(color)) {
            output.setSpan(new ForegroundColorSpan(Color.parseColor(color)), startIndex, endIndex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        // 设置字体大小
        if (!TextUtils.isEmpty(size)) {
            output.setSpan(new AbsoluteSizeSpan(Integer.parseInt(size)), startIndex, endIndex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * 解析所有属性值
     *
     * @param xmlReader
     */
    private void parseAttributes(final XMLReader xmlReader) {
        try {
            Field elementField = xmlReader.getClass().getDeclaredField("theNewElement");
            elementField.setAccessible(true);
            Object element = elementField.get(xmlReader);
            Field attsField = element.getClass().getDeclaredField("theAtts");
            attsField.setAccessible(true);
            Object atts = attsField.get(element);
            Field dataField = atts.getClass().getDeclaredField("data");
            dataField.setAccessible(true);
            String[] data = (String[]) dataField.get(atts);
            Field lengthField = atts.getClass().getDeclaredField("length");
            lengthField.setAccessible(true);
            int len = (Integer) lengthField.get(atts);

            for (int i = 0; i < len; i++) {
                attributes.put(data[i * 5 + 1], data[i * 5 + 4]);
            }
        } catch (Exception e) {

        }
    }
/*<SPAN class='gb'>Bet on: </SPAN><SPAN class='Normal2'>Player:<B>4.0</B></SPAN>
<BR><SPAN class='gb'>Result: </SPAN><SPAN class='Normal2'>Player Win,B Pair@</SPAN>*/
    public static String spanFont(String span) {
        String font = span.replaceAll("(?i)span", "font")
                .replaceAll("(?i)<font>", "</font>")
                .replaceAll("<BR>", "<br>")
                .replaceAll("(?i)class", "color")
                .replaceAll("(?i)style='color:", "color='")

                .replaceAll("(?i)color='red'", "color='#ff0000'")
                .replaceAll("(?i)color='blue'", "color='#0000ff'")
                .replaceAll("(?i)color='black'", "color='#000000'")
                .replaceAll("(?i)color='Error'", "color='#ff0000'")
                .replaceAll("(?i)color=\"orange\"", "color='#FBBC05'")

                .replaceAll("(?i)color='gb'", "color='#0000ff'")

                .replaceAll("(?i)Normal2", "#000000")

                .replaceAll("(?i)MM_red", "#ff0000")
                .replaceAll("(?i)Negative", "#ff0000")
                .replaceAll("(?i)MM_blue", "#0000ff")

                .replaceAll("(?i)gbGive", "#ee2c2c")
                .replaceAll("(?i)Give", "#ee2c2c")
                .replaceAll("(?i)gbTake2", "'#0000ff'")
                .replaceAll("(?i)MM_black", "#000000")
                ;

        LogUtil.d("font", "font:" + font + ",span:" + span);
        return font;
    }
    public static Spanned spanFontHtml(String span) {
        return Html.fromHtml(spanFont(span));

    }



}