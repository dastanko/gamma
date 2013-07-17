package project.gamma.forms.serviceform;

import org.uispec4j.TextBox;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;

import javax.swing.*;

public class BindingTest extends UISpecTestCase {
    ServiceForm form;

    @Override
    protected void setUp() throws Exception {
        form = new ServiceForm();
        setAdapter(new UISpecAdapter() {
            public Window getMainWindow() {
                JFrame frame = new JFrame();
                frame.add(form.getContentPanel());
                return new Window(frame);
            }
        });
    }

    public void testForwardBinding() {
        String expectedName = "name " + Math.random();
        int expectedPrice = (int) (Math.random() * 100);
        int expectedDuration = (int) (Math.random() * 100);

        ServiceFormData data = new ServiceFormData();
        data.setName(expectedName);
        data.setPrice(expectedPrice);
        data.setDuration(expectedDuration);

        form.setData(data);

        Window window = getMainWindow();
        window.getTextBox("nameField").textEquals(expectedName).check();
        window.getTextBox("priceField").textEquals(String.valueOf(expectedPrice)).check();
        window.getTextBox("durationField").textEquals(String.valueOf(expectedDuration)).check();
    }

    public void testReverseBinding() {
        String expectedName = "name " + Math.random();
        int expectedPrice = (int) (Math.random() * 100);
        int expectedDuration = (int) (Math.random() * 100);

        Window window = getMainWindow();
        TextBox nameField = window.getTextBox("nameField");
        TextBox priceField = window.getTextBox("priceField");
        TextBox durationField = window.getTextBox("durationField");

        nameField.setText(expectedName);
        nameField.focusLost();

        priceField.setText(String.valueOf(expectedPrice), true);
        priceField.focusLost();

        durationField.setText(String.valueOf(expectedDuration), true);
        durationField.focusLost();

        ServiceFormData data = form.getData();
        assertEquals(expectedName, data.getName());
        assertEquals(expectedPrice, data.getPrice());
        assertEquals(expectedDuration, data.getDuration());

    }
}
