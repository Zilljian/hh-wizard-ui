package lab.hhwizardui.builder;

import lab.hhwizardui.dto.ConnectionProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class RequestVacanciesBuilder {

    private final ConnectionProperties connectionProperties;

    public HttpEntity<MultiValueMap<String, String>> build(MultiValueMap<String, String> parameters) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", connectionProperties.getHeader());
        return new HttpEntity<>(parameters, headers);
    }
}
