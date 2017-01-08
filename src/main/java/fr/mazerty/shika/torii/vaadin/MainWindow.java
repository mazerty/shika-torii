package fr.mazerty.shika.torii.vaadin;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import fr.mazerty.shika.ishi.vaadin.MyGrid;
import fr.mazerty.shika.ishi.vaadin.MyWindow;
import fr.mazerty.shika.torii.bean.User;

import javax.inject.Inject;

public class MainWindow extends MyWindow {

    @Inject
    private AddWindow addWindow;

    public MainWindow() {
        super("Users");

        Button add = new Button("Add");
        add.addClickListener(event -> show(addWindow));

        HorizontalLayout horizontalLayout = new HorizontalLayout(add);
        horizontalLayout.setHeightUndefined();
        horizontalLayout.setWidth(100, Unit.PERCENTAGE);
        horizontalLayout.setComponentAlignment(add, Alignment.MIDDLE_RIGHT);

        MyGrid grid = new MyGrid<>(User.class);
        grid.setSizeFull();

        VerticalLayout verticalLayout = new VerticalLayout(horizontalLayout, grid);
        verticalLayout.setSizeFull();
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);
        verticalLayout.setExpandRatio(grid, 1);

        setContent(verticalLayout);
        setHeight(500, Unit.PIXELS);
        setWidth(700, Unit.PIXELS);

        addCloseListener(event -> navigateTo(LoginView.VIEW_NAME));
    }

}
