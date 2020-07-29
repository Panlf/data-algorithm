package com.plf.recursion;

/**
 * 传入T,R
 * @param <T>
 * @param <U>
 */
@FunctionalInterface
public interface UpperCaseContext<T,U>  {
    String dealContext(String t,String u);
}
