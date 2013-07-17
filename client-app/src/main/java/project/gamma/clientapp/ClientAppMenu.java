package project.gamma.clientapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component
public class ClientAppMenu {
    private JMenu file;
    private JMenu dictionary;
    private JMenu clients;
    private JMenu help;
    private JMenuBar menuBar;

    ClientAppActionContainer actionContainer;

    @PostConstruct
    public void init() {
        menuBar = new JMenuBar();

        file = new JMenu("Файл");
        file.add(new JMenuItem(actionContainer.getNewAttendance()));
        file.add(new JMenuItem(actionContainer.getLog()));
        file.add(new JMenuItem(actionContainer.getExit()));

        dictionary = new JMenu("Справочники");
        dictionary.add(new JMenuItem(actionContainer.getDoctors()));
        dictionary.add(new JMenuItem(actionContainer.getServices()));

        clients = new JMenu("Клиенты");
        clients.add(new JMenuItem(actionContainer.getFindClient()));
        clients.add(new JMenuItem(actionContainer.getNewClient()));

        help = new JMenu("Помощь");
        help.add(new JMenuItem(actionContainer.getAbout()));

        menuBar.add(file);
        menuBar.add(dictionary);
        menuBar.add(clients);
        menuBar.add(help);
    }

    @Autowired
    public void setActionContainer(ClientAppActionContainer actionContainer) {
        this.actionContainer = actionContainer;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
