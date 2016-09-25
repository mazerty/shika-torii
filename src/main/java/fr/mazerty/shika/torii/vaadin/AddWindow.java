package fr.mazerty.shika.torii.vaadin;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import fr.mazerty.shika.ishi.bean.User;
import fr.mazerty.shika.ishi.vaadin.MyBeanFieldGroup;
import fr.mazerty.shika.ishi.vaadin.MyTextField;
import fr.mazerty.shika.ishi.vaadin.MyWindow;

import static com.vaadin.event.ShortcutAction.KeyCode.ENTER;
import static com.vaadin.ui.themes.ValoTheme.BUTTON_PRIMARY;

public class AddWindow extends MyWindow {

    private final MyBeanFieldGroup<User> bfg;
    private final MyTextField email;

    public AddWindow() {
        super("Add user");

        bfg = new MyBeanFieldGroup<>(User.class);

        email = bfg.buildAndBind("Email", "email", MyTextField.class);

        Button add = new Button("Add");
        add.addStyleName(BUTTON_PRIMARY);
        add.setClickShortcut(ENTER);
        add.addClickListener(event -> close());

        FormLayout formLayout = new FormLayout(email, add);
        formLayout.setSizeUndefined();
        formLayout.setMargin(true);

        setContent(formLayout);
        setModal(true);
        setResizable(false);
    }

    @Override
    protected void enter() {
        bfg.setBean(new User());
        email.focus();
    }

}
