package project.gamma.forms.doctorform;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueModel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorForm {
    private JPanel contentPanel;
    private JTextField fioField;
    private JComboBox titleField;
    private JComboBox specialityField;
    private JTextArea workingHoursField;
    private JButton saveButton;
    private JButton cancelButton;

    DoctorFormActionListener actionListener;

    public DoctorForm() {
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.savePressed();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.cancelPressed();
            }
        });
    }

    public void setActionListener(DoctorFormActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    DoctorFormData data;
    PresentationModel<DoctorFormData> dataModel;

    public void setData(DoctorFormData data) {
        this.data = data;
        dataModel.setBean(data);
    }

    String[] titleList = new String[]{"Мед. сестра",
            "Лаборант",
            "Врач",
            "Врач высшей категории"};

    String[] specialityList = new String[]{"стоматолог",
            "отоларинголог",
            "хирург",
            "терапевт",
            "нарколог",
            "гомеопат",
            "зубной техник",
            "ортопед",
            "педиатор"
    };

    private void createUIComponents() {
        data = new DoctorFormData();
        dataModel = new PresentationModel<DoctorFormData>(data);

        fioField = BasicComponentFactory.createTextField(dataModel.getModel("name"), false);

        titleField = bindEditableComboBox(titleList, dataModel.getModel("title"));

        specialityField = bindEditableComboBox(specialityList, dataModel.getModel("speciality"));

        workingHoursField = BasicComponentFactory.createTextArea(dataModel.getModel("workingHours"));
    }

    private JComboBox bindEditableComboBox(String[] defaultValues, ValueModel valueModel) {
        JComboBox comboBox = new JComboBox(defaultValues);

        BasicComboBoxEditor editor = new BasicComboBoxEditor();
        JTextField textField = (JTextField) editor.getEditorComponent();

        Bindings.bind(textField, valueModel);
        comboBox.setEditor(editor);
        comboBox.setEditable(true);
        return comboBox;
    }

    public DoctorFormData getData() {
        return data;
    }
}
