package fr.mazerty.shika.ishi.vaadin;

import com.vaadin.ui.UI;

/**
 * Methods to help some UI classes handle popups and views
 */
interface UiManager {

    /**
     * Displays a {@link MyWindow} or a {@link MyBeanWindow} without a bean instance, eg. in creation mode.
     */
    default void show(MyWindow window) {
        UI.getCurrent().addWindow(window);
        window.enter();
    }

    /**
     * Displays a {@link MyBeanWindow} with a bean instance, eg. in update mode.
     */
    default <T> void show(MyBeanWindow<T> window, T bean) {
        UI.getCurrent().addWindow(window);
        window.enter(bean);
    }

    default void navigateTo(String navigationState) {
        UI.getCurrent().getNavigator().navigateTo(navigationState);
    }

}
