package project.gamma.forms.clientlistform;

public interface ClientListFormActionListener {
    void create();
    void edit(ClientListFormDataItem item);
    void delete(ClientListFormDataItem item);
}
