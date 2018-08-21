package fr.mazerty.torii.vaadin;

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
import fr.mazerty.torii.cdi.Session;

import javax.inject.Inject;

@CDIUI("")
@Theme("valo")
@PreserveOnRefresh
@Title("Torii")
public class ToriiUI extends UI {

    @Inject
    private CDIViewProvider cdiViewProvider;
    @Inject
    private Session session;

    private Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // defines that the application consists of a set of annotated views you can access through the ui's navigator
        navigator = new Navigator(this, this);
        navigator.addProvider(cdiViewProvider);
        navigator.addViewChangeListener(new MyViewChangeListener());

        navigator.navigateTo(LoginView.VIEW_NAME);
    }

    /**
     * The heart of the application's security. Every change of view is challenged here against the user's permissions.
     */
    private class MyViewChangeListener implements ViewChangeListener {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            // popups are not tied to the views, which is a shame, so we have to close them before moving on
            UI.getCurrent().getWindows().forEach(Window::close);

            boolean goingToLoginView = LoginView.VIEW_NAME.equals(event.getViewName());
            boolean goingToAdminView = AdminView.VIEW_NAME.equals(event.getViewName());

            if (goingToLoginView) {
                // anyone can access the login view but doing so will log you out
                session.logout();
                return true;
            } else if (session.isAdmin() || session.isLoggedIn() && !goingToAdminView) {
                // an admin can go anywhere, so does a normal user except for the admin view
                return true;
            } else {
                // any other attempt is forbidden and redirects to the login view
                navigator.navigateTo(LoginView.VIEW_NAME);
                return false;
            }
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
        }

    }

}
