package sample;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.Slider;


public class Controller {

    @FXML
    private LineChart<Number, Number> LineCh1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab diceDistrib;

    @FXML
    private TextField aver1;

    @FXML
    private TextField n1;

    @FXML
    private TextField d1;

    @FXML
    private Tab ACSimulate;

    @FXML
    private TextField AC;

    @FXML
    private TextField Damage;

    @FXML
    private Button resButton;

    @FXML
    private TextArea textAr;

    @FXML
    private Button GO1;

    @FXML
    private Slider simChoice;

    @FXML
    void reset(ActionEvent event) {
        ObservableList<XYChart.Series<Number, Number>> list;
        list = LineCh1.getData();
        list.clear();
        LineCh1.setData(list);
        textAr.setText("");
        aver1.setText("");
    }

    static void paint(int n, int d, int[] mas,LineChart LineCh) {
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName(n + "d" + d);
        SimulateHelper.getProc(series1, mas);
        LineCh.getData().add(series1);
    }

    static void paintSimAC(int ac, int[] mas,LineChart LineCh) {
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("КД = " + ac);
        SimulateHelper.getProc(series1, mas);
        LineCh.getData().add(series1);
    }

    static void paintSimAttack(int attack, int[] mas, LineChart LineCh) {
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Атака +" + attack);
        SimulateHelper.getProc(series2, mas);
        LineCh.getData().add(series2);
    }


    @FXML
    void Go1(ActionEvent event) {
        if (diceDistrib.isSelected()) {
            int n = Integer.parseInt(n1.getText());
            int d = Integer.parseInt(d1.getText());
            int[] mas = SimulateHelper.Go(n,d);
            Controller.paint(n, d, mas, LineCh1);
            double aver = SimulateHelper.getAver(mas);
            aver1.setText("" + aver);
            textAr.appendText("for " + n + "d" + d + " = " + aver + "\n");
        }
        if (ACSimulate.isSelected()) {
            if (simChoice.getValue() == 0) {
                int ac = Integer.parseInt(AC.getText());
                int[] mas = SimulateHelper.acSimulate(ac);
                Controller.paintSimAC(ac, mas, LineCh1);
            }
            if (simChoice.getValue() == 1) {
                int attack = Integer.parseInt(Damage.getText());
                int[] mas = SimulateHelper.attackSimulate(attack);
                Controller.paintSimAttack(attack, mas, LineCh1);
            }
        }
    }
}
