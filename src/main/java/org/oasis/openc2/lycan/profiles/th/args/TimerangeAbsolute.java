package org.oasis.openc2.lycan.profiles.th.args;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Periodo absoluto del hunt. Ambos campos son obligatorios en el JADN.
 * Formato esperado: yyyy-MM-ddTHH:mm:ssZ. No son tiempos de ejecucion del comando.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimerangeAbsolute {
    @JsonProperty("hunt_start_time")
    private String huntStartTime;

    @JsonProperty("hunt_stop_time")
    private String huntStopTime;

    public TimerangeAbsolute() {
    }

    public String getHuntStartTime() {
        return huntStartTime;
    }

    public void setHuntStartTime(String huntStartTime) {
        this.huntStartTime = huntStartTime;
    }

    public String getHuntStopTime() {
        return huntStopTime;
    }

    public void setHuntStopTime(String huntStopTime) {
        this.huntStopTime = huntStopTime;
    }
}