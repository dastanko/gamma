package project.gamma.forms.clientlistform;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UsageExample {
    public static void main(String[] args) {

        ClientListForm form = new ClientListForm();
        form.setProvider(new ClientListFormDataItemProvider() {
            public List<ClientListFormDataItem> getAll() {
                ArrayList<ClientListFormDataItem> items = new ArrayList<ClientListFormDataItem>();
                items.add(newItem("address", "male", "name", 1950));
                items.add(newItem("address 1", "female", "name 2", 1951));
                items.add(newItem("address 2", "female", "name 3", 1953));
                return items;
            }
        });

        form.setActionListener(new ClientListFormActionListener() {
            public void create() {
                System.out.println("UsageExample.create");
            }

            public void edit(ClientListFormDataItem item) {
                System.out.println("UsageExample.edit");
            }

            public void delete(ClientListFormDataItem item) {
                System.out.println("UsageExample.delete");
            }
        });

        form.refresh();

        JFrame frame = new JFrame("ClientListForm Usage Example");
        frame.add(form.getContentPanel());
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static ClientListFormDataItem newItem(String address, String gender, String name, int year) {
        ClientListFormDataItem item = new ClientListFormDataItem();
        item.setAddress(address);
        item.setGender(gender);
        item.setName(name);
        item.setYear(year);
        return item;
    }
}
