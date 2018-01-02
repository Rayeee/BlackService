package me.zgy.query;

import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;


/**
 * Created by IFT8 on 2016/11/2.
 */
public class ScriptHelper {
    //expression 效率最高
    public static final String DEFAULT_LANG = "expression";

    public static Script buildScript(String expression, ScriptService.ScriptType type, String lang) {
        return new Script(expression, type, lang, null);
    }

    public static Script buildScript(String expression, String lang) {
        return new Script(expression, Script.DEFAULT_TYPE, lang, null);
    }

    public static Script buildScript(String expression) {
        return new Script(expression, Script.DEFAULT_TYPE, DEFAULT_LANG, null);
    }

    public static String getFieldValue(String field) {
        return "doc['" + field + "'].value";
    }

    public static String abs(String value) {
        return "abs(" + value + ")";
    }

}
