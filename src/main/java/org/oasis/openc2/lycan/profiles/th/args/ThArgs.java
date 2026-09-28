package org.oasis.openc2.lycan.profiles.th.args;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Argumentos especificos del perfil TH.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThArgs {
    @JsonProperty("huntargs")
    private HuntArgs huntargs;

    public ThArgs() {
    }

    public HuntArgs getHuntargs() {
        return huntargs;
    }

    public void setHuntargs(HuntArgs huntargs) {
        this.huntargs = huntargs;
    }
}