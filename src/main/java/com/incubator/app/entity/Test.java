package com.incubator.app.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "questions")
@ToString(exclude = "questions")
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testId")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicId", nullable = false)
    private Topic topic;

    @OneToMany(mappedBy = "test")
    private Set<Question> questions = new HashSet<>();

    @Column(name = "isDeleted", columnDefinition="bit")
    private Integer isDeleted;

}
