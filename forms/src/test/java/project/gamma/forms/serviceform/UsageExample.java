package project.gamma.forms.serviceform;

import javax.swing.*;

public class UsageExample {
    public static void main(String[] args) {
        ServiceForm form = new ServiceForm();
        form.setActionListener(new ServiceFormActionListener() {
            public void savePressed() {
                System.out.println("UsageExample.savePressed");
            }

            public void cancelPressed() {
                System.out.println("UsageExample.cancelPressed");
            }
        });

        ServiceFormData data = new ServiceFormData();
        data.setName("name");
        data.setPrice(3);
        data.setDuration(3);
        form.setData(data);

        JFrame frame = new JFrame("Service form Usage Example");
        frame.setSize(320, 200);
        frame.setLocationRelativeTo(null);
        frame.add(form.getContentPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
