package com.codezl.huawei02.domain.bo;

import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/07/05/14:53
 * @Description: 内部接口只在内部类中实现
 */
@Data
public class InnerInterface {

    private final static ConcurrentMap<String,Addtion> ADD_MAP = new ConcurrentHashMap<>();
    private final static ConcurrentMap<String,Multi> MUL_MAP = new ConcurrentHashMap<>();

    public interface Addtion {
        int add(int a, int b);
    }

    public interface Multi {
        float mul(float a, float b);
    }

    static {
        ADD_MAP.put("ADD", (Addtion) Integer::sum);
        MUL_MAP.put("MUL", (Multi) (a, b) -> a*b);
    }

    public static ConcurrentMap<String,Addtion> getAdd() {
        return ADD_MAP;
    }

    public static ConcurrentMap<String,Multi> getMul() {
        return MUL_MAP;
    }

}
