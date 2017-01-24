package fr.mazerty.shika.torii.vaadin;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import fr.mazerty.shika.torii.cdi.Session;

import javax.inject.Inject;

@CDIUI("")
@Title("Torii")
@Theme("mytheme")
@PreserveOnRefresh
public class MyUI extends UI {

    @Inject
    private CDIViewProvider cdiViewProvider;
    @Inject
    private Session session;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, this);
        navigator.addProvider(cdiViewProvider);
        navigator.addViewChangeListener(new MyViewChangeListener());

        navigator.navigateTo(LoginView.VIEW_NAME);
    }

    private class MyViewChangeListener implements ViewChangeListener {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            getWindows().forEach(Window::close);

            boolean goingToLoginView = LoginView.VIEW_NAME.equals(event.getViewName());
            boolean goingToAdminView = AdminView.VIEW_NAME.equals(event.getViewName());

            if (goingToLoginView) {
                session.logout();
                return true;
            } else if (!goingToAdminView && session.isLoggedIn() || session.isAdmin()) {
                return true;
            } else {
                getNavigator().navigateTo(LoginView.VIEW_NAME);
                return false;
            }
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
        }

    }

}
