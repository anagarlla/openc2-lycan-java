package org.oasis.openc2.lycan.profiles.th.targets;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Elige qué información se quiere recibir de la consulta de huntflows.
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