package org.oasis.openc2.lycan.profiles.th.args;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.oasis.openc2.lycan.OpenC2Message;
import org.oasis.openc2.lycan.args.Args;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.profiles.th.targets.ThTarget;
import org.oasis.openc2.lycan.targets.Target;
import org.oasis.openc2.lycan.types.ActionType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ThArgsTest {

    @Test
    public void writesAndReadsHuntArguments() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Datasource datasource = new Datasource();
        datasource.setDsName("edr");
        datasource.setDsTags(Arrays.asList("endpoint"));

        TimerangeRelative relative = new TimerangeRelative();
        relative.setNumber(3L);
        relative.setTimeUnit(TimeUnit.DAYS);

        TimerangeAbsolute absolute = new TimerangeAbsolute();
        absolute.setHuntStartTime("2026-09-01T00:00:00Z");
        absolute.setHuntStopTime("2026-09-02T00:00:00Z");

        LanguageSpecTypes nativeData = new LanguageSpecTypes();
        nativeData.setEmailAddress("user@example.com");
        nativeData.setIpv4Network("192.0.2.0/24");
        nativeData.setMacAddress("00:11:22:33:44:55");
        nativeData.setPort(443);

        Map<String, String> typedArgs = new HashMap<String, String>();
        typedArgs.put("processes", "process");

        HuntArgs huntargs = new HuntArgs();
        huntargs.setStringArgs(Arrays.asList("example"));
        huntargs.setIntegerArgs(Arrays.asList(5000000000L));
        huntargs.setTypedArgs(typedArgs);
        huntargs.setNativeOc2(Arrays.asList(nativeData));
        // Solo comprobamos que se conserva el JSON externo, no su validez STIX/OCA.
        huntargs.setStix(mapper.readTree("{\"example\":\"sco\"}"));
        huntargs.setStixExtensions(mapper.readTree("{\"example\":\"oca\"}"));
        huntargs.setTimeranges(Arrays.asList(
                new Timerange(null, relative), new Timerange(absolute, null)));
        huntargs.setDatasources(Arrays.asList(datasource));

        ThArgs th = new ThArgs();
        th.setHuntargs(huntargs);

        Args args = new Args();
        args.setTh(th);
        args.setStartTime(1000L);

        Target target = new Target();
        target.setTh(ThTarget.hunt("buscar_proceso"));
        OpenC2Message message = new OpenC2Message(ActionType.INVESTIGATE, target, args);

        String expectedHuntargs = "{"
                + "\"string_args\":[\"example\"],"
                + "\"integer_args\":[5000000000],"
                + "\"typed_args\":{\"processes\":\"process\"},"
                + "\"native_oc2\":[{\"email-address\":\"user@example.com\","
                + "\"ipv4_network\":\"192.0.2.0/24\","
                + "\"mac_address\":\"00:11:22:33:44:55\",\"port\":443}],"
                + "\"stix\":{\"example\":\"sco\"},"
                + "\"stix_extensions\":{\"example\":\"oca\"},"
                + "\"timeranges\":[{\"timerange_relative\":{\"number\":3,\"time_unit\":\"Days\"}},"
                + "{\"timerange_absolute\":{\"hunt_start_time\":\"2026-09-01T00:00:00Z\","
                + "\"hunt_stop_time\":\"2026-09-02T00:00:00Z\"}}],"
                + "\"datasources\":[{\"ds_name\":\"edr\",\"ds_tags\":[\"endpoint\"]}]}";

        String json = JsonFormatter.getJson(message, false);
        JsonNode content = mapper.readTree(json);
        assertEquals(mapper.readTree(expectedHuntargs), content.path("args").path("th").path("huntargs"));
        assertEquals(1000L, content.path("args").path("start_time").asLong());
        assertEquals("complete", content.path("args").path("response_requested").asText());

        OpenC2Message restored = JsonFormatter.readOpenC2Message(json);
        HuntArgs restoredArgs = restored.getArgs().getTh().getHuntargs();
        assertEquals(Long.valueOf(5000000000L), restoredArgs.getIntegerArgs().get(0));
        assertEquals(TimeUnit.DAYS, restoredArgs.getTimeranges().get(0).getTimerangeRelative().getTimeUnit());
        assertEquals("edr", restoredArgs.getDatasources().get(0).getDsName());
        assertEquals(content, mapper.readTree(JsonFormatter.getJson(restored, false)));
    }

    @Test
    public void omitsUnspecifiedArguments() throws Exception {
        HuntArgs huntargs = new HuntArgs();
        huntargs.setStringArgs(Arrays.asList("example"));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(huntargs);
        assertEquals(mapper.readTree("{\"string_args\":[\"example\"]}"), mapper.readTree(json));

        OpenC2Message message = new OpenC2Message(ActionType.QUERY);
        message.setArgs(new Args());
        JsonNode content = mapper.readTree(JsonFormatter.getJson(message, false));
        assertFalse(content.path("args").has("th"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsTwoTimerangeAlternatives() {
        new Timerange(new TimerangeAbsolute(), new TimerangeRelative());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsMissingTimerangeAlternative() {
        new Timerange(null, null);
    }

    @Test(expected = IOException.class)
    public void rejectsTwoTimerangeAlternativesInJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue("{\"timerange_absolute\":{},\"timerange_relative\":{}}", Timerange.class);
    }

    @Test
    public void preservesTimeUnitNames() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(TimeUnit.values());
        assertEquals("[\"Days\",\"Hours\",\"Minutes\",\"Seconds\"]", json);
        TimeUnit[] units = mapper.readValue(json, TimeUnit[].class);
        assertEquals(TimeUnit.MINUTES, units[2]);
    }
}