package javamudavimleri.siparistakip.fx;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import javamudavimleri.siparistakip.fx.net.Istek;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    Button menuuruneklebtn;
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
    Text siparistoplamtxt;
    @FXML
    Text odemearatxt;
    @FXML
    Text odemegeneltxt;
    @FXML
    TextField menuurunaditxtf;
    @FXML
    TextField menuurunfiyattxtf;
    @FXML
    TextField odemebahsistxtf;
    @FXML
    TextField odemenottxtf;
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
    ComboBox<String> odemeyontemicombo;
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
    JSONObject girisYapan;
    HashMap<String, Long> urunTuruIsimleri;
    HashMap<String, Long> odemeYontemiIsimleri;
    String masaAdi;
    long masaSiparisID;
    double bahsis;
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
        
        le = new ListeEleman();
        istek = new Istek();
        
        masaevent = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0){
            	Node tiklananmasa = (Node) arg0.getSource();
            	masaAdi = tiklananmasa.getId().substring(0, 1).toUpperCase();
            	masaAdi += "-" + tiklananmasa.getId().substring(1);
            	JSONObject masaSiparisi = istek.masaSiparisi(girisYapan.getString("personelSifreHashed"), masaAdi);
            	if(masaSiparisi.getInt("yanitKodu")==HttpStatus.NOT_FOUND.value()) {
            		if(istek.masaGuncelle(girisYapan.getString("personelSifreHashed"), masaAdi).getInt("yanitKodu")==HttpStatus.OK.value()) {
            			masaSiparisi = istek.masaSiparisi(girisYapan.getString("personelSifreHashed"), masaAdi);
            		}
            	}
            	if(masaSiparisi.getInt("yanitKodu")==HttpStatus.OK.value()) {
            		siparismasatxt.setText(masaAdi);
                	sipariskitemholder.getChildren().clear();
            		JSONArray urunTurleri = istek.urunTurleri(girisYapan.getString("personelSifreHashed"));
                	if(urunTurleri.getJSONObject(0).getInt("yanitKodu")==HttpStatus.OK.value()) {
                		masaSiparisID = masaSiparisi.getLong("id");
                		sipariscombo.getItems().clear();
                		urunTuruIsimleri = new HashMap<String, Long>();
                		for(int i = 0 ; i < urunTurleri.length() ; i++) {
                			sipariscombo.getItems().add(urunTurleri.getJSONObject(i).getString("urunTuruAdi"));
                			urunTuruIsimleri.put(urunTurleri.getJSONObject(i).getString("urunTuruAdi"), urunTurleri.getJSONObject(i).getLong("id"));
                		}
                		sipariscombo.setOnAction(new EventHandler<ActionEvent>() {
        					@Override
        					public void handle(ActionEvent arg0) {
        						if(urunTuruIsimleri.get(sipariscombo.getValue()) != null) {
        							skihguncelle(urunTuruIsimleri.get(sipariscombo.getValue()));
        						}
        					}
                    	});
                		ssihguncelle(masaAdi);
                		masalarpane.setVisible(false);
                        siparispane.setVisible(true);
                	}
            	}
            }
        };
        siparisscevent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AnchorPane siparisscitem = (AnchorPane)((Node)(arg0.getSource())).getParent();
				TextField siparisscmiktar = (TextField)siparisscitem.lookup("#"+siparisscitem.getId()+"m");
				boolean basariliMi = false;
				int miktar = 0;
				try {
					miktar = Integer.valueOf(siparisscmiktar.getText());
					basariliMi = true;
				}
            	catch(Exception e) {
            		e.printStackTrace();
            	}
				if(basariliMi && miktar > 0) {
					JSONObject yanit = istek.siparisGuncelle(girisYapan.getString("personelSifreHashed")
							, masaSiparisID, Long.parseLong(siparisscitem.getId().substring(8)), miktar*(-1));
					if(yanit.getInt("yanitKodu")==HttpStatus.OK.value()) {
						ssihguncelle(masaAdi);	
					}
				}
			}
        };
        menukcevent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					AnchorPane menukcitem = (AnchorPane)((Node)(arg0.getSource())).getParent();
					if(istek.urunCikar(girisYapan.getString("personelSifreHashed")
							, menukcitem.getId().substring(5)).getInt("yanitKodu")==HttpStatus.OK.value()) {
						if(urunTuruIsimleri.get(menukcombo.getValue()) != null) {
							mkihguncelle(urunTuruIsimleri.get(menukcombo.getValue()));
						}
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
        };
        menukdevent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					AnchorPane menukditem = (AnchorPane)((Node)(arg0.getSource())).getParent();
					TextField menukditemadi = (TextField)menukditem.lookup("#"+menukditem.getId()+"a");
					TextField menukditemfiyati = (TextField)menukditem.lookup("#"+menukditem.getId()+"d");
					if(istek.urunCikar(girisYapan.getString("personelSifreHashed")
							, menukditem.getId().substring(5)).getInt("yanitKodu") == HttpStatus.OK.value()) {
						istek.urunEkle(girisYapan.getString("personelSifreHashed")
								, menukditemadi.getText()
								, Double.parseDouble(menukditemfiyati.getText())
								, urunTuruIsimleri.get(menukcombo.getValue()));
						menuurunfiyattxtf.setText("");
						menuurunaditxtf.setText("");
						if(urunTuruIsimleri.get(menukcombo.getValue()) != null) {
							mkihguncelle(urunTuruIsimleri.get(menukcombo.getValue()));
						}
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
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
					JSONObject yanit = istek.siparisGuncelle(girisYapan.getString("personelSifreHashed")
							, masaSiparisID, Long.parseLong(sipariskeitem.getId().substring(8)), miktar);
					if(yanit.getInt("yanitKodu")==HttpStatus.OK.value()) {
						ssihguncelle(masaAdi);	
					}
				}
			}
        };
        menuuruneklebtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				JSONObject urunEkle = istek.urunEkle(girisYapan.getString("personelSifreHashed")
							, menuurunaditxtf.getText()
							, Double.parseDouble(menuurunfiyattxtf.getText())
							, urunTuruIsimleri.get(menuecombo.getValue()));
				if(urunEkle.getInt("yanitKodu")==HttpStatus.OK.value()) {
					menuurunfiyattxtf.setText("");
					menuurunaditxtf.setText("");
					if(urunTuruIsimleri.get(menukcombo.getValue()) != null) {
						mkihguncelle(urunTuruIsimleri.get(menukcombo.getValue()));
					}
				}
			}
        });
        
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
            		girisYapan = giris;
            		kullaniciaditxt.setText(girisYapan.getString("personelAdi"));
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
            	JSONArray urunTurleri = istek.urunTurleri(girisYapan.getString("personelSifreHashed"));
            	if(urunTurleri.getJSONObject(0).getInt("yanitKodu")==HttpStatus.OK.value()) {
            		menukcombo.getItems().clear();
                	menuecombo.getItems().clear();
                	urunTuruIsimleri = new HashMap<String, Long>();
            		for(int i = 0 ; i < urunTurleri.length() ; i++) {
            			menukcombo.getItems().add(urunTurleri.getJSONObject(i).getString("urunTuruAdi"));
            			menuecombo.getItems().add(urunTurleri.getJSONObject(i).getString("urunTuruAdi"));
            			urunTuruIsimleri.put(urunTurleri.getJSONObject(i).getString("urunTuruAdi"), urunTurleri.getJSONObject(i).getLong("id"));
            		}
            		menukcombo.setOnAction(new EventHandler<ActionEvent>() {
    					@Override
    					public void handle(ActionEvent arg0) {
    						if(urunTuruIsimleri.get(menukcombo.getValue()) != null) {
    							mkihguncelle(urunTuruIsimleri.get(menukcombo.getValue()));
    						}
    					}
                	});
            		masalarpane.setVisible(false);
                    menupane.setVisible(true);
                	siparispane.setVisible(false);
                	fislerpane.setVisible(false);
                	odemepane.setVisible(false);
            	}
            }
        });
        fislerbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	JSONArray odemeGecmisi = istek.odemeGecmisi(girisYapan.getString("personelSifreHashed"));
            	if(odemeGecmisi.getJSONObject(0).getInt("yanitKodu")==HttpStatus.OK.value()) {
            		ObservableList<FisTablo> girdiler = fistablo.getItems();
            		for(int i = 0 ; i < odemeGecmisi.length() ; i++) {
            			JSONObject odeme = odemeGecmisi.getJSONObject(0);
            			double fisBahsis = odeme.getDouble("bahsis");
            			double fisToplam = odeme.getDouble("toplam");
            			girdiler.add(new FisTablo(odeme.getString("masaAdi"), odeme.getLong("id"), fisToplam-fisBahsis, fisBahsis, fisToplam
            				, odeme.getString("odemeYontemiAdi"), new Date(odeme.getLong("odemeZamani")), odeme.getString("personelAdi"), odeme.getString("odemeNotu")));
            		}
                    fistablo.setItems(girdiler);
                	masalarpane.setVisible(false);
                    menupane.setVisible(false);
                	siparispane.setVisible(false);
                	fislerpane.setVisible(true);
                	odemepane.setVisible(false);
            	}
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
            	JSONObject masaSiparisi = istek.masaSiparisi(girisYapan.getString("personelSifreHashed"), masaAdi);
        		if(masaSiparisi.getInt("yanitKodu")==HttpStatus.OK.value()) {
        			odemeitemholder.getChildren().clear();
                	odemeitemler = new ArrayList<Node>();
        			odemearatxt.setText(""+masaSiparisi.getDouble("toplamTutar"));
        			odemegeneltxt.setText(""+masaSiparisi.getDouble("toplamTutar"));
        			odemenottxtf.setText("");
        			odemebahsistxtf.setText("0");
        			bahsis = 0;
        			odememasatxt.setText(masaSiparisi.getString("masaAdi"));
        			JSONObject urunlerObje = new JSONObject(masaSiparisi.getString("urunler"));
        			JSONArray urunler = new JSONArray();
        			for(String urunID: urunlerObje.keySet()) {
        				JSONObject urun = istek.urun(girisYapan.getString("personelSifreHashed"), Long.parseLong(urunID));
        				urun.put("miktar", urunlerObje.getInt(urunID));
        				urunler.put(urun);
        			}
        			for(int i = 0 ; i < urunler.length() ; i++) {
        				odemeitemholder.getChildren().add(le.odemeitempane("odeme"+urunler.getJSONObject(i).getLong("id")
        				, urunler.getJSONObject(i).getString("urunAdi"), ""+urunler.getJSONObject(i).getDouble("urunFiyati")
        				, ""+urunler.getJSONObject(i).getInt("miktar")));
        			}
        		}
        		JSONArray odemeYontemleri = istek.odemeYontemleri(girisYapan.getString("personelSifreHashed"));
            	if(odemeYontemleri.getJSONObject(0).getInt("yanitKodu")==HttpStatus.OK.value()) {
            		odemeYontemiIsimleri = new HashMap<String, Long>();
            		odemeyontemicombo.getItems().clear();
            		for(int i = 0 ; i < odemeYontemleri.length() ; i++) {
            			odemeyontemicombo.getItems().add(odemeYontemleri.getJSONObject(i).getString("odemeYontemiAdi"));
            			odemeYontemiIsimleri.put(odemeYontemleri.getJSONObject(i).getString("odemeYontemiAdi"), odemeYontemleri.getJSONObject(i).getLong("id"));
            		}
            		sipariscombo.setOnAction(new EventHandler<ActionEvent>() {
    					@Override
    					public void handle(ActionEvent arg0) {
    						if(urunTuruIsimleri.get(sipariscombo.getValue()) != null) {
    							skihguncelle(urunTuruIsimleri.get(sipariscombo.getValue()));
    						}
    					}
                	});
            		odemebahsistxtf.textProperty().addListener(new ChangeListener<String>() {
    					@Override
    					public void changed(ObservableValue<? extends String> arg0, String eski, String yeni) {
    						try {
    							bahsis = Double.parseDouble(yeni);
    						}
    						catch(Exception e) {
    							e.printStackTrace();
    						}
    						odemegeneltxt.setText(""+(Double.parseDouble(odemearatxt.getText())+bahsis));
    					}
                	});
                	siparispane.setVisible(false);
                	odemepane.setVisible(true);
            	}
            }
        });
        odemetamamlabtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
            	JSONObject odeme = istek.odeme(girisYapan.getString("personelSifreHashed"), masaSiparisID
            			, odemeYontemiIsimleri.get(odemeyontemicombo.getValue()), bahsis, odemenottxtf.getText());
            	if(odeme.getInt("yanitKodu")==HttpStatus.OK.value()) {
            		odemepane.setVisible(false);
                	masalarpane.setVisible(true);
            	}
            }
        });
	}	
	public void mkihguncelle(long urunTuruID) {
		menukitemholder.getChildren().clear();
		JSONArray urunler = istek.urunler(girisYapan.getString("personelSifreHashed")
				, urunTuruID);
		if(urunler.getJSONObject(0).getInt("yanitKodu")==HttpStatus.OK.value()) {
			for(int i = 0 ; i < urunler.length() ; i++) {
				menukitemholder.getChildren().add(le.menukitempane("menuk"+urunler.getJSONObject(i).getLong("id")
				, urunler.getJSONObject(i).getString("urunAdi"), ""+urunler.getJSONObject(i).getDouble("urunFiyati"), menukdevent, menukcevent));
			}
		}
	}
	public void skihguncelle(long urunTuruID) {
		sipariskitemholder.getChildren().clear();
		JSONArray urunler = istek.urunler(girisYapan.getString("personelSifreHashed")
				, urunTuruID);
		if(urunler.getJSONObject(0).getInt("yanitKodu")==HttpStatus.OK.value()) {
			for(int i = 0 ; i < urunler.length() ; i++) {
				sipariskitemholder.getChildren().add(le.sipariskitempane("siparisk"+urunler.getJSONObject(i).getLong("id")
				, urunler.getJSONObject(i).getString("urunAdi"), ""+urunler.getJSONObject(i).getDouble("urunFiyati"), sipariskeevent));
			}
		}
	}
	public void ssihguncelle(String ssihMasaAdi) {
		JSONObject masaSiparisi = istek.masaSiparisi(girisYapan.getString("personelSifreHashed"), ssihMasaAdi);
		if(masaSiparisi.getInt("yanitKodu")==HttpStatus.OK.value()) {
			siparissitemler = new ArrayList<Node>();
	    	siparissitemholder.getChildren().clear();
			siparistoplamtxt.setText(""+masaSiparisi.getDouble("toplamTutar"));
			JSONObject urunlerObje = new JSONObject(masaSiparisi.getString("urunler"));
			JSONArray urunler = new JSONArray();
			for(String urunID: urunlerObje.keySet()) {
				JSONObject urun = istek.urun(girisYapan.getString("personelSifreHashed"), Long.parseLong(urunID));
				urun.put("miktar", urunlerObje.getInt(urunID));
				urunler.put(urun);
			}
			for(int i = 0 ; i < urunler.length() ; i++) {
				siparissitemholder.getChildren().add(le.siparissitempane("sipariss"+urunler.getJSONObject(i).getLong("id")
				, urunler.getJSONObject(i).getString("urunAdi"), ""+urunler.getJSONObject(i).getDouble("urunFiyati")
				, ""+urunler.getJSONObject(i).getInt("miktar"), siparisscevent));
			}
		}
	}
}
