package com.example.gui;

import java.util.Random;

public class RandomKeyGenerator {
    private StringBuilder key;
    public void createKey() {
        key = new StringBuilder(new String(new char[20]));
        for(int i = 0; i < 20; i++) {
            key.setCharAt(i,GlovalValues.alphabet.charAt((int) (Math.random()*(double)(GlovalValues.alphabet.length()))));
        }
    }
    public String getKey() {
        return key.toString();
    }
}
