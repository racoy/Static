package ControllerTools;

import java.util.Random;

public class Dice {
    Random rnd = new Random(System.currentTimeMillis());

    public int rd4() {
        return rnd.nextInt(4) + 1;
    }

    public int rd6() {
        return rnd.nextInt(6) + 1;
    }

    public int rd8() {
        return rnd.nextInt(8) + 1;
    }

    public int rd10() {
        return rnd.nextInt(10) + 1;
    }

    public int rd12() {
        return rnd.nextInt(12) + 1;
    }

    public int rd20() {
        return rnd.nextInt(20) + 1;
    }

    public int rd100() {
        return rnd.nextInt(100) + 1;
    }

    public int roll(int n, int diceType) {
        if ((diceType != 4) && (diceType != 6) && (diceType != 8) && (diceType != 10) && (diceType != 12) && (diceType != 20) && (diceType != 100)) {
            return -1;
        }
        int s = 0;
        for (int i = 0; i < n; i++) {
            s += rnd.nextInt(diceType) + 1;
        }
        return s;
    }


}
