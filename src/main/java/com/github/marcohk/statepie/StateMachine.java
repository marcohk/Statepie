package com.github.marcohk.statepie;

import com.github.marcohk.statepie.util.State;

public interface StateMachine<T extends State, E> {
    T getState();

    void setStateSilent(T newState);

    void setState(T newState, E event);

    boolean fire(E event);

    boolean update();
}
