package com.github.marcohk.statepie.sample;

public class Main {
    public static void main(String[] args) {

        Button button = new Button();
        button.press();
        button.release();
        button.press();
        button.release();
        button.press();
        button.release();
        button.press();
        button.press();
        button.release();
        button.press();
        button.release();
        button.release();
        button.press();

        System.out.println("Final State: " + button.getState().getName());
    }
}
