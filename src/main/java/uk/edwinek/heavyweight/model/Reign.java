package uk.edwinek.heavyweight.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Reign extends BaseModel {

    private String champion;
    @Id
    private String id;
    private String nationality;
    private String recognition;
    private LocalDate reignBegan;
    private LocalDate reignEnded;

    public Reign() {
    }

    public Reign(String champion, String id, String nationality, String recognition, LocalDate reignBegan,
                 LocalDate reignEnded) {
        this.champion = champion;
        this.id = id;
        this.nationality = nationality;
        this.recognition = recognition;
        this.reignBegan = reignBegan;
        this.reignEnded = reignEnded;
    }

    public Reign(Builder builder) {
        this.champion = builder.champion;
        this.id = builder.id;
        this.nationality = builder.nationality;
        this.recognition = builder.recognition;
        this.reignBegan = builder.reignBegan;
        this.reignEnded = builder.reignEnded;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public LocalDate getReignBegan() {
        return reignBegan;
    }

    public void setReignBegan(LocalDate reignBegan) {
        this.reignBegan = reignBegan;
    }

    public LocalDate getReignEnded() {
        return reignEnded;
    }

    public void setReignEnded(LocalDate reignEnded) {
        this.reignEnded = reignEnded;
    }

    public static class Builder {

        private String champion;
        private String id;
        private String nationality;
        private String recognition;
        private LocalDate reignBegan;
        private LocalDate reignEnded;

        public Builder withChampion(String champion) {
            this.champion = champion;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withNationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public Builder withRecognition(String recognition) {
            this.recognition = recognition;
            return this;
        }

        public Builder withReignBegan(LocalDate reignBegan) {
            this.reignBegan = reignBegan;
            return this;
        }

        public Builder withReignEnded(LocalDate reignEnded) {
            this.reignEnded = reignEnded;
            return this;
        }

        public Reign build() {
            return new Reign(this);
        }

    }

}
