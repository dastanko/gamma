package project.gamma.clientapp.actions;

import org.jdesktop.swingx.action.BoundAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        int option = JOptionPane.showConfirmDialog(
                null,
                "Вы уверены, что хотите выйти из программы?",
                "Выход из программы",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }
}
