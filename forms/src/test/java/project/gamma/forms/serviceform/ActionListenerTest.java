package project.gamma.forms.serviceform;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;

import javax.swing.*;

public class ActionListenerTest extends UISpecTestCase {
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

    Mockery context = new JUnit4Mockery();

    public void testActionListener() {
        final ServiceFormActionListener mock = context.mock(ServiceFormActionListener.class);
        form.setActionListener(mock);
        context.checking(new Expectations() {{
            Sequence sequence = context.sequence("sequence");
            one(mock).cancelPressed(); inSequence(sequence);
            one(mock).savePressed(); inSequence(sequence);
        }});
        getMainWindow().getButton("cancelButton").click();
        getMainWindow().getButton("saveRecordButton").click();
        context.assertIsSatisfied();
    }
}
