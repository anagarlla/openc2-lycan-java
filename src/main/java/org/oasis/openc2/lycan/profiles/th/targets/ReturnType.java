package org.oasis.openc2.lycan.profiles.th.targets;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Sirve para buscar huntflows según el tipo de variable que devuelven.
 * Por ejemplo, si se quiere buscar huntflows que devuelvan un tipo de variable "file", se puede usar esta clase para especificar ese filtro.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnType {
    @JsonProperty("var_name")
    private String varName;

    @JsonProperty("var_type")
    private String varType;

    public ReturnType() {
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }
}