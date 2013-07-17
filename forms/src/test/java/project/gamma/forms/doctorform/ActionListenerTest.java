package project.gamma.forms.doctorform;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;

import javax.swing.*;

public class ActionListenerTest extends UISpecTestCase {
    Mockery context = new JUnit4Mockery();
    DoctorForm form;
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

    public void testActionListener() {
        final DoctorFormActionListener mock = context.mock(DoctorFormActionListener.class);
        form.setActionListener(mock);
        context.checking(new Expectations() {{
            Sequence sequence = context.sequence("sequence");
            oneOf(mock).cancelPressed(); inSequence(sequence);
            oneOf(mock).savePressed(); inSequence(sequence);
        }});
        Window window = getMainWindow();
        window.getButton("cancelButton").click();
        window.getButton("saveButton").click();
        context.assertIsSatisfied();
    }
}
