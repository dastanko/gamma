package project.gamma.forms.clientlistform;

import org.uispec4j.Table;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BindingTest extends UISpecTestCase {
    ClientListForm form;

    @Override
    protected void setUp() throws Exception {
        form = new ClientListForm();
        setAdapter(new UISpecAdapter() {
            public Window getMainWindow() {
                JFrame frame = new JFrame();
                frame.add(form.getContentPanel());
                return new Window(frame);
            }
        });
    }

    public void testForwardBinding() {

        final String row1addess = "address " + Math.random();
        final String row2addess = "address " + Math.random();
        final String row3addess = "address " + Math.random();

        final String row1name = "name " + Math.random();
        final String row2name = "name " + Math.random();
        final String row3name = "name " + Math.random();

        final String row1gender = "gender " + Math.random();
        final String row2gender = "gender " + Math.random();
        final String row3gender = "gender " + Math.random();

        final int row1year = (int) (1900 + Math.random() * 100);
        final int row2year = (int) (1900 + Math.random() * 100);
        final int row3year = (int) (1900 + Math.random() * 100);

        form.setProvider(new ClientListFormDataItemProvider() {
            public List<ClientListFormDataItem> getAll() {
                ArrayList<ClientListFormDataItem> items = new ArrayList<ClientListFormDataItem>();
                items.add(newItem(row1addess, row1gender, row1name, row1year));
                items.add(newItem(row2addess, row2gender, row2name, row2year));
                items.add(newItem(row3addess, row3gender, row3name, row3year));
                return items;
            }
        });

        form.refresh();

        Table table = getMainWindow().getTable();
        table.cellEquals(0, 0, row1name).check();
        table.cellEquals(1, 0, row2name).check();
        table.cellEquals(2, 0, row3name).check();

        table.cellEquals(0, 1, String.valueOf(row1year)).check();
        table.cellEquals(1, 1, String.valueOf(row2year)).check();
        table.cellEquals(2, 1, String.valueOf(row3year)).check();

        table.cellEquals(0, 2, row1gender).check();
        table.cellEquals(1, 2, row2gender).check();
        table.cellEquals(2, 2, row3gender).check();

        table.cellEquals(0, 3, row1addess).check();
        table.cellEquals(1, 3, row2addess).check();
        table.cellEquals(2, 3, row3addess).check();
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
