package project.gamma.forms.servicelist;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.uispec4j.MenuItem;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.PopupMenuInterceptor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ActionListenerTest extends UISpecTestCase {
    Mockery context = new JUnit4Mockery();

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

    public void testActionListener() {
        final ServiceListFormActionListener mock = context.mock(ServiceListFormActionListener.class);
        form.setActionListener(mock);
        form.setProvider(new MyServiceListFormDataItemProvider());

        context.checking(new Expectations(){{
            oneOf(mock).newItem();
        }});

        Window mainWindow = getMainWindow();

        MenuItem menuItem = PopupMenuInterceptor.run(mainWindow.getTable().triggerRightClick(0, 0));
        menuItem.getSubMenu("Новая услуга").click();

        context.assertIsSatisfied();
    }

    private static class MyServiceListFormDataItemProvider implements ServiceListFormDataItemProvider {
        public List<ServiceListFormDataItem> getAll() {
            ArrayList<ServiceListFormDataItem> list = new ArrayList<ServiceListFormDataItem>();
            list.add(new ServiceListFormDataItem());
            return list;
        }
    }
}
