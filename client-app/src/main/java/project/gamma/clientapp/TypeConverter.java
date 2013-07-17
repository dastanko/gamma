package project.gamma.clientapp;

import org.springframework.stereotype.Component;
import project.gamma.data.Client;
import project.gamma.data.Gender;
import project.gamma.forms.clientform.ClientFormData;
import project.gamma.forms.clientlistform.ClientListFormDataItem;

public class TypeConverter {

    public Client convert(ClientFormData data, Class<Client> aClass) {
        Client client = new Client();
        update(data, client);
        return client;
    }

    public Client convert(ClientListFormDataItem data, Class<Client> aClass) {
        return (Client) data.getTag();
    }

    public ClientFormData convert(ClientListFormDataItem item, Class<ClientFormData> aClass) {
        ClientFormData data = new ClientFormData();
        data.setName(item.getName());
        data.setAddress(item.getAddress());
        data.setGender(item.getGender());
        data.setYear(item.getYear());
        return data;
    }

    public ClientListFormDataItem convert(Client client, Class<ClientListFormDataItem> aClass) {
        ClientListFormDataItem item = new ClientListFormDataItem();
        item.setName(client.getName());
        item.setAddress(client.getAddress());
        item.setGender(genderToString(client.getGender()));
        item.setYear(client.getYear());
        item.setTag(client);
        return item;
    }

    public void update(ClientFormData src, Client target) {
        target.setAddress(src.getAddress());
        target.setName(src.getName());
        target.setYear(src.getYear());
        target.setGender(parseGender(src.getGender()));
    }

    static String genderToString(Gender gender) {
        if (gender == Gender.female)
            return "Ж";
        if (gender == Gender.male)
            return "М";
        return null;
    }

    static Gender parseGender(String gender) {
        if ("м".equalsIgnoreCase(gender))
            return Gender.male;
        if ("ж".equalsIgnoreCase(gender))
            return Gender.female;
        return null;
    }

}
