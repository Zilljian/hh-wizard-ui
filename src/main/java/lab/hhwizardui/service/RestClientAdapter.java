package lab.hhwizardui.service;

import lab.hhwizardui.builder.RequestUrlBuilder;
import lab.hhwizardui.dto.ConnectionProperties;
import lab.hhwizardui.model.Specializations;
import lab.hhwizardui.model.Universities;
import lab.hhwizardui.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class RestClientAdapter {

    private final RestClient restClient;
    private final ConnectionProperties connectionProperties;

    public List<Vacancy> exchangeVacancies(MultiValueMap<String, String> parameters) {
        RequestUrlBuilder builder = RequestUrlBuilder.builder()
            .setBaseUrl(connectionProperties.getUrl().concat("/vacancies"))
            .setParameters(parameters)
            .setHeader(connectionProperties.getHeader())
            .build();
        ResponseEntity<List<Vacancy>> response = restClient.exchangeVacancies(builder.getEntity(), builder.getUrl());
        return ofNullable(response.getBody()).orElseThrow(IllegalStateException::new);
    }

    public Universities exchangeUniversityTips(String text) {
        RequestUrlBuilder builder = RequestUrlBuilder.builder()
            .setBaseUrl(connectionProperties.getUrl().concat("/suggests/educational_institutions"))
            .setCompliter(true)
            .setText(text)
            .setHeader(connectionProperties.getHeader())
            .build();
        ResponseEntity<Universities> response = restClient.exchangeUniversityTips(builder.getEntity(), builder.getUrl());
        return ofNullable(response.getBody()).orElseThrow(IllegalStateException::new);
    }

    public Specializations exchangeSpecializations(String text) {
        RequestUrlBuilder builder = RequestUrlBuilder.builder()
            .setBaseUrl(connectionProperties.getUrl().concat("/suggests/fields_of_study"))
            .setCompliter(true)
            .setText(text)
            .setHeader(connectionProperties.getHeader())
            .build();
        ResponseEntity<Specializations> response = restClient.exchangeSpecializations(builder.getEntity(), builder.getUrl());
        return ofNullable(response.getBody()).orElseThrow(IllegalStateException::new);
    }
}
