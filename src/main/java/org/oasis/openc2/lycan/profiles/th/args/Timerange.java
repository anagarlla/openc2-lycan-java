package org.oasis.openc2.lycan.profiles.th.args;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Selecciona un periodo absoluto o relativo, nunca ambos. */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Timerange {
    private final TimerangeAbsolute timerangeAbsolute;
    private final TimerangeRelative timerangeRelative;

    @JsonCreator
    public Timerange(
            @JsonProperty("timerange_absolute") TimerangeAbsolute timerangeAbsolute,
            @JsonProperty("timerange_relative") TimerangeRelative timerangeRelative) {
        if (timerangeAbsolute == null && timerangeRelative == null) {
            throw new IllegalArgumentException("Debe indicar un periodo absoluto o relativo.");
        }
        if (timerangeAbsolute != null && timerangeRelative != null) {
            throw new IllegalArgumentException("Solo puede indicar un tipo de periodo.");
        }
        this.timerangeAbsolute = timerangeAbsolute;
        this.timerangeRelative = timerangeRelative;
    }

    @JsonProperty("timerange_absolute")
    public TimerangeAbsolute getTimerangeAbsolute() {
        return timerangeAbsolute;
    }

    @JsonProperty("timerange_relative")
    public TimerangeRelative getTimerangeRelative() {
        return timerangeRelative;
    }
}