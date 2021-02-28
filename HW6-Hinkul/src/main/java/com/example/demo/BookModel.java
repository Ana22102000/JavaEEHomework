package com.example.demo;

import com.example.demo.Database.AuthorEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BookModel {

    private String title;
    private String isbn;
    private String authorSurname;
    private String authorName;

}
