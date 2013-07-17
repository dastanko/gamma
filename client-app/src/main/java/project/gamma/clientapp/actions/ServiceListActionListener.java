package project.gamma.clientapp.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.gamma.MainFacade;
import project.gamma.clientapp.ClientAppScreenManager;
import project.gamma.forms.servicelist.ServiceListForm;

import javax.annotation.PostConstruct;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ServiceListActionListener implements ActionListener {
    private static final String SERVICE_LIST = "serviceList";

    ClientAppScreenManager screenManager;
    ServiceListForm serviceListForm;
    MainFacade mainFacade;

    public void actionPerformed(ActionEvent e) {
        serviceListForm.refresh();
        screenManager.show(SERVICE_LIST);
    }

    @PostConstruct
    public void init() {
        screenManager.register(SERVICE_LIST, serviceListForm.getContentPanel());
    }

    @Autowired
    public void setScreenManager(ClientAppScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    @Autowired
    public void setServiceListForm(ServiceListForm serviceListForm) {
        this.serviceListForm = serviceListForm;
    }

    @Autowired
    public void setMainFacade(MainFacade mainFacade) {
        this.mainFacade = mainFacade;
    }
}
