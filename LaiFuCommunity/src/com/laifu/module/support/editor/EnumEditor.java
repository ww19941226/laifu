package com.laifu.module.support.editor;

import java.beans.PropertyEditorSupport;
/**
 * 枚举类绑定
 * @author Raindrops
 * @version 2016/9/1
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class EnumEditor extends PropertyEditorSupport {

    private Class enumClass;

    public EnumEditor(Class cls) {
        this.enumClass = cls;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && text.trim().length() > 0) {
            setValue(Enum.valueOf(enumClass, text));
        }
    }

    @Override
    public String getAsText() {
        return (getValue() == null) ? "" : getValue().toString();
    }
}
