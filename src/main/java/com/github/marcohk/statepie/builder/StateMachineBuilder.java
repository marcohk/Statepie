package com.github.marcohk.statepie.builder;

import com.github.marcohk.statepie.SimpleStateMachine;
import com.github.marcohk.statepie.util.State;
import com.github.marcohk.statepie.util.StateEventPair;

import java.util.HashMap;
import java.util.Map;

public class StateMachineBuilder<T extends State<T, E>, E> {

    public static <T extends State<T, E>, E> FromStateMachineBuilder initial(T initialState) {
        return new FromStateMachineBuilder<T, E>(new HashMap<StateEventPair<T, E>, T>(), initialState);
    }

    public static class FromStateMachineBuilder<T extends State<T, E>, E> {
        private final Map<StateEventPair<T, E>, T> stateEventMap;
        private final T initialState;

        FromStateMachineBuilder(Map<StateEventPair<T, E>, T> stateEventMap, T initialState) {
            this.stateEventMap = stateEventMap;
            this.initialState = initialState;
        }

        public ToStateMachineBuilder from(T fromState) {
            return new ToStateMachineBuilder<T, E>(stateEventMap, initialState, fromState);
        }

        public SimpleStateMachine build() {
            return new SimpleStateMachine<T, E>(stateEventMap, initialState);
        }
    }

    public static class ToStateMachineBuilder<T extends State<T, E>, E> {
        private final Map<StateEventPair<T, E>, T> stateEventMap;
        private final T initialState;

        private final T fromState;

        ToStateMachineBuilder(Map<StateEventPair<T, E>, T> stateEventMap, T initialState, T fromState) {
            this.stateEventMap = stateEventMap;
            this.initialState = initialState;
            this.fromState = fromState;
        }

        public OnStateMachineBuilder to(T toState) {
            return new OnStateMachineBuilder<T, E>(stateEventMap, initialState, fromState, toState);
        }
    }


    public static class OnStateMachineBuilder<T extends State<T, E>, E> {
        private final Map<StateEventPair<T, E>, T> stateEventMap;
        private final T initialState;

        private final T fromState;
        private final T toState;

        OnStateMachineBuilder(Map<StateEventPair<T, E>, T> stateEventMap, T initialState, T fromState, T toState) {
            this.stateEventMap = stateEventMap;
            this.initialState = initialState;
            this.fromState = fromState;
            this.toState = toState;
        }

        public FromStateMachineBuilder on(E event) {
            stateEventMap.put(new StateEventPair<T, E>(fromState, event), toState);
            return new FromStateMachineBuilder<T, E>(stateEventMap, initialState);
        }
    }

}

