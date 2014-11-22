package com.github.marcohk.statepie.util;

public class StateEventPair<T extends State, E> {
    private final T state;
    private final E event;

    public StateEventPair(T state, E event) {
        this.state = state;
        this.event = event;
    }

    public T getState() {
        return state;
    }

    public E getEvent() {
        return event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateEventPair that = (StateEventPair) o;

        if (event != null ? !event.equals(that.event) : that.event != null) return false;
        if (!state.equals(that.state)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }
}
