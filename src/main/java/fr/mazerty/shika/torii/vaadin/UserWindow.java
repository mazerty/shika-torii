package fr.mazerty.shika.torii.vaadin;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import fr.mazerty.shika.ishi.vaadin.*;
import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.cdi.LanguageProxy;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class UserWindow extends MyBeanWindow<User> {

    @Inject
    private LanguageProxy lp;

    private MyBeanFieldGroup<User> bfg = new MyBeanFieldGroup<>(User.class);
    private MyTextField email;
    private CheckBox admin;
    private MyButton save;

    @PostConstruct
    public void postConstruct() {
        email = bfg.buildAndBind(lp.l("user.email.caption"), "email", MyTextField.class);
        admin = bfg.buildAndBind(lp.l("user.admin.caption"), "admin", CheckBox.class);

        save = new PrimaryButton(lp.l("save"));

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

        save.setClickListener(event -> close());

        bfg.setBean(new User());
        email.focus();
    }

    @Override
    protected void enter(User bean) {
        setCaption(lp.l("userwindow.caption.edit"));
    }

}
