package org.oasis.openc2.lycan.profiles.th.args;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Parametros de un hunt. typed_args relaciona nombres con tipos.
 * STIX y OCA se conservan como JSON; sus esquemas externos no se validan aqui.
 * La validacion completa de obligatoriedad y cardinalidades queda pendiente.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HuntArgs {
    @JsonProperty("string_args")
    private List<String> stringArgs;

    @JsonProperty("integer_args")
    private List<Long> integerArgs;

    @JsonProperty("typed_args")
    private Map<String, String> typedArgs;

    @JsonProperty("native_oc2")
    private List<LanguageSpecTypes> nativeOc2;

    @JsonProperty("stix")
    private JsonNode stix;

    @JsonProperty("stix_extensions")
    private JsonNode stixExtensions;

    @JsonProperty("timeranges")
    private List<Timerange> timeranges;

    @JsonProperty("datasources")
    private List<Datasource> datasources;

    public HuntArgs() {
    }

    public List<String> getStringArgs() {
        return stringArgs;
    }

    public void setStringArgs(List<String> stringArgs) {
        this.stringArgs = stringArgs;
    }

    public List<Long> getIntegerArgs() {
        return integerArgs;
    }

    public void setIntegerArgs(List<Long> integerArgs) {
        this.integerArgs = integerArgs;
    }

    public Map<String, String> getTypedArgs() {
        return typedArgs;
    }

    public void setTypedArgs(Map<String, String> typedArgs) {
        this.typedArgs = typedArgs;
    }

    public List<LanguageSpecTypes> getNativeOc2() {
        return nativeOc2;
    }

    public void setNativeOc2(List<LanguageSpecTypes> nativeOc2) {
        this.nativeOc2 = nativeOc2;
    }

    public JsonNode getStix() {
        return stix;
    }

    public void setStix(JsonNode stix) {
        this.stix = stix;
    }

    public JsonNode getStixExtensions() {
        return stixExtensions;
    }

    public void setStixExtensions(JsonNode stixExtensions) {
        this.stixExtensions = stixExtensions;
    }

    public List<Timerange> getTimeranges() {
        return timeranges;
    }

    public void setTimeranges(List<Timerange> timeranges) {
        this.timeranges = timeranges;
    }

    public List<Datasource> getDatasources() {
        return datasources;
    }

    public void setDatasources(List<Datasource> datasources) {
        this.datasources = datasources;
    }
}