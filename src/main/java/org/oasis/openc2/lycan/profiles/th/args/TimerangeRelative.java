package org.oasis.openc2.lycan.profiles.th.args;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Periodo relativo del hunt, por ejemplo 3 Days.
 * Ambos campos son obligatorios en el JADN.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimerangeRelative {
    @JsonProperty("number")
    private Long number;

    @JsonProperty("time_unit")
    private TimeUnit timeUnit;

    public TimerangeRelative() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}