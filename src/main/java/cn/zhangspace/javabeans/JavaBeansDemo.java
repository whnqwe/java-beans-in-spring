package cn.zhangspace.javabeans;

import java.beans.*;
import java.lang.reflect.Method;
import java.util.stream.Stream;


public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException, ClassNotFoundException {

        //跟spring的xml很像
        Class<?> beanClass = Class.forName("cn.zhangspace.javabeans.User");
        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass,Object.class);
        //BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Object.class);
       // BeanInfo beanInfo = Introspector.getBeanInfo(User.class);


        /**Bean描述符（BeanDescriptor）
         * * A BeanDescriptor provides global information about a "bean",
         * including its Java class, its displayName, etc.
         */
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        //System.out.println(beanDescriptor);


        /**
         *
         *  方法描述符（MethodDescriptor）
         */
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        //Stream.of(methodDescriptors).forEach(System.out::println);


        /**
         *属性描述符（PropertyDescriptor）
         */
//        PropertyDescriptor[] propertyDescriptors  = beanInfo.getPropertyDescriptors();
//        Stream.of(propertyDescriptors).forEach(System.out::println);

        User user = new User();

        PropertyDescriptor[] propertyDescriptors  = beanInfo.getPropertyDescriptors();

        Stream.of(propertyDescriptors).forEach(propertyDescriptor->{
            String propertyName = propertyDescriptor.getName();
            if("id".equals(propertyName)){
                propertyDescriptor.setPropertyEditorClass(IdPropertyEditor.class);
                PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(user);
                propertyEditor.addPropertyChangeListener(new SetPropertyChangeListener(user,propertyDescriptor.getWriteMethod()));
                propertyEditor.setAsText("55");
            }

            if("date".equals(propertyName)){
                propertyDescriptor.setPropertyEditorClass(DatePropertyEditor.class);
                PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(user);
                propertyEditor.addPropertyChangeListener(new SetPropertyChangeListener(user,propertyDescriptor.getWriteMethod()));
                propertyEditor.setAsText("2018-11-25");
            }
        });

        System.out.println(user);

    }


    private static class SetPropertyChangeListener implements PropertyChangeListener {

        private final Object bean;
        private final Method setterMethod;

        private SetPropertyChangeListener(Object bean, Method setterMethod) {
            this.setterMethod = setterMethod;
            this.bean = bean;
        }

        @Override
        public void propertyChange(PropertyChangeEvent event) {
            PropertyEditor source = (PropertyEditor) event.getSource();
            try {
                setterMethod.invoke(bean, source.getValue());
            } catch (Exception e) {
            }
        }
    }

}
