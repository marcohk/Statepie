package com.github.marcohk.statepie.sample;

import com.github.marcohk.statepie.util.State;

abstract public class TriggerState implements State<TriggerState, TriggerEvent> {
    abstract public String getName();
}
