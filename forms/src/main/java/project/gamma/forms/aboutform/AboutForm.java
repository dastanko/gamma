package project.gamma.forms.aboutform;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutForm {
    private JPanel contentPanel;
    private JTextArea aboutTextArea;
    private JButton closeButton;

    private AboutFormActionListener actionListener;

    public AboutForm() {
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.closePressed();
            }
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setActionListener(AboutFormActionListener actionListener) {
        this.actionListener = actionListener;
    }
}
