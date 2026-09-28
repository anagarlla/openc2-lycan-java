package org.oasis.openc2.lycan.profiles.th.targets;

import org.oasis.openc2.lycan.targets.Target;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import org.oasis.openc2.lycan.OpenC2Message;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.types.ActionType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HuntflowSpecifiersTest {

    @Test
    public void writesAndReadsAllFilters() throws Exception {
        ReturnType formatTypes = new ReturnType();
        formatTypes.setVarName("processes");
        formatTypes.setVarType("process");

        HuntflowSpecifiers filters = new HuntflowSpecifiers();
        filters.setPath("/hunts");
        filters.setTags(Arrays.asList("windows"));
        filters.setArgTypes(Arrays.asList("process"));
        filters.setArgNames(Arrays.asList("name"));
        filters.setFormatTypes(formatTypes);
        filters.setReturnFormat(Arrays.asList(HuntflowSection.values()));

        Target target = new Target();
        target.setTh(ThTarget.huntflows(filters));
        OpenC2Message message = new OpenC2Message(ActionType.QUERY, target);

        String expectedJson = "{\"action\":\"query\",\"target\":{\"th\":{\"huntflows\":{"
                + "\"path\":\"/hunts\",\"tags\":[\"windows\"],"
                + "\"arg_types\":[\"process\"],\"arg_names\":[\"name\"],"
                + "\"format_types\":{\"var_name\":\"processes\",\"var_type\":\"process\"},"
                + "\"return_format\":[\"path\",\"uniqueId\",\"version\","
                + "\"args_required\",\"expected_returns\",\"script\"]}}}}";

        String generatedJson = JsonFormatter.getJson(message, false);
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(expectedJson), mapper.readTree(generatedJson));

        OpenC2Message restoredMessage = JsonFormatter.readOpenC2Message(expectedJson);
        HuntflowSpecifiers restored = restoredMessage.getTarget().getTh().getHuntflows();
        assertEquals("/hunts", restored.getPath());
        assertEquals(filters.getTags(), restored.getTags());
        assertEquals(filters.getArgTypes(), restored.getArgTypes());
        assertEquals(filters.getArgNames(), restored.getArgNames());
        assertEquals("processes", restored.getFormatTypes().getVarName());
        assertEquals("process", restored.getFormatTypes().getVarType());
        assertEquals(filters.getReturnFormat(), restored.getReturnFormat());

        String restoredJson = JsonFormatter.getJson(restoredMessage, false);
        assertEquals(mapper.readTree(expectedJson), mapper.readTree(restoredJson));
    }

    @Test
    public void omitsAbsentFiltersAndPreservesEmptyReturnFormat() throws Exception {
        HuntflowSpecifiers filters = new HuntflowSpecifiers();
        filters.setReturnFormat(Collections.emptyList());

        ObjectMapper mapper = new ObjectMapper();
        String generatedJson = mapper.writeValueAsString(filters);
        assertEquals(mapper.readTree("{\"return_format\":[]}"), mapper.readTree(generatedJson));
    }

    @Test(expected = IOException.class)
    public void rejectsUnknownReturnSection() throws Exception {
        String json = "{\"action\":\"query\",\"target\":{\"th\":{\"huntflows\":"
                + "{\"return_format\":[\"unknown\"]}}}}";
        JsonFormatter.readOpenC2Message(json);
    }
}