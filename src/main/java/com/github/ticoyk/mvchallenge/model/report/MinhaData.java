package com.github.ticoyk.mvchallenge.model.report;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MinhaData {
    
    private String from;
    private String to;
    
    public MinhaData(){}

    public MinhaData(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public LocalDate getLocalDateFrom() {
        return LocalDate.parse(this.from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public LocalDate getLocalDateTo() {
        return LocalDate.parse(this.to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
