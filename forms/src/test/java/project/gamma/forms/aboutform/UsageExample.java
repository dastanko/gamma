package project.gamma.forms.aboutform;

import javax.swing.*;

public class UsageExample {
    public static void main(String[] args) {

        AboutForm form = new AboutForm();
        form.setActionListener(new AboutFormActionListener() {
            public void closePressed() {
                System.out.println("UsageExample.closePressed");
            }
        });

        JFrame frame = new JFrame("About form");
        frame.add(form.getContentPanel());

        frame.setSize(480, 320);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
