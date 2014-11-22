package com.github.marcohk.statepie;

import com.github.marcohk.statepie.util.PredicateEventPair;
import com.github.marcohk.statepie.util.State;

import java.util.List;

public class TriggerStateMachine<T extends State<T, E>, E> implements StateMachine<T, E> {

    private final StateMachine<T, E> sm;
    private final List<PredicateEventPair<T, E>> list;

    public TriggerStateMachine(StateMachine<T, E> sm, List<PredicateEventPair<T, E>> list) {
        this.sm = sm;
        this.list = list;
    }

    @Override
    public T getState() {
        return sm.getState();
    }

    @Override
    public void setStateSilent(T newState) {
        sm.setStateSilent(newState);
    }

    @Override
    public void setState(T newState, E event) {
        sm.setState(newState, event);
    }

    @Override
    public boolean fire(E event) {
        return sm.fire(event);
    }

    @Override
    public boolean update() {
        for (PredicateEventPair<T, E> pair : list) {
            if (pair.getPredicate().test(this.getState(), pair.getEvent())) {
                this.fire(pair.getEvent());
                return true;
            }
        }

        return false;
    }
}
