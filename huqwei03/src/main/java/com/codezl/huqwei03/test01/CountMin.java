package com.codezl.huqwei03.test01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/05/28/9:33
 * @Description:
 */
public class CountMin {
    /*
  1.输入字符串s输出s中包含所有整数的最小和，
  说明：1字符串s只包含a~z,A~Z,+,-，
  2.合法的整数包括正整数，一个或者多个0-9组成，如：0,2,3,002,102
  3.负整数，负号开头，数字部分由一个或者多个0-9组成，如-2,-012,-23,-00023
  输入描述：包含数字的字符串
  输出描述：所有整数的最小和
  示例：
    输入：
      bb1234aa
  　输出
      10
  　输入：
      bb12-34aa
  　输出：
      -31
  说明：1+2-(34)=-31q
   */
    private static Pattern pattern = Pattern.compile("(-?)\\d+");
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入字符与数字:");
        String s = in.nextLine();
        while (s.length()==0) {
            System.out.print("输入不能为空\n请输入字符与数字:");
            s = in.nextLine();
        }
        in.close();
        int count = 0;
        String[] split = s.split("-");
        Matcher matcher = pattern.matcher(s);
        int lastM = 0;
        int nowM = 0;
        while (matcher.find()) {
            nowM = Integer.parseInt(matcher.group(0));
            if (nowM>10) {
                String nowS = String.valueOf(nowM);
                for (int i=0;nowS.length()>i;i++) {}
            }
            lastM+=Integer.parseInt(matcher.group(0));
            System.out.print("match:"+matcher.group(0)+","+"\n"+lastM);
        }
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            boolean matches = s.matches("\\d");
            //System.out.print(c+","+"\n");
        }
        // 将所有非数字字符串用","替换，如果第一个为","则去除
        String s1 = s.replaceAll("\\D+", ",");
        if (s1.startsWith(",",0)) {
            s1 = s1.replaceFirst(",","");
        }
        //System.out.print(s1);
        myC(s);

        String substring = s.substring(1, 2);
        System.out.print("分割"+substring+"\n");


    }

    public static void myC(String s) {
        StringBuilder now = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        int count = 0;
        // 方法开始
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);

            // 1-1-01oi3
            boolean b = isDigit(c);
            if (b) {
                // boolean b1 = Integer.parseInt(Character.toString(c)) == 0;
                int ci = c - '0';
                boolean b1 = ci == 0;
                //System.out.print("转换数字"+ci);
                if (now.indexOf("-")==0) {
                    //if (!b1) {
                    //    now.append(ci);
                    //}
                    if (b1) {
                        if (now.length()==1){
                            continue;
                        }
                    }
                    // 如果这里是最后一次循环，会导致数字未存入list，故需最后加入
                    now.append(ci);
                }else {
                    if (b1) {
                        continue;
                    }
                    list.add(ci);
                    count+=ci;
                }
            }else {
                String nowStr = now.toString();
                if (nowStr.length()>1) {
                    list.add(Integer.valueOf(nowStr));
                    count+=Integer.parseInt(nowStr);
                    now = new StringBuilder();
                    continue;
                }
                // 需要放到最后，如果放到前面，之前存储的数组会被清空
                if ("-".equals(Character.toString(c))) {
                    // 避免两个负号
                    now = new StringBuilder("-");
                }else {
                    now = new StringBuilder();
                }
            }
        }
        // 因为负数的判断里没有结尾处理，故在此处理
        String nowStr = now.toString();
        if (nowStr.length()>1) {
            list.add(Integer.valueOf(nowStr));
            count+=Integer.parseInt(nowStr);
        }
        System.out.print("我的方法"+list+"总数"+count+"\n");
        demo(s);
    }

    public static void demo(String s) {
        int sum = 0;
        char[] chars = s.toCharArray();
        for (int i=0;i<chars.length;i++) {
            char c = chars[i];
            if (c == '-') {
                i++;
                int start = i;
                while (i<chars.length && Character.isDigit(chars[i])) {
                    i++;
                }
                String substring = s.substring(start, i);
                if (substring.length()>0) {
                    sum -=Integer.parseInt(substring);
                }
            }
            if (Character.isDigit(c)) {
                sum += Character.digit(c, 10);
            }
        }
        System.out.print("默认方法："+sum);
    }
}
