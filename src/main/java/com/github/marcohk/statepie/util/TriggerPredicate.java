package com.github.marcohk.statepie.util;

public interface TriggerPredicate<T extends State<T, E>, E> {
    boolean test(T currentState, E event);
}
