package com.codezl.huawei02.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/06/10/10:04
 * @Description:
 */
public class CountMinPay {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> list = Arrays.stream(in.nextLine().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int p = Integer.parseInt(in.nextLine());
        in.close();

        int max = -1;
        int sum = 0;
        for (int i = 0;i < list.size()-2;i++) {
            for (int j = i+1;j < list.size()-1;j++) {
                for (int k = j+1;k < list.size();k++) {
//                    if (i!=j&&j!=k) {
//
//                    }
                    sum = list.get(i) + list.get(j) + list.get(k);
                    if (sum < p&&max < sum) {
                        max = sum;
                        if (p-max==1) {
                            System.out.print("最先找到差1最优方案："+list.get(i) +","+ list.get(j) +","+ list.get(k)+"\n");
                             break;
                        }
                    }

//                    if (sum < p&&max <= sum) {
//                        max = sum;
//                        if (p-max==1) {
//                            System.out.print("所有差1方案："+list.get(i) +","+ list.get(j) +","+ list.get(k)+"\n");
//                            // break;
//                        }
//                    }
                }
            }
        }
        System.out.print("最大"+max);
    }
}
