package org.oasis.openc2.lycan.profiles.th.targets;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Filtros opcionales para consultar huntflows y las opciones sobre qué información recibir.
 * 
 * Por ejemplo:
*       HuntflowSpecifiers filters = new HuntflowSpecifiers();
*       filters.setTags(Arrays.asList("windows"));
*
*       ThTarget th = ThTarget.huntflows(filters);
*
*  Esto consulta huntflows con la etiqueta "windows". 
*  Si no se rellena ningún campo, la consulta no lleva filtros.
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HuntflowSpecifiers {

    private String path;
    private List<String> tags;

    @JsonProperty("arg_types")
    private List<String> argTypes;

    @JsonProperty("arg_names")
    private List<String> argNames;

    @JsonProperty("format_types")
    private ReturnType formatTypes;

    @JsonProperty("return_format")
    private List<HuntflowSection> returnFormat;

    public HuntflowSpecifiers() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getArgTypes() {
        return argTypes;
    }

    public void setArgTypes(List<String> argTypes) {
        this.argTypes = argTypes;
    }

    public List<String> getArgNames() {
        return argNames;
    }

    public void setArgNames(List<String> argNames) {
        this.argNames = argNames;
    }

    public ReturnType getFormatTypes() {
        return formatTypes;
    }

    public void setFormatTypes(ReturnType formatTypes) {
        this.formatTypes = formatTypes;
    }

    public List<HuntflowSection> getReturnFormat() {
        return returnFormat;
    }

    public void setReturnFormat(List<HuntflowSection> returnFormat) {
        this.returnFormat = returnFormat;
    }
}