package project.gamma.forms.doctorlist;

import com.jgoodies.binding.list.ArrayListModel;
import org.uispec4j.Table;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 03.03.11
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public class BindingTest extends UISpecTestCase {
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

 public void testForwardBinding () {
        String fio = "fio" + Math.random();
        String title = "Врач";
        String speciality = "нарколог";
        String workinghours = "workinghours" + Math.random();

        final DoctorListFormDataItem item = new DoctorListFormDataItem();
        item.setFio(fio);
        item.setTitle(title);
        item.setSpeciality(speciality);
        item.setWorkinghours(workinghours);

        form.setProvider(new DoctorListFormDataItemProvider() {
            public List<DoctorListFormDataItem> getAll() {
                ArrayList<DoctorListFormDataItem> items = new ArrayListModel<DoctorListFormDataItem>();
                items.add(item);
                return items;
            }
        });
        form.refresh();

        Window window = getMainWindow();
        Table table = window.getTable();

        waitUntil(table.rowCountEquals(1),1000);

        table.cellEquals(0,0,fio).check();
         table.cellEquals(0,1,title).check();
         table.cellEquals(0,2,speciality).check();
        table.cellEquals(0,3,workinghours).check();


    }




}