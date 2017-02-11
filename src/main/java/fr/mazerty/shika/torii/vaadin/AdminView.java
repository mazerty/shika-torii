package fr.mazerty.shika.torii.vaadin;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import fr.mazerty.shika.ishi.vaadin.MyGrid;
import fr.mazerty.shika.ishi.vaadin.MyView;
import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.cdi.LanguageProxy;
import fr.mazerty.shika.torii.service.UserService;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void postConstruct() {
        Button back = new Button(lp.l("back"));
        back.addClickListener(event -> navigateTo(MainView.VIEW_NAME));

        Button add = new Button(lp.l("add"));
        add.addClickListener(event -> show(userWindow));

        MyGrid<User> grid = new MyGrid<>(User.class);
        grid.setColumns("email", "admin");
        grid.setColumnHeaderCaptions(lp.l("user.email.caption"), lp.l("user.admin.caption"));
        grid.getColumn("admin").setConverter(new StringToBooleanConverter(lp.l("yes"), lp.l("no")));
        grid.addAll(userService.list());

        HorizontalLayout horizontalLayout = new HorizontalLayout(back, grid, add);
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);

        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.TOP_CENTER);
    }

}
