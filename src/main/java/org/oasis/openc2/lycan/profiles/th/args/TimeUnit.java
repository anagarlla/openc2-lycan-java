package org.oasis.openc2.lycan.profiles.th.args;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Unidades de tiempo admitidas por el perfil TH. */
public enum TimeUnit {
    @JsonProperty("Days")
    DAYS,
    @JsonProperty("Hours")
    HOURS,
    @JsonProperty("Minutes")
    MINUTES,
    @JsonProperty("Seconds")
    SECONDS
}