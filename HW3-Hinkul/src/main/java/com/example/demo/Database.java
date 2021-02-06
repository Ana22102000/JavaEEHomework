package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class Database {
    private static ArrayList<BookModel> bookModelList = startDatabase();

    private static ArrayList<BookModel> startDatabase() {
        ArrayList<BookModel> arrayList = new ArrayList<>();
        arrayList.add(new BookModel("Harry Potter","978-0-2547-5721-6","Rowling","Joanne"));
        arrayList.add(new BookModel("Lord of the rings","894-3-9825-2425-1","Tolkien","John"));
        arrayList.add(new BookModel("Sherlock Holmes","998-9-3766-9117-4","Conan Doyle","Arthur"));

        return arrayList;
    }

    public static ArrayList<BookModel> getBookModelList(){
        return bookModelList;

    }
}
