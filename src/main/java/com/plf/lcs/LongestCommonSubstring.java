package com.plf.lcs;

/**
 * 最长公共字串/最长公共子序列
 * @author panlf
 * @date 2021/9/10
 */
public class LongestCommonSubstring {
    public static int getLCS(String s, String t) {
        int maxStringLen = 0;
        if (s == null || t == null) {
            return 0;
        }
        int result = 0;
        int sLength = s.length();
        int tLength = t.length();
        int[][] dp = new int[sLength][tLength];
        for (int i = 0; i < sLength; i++) {
            for (int k = 0; k < tLength; k++) {
                if (s.charAt(i) == t.charAt(k)) {
                    if (i == 0 || k == 0) {
                        dp[i][k] = 1;
                    } else {
                        dp[i][k] = dp[i - 1][k - 1] + 1;
                    }
                    //result = Math.max(dp[i][k], result);
                    if(result < dp[i][k]){
                        result = dp[i][k];
                        // 记录最后面相同的字符位置
                        maxStringLen = i;
                    }
                } else {
                    dp[i][k] = 0;
                }
            }
        }
        System.out.println(s.substring(maxStringLen-result+1,maxStringLen+1));
        return result;
    }

    public static int getLCSDp(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        int sLength = s.length();
        int tLength = t.length();
        int[][] dp = new int[sLength+1][tLength+1];
        for (int i = 1; i <= sLength; i++) {
            for (int k = 1;k <= tLength; k++) {
                if (s.charAt(i-1) == t.charAt(k-1)) {
                    dp[i][k] = dp[i - 1][k - 1] + 1;
                }else{
                    dp[i][k] = Math.max(dp[i - 1][k], dp[i][k - 1]);
                }
            }
        }

        for(int i=1;i<=sLength;i++){
            for(int k=1;k<=tLength;k++){
                System.out.print(dp[i][k]+" ");
            }
            System.out.println();
        }
        return dp[sLength][tLength];
    }



    public static void main(String[] args) {
        String s= "ACE";
        String t = "ABCDEF";
        System.out.println(getLCSDp(s,t));
    }
}
