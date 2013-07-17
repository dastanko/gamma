package project.gamma.forms.doctorlist;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 03.03.11
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 */
public interface DoctorListFormActionListener {
        void edit(DoctorListFormDataItem item);
    void  newItem();
    void deleteItem(DoctorListFormDataItem item);
}
