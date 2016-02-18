package fr.mazerty.shika.torii;

import com.vaadin.data.fieldgroup.BeanFieldGroup;

public class MyBeanFieldGroup<T> extends BeanFieldGroup<T> {

    public MyBeanFieldGroup(Class<T> beanType) {
        super(beanType);

        try {
            setItemDataSource(beanType.newInstance());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace(); // TODO
        }
    }

}
