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
@EqualsAndHashCode(exclude = "links")
@ToString(exclude = "links")
@Table(name = "literature")
public class Literature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "literatureId")
    private long id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId", nullable = false)
    private Question question;

    @OneToMany(mappedBy = "literature", cascade = CascadeType.ALL)
    private Set<Link> links = new HashSet<>();
}
