package project.gamma.forms.clientform;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;

import javax.swing.*;

public class ActionListenerTest extends UISpecTestCase{
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

    Mockery context = new JUnit4Mockery();

    public void testActions() {
        final ClientFormActionListener mock = context.mock(ClientFormActionListener.class);
        form.setActionListener(mock);
        context.checking(new Expectations(){{
            Sequence sequence = context.sequence("sequence");
            oneOf(mock).cancel(); inSequence(sequence);
            oneOf(mock).save(); inSequence(sequence);
        }});
        getMainWindow().getButton("cancelButton").click();
        getMainWindow().getButton("saveButton").click();
        context.assertIsSatisfied();
    }
}
