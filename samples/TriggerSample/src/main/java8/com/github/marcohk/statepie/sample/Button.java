package com.github.marcohk.statepie.sample;

import com.github.marcohk.statepie.SimpleStateMachine;
import com.github.marcohk.statepie.StateMachine;
import com.github.marcohk.statepie.builder.StateMachineBuilder;
import com.github.marcohk.statepie.builder.TriggerStateMachineBuilder;

public class Button {
    private StateMachine<TriggerState, TriggerEvent> sm;
    private boolean pressed = false;
    private boolean overload = false;

    public Button() {
        SimpleStateMachine<TriggerState, TriggerEvent> ssm = StateMachineBuilder.<TriggerState, TriggerEvent>initial(off)
                .from(off).to(on).on(TriggerEvent.PRESS)
                .from(on).to(off).on(TriggerEvent.PRESS)
                .from(on).to(error).on(TriggerEvent.OVERLOAD)
                .from(error).to(off).on(TriggerEvent.PRESS)
                .build();

        this.sm = TriggerStateMachineBuilder.<TriggerState, TriggerEvent>with(ssm)
                .fire(TriggerEvent.OVERLOAD).when((state, event) -> on == state && isOverload())
                .fire(TriggerEvent.PRESS).when((state, event) -> isPressed())
                .build();


    }

    public void press() {
        System.out.println("press");
        if (pressed) {
            overload = true;
        }

        pressed = true;
        sm.update();
    }

    public void release() {
        System.out.println("release");
        pressed = false;
    }

    public void cooldown() {
        System.out.println("cooldown");
        overload = false;
        pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean isOverload() {
        return overload;
    }

    public TriggerState getState() {
        return sm.getState();
    }

    private TriggerState off = new TriggerState() {

        @Override
        public String getName() {
            return "OFF";
        }

        @Override
        public void enter(TriggerState previousState, TriggerEvent event) {
            System.out.println("Enter " + this.getName() + " State from: " + previousState.getName());
        }

        @Override
        public void exit(TriggerState nextState, TriggerEvent event) {
            System.out.println("EXIT " + this.getName() + " State to: " + nextState.getName());
        }
    };

    private TriggerState on = new TriggerState() {

        @Override
        public String getName() {
            return "ON";
        }

        @Override
        public void enter(TriggerState previousState, TriggerEvent event) {
            System.out.println("Enter " + this.getName() + " State from: " + previousState.getName());
        }

        @Override
        public void exit(TriggerState nextState, TriggerEvent event) {
            System.out.println("EXIT " + this.getName() + " State to: " + nextState.getName());
        }
    };

    private TriggerState error = new TriggerState() {

        @Override
        public String getName() {
            return "ERROR";
        }

        @Override
        public void enter(TriggerState previousState, TriggerEvent event) {
            System.out.println("Enter " + this.getName() + " State from: " + previousState.getName());
            cooldown();
            System.out.println("Button Cooled Down");
        }

        @Override
        public void exit(TriggerState nextState, TriggerEvent event) {
            System.out.println("EXIT " + this.getName() + " State to: " + nextState.getName());
        }
    };
}
