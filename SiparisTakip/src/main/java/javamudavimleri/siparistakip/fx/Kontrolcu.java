package javamudavimleri.siparistakip.fx;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import javamudavimleri.siparistakip.fx.net.Istek;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class Kontrolcu implements Initializable{
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
    Button odemetamamlabtn;
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
    Text odememasatxt;
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
    @FXML
    VBox odemeitemholder;
    @FXML
    VBox sipariskitemholder;
    @FXML
    VBox siparissitemholder;
    @FXML
    VBox menukitemholder;
    @FXML
    ComboBox<String> odemeturucombo;
    @FXML
    ComboBox<String> sipariscombo;
    @FXML
    ComboBox<String> menukcombo;
    @FXML
    ComboBox<String> menuecombo;
    EventHandler<MouseEvent> masaevent;
    EventHandler<ActionEvent> sipariskeevent;
    EventHandler<ActionEvent> siparisscevent;
    EventHandler<ActionEvent> menukdevent;
    EventHandler<ActionEvent> menukcevent;
    List<Node> odemeitemler;
    List<Node> sipariskitemler;
    List<Node> siparissitemler;
    ListeEleman le;
    Istek istek;
    HashMap<String, String> kategoriler;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fissatirmasa.setCellValueFactory(new PropertyValueFactory<FisTablo, String>("masaAdi"));
        fissatiradisyon.setCellValueFactory(new PropertyValueFactory<FisTablo, Integer>("adisyonNo"));
        fissatirtutar.setCellValueFactory(new PropertyValueFactory<FisTablo, Double>("tutar"));
        fissatirtip.setCellValueFactory(new PropertyValueFactory<FisTablo, Double>("tipToplami"));
        fissatirodenen.setCellValueFactory(new PropertyValueFactory<FisTablo, Double>("odenenTutar"));
        fissatirodeme.setCellValueFactory(new PropertyValueFactory<FisTablo, String>("odemeTuru"));
        fissatirtarih.setCellValueFactory(new PropertyValueFactory<FisTablo, Date>("odenilenTarih"));
        fissatirpersonel.setCellValueFactory(new PropertyValueFactory<FisTablo, String>("personelAdi"));
        fissatirnot.setCellValueFactory(new PropertyValueFactory<FisTablo, String>("odemeNotu"));
        
        kategoriler = new HashMap<String, String>();
        kategoriler.put("Ana Yemek", "ana");
		kategoriler.put("Sıcak Başlangıç", "baslangic");
		kategoriler.put("İçecek", "icecek");
		kategoriler.put("Tatlı", "tatli");
		kategoriler.put("Ara Sıcak", "ara");
		kategoriler.put("Salata", "salata");
		kategoriler.put("Meze", "meze");
        
        le = new ListeEleman();
        istek = new Istek();
        
        masaevent = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){
            	Node tiklananmasa = (Node) e.getSource();
            	String masaAdi = tiklananmasa.getId().substring(0, 1).toUpperCase();
            	masaAdi += "-" + tiklananmasa.getId().substring(1);
            	siparismasatxt.setText(masaAdi);
            	sipariscombo.getItems().clear();
            	sipariscombo.getItems().addAll(kategoriler.keySet());
            	sipariscombo.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						kategoriGetir(kategoriler.get(sipariscombo.getValue()));
					}
            	});
            	siparissitemholder.getChildren().clear();
        		siparissitemler = new ArrayList<Node>();
        		masalarpane.setVisible(false);
                siparispane.setVisible(true);
            }
        };
        siparisscevent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
        };
        menukdevent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
        };
        menukcevent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
        };
        sipariskeevent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AnchorPane sipariskeitem = (AnchorPane)((Node)(arg0.getSource())).getParent();
				TextField sipariskemiktar = (TextField)sipariskeitem.lookup("#"+sipariskeitem.getId()+"m");
				boolean basariliMi = false;
				int miktar = 0;
				try {
					miktar = Integer.valueOf(sipariskemiktar.getText());
					basariliMi = true;
				}
            	catch(Exception e) {
            		e.printStackTrace();
            	}
				if(basariliMi && miktar > 0) {
					siparissitemler.add(le.siparissitempane("sipariss"+sipariskeitem.getId(), sipariskeitem.getId(), "64", ""+miktar, siparisscevent)) ;
		    		siparissitemholder.getChildren().setAll(siparissitemler);	
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
            	JSONObject giris = istek.girisYap(sifrefield.getText());
            	if(giris.getInt("yanitKodu")==HttpStatus.OK.value()) {
            		giristxt.setText("Giriş başarılı! ");
            		giristxt.setFill(Color.GREEN);
            		kullaniciaditxt.setText(giris.getString("personelAdi"));
            		girispane.setVisible(false);
            		masalarpane.setVisible(true);
            		barpane.setVisible(true);
            	}
            	else{
            		giristxt.setText("Giriş başarısız! ");
            		giristxt.setFill(Color.RED);
            	}
            }
        });
        menubtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
            	menukcombo.getItems().clear();
            	menukcombo.getItems().addAll(kategoriler.keySet());
            	menukcombo.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						menukitemholder.getChildren().add(
								le.menukitempane("menuk" + kategoriler.get(menukcombo.getValue())
												, kategoriler.get(menukcombo.getValue()), "4", menukdevent, menukcevent));
						//siparisk ile aşşırı benzer
					}
            	});
            	menuecombo.getItems().clear();
            	menuecombo.getItems().addAll(kategoriler.keySet());
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
                FisTablo girdi = new FisTablo("A-09", 35, 356.65, 3.35, 360.00, "PayPal", new Date(System.currentTimeMillis()), "yasin", "");
                girdiler.add(girdi);
                girdi = new FisTablo("A-15", 41, 376.65, 3.35, 380.00, "Netflix Hediye Kartı", new Date(System.currentTimeMillis()+1000*60*60*24), "kerem", "Hizmet Güzeldi");
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
            	odememasatxt.setText(siparismasatxt.getText());
            	//String siparismasaID = siparismasatxt.getText().substring(0, 1).toLowerCase() + siparismasatxt.getText().substring(2);
            	odemeitemholder.getChildren().clear();
            	odemeitemler = new ArrayList<Node>();
            	for(int i = 0 ; i < 30 ; i++) {
            		odemeitemler.add(le.odemeitempane("odemeitem"+(i+1), "Sardalya", "32", "4")) ;
            		odemeitemholder.getChildren().add(odemeitemler.get(i));
            	}
            	odemeturucombo.getItems().clear();
            	odemeturucombo.getItems().addAll(new String[]{"Nakit", "Kredi Kartı", "Netflix Hediye Kartı", "PayPal"});
            	siparispane.setVisible(false);
            	odemepane.setVisible(true);
            }
        });
        odemetamamlabtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
            	odemepane.setVisible(false);
            	masalarpane.setVisible(true);
            }
        });
	}	
	
	public void kategoriGetir(String kategoriID) {
		sipariskitemholder.getChildren().clear();
		sipariskitemler = new ArrayList<Node>();
		if(kategoriID.equals("ana")) {
			for(int i = 0 ; i < 64 ; i++) {
	    		sipariskitemler.add(le.sipariskitempane("sipariskAna"+(i+1), "Ana"+(i+1), "64", sipariskeevent)) ;
	    		sipariskitemholder.getChildren().add(sipariskitemler.get(i));
	    	}
		}
		else if(kategoriID.equals("baslangic")) {
			for(int i = 0 ; i < 32 ; i++) {
	    		sipariskitemler.add(le.sipariskitempane("sipariskBaslangic"+(i+1), "Baslangic"+(i+1), "32", sipariskeevent)) ;
	    		sipariskitemholder.getChildren().add(sipariskitemler.get(i));
	    	}
		}
		else if(kategoriID.equals("icecek")) {
			for(int i = 0 ; i < 16 ; i++) {
	    		sipariskitemler.add(le.sipariskitempane("siparisIicecek"+(i+1), "Icecek"+(i+1), "16", sipariskeevent)) ;
	    		sipariskitemholder.getChildren().add(sipariskitemler.get(i));
	    	}
		}	
		else if(kategoriID.equals("tatli")) {
			for(int i = 0 ; i < 8 ; i++) {
	    		sipariskitemler.add(le.sipariskitempane("sipariskTatli"+(i+1), "Tatli"+(i+1), "8", sipariskeevent)) ;
	    		sipariskitemholder.getChildren().add(sipariskitemler.get(i));
	    	}
		}
		else if(kategoriID.equals("ara")) {
			for(int i = 0 ; i < 4 ; i++) {
	    		sipariskitemler.add(le.sipariskitempane("sipariskAra"+(i+1), "Ara"+(i+1), "4", sipariskeevent)) ;
	    		sipariskitemholder.getChildren().add(sipariskitemler.get(i));
	    	}
		}
		else if(kategoriID.equals("salata")) {
			for(int i = 0 ; i < 2 ; i++) {
	    		sipariskitemler.add(le.sipariskitempane("sipariskSalata"+(i+1), "Salata"+(i+1), "2", sipariskeevent)) ;
	    		sipariskitemholder.getChildren().add(sipariskitemler.get(i));
	    	}
		}
		else if(kategoriID.equals("meze")) {
			for(int i = 0 ; i < 1 ; i++) {
	    		sipariskitemler.add(le.sipariskitempane("sipariskMeze"+(i+1), "Meze"+(i+1), "1", sipariskeevent)) ;
	    		sipariskitemholder.getChildren().add(sipariskitemler.get(i));
	    	}
		}
	}
}
