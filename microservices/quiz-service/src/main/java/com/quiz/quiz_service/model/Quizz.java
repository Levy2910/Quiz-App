package com.quiz.quiz_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Quizz {
    @Id
    @GeneratedValue
    private Long id;

    private String quizzName;

    @ElementCollection
    private List<Integer> questions;
}
