package lab.hhwizardui.ui.component;

import com.vaadin.componentfactory.Autocomplete;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import javax.annotation.PostConstruct;

import static java.util.Objects.isNull;

@Getter
@UIScope
//@Scope("prototype")
@Component
@RequiredArgsConstructor
public class TextFieldComponent {

    private final ComboBox<String> filterText = new ComboBox<>();
    private final Autocomplete autocomplete = new Autocomplete(5);

    public void setListenerAction(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<?, String>> listener) {
        filterText.addValueChangeListener(listener);
    }

    public String getValue() {
        return isNull(filterText.getValue()) ? "" : filterText.getValue();
    }

    public void setPlaceholder(String placeholder) {
        filterText.setPlaceholder(placeholder);
    }

    public void setItems(Collection<String> items) {
        filterText.setItems(items);
    }

    @PostConstruct
    private void textField() {
        filterText.setPlaceholder("Application name");
        filterText.setWidth("20vw");
    }
}
