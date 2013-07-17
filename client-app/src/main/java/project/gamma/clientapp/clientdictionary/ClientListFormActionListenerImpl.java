package project.gamma.clientapp.clientdictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.gamma.MainFacade;
import project.gamma.clientapp.TypeConverter;
import project.gamma.data.Client;
import project.gamma.data.Gender;
import project.gamma.forms.clientform.ClientForm;
import project.gamma.forms.clientform.ClientFormActionListener;
import project.gamma.forms.clientform.ClientFormData;
import project.gamma.forms.clientlistform.ClientListForm;
import project.gamma.forms.clientlistform.ClientListFormActionListener;
import project.gamma.forms.clientlistform.ClientListFormDataItem;

import javax.swing.*;
import java.awt.*;

@Component
public class ClientListFormActionListenerImpl implements ClientListFormActionListener {

    MainFacade mainFacade;

    ClientForm clientForm;
    ClientListForm clientListForm;

    TypeConverter typeConverter;

    public void create() {
        final JDialog dialog = new JDialog(null, "Новый клиент", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(480, 320);
        dialog.setLocationRelativeTo(null);
        dialog.add(clientForm.getContentPanel());
        clientForm.setData(new ClientFormData());
        clientForm.setActionListener(new ClientFormActionListener() {
            public void save() {
                ClientFormData data = clientForm.getData();
                Client client = typeConverter.convert(data, Client.class);
                mainFacade.client().create(client);
                dialog.setVisible(false);
                clientListForm.refresh();
            }

            public void cancel() {
                dialog.setVisible(false);
            }
        });
        dialog.setVisible(true);
    }

    public void edit(final ClientListFormDataItem item) {
        final JDialog dialog = new JDialog(null, "Редактирование клиента", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(480, 320);
        dialog.setLocationRelativeTo(null);
        dialog.add(clientForm.getContentPanel());

        final ClientFormData data = typeConverter.convert(item, ClientFormData.class);

        clientForm.setData(data);
        clientForm.setActionListener(new ClientFormActionListener() {
            public void save() {
                Client client = typeConverter.convert(item, Client.class);
                typeConverter.update(clientForm.getData(), client);

                mainFacade.client().update(client);
                dialog.setVisible(false);
                clientListForm.refresh();
            }

            public void cancel() {
                dialog.setVisible(false);
            }
        });

        dialog.setVisible(true);

    }

    public void delete(ClientListFormDataItem item) {
        mainFacade.client().delete(typeConverter.convert(item, Client.class));
        clientListForm.refresh();
    }

    @Autowired
    public void setClientListForm(ClientListForm clientListForm) {
        this.clientListForm = clientListForm;
        clientListForm.setActionListener(this);
    }

    @Autowired
    public void setMainFacade(MainFacade mainFacade) {
        this.mainFacade = mainFacade;
    }

    @Autowired
    public void setClientForm(ClientForm clientForm) {
        this.clientForm = clientForm;
    }

    @Autowired
    public void setTypeConverter(TypeConverter typeConverter) {
        this.typeConverter = typeConverter;
    }
}
