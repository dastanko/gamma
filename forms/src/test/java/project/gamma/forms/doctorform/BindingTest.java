package project.gamma.forms.doctorform;

import com.sun.org.apache.xpath.internal.axes.WalkingIterator;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;

import javax.swing.*;

public class BindingTest extends UISpecTestCase {
    private DoctorForm form;

    @Override
    protected void setUp() throws Exception {
        form = new DoctorForm();
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
        String expectedTitle = "Врач";
        String expectedSpeciality = "нарколог";
        String expectedWorkingHours = "working hours " + Math.random();

        DoctorFormData data = new DoctorFormData();
        data.setName(expectedName);
        data.setSpeciality(expectedSpeciality);
        data.setTitle(expectedTitle);
        data.setWorkingHours(expectedWorkingHours);
        form.setData(data);

        Window window = getMainWindow();

        window.getTextBox("fioField").textEquals(expectedName).check();
        window.getTextBox("workingHoursField").textEquals(expectedWorkingHours).check();

        window.getComboBox("titleField").selectionEquals(expectedTitle).check();
        window.getComboBox("specialityField").selectionEquals(expectedSpeciality).check();
    }

    public void testReverseBinding() {
        String expectedName = "name " + Math.random();
        String expectedTitle = "Врач";
        String expectedSpeciality = "нарколог";
        String expectedWorkingHours = "working hours " + Math.random();

        Window window = getMainWindow();

        window.getTextBox("fioField").setText(expectedName);
        window.getTextBox("workingHoursField").setText(expectedWorkingHours);
        window.getTextBox("workingHoursField").focusLost();

        window.getComboBox("titleField").select(expectedTitle);
        window.getComboBox("specialityField").select(expectedSpeciality);

        DoctorFormData data = form.getData();
        assertEquals(expectedName, data.getName());
        assertEquals(expectedTitle, data.getTitle());
        assertEquals(expectedSpeciality, data.getSpeciality());
        assertEquals(expectedWorkingHours, data.getWorkingHours());
    }

}
