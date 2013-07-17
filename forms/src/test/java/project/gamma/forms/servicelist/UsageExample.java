package project.gamma.forms.servicelist;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UsageExample {
    public static void main(String[] args) {
        ServiceListForm form = new ServiceListForm();

        form.setProvider(new MyServiceListFormDataItemProvider());
        form.setActionListener(new ServiceListFormActionListener() {
            public void edit(ServiceListFormDataItem item) {
                System.out.println("UsageExample.edit " + item.getName());
            }

            public void newItem() {
                System.out.println("UsageExample.newItem");
            }

            public void delete(ServiceListFormDataItem item) {
                System.out.println("UsageExample.delete " + item.getName());
            }
        });
        form.refresh();

        JFrame frame = new JFrame("Service list Usage Example");
        frame.add(form.getContentPanel());
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static class MyServiceListFormDataItemProvider implements ServiceListFormDataItemProvider {
        public List<ServiceListFormDataItem> getAll() {
            ArrayList<ServiceListFormDataItem> items = new ArrayList<ServiceListFormDataItem>();

            items.add(createItem("Массаж", 200, "01:00"));
            items.add(createItem("Протезирование", 150, "00:30"));
            items.add(createItem("Перевязка", 50, "00:15"));
            items.add(createItem("Взятие анализа", 50, "00:10"));

            return items;
        }

        private ServiceListFormDataItem createItem(String name, int price, String duration) {
            ServiceListFormDataItem item = new ServiceListFormDataItem();
            item.setName(name);
            item.setPrice(price);
            item.setDuration(duration);
            return item;
        }
    }
}
