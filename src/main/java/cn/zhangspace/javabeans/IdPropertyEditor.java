package cn.zhangspace.javabeans;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * Id 属性修改器
 * id long 类型
 */
public class IdPropertyEditor extends PropertyEditorSupport {


        public void setAsText(String text) {
        if (StringUtils.hasText(text)) {
            long id = Long.parseLong(text);
            setValue(id);
        } else {
            setValue(Long.MIN_VALUE);
        }

    }
}
