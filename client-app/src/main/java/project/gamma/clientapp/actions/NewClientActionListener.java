package project.gamma.clientapp.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.gamma.clientapp.clientdictionary.ClientListFormActionListenerImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class NewClientActionListener implements ActionListener {
    ClientListFormActionListenerImpl clientListFormActionListener;

    public void actionPerformed(ActionEvent e) {
        clientListFormActionListener.create();
    }

    @Autowired
    public void setClientListFormActionListener(ClientListFormActionListenerImpl clientListFormActionListener) {
        this.clientListFormActionListener = clientListFormActionListener;
    }
}
