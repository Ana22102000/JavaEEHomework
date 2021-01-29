package kma.topic2.junit.exceptions;

public class FullnameRegexException extends RuntimeException {
    public FullnameRegexException(String fullname) {super(String.format("Fullname doesnt match regex: " + fullname));
    }
}
