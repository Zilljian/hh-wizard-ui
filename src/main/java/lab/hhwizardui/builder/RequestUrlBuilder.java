package lab.hhwizardui.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@ToString
@AllArgsConstructor
public class RequestUrlBuilder {

    private final String url;
    private final HttpEntity entity;

    public static Builder builder() {
        return new Builder();
    }

    @Setter
    @Getter
    @Accessors(chain = true)
    public static class Builder {

        private String baseUrl;
        private MultiValueMap<String, String> parameters;
        private String header;
        private String text;
        private boolean isCompliter;

        public RequestUrlBuilder build() {
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", header);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(parameters, headers);

            UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl);

            if (isCompliter) {
                componentsBuilder.queryParam("text", text);
                parameters = new LinkedMultiValueMap<>();
                parameters.add("text", text);
            } else {
                parameters.forEach(componentsBuilder::queryParam);
            }

            return new RequestUrlBuilder(componentsBuilder.build().toUriString(), entity);
        }
    }
}
