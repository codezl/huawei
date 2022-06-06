package com.codezl.huqwei03.test01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/05/27/17:53
 * @Description:
 */
public class SortRequestion {

    public static void  main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> ids = toIntList(in.nextLine());
        in.close();
        System.out.print(ids);
    }

    public static List<Integer> toIntList(String str) {
        return Arrays.stream(str.split(","))
                .map(Integer::parseInt).collect(Collectors.toList());
    }
}
