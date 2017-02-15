package com.laifu.module.support.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * 在SpringMVC中，bean中定义了Date，double等类型，
 * 如果没有做任何处理的话，日期以及double都无法绑定。
 * 这个是日期绑定类
 * @author Raindrops
 * 
 */
public class DateEditor extends PropertyEditorSupport {

    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && text.trim().length() > 0) {
            try {
                setValue(formater.parse(text));
            }
            catch (ParseException ex) {
            }
        }
    }

    @Override
    public String getAsText() {
        return (getValue() == null) ? "" : formater.format(getValue());
    }
}
