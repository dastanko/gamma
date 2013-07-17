package project.gamma.clientapp;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@org.springframework.stereotype.Component
public class ClientAppScreenManager {

    JPanel contentPanel = new JPanel();
    private CardLayout layout;

    public ClientAppScreenManager() {
        layout = new CardLayout();
        contentPanel.setLayout(layout);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void register(String name, JComponent component) {
        contentPanel.add(component, name);
    }

    public void show(String name) {
        layout.show(contentPanel, name);
    }

    @PostConstruct
    public void init() {
        register("blankPanel", new JPanel());
    }
}
