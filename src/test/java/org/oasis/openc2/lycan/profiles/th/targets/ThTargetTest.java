package org.oasis.openc2.lycan.profiles.th.targets;

import org.oasis.openc2.lycan.targets.Target;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.oasis.openc2.lycan.OpenC2Message;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.types.ActionType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ThTargetTest {

    /**
     * Comprueba el JSON generado y que pueda leerse de nuevo sin perder datos.
     */
    private void checkJson(ThTarget thTarget, ActionType action, String expectedJson)
            throws Exception {

        Target target = new Target();
        target.setTh(thTarget);

        OpenC2Message message = new OpenC2Message(action, target);
        String generatedJson = JsonFormatter.getJson(message, false);

        // Comparamos el contenido sin depender del orden de las propiedades.
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedContent = mapper.readTree(expectedJson);
        JsonNode generatedContent = mapper.readTree(generatedJson);
        assertEquals(expectedContent, generatedContent);

        OpenC2Message restoredMessage = JsonFormatter.readOpenC2Message(generatedJson);
        Target restoredTarget = restoredMessage.getTarget();
        assertNotNull(restoredTarget.getTh());

        String restoredJson = JsonFormatter.getJson(restoredMessage, false);
        JsonNode restoredContent = mapper.readTree(restoredJson);
        assertEquals(expectedContent, restoredContent);
    }

    @Test
    public void huntIsNestedString() throws Exception {
        ThTarget thTarget = ThTarget.hunt("buscar_proceso");
        String expectedJson =
                "{\"action\":\"investigate\",\"target\":{\"th\":{\"hunt\":\"buscar_proceso\"}}}";

        checkJson(thTarget, ActionType.INVESTIGATE, expectedJson);
    }

    @Test
    public void datasourcesIsNestedString() throws Exception {
        ThTarget thTarget = ThTarget.datasources("edr");
        String expectedJson =
                "{\"action\":\"query\",\"target\":{\"th\":{\"datasources\":\"edr\"}}}";

        checkJson(thTarget, ActionType.QUERY, expectedJson);
    }

    @Test
    public void emptyHuntflowSpecifiersArePreserved() throws Exception {
        HuntflowSpecifiers specifiers = new HuntflowSpecifiers();
        ThTarget thTarget = ThTarget.huntflows(specifiers);
        String expectedJson =
                "{\"action\":\"query\",\"target\":{\"th\":{\"huntflows\":{}}}}";

        checkJson(thTarget, ActionType.QUERY, expectedJson);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsTargetWithoutAnOption() {
        new ThTarget(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsHuntWithDatasources() {
        new ThTarget("hunt", null, "edr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsHuntWithHuntflows() {
        HuntflowSpecifiers specifiers = new HuntflowSpecifiers();
        new ThTarget("hunt", specifiers, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsHuntflowsWithDatasources() {
        HuntflowSpecifiers specifiers = new HuntflowSpecifiers();
        new ThTarget(null, specifiers, "edr");
    }

    private void checkInvalidJson(String thJson) {
        String messageJson =
                "{\"action\":\"query\",\"target\":{\"th\":" + thJson + "}}";

        try {
            JsonFormatter.readOpenC2Message(messageJson);
            fail("La lectura deberia rechazar el target: " + thJson);
        } catch (IOException exception) {
            // La lectura ha rechazado el target, como esperabamos.
        }
    }

    @Test
    public void jsonRejectsZeroOrMultipleAlternatives() {
        checkInvalidJson("{}");
        checkInvalidJson("{\"hunt\":null}");
        checkInvalidJson("{\"hunt\":\"h\",\"datasources\":\"d\"}");
        checkInvalidJson("{\"huntflows\":{},\"hunt\":\"h\"}");
        checkInvalidJson("{\"datasources\":\"d\",\"huntflows\":{}}");
    }
}