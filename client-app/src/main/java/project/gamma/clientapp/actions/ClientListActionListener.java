package project.gamma.clientapp.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.gamma.clientapp.ClientAppScreenManager;
import project.gamma.forms.clientlistform.ClientListForm;

import javax.annotation.PostConstruct;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ClientListActionListener implements ActionListener {

    private static final String CLIENT_LIST_FORM = "ClientListForm";

    ClientAppScreenManager screenManager;

    ClientListForm clientListForm;

    public void actionPerformed(ActionEvent e) {
        clientListForm.refresh();
        screenManager.show(CLIENT_LIST_FORM);
    }

    @PostConstruct
    public void init() {
        screenManager.register(CLIENT_LIST_FORM, clientListForm.getContentPanel());
    }

    @Autowired
    public void setScreenManager(ClientAppScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    @Autowired
    public void setClientListForm(ClientListForm clientListForm) {
        this.clientListForm = clientListForm;
    }
}
