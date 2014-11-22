package com.github.marcohk.statepie.util;

public interface State<T extends State<T, E>, E> {
    void enter(T previousState, E event);

    void exit(T nextState, E event);
}
