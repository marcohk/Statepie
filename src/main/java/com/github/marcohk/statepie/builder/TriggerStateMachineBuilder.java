package com.github.marcohk.statepie.builder;

import com.github.marcohk.statepie.SimpleStateMachine;
import com.github.marcohk.statepie.TriggerStateMachine;
import com.github.marcohk.statepie.util.PredicateEventPair;
import com.github.marcohk.statepie.util.State;
import com.github.marcohk.statepie.util.TriggerPredicate;

import java.util.ArrayList;
import java.util.List;

public class TriggerStateMachineBuilder<T extends State<T, E>, E> {

    public static <T extends State<T, E>, E> WhenTriggerStateMachineBuilder<T, E> with(SimpleStateMachine<T, E> sm) {
        return new WhenTriggerStateMachineBuilder<T, E>(sm);
    }

    public static class WhenTriggerStateMachineBuilder<T extends State<T, E>, E> {
        private final SimpleStateMachine<T, E> sm;
        private final List<PredicateEventPair<T, E>> list;

        WhenTriggerStateMachineBuilder(SimpleStateMachine<T, E> sm) {
            this.sm = sm;
            this.list = new ArrayList<PredicateEventPair<T, E>>();
        }

        WhenTriggerStateMachineBuilder(SimpleStateMachine<T, E> sm, List<PredicateEventPair<T, E>> list) {
            this.sm = sm;
            this.list = list;
        }

        public FireTriggerStateMachineBuilder<T, E> fire(E event) {
            return new FireTriggerStateMachineBuilder<T, E>(sm, list, event);
        }

        public TriggerStateMachine<T, E> build() {
            return new TriggerStateMachine<T, E>(sm, list);
        }


    }

    public static class FireTriggerStateMachineBuilder<T extends State<T, E>, E> {
        private final SimpleStateMachine<T, E> sm;
        private final List<PredicateEventPair<T, E>> list;

        private final E event;

        FireTriggerStateMachineBuilder(SimpleStateMachine<T, E> sm, List<PredicateEventPair<T, E>> list, E event) {
            this.sm = sm;
            this.list = list;
            this.event = event;
        }

        public WhenTriggerStateMachineBuilder<T, E> when(TriggerPredicate<T, E> predicate) {
            list.add(new PredicateEventPair<T, E>(predicate, event));
            return new WhenTriggerStateMachineBuilder<T, E>(sm, list);
        }
    }

}
