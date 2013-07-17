package project.gamma.clientapp;

import javax.swing.*;

public class ClientAppScreenManagerUsageExample {
    public static void main(String[] args) throws InterruptedException {

        ClientAppScreenManager manager = new ClientAppScreenManager();

        manager.register("button", new JButton("Кнопка"));
        manager.register("label", new JLabel("Надпись"));

        JFrame frame = new JFrame("Screen Manager Usage Example");
        frame.setSize(640, 480);
        frame.add(manager.getContentPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        while (true) {

            manager.show("button");
            Thread.sleep(5000);

            manager.show("label");
            Thread.sleep(5000);

        }
    }
}
