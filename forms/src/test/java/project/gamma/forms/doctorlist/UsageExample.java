package project.gamma.forms.doctorlist;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 03.03.11
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */
public class UsageExample {
    public static void main(String[] args) {
        DoctorListForm form = new DoctorListForm();


        form.setProvider(new MyDoctorListFormDataItemProvider());

        form.setActionListener(new DoctorListFormActionListener() {
            public void edit(DoctorListFormDataItem item) {
                System.out.println("UsageExample.edit" + item.getFio());
            }

            public void newItem() {
                System.out.println("UsageExample.newItem");
            }

            public void deleteItem(DoctorListFormDataItem item) {
                System.out.println("UsageExample.deleteItem" + item.getFio());
            }
        });

        form.refresh();


                JFrame frame = new JFrame("Doctor list Usage Example");
        frame.add(form.getContentPanel());
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

      private static class MyDoctorListFormDataItemProvider implements DoctorListFormDataItemProvider {
        public List<DoctorListFormDataItem> getAll() {
            ArrayList<DoctorListFormDataItem> items = new ArrayList<DoctorListFormDataItem>();

            items.add(createItem("Курпатов", "Доктор", "Терапевт", "Пн: 09:00 17:00\nmjhgjhgjh\nghgfh"));
            items.add(createItem("Малахов", "Доктор", "Отолоринголог", "Пн: 09:00 17:00"));


            return items;
}

    private DoctorListFormDataItem createItem(String fio, String title, String speciality, String workinghours) {
            DoctorListFormDataItem item = new DoctorListFormDataItem();
            item.setFio(fio);
            item.setTitle(title);
            item.setSpeciality(speciality);
            item.setWorkinghours(workinghours);
            return item;
        }
}
}

