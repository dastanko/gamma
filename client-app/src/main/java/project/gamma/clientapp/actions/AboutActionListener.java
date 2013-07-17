package project.gamma.clientapp.actions;

import project.gamma.forms.aboutform.AboutForm;
import project.gamma.forms.aboutform.AboutFormActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        AboutForm form = new AboutForm();

        final JDialog dialog = new JDialog((JFrame) null, true);
        dialog.setSize(480, 320);
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("О программе");
        dialog.add(form.getContentPanel());
        form.setActionListener(new AboutFormActionListener() {
            public void closePressed() {
                dialog.setVisible(false);
            }
        });
        dialog.setVisible(true);
    }
}
