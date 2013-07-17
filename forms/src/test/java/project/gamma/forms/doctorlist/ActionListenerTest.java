package project.gamma.forms.doctorlist;

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

        DoctorListForm form;

    @Override
    protected void setUp() throws Exception {
        form = new DoctorListForm();
        setAdapter(new UISpecAdapter() {
            public Window getMainWindow() {
                JFrame frame = new JFrame();
                frame.add(form.getContentPanel());
                return new Window(frame);
            }
        });
    }
    public void testActionListener() {
         final DoctorListFormActionListener mock = context.mock(DoctorListFormActionListener.class);
         form.setActionListener(mock);
         form.setProvider(new MyDoctorListFormDataItemProvider());

          context.checking(new Expectations() {{
             oneOf(mock).newItem();
         }});

         Window mainWindow = getMainWindow();
         MenuItem menuItem = PopupMenuInterceptor.run(mainWindow.getTable().triggerRightClick(0, 0));
         menuItem.getSubMenu("Новый врач").click();

         context.assertIsSatisfied();
     }



     private static class MyDoctorListFormDataItemProvider implements DoctorListFormDataItemProvider {
         public List<DoctorListFormDataItem> getAll() {
             ArrayList<DoctorListFormDataItem> list = new ArrayList<DoctorListFormDataItem>();

             return list;
         }
     }
 }
