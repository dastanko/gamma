package project.gamma.forms.doctorform;

import javax.swing.*;

public class UsageExample {
    public static void main(String[] args) {
        final DoctorForm form = new DoctorForm();
        form.setActionListener(new DoctorFormActionListener() {
            public void savePressed() {
                System.out.println("UsageExample.savePressed " + form.getData());
            }

            public void cancelPressed() {
                System.out.println("UsageExample.cancelPressed");
            }
        });

        DoctorFormData data = new DoctorFormData();
        data.setName("Курпатов");
        data.setSpeciality("нарколог");
        data.setTitle("Врач высшей категории");
        data.setWorkingHours("Working hours");

        form.setData(data);

        JFrame frame = new JFrame("Doctor Form Usage Example");
        frame.add(form.getContentPanel());
        frame.setSize(480, 320);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
