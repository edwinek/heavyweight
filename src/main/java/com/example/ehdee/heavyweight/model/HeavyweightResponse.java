package com.example.ehdee.heavyweight.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HeavyweightResponse extends BaseModel {

    private String error;
    private List<Reign> reigns;

    public HeavyweightResponse(Builder builder) {
        this.error = builder.error;
        this.reigns = builder.reigns;
    }

    public static class Builder{

        private String error;
        private List<Reign> reigns = new ArrayList<>();

        public Builder withError(String error){
            this.error = error;
            return this;
        }

        public Builder withReigns(List<Reign> reigns) {
            this.reigns = reigns;
            return this;
        }

        public Builder withReign(Reign reign) {
            this.reigns.add(reign);
            return this;
        }

        public HeavyweightResponse build() {
            return new HeavyweightResponse(this);
        }
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Reign> getReigns() {
        return reigns;
    }

    public void setReigns(List<Reign> reigns) {
        this.reigns = reigns;
    }
}
