package project.gamma.forms.clientlistform;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.uispec4j.MenuItem;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.PopupMenuInterceptor;
import project.gamma.forms.doctorlist.DoctorListForm;
import project.gamma.forms.doctorlist.DoctorListFormActionListener;
import project.gamma.forms.doctorlist.DoctorListFormDataItem;
import project.gamma.forms.doctorlist.DoctorListFormDataItemProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ActionListenerTest extends UISpecTestCase {
    Mockery context = new JUnit4Mockery();

    ClientListForm form;

    @Override
    protected void setUp() throws Exception {
        form = new ClientListForm();
        Component[] components = form.popupMenu.getComponents();
        for (Component component : components) {
            if (component instanceof JSeparator)
                form.popupMenu.remove(component);
        }

        setAdapter(new UISpecAdapter() {
            public Window getMainWindow() {
                JFrame frame = new JFrame();
                frame.add(form.getContentPanel());
                return new Window(frame);
            }
        });
    }

    public void testActionListener() throws InterruptedException {
        final ClientListFormActionListener mock = context.mock(ClientListFormActionListener.class);
        form.setActionListener(mock);
        form.setProvider(new MyClientListFormDataItemProvider());
        form.refresh();
        context.checking(new Expectations() {{
            Sequence sequence = context.sequence("sequence");
            oneOf(mock).create(); inSequence(sequence);
            oneOf(mock).edit(item1); inSequence(sequence);
            oneOf(mock).delete(item2); inSequence(sequence);
        }});

        Window mainWindow = getMainWindow();
        MenuItem menuItem = PopupMenuInterceptor.run(mainWindow.getTable().triggerRightClick(0, 0));
        menuItem.getSubMenu("Новый клиент").click();
        form.popupMenu.setVisible(false);

        menuItem = PopupMenuInterceptor.run(mainWindow.getTable().triggerRightClick(0, 0));
        menuItem.getSubMenu("Редактировать").click();
        form.popupMenu.setVisible(false);

        menuItem = PopupMenuInterceptor.run(mainWindow.getTable().triggerRightClick(1, 0));
        menuItem.getSubMenu("Удалить").click();

        context.assertIsSatisfied();
    }

    private ClientListFormDataItem item1;
    private ClientListFormDataItem item2;

    private class MyClientListFormDataItemProvider implements ClientListFormDataItemProvider {

        public List<ClientListFormDataItem> getAll() {
            ArrayList<ClientListFormDataItem> items = new ArrayList<ClientListFormDataItem>();

            item1 = new ClientListFormDataItem();
            item1.setName("item1");
            items.add(item1);

            item2 = new ClientListFormDataItem();
            item2.setName("item2");
            items.add(item2);

            return items;
        }
    }
}
