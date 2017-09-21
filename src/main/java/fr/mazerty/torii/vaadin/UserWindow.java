package fr.mazerty.torii.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import fr.mazerty.shika.ishi.vaadin.MyBeanWindow;
import fr.mazerty.shika.ishi.vaadin.PrimaryButton;
import fr.mazerty.torii.bean.User;
import fr.mazerty.torii.cdi.LanguageProxy;
import fr.mazerty.torii.cdi.event.UserChangedEvent;
import fr.mazerty.torii.service.UserService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class UserWindow extends MyBeanWindow<User> {

    @Inject
    private LanguageProxy lp;
    @Inject
    private UserService userService;
    @Inject
    private javax.enterprise.event.Event<UserChangedEvent> userChangedEvent;

    private Binder<User> binder = new Binder<>();
    private TextField email;
    private CheckBox admin;
    private Button save;

    @PostConstruct
    public void postConstruct() {
        email = new TextField(lp.l("user.email.caption"));
        admin = new CheckBox(lp.l("user.admin.caption"));
        save = new PrimaryButton(lp.l("save"));

        binder.forField(email).bind(User::getEmail, User::setEmail);
        binder.forField(admin).bind(User::getAdmin, User::setAdmin);

        FormLayout formLayout = new FormLayout(email, admin, save);
        formLayout.setSizeUndefined();
        formLayout.setMargin(true);
        setContent(formLayout);

        setModal(true);
        setResizable(false);
    }

    @Override
    protected void enter() {
        setCaption(lp.l("userwindow.caption.add"));

        save.addClickListener(event -> {
            userService.create(binder.getBean());
            close();
            userChangedEvent.fire(new UserChangedEvent());
        });

        binder.setBean(new User());
        email.focus();
    }

    @Override
    protected void enter(User bean) {
        setCaption(lp.l("userwindow.caption.edit"));
    }

}
