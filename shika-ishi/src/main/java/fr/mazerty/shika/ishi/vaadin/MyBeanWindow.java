package fr.mazerty.shika.ishi.vaadin;

/**
 * Custom {@link MyWindow} that revolves around a specific bean and handles dynamic content based on it.
 *
 * @see UiManager
 */
public abstract class MyBeanWindow<T> extends MyWindow {

    /**
     * This method is called when entering the window without a bean's instance, eg. in creation mode.
     */
    @Override
    protected abstract void enter();

    /**
     * This method is called when entering the window with an instance of the bean, eg. in update mode.
     */
    protected abstract void enter(T bean);

}
