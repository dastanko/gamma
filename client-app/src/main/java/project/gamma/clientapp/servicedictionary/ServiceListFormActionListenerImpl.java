package project.gamma.clientapp.servicedictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import project.gamma.MainFacade;
import project.gamma.data.Service;
import project.gamma.forms.serviceform.ServiceForm;
import project.gamma.forms.serviceform.ServiceFormActionListener;
import project.gamma.forms.serviceform.ServiceFormData;
import project.gamma.forms.servicelist.ServiceListForm;
import project.gamma.forms.servicelist.ServiceListFormActionListener;
import project.gamma.forms.servicelist.ServiceListFormDataItem;

import javax.swing.*;
import java.awt.*;

@org.springframework.stereotype.Component
public class ServiceListFormActionListenerImpl implements ServiceListFormActionListener {

    ServiceForm serviceForm;
    ServiceListForm serviceListForm;

    MainFacade mainFacade;

    public void edit(ServiceListFormDataItem item) {
        final JDialog dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setTitle("Редактирование услуги");
        dialog.add(serviceForm.getContentPanel());

        final Service service = (Service) item.getTag();

        ServiceFormData formData = new ServiceFormData();
        formData.setDuration(service.getDuration());
        formData.setPrice(service.getPrice());
        formData.setName(service.getName());
        serviceForm.setData(formData);

        serviceForm.setActionListener(new ServiceFormActionListener() {
            public void savePressed() {
                ServiceFormData serviceFormData = serviceForm.getData();
                service.setName(serviceFormData.getName());
                service.setDuration(serviceFormData.getDuration());
                service.setPrice(serviceFormData.getPrice());
                mainFacade.service().update(service);
                dialog.setVisible(false);
                serviceListForm.refresh();
            }

            public void cancelPressed() {
                dialog.setVisible(false);
            }
        });
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }

    public void newItem() {
        final JDialog dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setTitle("Новая услуга");
        dialog.add(serviceForm.getContentPanel());
        serviceForm.setActionListener(new MyServiceFormActionListener(dialog));
        serviceForm.setData(new ServiceFormData());
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void delete(ServiceListFormDataItem item) {
        mainFacade.service().delete((Service) item.getTag());
        serviceListForm.refresh();
    }

    private class MyServiceFormActionListener implements ServiceFormActionListener {
        private final JDialog dialog;

        public MyServiceFormActionListener(JDialog dialog) {
            this.dialog = dialog;
        }

        public void savePressed() {
            ServiceFormData formData = serviceForm.getData();

            Service service = new Service();
            service.setDuration(formData.getDuration());
            service.setPrice(formData.getPrice());
            service.setName(formData.getName());

            mainFacade.service().create(service);
            dialog.setVisible(false);
            serviceListForm.refresh();
        }

        public void cancelPressed() {
            dialog.setVisible(false);
        }
    }

    @Autowired
    public void setServiceForm(ServiceForm serviceForm) {
        this.serviceForm = serviceForm;
    }

    @Autowired
    public void setMainFacade(MainFacade mainFacade) {
        this.mainFacade = mainFacade;
    }

    @Autowired
    public void setServiceListForm(ServiceListForm serviceListForm) {
        this.serviceListForm = serviceListForm;
        serviceListForm.setActionListener(this);
    }
}
