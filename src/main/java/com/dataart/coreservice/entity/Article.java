package com.dataart.coreservice.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "title")
    private String title;

    @Getter
    @Setter
    @Column(name = "body", length = 5000)
    private String body;

    @Getter
    @Setter
    @Column(name = "topic")
    private String topic;

    @Getter
    @CreatedDate
    @Column(name = "createdDt")
    private final Instant createdDt = Instant.now();

    public Article(String title, String body, String topic) {
        this.title = title;
        this.body = body;
        this.topic = topic;
    }
}
