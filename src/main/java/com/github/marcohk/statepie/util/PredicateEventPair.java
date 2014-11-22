package com.github.marcohk.statepie.util;

public class PredicateEventPair<T extends State<T, E>, E> {

    private final TriggerPredicate<T, E> predicate;
    private final E event;

    public PredicateEventPair(TriggerPredicate<T, E> predicate, E event) {
        this.predicate = predicate;
        this.event = event;
    }

    public TriggerPredicate<T, E> getPredicate() {
        return predicate;
    }

    public E getEvent() {
        return event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PredicateEventPair that = (PredicateEventPair) o;

        if (!event.equals(that.event)) return false;
        if (!predicate.equals(that.predicate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = predicate.hashCode();
        result = 31 * result + event.hashCode();
        return result;
    }
}
