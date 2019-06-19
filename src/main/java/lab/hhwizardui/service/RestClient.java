package lab.hhwizardui.service;

import lab.hhwizardui.model.Specializations;
import lab.hhwizardui.model.Universities;
import lab.hhwizardui.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestClient {

    private final RestTemplate restTemplate;

    ResponseEntity<List<Vacancy>> exchangeVacancies(HttpEntity<MultiValueMap<String, String>> entity, String url) {
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<List<Vacancy>>() {});
    }

    ResponseEntity<Universities> exchangeUniversityTips(HttpEntity<MultiValueMap<String, String>> entity, String url) {
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Universities>() {});
    }

    ResponseEntity<Specializations> exchangeSpecializations(HttpEntity<MultiValueMap<String, String>> entity, String url) {
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Specializations>() {});
    }
}
