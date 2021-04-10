package com.example.demo.Controllers;

import com.example.demo.Database.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class IsbnValidator {



    public static boolean validateSum(String isbn){
        ArrayList<Integer> numbers=new ArrayList<>();
        for(int i=0;i<isbn.length();i++){
            if(isbn.charAt(i)!='-'){
                numbers.add(Character.getNumericValue(isbn.charAt(i)));
            }

        }
        int checkSum=0;

        for(int i=0; i<numbers.size()-1; i++){
            int mult = 1;
            if(i%2==1)
                mult = 3;
            checkSum += numbers.get(i)*mult;
        }

        if(checkSum % 10 == numbers.get(numbers.size()-1))
            return true;

        return false;
    }


}
