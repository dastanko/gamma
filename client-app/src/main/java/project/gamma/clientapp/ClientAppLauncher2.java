package project.gamma.clientapp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientAppLauncher2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.scan(
                "project.gamma.clientapp",
                "project.gamma.clientapp.actions",
                "project.gamma.clientapp.servicedictionary"
        );

        context.refresh();

        ClientApp app = context.getBean(ClientApp.class);
        app.start();
    }
}
