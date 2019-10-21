package sample;

import javafx.scene.chart.XYChart;
import sample.Dice;

class SimulateHelper {
    private static Dice dice = new Dice();
    private static int rollNumb = 1000000;

    static int[] acSimulate(int ac) {
        int d20;
        int ARRSIZE =20;
        int[] mas = new int[ARRSIZE];
        for (int j = 0; j < ARRSIZE; j++) {
            mas[j] = 0;
            for (int i = 0; i < rollNumb; i++) {
                d20 = dice.rd20();
                if (ac < d20 + j) mas[j]++;
            }
        }
        mas [mas.length - 1] = rollNumb;
        return mas;
    }

    static int[] attackSimulate(int attack) {
        int d20;
        int ARRSIZE =30;
        int[] mas = new int[ARRSIZE];
        for (int j = 10; j < ARRSIZE; j++) {
            mas[j] = 0;
            for (int i = 0; i < rollNumb; i++) {
                d20 = dice.rd20();
                if (attack + d20 > j) mas[j]++;
            }
        }
        mas [mas.length - 1] = rollNumb;
        return mas;
    }

    static int[] Go(int n, int d) {
        int twod6;
        int ARRSIZE =15000;
        int[] mas = new int[ARRSIZE];
        for (int i = 0; i < ARRSIZE; i++) {
            mas[i] = 0;
        }
        for (int i = 0; i < rollNumb; i++) {
            twod6 = dice.roll(n, d);
            mas[twod6]++;
        }
        mas [mas.length - 1] = rollNumb;
        return mas;
    }

    static double getAver(int[] mas) {
        double s = 0;
        for (int i = 0; i < mas.length - 1; i++) {
            s += mas[i]*i;
        }
        s /= mas[mas.length - 1];
        s *= 100;
        s = Math.round(s);
        s /= 100;
        return s;
    }

    static void getProc(XYChart.Series<Number, Number> series, int[] mas) {
        double proc;
        for (int i = 0; i < mas.length - 1; i++) {
            proc = mas[i];
            proc /= mas[mas.length - 1];
            proc *= 1000;
            proc = Math.round(proc);
            proc /= 10;
            if (proc != 0) {
                series.getData().add(new XYChart.Data<>(i, proc));
            }
        }
    }

}
