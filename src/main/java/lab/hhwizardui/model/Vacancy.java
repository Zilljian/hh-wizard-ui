package lab.hhwizardui.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Vacancy {

    private String id;
    private String description;
    private Map<String, String> address;
    private Map<String, String> salary;
    private String salaryCurrency;
    private Map<String, String> employment;
    private String alternateUrl;
    private String applyAlternateUrl;
    private List<String> keySkills;
}
