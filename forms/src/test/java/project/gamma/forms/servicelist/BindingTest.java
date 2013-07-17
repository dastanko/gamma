package project.gamma.forms.servicelist;

import org.uispec4j.Table;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BindingTest extends UISpecTestCase {

    ServiceListForm form;

    @Override
    protected void setUp() throws Exception {
        form = new ServiceListForm();
        setAdapter(new UISpecAdapter() {
            public Window getMainWindow() {
                JFrame frame = new JFrame();
                frame.add(form.getContentPanel());
                return new Window(frame);
            }
        });
    }

    public void testForwardBinding() {
        String name = "name " + Math.random();
        String duration = "duration " + Math.random();
        int price = (int) (Math.random() * 1000);

        final ServiceListFormDataItem item = new ServiceListFormDataItem();
        item.setName(name);
        item.setDuration(duration);
        item.setPrice(price);

        form.setProvider(new ServiceListFormDataItemProvider() {
            public List<ServiceListFormDataItem> getAll() {
                ArrayList<ServiceListFormDataItem> items = new ArrayList<ServiceListFormDataItem>();
                items.add(item);
                return items;
            }
        });

        form.refresh();

        Window window = getMainWindow();
        Table table = window.getTable();

        waitUntil(table.rowCountEquals(1), 1000);

        table.cellEquals(0, 0, name).check();
        table.cellEquals(0, 1, String.valueOf(price)).check();
        table.cellEquals(0, 2, duration).check();
    }
}
