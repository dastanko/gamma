package project.gamma.forms.clientform;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.message.SimpleValidationMessage;
import com.jgoodies.validation.util.ValidationUtils;

public class ClientFormDataValidator implements Validator<ClientFormData>{

    public ValidationResult validate(ClientFormData data) {
        ValidationResult result = new ValidationResult();

        if (ValidationUtils.isEmpty(data.getName()))
            result.add(new SimpleValidationMessage("ФИО - обязательное поле"));

        if (data.getYear() < 1850)
            result.add(new SimpleValidationMessage("Год рождения - Значение должно быть более 1850"));

        if (ValidationUtils.isEmpty(data.getGender()))
            result.add(new SimpleValidationMessage("Пол - обязательное поле"));

        return result;
    }
}
