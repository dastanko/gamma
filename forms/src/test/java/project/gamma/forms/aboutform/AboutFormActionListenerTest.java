package project.gamma.forms.aboutform;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;

import javax.swing.*;

public class AboutFormActionListenerTest extends UISpecTestCase {

    Mockery context = new JUnit4Mockery();

    AboutForm form;
    @Override
    protected void setUp() throws Exception {
        form = new AboutForm();
        setAdapter(new UISpecAdapter() {
            public Window getMainWindow() {
                JFrame frame = new JFrame();
                frame.add(form.getContentPanel());
                return new Window(frame);
            }
        });
    }

    public void testCloseButton() {
        final AboutFormActionListener listener = context.mock(AboutFormActionListener.class);
        context.checking(new Expectations(){{
            one(listener).closePressed();
        }});
        form.setActionListener(listener);
        Window window = getMainWindow();
        window.getButton("closeButton").click();
        context.assertIsSatisfied();
    }
}
