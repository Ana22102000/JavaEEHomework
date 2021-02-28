package com.example.demo.Database;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AuthorEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "author_surname")
    private String authorSurname;
    @Column(name = "author_name")
    private String authorName;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<BookEntity> books;


}
