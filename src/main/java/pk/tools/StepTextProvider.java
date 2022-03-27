package pk.tools;

import com.epam.jdi.tools.pairs.Pair;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pk.tools.utils.PropertyLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StepTextProvider {

    private static StepTextYmlObject stepTextYmlObject;
    private static final String localization = PropertyLoader.loadSystemPropertyOrDefault("localization", "english");
    private static final int stepDetailingLevel = Integer
            .parseInt(PropertyLoader.loadSystemPropertyOrDefault("stepDetalizationLevel", "2"));

    static {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try {
            stepTextYmlObject = mapper.readValue(new File("src/main/resources/allurium-steps.yml"),
                    StepTextYmlObject.class);
            System.out.println(stepTextYmlObject.english.size() + ">>>");
        } catch (IOException e) {
            log.warn("failed during parsing \"src/main/resources/allurium-steps.yml\"");
            e.printStackTrace();
        }
    }

    public static String getStepTextTemplate(String stepName) {
        return stepTextYmlObject.getLocalizedSteps(localization).get(stepDetailingLevel).get(stepName);
    }

    private static String getStepTextTemplate(String stepName, String localization, int stepDetailingLevel) {
        return stepTextYmlObject.getLocalizedSteps(localization).get(stepName).get(stepDetailingLevel);
    }


    public static String getStepText(String stepName, Pair<String,String>... patterns) {
        String name = "";
        String elementType = "";
        String parent = "";

        for (Pair<String, String> pattern : patterns) {
            if (pattern.key.equals("{name}") && !pattern.value.equals("")) name = pattern.value;
            if (pattern.key.equals("{element}") && !pattern.value.equals("")) elementType = pattern.value;
            if (pattern.key.equals("{parent}") && !pattern.value.equals("")) parent = pattern.value;
        }

        String pattern = "";
        if (!name.equals("") && !parent.equals("")) {
            pattern = getStepTextTemplate(stepName, localization, 2);
        } else {
            pattern = getStepTextTemplate(stepName, localization, 1);
        }

        if (elementType.equals(""))
            pattern = pattern.replace("{element}", StepsLocalizedPattern.element.getLocalizedValue(localization));

        for (Pair<String, String> namesPattern : patterns) {
            pattern = pattern.replace(namesPattern.key, namesPattern.value);
        }

        return pattern;
    }


    public static String getStepText(String stepName, List<Pair<String,String>> patternValues) {
        Pair<String,String>[] arrayOfPatterns = new Pair[patternValues.size()];
        return getStepText(stepName, patternValues.toArray(arrayOfPatterns));
    }
}
