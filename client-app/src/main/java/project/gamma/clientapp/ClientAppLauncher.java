package project.gamma.clientapp;

import project.gamma.MainFacade;
import project.gamma.clientapp.actions.ServiceListActionListener;
import project.gamma.clientapp.servicedictionary.ServiceListFormActionListenerImpl;
import project.gamma.clientapp.servicedictionary.ServiceListFormDataItemProviderImpl;
import project.gamma.forms.serviceform.ServiceForm;
import project.gamma.forms.servicelist.ServiceListForm;

public class ClientAppLauncher {
    public static void main(String[] args) {
        MainFacade.init();

        ClientApp app = new ClientApp();
        ClientAppMenu appMenu = new ClientAppMenu();
        ClientAppActionContainer actionContainer = new ClientAppActionContainer();
        ClientAppScreenManager screenManager = new ClientAppScreenManager();

        ServiceListForm serviceListForm = new ServiceListForm();

        ServiceListActionListener serviceListActionListener = new ServiceListActionListener();
        ServiceListFormActionListenerImpl serviceListFormActionListener = new ServiceListFormActionListenerImpl();
        ServiceForm serviceForm = new ServiceForm();
        ServiceListFormDataItemProviderImpl serviceListFormDataItemProvider = new ServiceListFormDataItemProviderImpl();


        serviceListFormDataItemProvider.setMainFacade(MainFacade.getInstance());

        serviceListFormActionListener.setMainFacade(MainFacade.getInstance());
        serviceListFormActionListener.setServiceForm(serviceForm);
        serviceListFormActionListener.setServiceListForm(serviceListForm);

        serviceListForm.setActionListener(serviceListFormActionListener);
        serviceListForm.setProvider(serviceListFormDataItemProvider);

        screenManager.init();

        serviceListActionListener.setMainFacade(MainFacade.getInstance());
        serviceListActionListener.setScreenManager(screenManager);
        serviceListActionListener.setServiceListForm(serviceListForm);
        serviceListActionListener.init();

        actionContainer.setServiceListActionListener(serviceListActionListener);
        actionContainer.init();

        appMenu.setActionContainer(actionContainer);
        appMenu.init();

        app.setClientAppMenu(appMenu);
        app.setActionContainer(actionContainer);
        app.setScreenManager(screenManager);
        app.init();

        app.start();
    }
}
