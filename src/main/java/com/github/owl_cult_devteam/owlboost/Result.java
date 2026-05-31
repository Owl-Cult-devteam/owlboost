package com.github.owl_cult_devteam.owlboost;

import com.github.owl_cult_devteam.owlboost.exceptions.EmptyUnwrapException;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public sealed interface Result<T, E> {
    record Ok<T, E>(T t) implements Result<T, E> {
        @Override
        public T unwrap() {
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

        @Override
        public E error() {
            return null;
        }

        @Override
        public T expect(String _s) {
            return t;
        }
    }

    record Err<T, E>(E e) implements Result<T, E> {
        @Override
        public T unwrap() throws EmptyUnwrapException {
            throw new EmptyUnwrapException("Unwrap failed on Result.Err: " + e);
        }

        @Override
        public T unwrap_or(T _t) {
            return _t;
        }

        @Override
        public T unwrap_or_null() {
            return null;
        }

        @Override
        public T unwrap_or_else(Supplier<T> tSupplier) {
            return tSupplier.get();
        }

        @Override
        public E error() {
            return e;
        }

        @java.lang.Override
        public T expect(String msg) throws RuntimeException {
            throw new RuntimeException("Trying to unwrap Result.Err");
        }
    }

    T unwrap() throws EmptyUnwrapException;
    T unwrap_or(T t);
    T unwrap_or_null();
    T unwrap_or_else(Supplier<T> t);

    T expect(String msg) throws RuntimeException;

    E error();
}