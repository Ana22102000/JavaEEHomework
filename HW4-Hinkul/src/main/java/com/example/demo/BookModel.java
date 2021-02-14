package com.example.demo;

import lombok.*;

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
