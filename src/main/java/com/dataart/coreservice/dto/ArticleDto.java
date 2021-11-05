package com.dataart.coreservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ArticleDto {
    @JsonProperty("title")
    private String title;

    @JsonProperty("body")
    private String body;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("createdDt")
    private final Instant createdDt = Instant.now();
}
