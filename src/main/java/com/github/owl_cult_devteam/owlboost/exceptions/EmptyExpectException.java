package com.github.owl_cult_devteam.owlboost.exceptions;

public class EmptyExpectException extends RuntimeException {
    public EmptyExpectException(String msg) {
        super("Expect failed: " + msg);
    }
}
