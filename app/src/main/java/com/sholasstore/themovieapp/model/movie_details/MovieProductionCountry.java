package com.sholasstore.themovieapp.model.movie_details;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class MovieProductionCountry {


    @SerializedName("iso_3166_1")
    private String iso31661;
    @SerializedName("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("iso_3166_1")
    public String getIso31661() {
        return iso31661;
    }

    @JsonProperty("iso_3166_1")
    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
