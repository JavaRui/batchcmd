package com.crwu.tool.batchcmd;

import com.yt.tool.swt.util.YtColorUtil;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/25 10:43
 */
public class Util {

    public static boolean isIp(String ss){
        String regex = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";
        boolean matches = ss.matches(regex);
        return matches;
    }

    public static void addRange(StyledText contentText , String content){


        //上色
        StyleRange range = new StyleRange();

        int start = contentText.getText().indexOf(content);

        range.start = start;
        range.length = content.length();
        range.background = YtColorUtil.buleColor;
        contentText.setStyleRange(range);
    }

    public static void addRangeAllAppear(StyledText contentText , String content){
        List<Integer> list = appearNumber(contentText.getText(), content);
        list.forEach(index->{
            //上色
            StyleRange range = new StyleRange();

            int start = index;

            range.start = start;
            range.length = content.length();
            range.background = YtColorUtil.buleColor;
            contentText.setStyleRange(range);
        });
    }


    /**
     * 获取指定字符bai串出现的du次数
     *
     * @param srcText 源字符串
     * @param findText 要查找的字符串
     * @return
     */
    public static List<Integer> appearNumber(String srcText, String findText) {
        List<Integer> indexList = new ArrayList<>();
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        String substr = srcText;

//        System.out.println(srcText+"   split   "+findText);

        while (m.find()) {
            int i = substr.indexOf(findText);
            substr = substr.substring(i+findText.length(), substr.length());
//            System.out.println(substr);

            int i1 = srcText.indexOf(substr);
//            System.out.println(count + "   index:   "+(i1-findText.length()));
            indexList.add((i1-findText.length()));
            count++;
        }
        return indexList;
    }

    public static void addSelectAllListener(StyledText text){
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyReleased(KeyEvent arg0) {
                boolean ctrl = false;
                boolean shift = false;
                if ((arg0.stateMask & 262144) != 0  || (arg0.stateMask & 4194304) != 0) {
                    ctrl = true;
                }

                if ((arg0.stateMask & 131072) != 0) {
                    shift = true;
                }

                if (ctrl && arg0.keyCode == 97 ) {
                    text.selectAll();
                }

            }
            @Override
            public void keyPressed(KeyEvent arg0) {
            }
        };
        text.addKeyListener(keyListener);
    }

    public static void main(String[] args) {
        String s = "234121542155";
        List indexList = appearNumber(s,"21");
//        List<String> list = Arrays.asList(split);
        System.out.println(indexList);
    }


}
