package project.gamma.clientapp.clientdictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.gamma.MainFacade;
import project.gamma.clientapp.TypeConverter;
import project.gamma.data.Client;
import project.gamma.data.Gender;
import project.gamma.forms.clientlistform.ClientListFormDataItem;
import project.gamma.forms.clientlistform.ClientListFormDataItemProvider;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientListFormDataItemProviderImpl implements ClientListFormDataItemProvider {
    MainFacade mainFacade;

    TypeConverter typeConverter;

    public List<ClientListFormDataItem> getAll() {
        ArrayList<ClientListFormDataItem> items = new ArrayList<ClientListFormDataItem>();
        List<Client> clients = mainFacade.client().getAll();

        for (Client client : clients) {
            ClientListFormDataItem item = typeConverter.convert(client, ClientListFormDataItem.class);
            items.add(item);
        }

        return items;
    }

    @Autowired
    public void setMainFacade(MainFacade mainFacade) {
        this.mainFacade = mainFacade;
    }

    @Autowired
    public void setTypeConverter(TypeConverter typeConverter) {
        this.typeConverter = typeConverter;
    }
}
