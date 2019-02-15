package com.hrabbit.admin.core.common.http;

import java.util.regex.Pattern;

/**
 * Web防火墙工具类
 *
 * @author hrabbit
 * @Date 2018-09-18
 */
public class WafUtils {

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤XSS脚本内容
     */
    public static String stripXSS(String value) {
        String rlt = null;

        if (null != value) {
            rlt = value.replaceAll("", "");
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
        }

        return rlt;
    }

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤SQL注入内容
     */
    public static String stripSqlInjection(String value) {
        return (null == value) ? null : value.replaceAll("('.+--)|(--)|(%7C)", ""); //value.replaceAll("('.+--)|(--)|(\\|)|(%7C)", "");
    }

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤SQL/XSS注入内容
     */
    public static String stripSqlXSS(String value) {
        return stripXSS(stripSqlInjection(value));
    }

}
