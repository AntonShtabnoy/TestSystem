package com.incubator.app.dto;

import com.incubator.app.entity.Answer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class QuestionDTO {
    private List<String> answers;
    private List<String> correct;
    private String description;
    private String literature;
    private String link;
}
