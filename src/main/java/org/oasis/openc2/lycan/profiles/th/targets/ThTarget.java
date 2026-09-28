package org.oasis.openc2.lycan.profiles.th.targets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Objetivo del perfil Threat Hunting.
 * Solo puede contener una de estas opciones: hunt, huntflows o datasources.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ThTarget {
    private final String hunt;
    private final HuntflowSpecifiers huntflows;
    private final String datasources;

    /**
     * Crea un objetivo con una sola opcion.
     * Los otros dos parametros deben ser null.
     */
    // Jackson utiliza este constructor al leer el objeto "th" del JSON.
    @JsonCreator
    public ThTarget(
            @JsonProperty("hunt") String hunt,
            @JsonProperty("huntflows") HuntflowSpecifiers huntflows,
            @JsonProperty("datasources") String datasources) {

        validateTargetSelection(hunt, huntflows, datasources);

        this.hunt = hunt;
        this.huntflows = huntflows;
        this.datasources = datasources;
    }

    /**
     * Comprueba que se haya indicado exactamente una opcion.
     */
    private static void validateTargetSelection(
            String hunt,
            HuntflowSpecifiers huntflows,
            String datasources) {

        int selectedOptions = 0;

        if (hunt != null) {
            selectedOptions = selectedOptions + 1;
        }

        if (huntflows != null) {
            selectedOptions = selectedOptions + 1;
        }

        if (datasources != null) {
            selectedOptions = selectedOptions + 1;
        }

        if (selectedOptions == 0) {
            throw new IllegalArgumentException(
                    "Debe indicar hunt, huntflows o datasources.");
        }

        if (selectedOptions > 1) {
            throw new IllegalArgumentException(
                    "Solo puede indicar una opcion: hunt, huntflows o datasources.");
        }
    }

    /**
     * Crea un target para ejecutar un hunt.
     */
    public static ThTarget hunt(String hunt) {
        return new ThTarget(hunt, null, null);
    }

    /**
     * Crea un target para consultar los huntflows.
     */
    public static ThTarget huntflows(HuntflowSpecifiers huntflows) {
        return new ThTarget(null, huntflows, null);
    }

    /**
     * Crea un target para consultar las fuentes de datos.
     */
    public static ThTarget datasources(String datasources) {
        return new ThTarget(null, null, datasources);
    }

    public String getHunt() {
        return hunt;
    }

    public HuntflowSpecifiers getHuntflows() {
        return huntflows;
    }

    public String getDatasources() {
        return datasources;
    }
}