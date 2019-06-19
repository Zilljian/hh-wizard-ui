package lab.hhwizardui.ui;

import com.vaadin.componentfactory.Autocomplete;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.annotation.UIScope;
import lab.hhwizardui.model.Spec;
import lab.hhwizardui.model.Univer;
import lab.hhwizardui.service.RestClientAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

@UIScope
@Component
@Route("ui")
@RequiredArgsConstructor
@PWA(name = "HH Wizard UI", shortName = "hh-wizard")
public class UiFrame extends VerticalLayout {

    private final RestClientAdapter restClientAdapter;
    private final Autocomplete specialityField = new Autocomplete(5);
    private final Autocomplete universityField = new Autocomplete(5);

    @PostConstruct
    private void init() {
        specialityField.addChangeListener(event -> {
            String text = event.getValue();
            if(text.length() >= 2) {
                List<Spec> specs = restClientAdapter.exchangeSpecializations(text).getItems();
                specialityField.setOptions(specs.stream().map(Spec::getText).collect(Collectors.toList()));
            }
        });
        specialityField.setLabel("Специальность");

        universityField.addChangeListener(event -> {
            String text = event.getValue();
            if(text.length() >= 2) {
                List<Univer> specs = restClientAdapter.exchangeUniversityTips(text).getItems();
                universityField.setOptions(specs.stream().map(Univer::getAcronym).collect(Collectors.toList()));
            }
        });

        universityField.setLabel("Университет");

        add(specialityField, universityField);
    }

}
