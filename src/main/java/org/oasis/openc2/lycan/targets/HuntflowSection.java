package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Secciones que se pueden solicitar en la respuesta de una consulta de huntflows.
 */
public enum HuntflowSection {
    @JsonProperty("path")
    PATH,

    @JsonProperty("uniqueId")
    UNIQUE_ID,

    @JsonProperty("version")
    VERSION,

    @JsonProperty("args_required")
    ARGS_REQUIRED,

    @JsonProperty("expected_returns")
    EXPECTED_RETURNS,

    @JsonProperty("script")
    SCRIPT
}