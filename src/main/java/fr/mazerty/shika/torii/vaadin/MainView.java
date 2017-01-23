package fr.mazerty.shika.torii.vaadin;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import fr.mazerty.shika.ishi.vaadin.MyGrid;
import fr.mazerty.shika.ishi.vaadin.MyView;
import fr.mazerty.shika.torii.bean.User;

import javax.annotation.PostConstruct;

@CDIView(MainView.MAIN_VIEW_NAME)
public class MainView extends MyView {

    static final String MAIN_VIEW_NAME = "main";

    @PostConstruct
    public void postConstruct() {
        // TODO : i18n when moved to admin view
        setCaption("Users");

        Button add = new Button("Add");

        HorizontalLayout horizontalLayout = new HorizontalLayout(add);
        horizontalLayout.setHeightUndefined();
        horizontalLayout.setWidth(100, Unit.PERCENTAGE);
        horizontalLayout.setComponentAlignment(add, Alignment.MIDDLE_RIGHT);

        MyGrid grid = new MyGrid<>(User.class);
        grid.setSizeFull();

        addComponents(horizontalLayout, grid);
        setSizeFull();
        setMargin(true);
        setSpacing(true);
        setExpandRatio(grid, 1);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
