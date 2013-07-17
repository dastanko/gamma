package project.gamma.clientapp;

import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.classmap.MappingFileData;
import org.dozer.converters.CustomConverterDescription;
import org.dozer.loader.DozerBuilder;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;
import project.gamma.data.Client;
import project.gamma.data.Gender;
import project.gamma.forms.clientform.ClientFormData;
import project.gamma.forms.clientlistform.ClientListFormDataItem;

@Component
public class DozerTypeConverter extends TypeConverter {
    private DozerBeanMapper mapper;

    public DozerTypeConverter() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Client.class, ClientListFormDataItem.class)
                        .fields(this_(), "tag", customConverter(TagConverter.class))
                        .fields("gender", "gender", customConverter(GenderConverter.class));

                mapping(ClientFormData.class, Client.class)
                        .fields("gender", "gender", customConverter(GenderConverter.class));

                mapping(ClientListFormDataItem.class, ClientFormData.class);
            }
        };

        mapper = new DozerBeanMapper();
        mapper.addMapping(builder);
    }

    public static class GenderConverter implements CustomConverter {

        public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
            if (sourceFieldValue instanceof Gender)
                return genderToString((Gender) sourceFieldValue);
            if (sourceFieldValue instanceof String)
                return parseGender((String) sourceFieldValue);
            return null;
        }
    }

    public static class TagConverter implements CustomConverter {
        public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
            return sourceFieldValue;
        }
    }

    @Override
    public Client convert(ClientFormData data, Class<Client> aClass) {
        return mapper.map(data, aClass);
    }

    @Override
    public Client convert(ClientListFormDataItem data, Class<Client> aClass) {
        return super.convert(data, aClass);
    }

    @Override
    public ClientFormData convert(ClientListFormDataItem item, Class<ClientFormData> aClass) {
        return mapper.map(item, aClass);
    }

    @Override
    public void update(ClientFormData src, Client target) {
        mapper.map(src, target);
    }

    @Override
    public ClientListFormDataItem convert(Client client, Class<ClientListFormDataItem> aClass) {
        return mapper.map(client, aClass);
    }
}
