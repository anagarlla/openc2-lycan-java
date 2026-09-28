package org.oasis.openc2.lycan.profiles.th.args;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Fuente de datos utilizada por un hunt. ds_name es obligatorio en el JADN.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Datasource {
    @JsonProperty("ds_name")
    private String dsName;

    @JsonProperty("ds_tags")
    private List<String> dsTags;

    public Datasource() {
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public List<String> getDsTags() {
        return dsTags;
    }

    public void setDsTags(List<String> dsTags) {
        this.dsTags = dsTags;
    }
}