package com.github.owl_cult_devteam.owlboost;

import com.github.owl_cult_devteam.owlboost.exceptions.*;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public sealed interface Option<T> {
    record Some<T>(T t) implements Option<T> {
        @Override
        public T unwrap() throws EmptyUnwrapException {
            return t;
        }

        @Override
        public T unwrap_or(T _t) {
            return t;
        }

        @Override
        public T unwrap_or_null() {
            return t;
        }

        @Override
        public T unwrap_or_else(Supplier<T> _tSupplier) {
            return t;
        }
    }

    record None<T>() implements Option<T> {
        @Override
        public T unwrap() throws EmptyUnwrapException {
            throw new EmptyUnwrapException("Trying to unwrap empty Option");
        }

        @Override
        public T unwrap_or(T t) {
            return t;
        }

        @Override
        public T unwrap_or_null() {
            return null;
        }

        @Override
        public T unwrap_or_else(Supplier<T> tSupplier) {
            return tSupplier.get();
        }
    }

    T unwrap() throws EmptyUnwrapException;
    T unwrap_or(T t);
    T unwrap_or_null();
    T unwrap_or_else(Supplier<T> t);
}