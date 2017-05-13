package fr.mazerty.shika.torii.vaadin;

import com.vaadin.cdi.CDIView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import fr.mazerty.shika.ishi.vaadin.BooleanRenderer;
import fr.mazerty.shika.ishi.vaadin.MyView;
import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.cdi.LanguageProxy;
import fr.mazerty.shika.torii.cdi.event.UserChangedEvent;
import fr.mazerty.shika.torii.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * Special view for the application's administration. Logged user needs to be administrator to access it.
 */
@CDIView(AdminView.VIEW_NAME)
public class AdminView extends MyView {

    static final String VIEW_NAME = "admin";

    @Inject
    private LanguageProxy lp;
    @Inject
    private UserWindow userWindow;
    @Inject
    private UserService userService;

    private Grid<User> grid;

    @PostConstruct
    public void postConstruct() {
        Button back = new Button(lp.l("back"));
        back.addClickListener(event -> navigateTo(MainView.VIEW_NAME));

        Button add = new Button(lp.l("add"));
        add.addClickListener(event -> show(userWindow));

        grid = new Grid<>();
        grid.addColumn(User::getEmail).setCaption(lp.l("user.email.caption"));
        grid.addColumn(User::getAdmin, new BooleanRenderer(lp.l("yes"), lp.l("no"))).setCaption(lp.l("user.admin.caption"));

        HorizontalLayout horizontalLayout = new HorizontalLayout(back, grid, add);
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);

        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.TOP_CENTER);

        refreshGrid(null);
    }

    private void refreshGrid(@Observes UserChangedEvent event) {
        grid.setItems(userService.list());
    }

}
