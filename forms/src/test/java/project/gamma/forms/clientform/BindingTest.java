package project.gamma.forms.clientform;

import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;

import javax.swing.*;

public class BindingTest extends UISpecTestCase {
    ClientForm form;

    @Override
    protected void setUp() throws Exception {
        form = new ClientForm();
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
        String expectedAddress = "address " + Math.random();
        int expectedYear = (int) (1850 + Math.random() * 150);
        String expectedGender = Math.random() > 0.5 ? "М" : "Ж";

        ClientFormData data = new ClientFormData();

        data.setName(expectedName);
        data.setAddress(expectedAddress);
        data.setYear(expectedYear);
        data.setGender(expectedGender);

        form.setData(data);

        getMainWindow().getTextBox("nameField").textEquals(expectedName);
        getMainWindow().getSpinner("yearField").valueEquals(expectedYear);
        if ("М".equals(expectedGender)) {
            not(getMainWindow().getRadioButton("femaleRadioButton").isSelected()).check();
            getMainWindow().getRadioButton("maleRadioButton").isSelected().check();
        } else {
            getMainWindow().getRadioButton("femaleRadioButton").isSelected().check();
            not(getMainWindow().getRadioButton("maleRadioButton").isSelected()).check();
        }
        getMainWindow().getTextBox("addressField").textEquals(expectedAddress);
    }

    public void testReverseBinding() {
        String expectedName = "name " + Math.random();
        String expectedAddress = "address " + Math.random();
        int expectedYear = (int) (1850 + Math.random() * 150);
        String expectedGender = Math.random() > 0.5 ? "М" : "Ж";

        getMainWindow().getTextBox("nameField").setText(expectedName);
        getMainWindow().getSpinner("yearField").setValue(expectedYear);
        if ("М".equals(expectedGender)) {
            getMainWindow().getRadioButton("maleRadioButton").click();
        } else {
            getMainWindow().getRadioButton("femaleRadioButton").click();
        }
        getMainWindow().getTextBox("addressField").setText(expectedAddress);

        ClientFormData data = form.getData();

        assertEquals(expectedName, data.getName());
        assertEquals(expectedAddress, data.getAddress());
        assertEquals(expectedGender, data.getGender());
        assertEquals(expectedYear, data.getYear());


    }
}
