package com.github.marcohk.statepie;

import com.github.marcohk.statepie.util.State;
import com.github.marcohk.statepie.util.StateEventPair;

import java.util.Map;

public class SimpleStateMachine<T extends State<T, E>, E> implements StateMachine<T, E> {

    private final Map<StateEventPair<T, E>, T> stateEventMap;
    private T currentState;

    public SimpleStateMachine(Map<StateEventPair<T, E>, T> stateEventMap, T initialState) {
        this.stateEventMap = stateEventMap;
        this.currentState = initialState;
    }

    @Override
    public T getState() {
        return currentState;
    }

    @Override
    public void setStateSilent(T newState) {
        this.currentState = newState;
    }

    @Override
    public void setState(T newState, E event) {
        currentState.exit(newState, event);
        T previousState = currentState;
        currentState = newState;
        currentState.enter(previousState, event);
    }

    @Override
    public boolean fire(E event) {
        T newState = stateEventMap.get(new StateEventPair<T, E>(currentState, event));

        if (newState == null) {
            return false;
        }

        this.setState(newState, event);

        return true;
    }

    @Override
    public boolean update() {
        return false;
    }

}
