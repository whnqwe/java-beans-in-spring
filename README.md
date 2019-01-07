# Java Beans 内省机制以及在spring中的应用

> - 理解 Java Beans 内省机制
> - 内省机制在Spring 中的应用
> - 重新认识 Java Beans



> 理解的前提： 
>
> - java的反射
> - 状态bean 与 非状态bean



###### 课前思考

> 在spring boot  application.properties 的server.port 怎么映射上ServerProperties



##### java beans内省

> java bean 的自我反省



###### 反射

> Class的信息
>
> > 构造器(Constructor)
> >
> > 方法(Method)
> >
> > 字段(Field)

######  内省

> java beanInfo
>
> > - Bean描述符（BeanDescriptor）
> >
> >
> > - 属性描述符（PropertyDescriptor）
> >
> >
> > - 方法描述符（MethodDescriptor）


> **当一个属性发生变化的时候，发生了相对应的事件 （idea修改字体）**
>
> > 1.  传递一个字符串类（text）型
> > 2. 传递的值转换为不同的数据类型，并且赋值
> > 3. 对应事件的发生
> >
> > > 不同属性改变触发的事件可能不同（修改字体与修改行距发生的事件不同）



###### Java Beans事件监听

> - 属性变化监听器（PropertyChangeListener）

> - 属性变化事件（PropertyChangeEvent）
>
> > 事件源（Source）
> >
> > 属性名称（PropertyName）
> >
> > 变化前值（OldValue）
> >
> > 变化后值（NewValue）



###### 代码演示

> 属性 与 字段的区别：属性是带操作方法的字段。
>
> > readMethod  
> >
> > writeMethod
> >
> > 不一定都出现，但必须出现一个
>
> java bean get/set 方法不一定要成对的出现

```java
public class User {
    private long id;
    private String name;
    ...get/set...
}
```

```java

        BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Object.class);
       // BeanInfo beanInfo = Introspector.getBeanInfo(User.class);


        /**Bean描述符（BeanDescriptor）
         * * A BeanDescriptor provides global information about a "bean",
         * including its Java class, its displayName, etc.
         */
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println(beanDescriptor);


        /**
         *
         *  方法描述符（MethodDescriptor）
         */
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        Stream.of(methodDescriptors).forEach(System.out::println);


        /**
         *属性描述符（PropertyDescriptor）
         */
        PropertyDescriptor[] propertyDescriptors  = beanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(System.out::println);
```





##### Spring Bean属性处理



> 属性修改器（PropertyEditor）
> 属性修改器注册（PropertyEditorRegistry
>
> PropertyEditor注册器（PropertyEditorRegistrar）
> 自定义PropertyEditor配置器（CustomEditorConfigurer）

