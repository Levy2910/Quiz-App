package com.quiz.quiz_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionWrapper {
    @Id
    @GeneratedValue
    private Long id;
    private String question_type;
    private String question_title;
    private String first_option;
    private String second_option;
    private String third_option;
    private String fourth_option;
}