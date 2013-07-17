package project.gamma.clientapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
public class ClientApp {

    private JFrame frame;
    ClientAppMenu clientAppMenu;
    ClientAppActionContainer actionContainer;
    ClientAppScreenManager screenManager;

    @PostConstruct
    public void init() {
        frame = new JFrame("Медицинский центр \"Гамма\"");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setJMenuBar(clientAppMenu.getMenuBar());
        frame.add(screenManager.getContentPanel());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionContainer.getExit().actionPerformed(null);
            }
        });
    }

    public void start() {
        frame.setVisible(true);
    }

    @Autowired
    public void setClientAppMenu(ClientAppMenu clientAppMenu) {
        this.clientAppMenu = clientAppMenu;
    }

    @Autowired
    public void setActionContainer(ClientAppActionContainer actionContainer) {
        this.actionContainer = actionContainer;
    }

    @Autowired
    public void setScreenManager(ClientAppScreenManager screenManager) {
        this.screenManager = screenManager;
    }
}
