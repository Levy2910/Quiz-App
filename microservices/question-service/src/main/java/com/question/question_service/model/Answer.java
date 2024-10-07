package com.question.question_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Answer {
    @Id
    private Long id;
    private String answer;

}
