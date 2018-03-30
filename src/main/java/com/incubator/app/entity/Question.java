package com.incubator.app.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionId")
    private long id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testId", nullable = false)
    private Test test;

    @OneToMany(mappedBy = "question")
    private Set<Literature> literatureList = new HashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers = new HashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<Statistic> statistics = new HashSet<>();

    @Column(name = "isDeleted", columnDefinition = "bit")
    private Integer isDeleted;


}
