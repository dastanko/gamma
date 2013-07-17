package project.gamma.forms.clientform;

import javax.swing.*;

public class UsageExample {
    public static void main(String[] args) {
        final ClientForm form = new ClientForm();
        form.setActionListener(new ClientFormActionListener() {
            public void save() {
                System.out.println("UsageExample.save " + form.getData());
            }

            public void cancel() {
                System.out.println("UsageExample.cancel");
            }
        });

        ClientFormData data = new ClientFormData();
        data.setName("Иванов");
        form.setData(data);

        JFrame frame = new JFrame();
        frame.add(form.getContentPanel());
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
