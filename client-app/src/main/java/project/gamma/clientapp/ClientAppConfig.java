package project.gamma.clientapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.gamma.MainFacade;
import project.gamma.forms.clientform.ClientForm;
import project.gamma.forms.clientlistform.ClientListForm;
import project.gamma.forms.clientlistform.ClientListFormDataItemProvider;
import project.gamma.forms.doctorform.DoctorForm;
import project.gamma.forms.doctorlist.DoctorListForm;
import project.gamma.forms.doctorlist.DoctorListFormDataItemProvider;
import project.gamma.forms.serviceform.ServiceForm;
import project.gamma.forms.servicelist.ServiceListForm;
import project.gamma.forms.servicelist.ServiceListFormActionListener;
import project.gamma.forms.servicelist.ServiceListFormDataItemProvider;

@Configuration
public class ClientAppConfig {

    @Autowired
    ServiceListFormDataItemProvider serviceListFormDataItemProvider;

    @Autowired
    DoctorListFormDataItemProvider doctorListFormDataItemProvider;

    @Autowired
    ClientListFormDataItemProvider clientListFormDataItemProvider;

    @Bean
    public MainFacade mainFacade() {
        MainFacade.init();
        return MainFacade.getInstance();
    }

    @Bean
    public ServiceListForm serviceListForm() {
        ServiceListForm form = new ServiceListForm();
        form.setProvider(serviceListFormDataItemProvider);
        return form;
    }

    @Bean
    public ServiceForm serviceForm() {
        return new ServiceForm();
    }

    @Bean
    public DoctorListForm doctorListForm() {
        DoctorListForm form = new DoctorListForm();
        form.setProvider(doctorListFormDataItemProvider);
        return form;
    }

    @Bean
    public ClientListForm clientListForm() {
        ClientListForm clientListForm = new ClientListForm();
        clientListForm.setProvider(clientListFormDataItemProvider);
        return clientListForm;
    }

    @Bean
    public ClientForm clientForm() {
        return new ClientForm();
    }

    @Bean
    public DoctorForm doctorForm() {
        return new DoctorForm();
    }
}
