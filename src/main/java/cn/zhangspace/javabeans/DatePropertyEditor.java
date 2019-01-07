package cn.zhangspace.javabeans;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Id 属性修改器
 * id long 类型
 */
public class DatePropertyEditor extends PropertyEditorSupport {


        public void setAsText(String text) {
        if (StringUtils.hasText(text)) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(text);
                setValue(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    }
}
