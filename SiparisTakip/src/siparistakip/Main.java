package siparistakip;

import java.util.Date;
import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.Node;
// A
public class Main extends Application {
    @FXML
    Button menubtn;
    @FXML
    Button masalarbtn;
    @FXML
    Button fislerbtn;
    @FXML
    Button sipariskaydetbtn;
    @FXML
    Button odemebtn;
    @FXML
    Button girisbtn;
    @FXML
    PasswordField sifrefield;
    @FXML
    AnchorPane girispane;
    @FXML
    AnchorPane menupane;
    @FXML
    AnchorPane siparispane;
    @FXML
    AnchorPane masalarpane;
    @FXML
    AnchorPane fislerpane;
    @FXML
    AnchorPane odemepane;
    @FXML
    AnchorPane barpane;
    @FXML
    Text giristxt;
    @FXML
    Text kullaniciaditxt;
    @FXML
    Text siparismasatxt;
    @FXML
    TableView<FisTablo> fistablo;
    @FXML
    TableColumn<FisTablo, String> fissatirmasa;
    @FXML
    TableColumn<FisTablo, Integer> fissatiradisyon;
    @FXML
    TableColumn<FisTablo, Double> fissatirtutar;
    @FXML
    TableColumn<FisTablo, Double> fissatirtip;
    @FXML
    TableColumn<FisTablo, Double> fissatirodenen;
    @FXML
    TableColumn<FisTablo, String> fissatirodeme;
    @FXML
    TableColumn<FisTablo, Date> fissatirtarih;
    @FXML
    TableColumn<FisTablo, String> fissatirpersonel;
    @FXML
    TableColumn<FisTablo, String> fissatirnot;
    @FXML
    VBox root;
    @FXML
    AnchorPane a01;
    @FXML
    AnchorPane a02;
    @FXML
    AnchorPane a03;
    @FXML
    AnchorPane a04;
    @FXML
    AnchorPane a05;
    @FXML
    AnchorPane a06;
    @FXML
    AnchorPane a07;
    @FXML
    AnchorPane a08;
    @FXML
    AnchorPane a09;
    @FXML
    AnchorPane a10;
    @FXML
    AnchorPane a11;
    @FXML
    AnchorPane a12;
    @FXML
    AnchorPane a13;
    @FXML
    AnchorPane a14;
    @FXML
    AnchorPane a15;
    @FXML
    AnchorPane a16;
    @FXML
    AnchorPane a17;
    @FXML
    AnchorPane a18;
    Scene scene;
    FXMLLoader loader;
    HashMap<String, String> kullanicilar;
    EventHandler<MouseEvent> masaevent;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("Siparis_Takip.fxml"));
        loader.setController(Main.this);
        root = loader.load();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        kullanicilar = new HashMap<String, String>();
        kullanicilar.put("123", "yasin");
        kullanicilar.put("321", "kerem");
        fissatirmasa.setCellValueFactory(new PropertyValueFactory<FisTablo, String>("masaAdi"));
        fissatiradisyon.setCellValueFactory(new PropertyValueFactory<FisTablo, Integer>("adisyonNo"));
        fissatirtutar.setCellValueFactory(new PropertyValueFactory<FisTablo, Double>("tutar"));
        fissatirtip.setCellValueFactory(new PropertyValueFactory<FisTablo, Double>("tipToplami"));
        fissatirodenen.setCellValueFactory(new PropertyValueFactory<FisTablo, Double>("odenenTutar"));
        fissatirodeme.setCellValueFactory(new PropertyValueFactory<FisTablo, String>("odemeTuru"));
        fissatirtarih.setCellValueFactory(new PropertyValueFactory<FisTablo, Date>("odenilenTarih"));
        fissatirpersonel.setCellValueFactory(new PropertyValueFactory<FisTablo, String>("personelAdi"));
        fissatirnot.setCellValueFactory(new PropertyValueFactory<FisTablo, String>("odemeNotu"));
        masaevent = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){
            	Node tiklananmasa = (Node) e.getSource();
            	siparismasatxt.setText(tiklananmasa.getId());
            	if(masalarpane.isVisible()) {
            		masalarpane.setVisible(false);
                	siparispane.setVisible(true);
            	}
            	else if(siparispane.isVisible()){
            		masalarpane.setVisible(true);
                	siparispane.setVisible(false);
            	}
            }
        };
        a01.setOnMouseClicked(masaevent);
        a02.setOnMouseClicked(masaevent);
        a03.setOnMouseClicked(masaevent);
        a04.setOnMouseClicked(masaevent);
        a05.setOnMouseClicked(masaevent);
        a06.setOnMouseClicked(masaevent);
        a07.setOnMouseClicked(masaevent);
        a08.setOnMouseClicked(masaevent);
        a09.setOnMouseClicked(masaevent);
        a10.setOnMouseClicked(masaevent);
        a11.setOnMouseClicked(masaevent);
        a12.setOnMouseClicked(masaevent);
        a13.setOnMouseClicked(masaevent);
        a14.setOnMouseClicked(masaevent);
        a15.setOnMouseClicked(masaevent);
        a16.setOnMouseClicked(masaevent);
        a17.setOnMouseClicked(masaevent);
        a18.setOnMouseClicked(masaevent);
        girisbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
            	if(!kullanicilar.getOrDefault(sifrefield.getText(), "").equals("")) {
            		giristxt.setText("Giriş başarılı! ");
            		giristxt.setFill(Color.GREEN);
            		girispane.setVisible(false);
            		masalarpane.setVisible(true);
            		barpane.setVisible(true);
            		kullaniciaditxt.setText(kullanicilar.get(sifrefield.getText()));
            	}
            	else{
            		giristxt.setText("Giriş başarısız! ");
            		giristxt.setFill(Color.RED);
            	}
            }
        });
        menubtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
            	masalarpane.setVisible(false);
                menupane.setVisible(true);
            	siparispane.setVisible(false);
            	fislerpane.setVisible(false);
            	odemepane.setVisible(false);
            }
        });
        fislerbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ObservableList<FisTablo> girdiler = fistablo.getItems();
                FisTablo girdi = new FisTablo("A-09", 35, 356.65, 3.35, 360.00, "PayPal", new Date(System.currentTimeMillis()), "yasin", "bahşiş düşük");
                girdiler.add(girdi);
                girdi = new FisTablo("A-15", 41, 376.65, 3.35, 380.00, "Netflix Hediye Kartı", new Date(System.currentTimeMillis()+1000*60*60*24), "kerem", "netflix ile ödeme alan ilk yerli ve milli şirket");
                girdiler.add(girdi);
                fistablo.setItems(girdiler);
            	masalarpane.setVisible(false);
                menupane.setVisible(false);
            	siparispane.setVisible(false);
            	fislerpane.setVisible(true);
            	odemepane.setVisible(false);
            }
        });
        masalarbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
            	masalarpane.setVisible(true);
                menupane.setVisible(false);
            	siparispane.setVisible(false);
            	fislerpane.setVisible(false);
            	odemepane.setVisible(false);
            }
        });
        sipariskaydetbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
            	masalarpane.setVisible(true);
            	siparispane.setVisible(false);
            }
        });
        odemebtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
            	siparispane.setVisible(false);
            	odemepane.setVisible(true);
            }
        });
    }
}
