package javamudavimleri.siparistakip.fx;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class ListeEleman {
	public AnchorPane odemeitempane(String id, String isim, String fiyat, String adet) {
		AnchorPane item=new AnchorPane();
		item.setPrefWidth(640);
		item.setPrefHeight(60);
		Text[] itemler = new Text[6];
		String[] yazilar = new String[]{isim, "Birim Fiyatı: ", fiyat, " ₺", "Adet: ", adet};
		double[] yazix = new double[]{21, 139, 220, 250, 320, 361};
		item.setId(id);
		for(int i = 0 ; i < yazix.length ; i++) {
			itemler[i] = new Text();
			itemler[i].setText(yazilar[i]);
			itemler[i].setLayoutX(yazix[i]);
			itemler[i].setLayoutY(32);
			item.getChildren().add(itemler[i]);
		}
		return item;
	}
	public AnchorPane sipariskitempane(String id, String isim, String fiyat, EventHandler<ActionEvent> ekleevent) {
		AnchorPane item = new AnchorPane();
		item.setPrefWidth(640);
		item.setPrefHeight(60);
		Text[] itemler = new Text[5];
		String[] yazilar = new String[]{isim, "Birim Fiyatı: ", fiyat, " ₺", "Miktar: "};
		double[] yazix = new double[]{21, 172, 255, 285, 413};
		item.setId(id);
		for(int i = 0 ; i < yazix.length ; i++) {
			itemler[i] = new Text();
			itemler[i].setText(yazilar[i]);
			itemler[i].setLayoutX(yazix[i]);
			itemler[i].setLayoutY(35);
			item.getChildren().add(itemler[i]);
		}
		TextField miktar = new TextField("1");
		miktar.setLayoutX(461);
		miktar.setLayoutY(17);
		miktar.setPrefWidth(34);
		miktar.setPrefHeight(26);
		miktar.setId(id+"m");
		item.getChildren().add(miktar);
		Button ekle = new Button("Ekle");
		ekle.setLayoutX(522);
		ekle.setLayoutY(17);
		ekle.setOnAction(ekleevent);
		item.getChildren().add(ekle);
		return item;
	}
	public AnchorPane siparissitempane(String id, String isim, String fiyat, String adet, EventHandler<ActionEvent> cikarevent) {
		AnchorPane item = new AnchorPane();
		item.setPrefWidth(640);
		item.setPrefHeight(60);
		Text[] itemler = new Text[6];
		String[] yazilar = new String[]{isim, "Birim Fiyatı: ", fiyat, " ₺", "Adet: ", adet};
		double[] yazix = new double[]{21, 139, 220, 250, 320, 360};
		item.setId(id);
		for(int i = 0 ; i < yazix.length ; i++) {
			itemler[i] = new Text();
			itemler[i].setText(yazilar[i]);
			itemler[i].setLayoutX(yazix[i]);
			itemler[i].setLayoutY(33);
			item.getChildren().add(itemler[i]);
		}
		TextField miktar = new TextField("1");
		miktar.setLayoutX(482);
		miktar.setLayoutY(17);
		miktar.setPrefWidth(34);
		miktar.setPrefHeight(26);
		miktar.setId(id+"m");
		item.getChildren().add(miktar);
		Button cikar = new Button("Çıkar");
		cikar.setLayoutX(522);
		cikar.setLayoutY(17);
		cikar.setOnAction(cikarevent);
		item.getChildren().add(cikar);
		return item;
	}
	public AnchorPane menukitempane(String id, String isim, String fiyat, EventHandler<ActionEvent> duzenleevent, EventHandler<ActionEvent> cikarevent) {
		AnchorPane item = new AnchorPane();
		item.setPrefWidth(640);
		item.setPrefHeight(60);
		item.setId(id);
		TextField ad = new TextField(isim);
		ad.setLayoutX(14);
		ad.setLayoutY(17);
		ad.setPrefWidth(112);
		ad.setPrefHeight(26);
		ad.setId(id+"a");
		item.getChildren().add(ad);
		Text fiyatsabit = new Text("Birim Fiyatı: ");
		fiyatsabit.setLayoutX(172);
		fiyatsabit.setLayoutY(35);
		item.getChildren().add(fiyatsabit);
		TextField deger = new TextField(fiyat);
		deger.setLayoutX(252);
		deger.setLayoutY(17);
		deger.setPrefWidth(41);
		deger.setPrefHeight(26);
		deger.setId(id+"d");
		item.getChildren().add(deger);
		Text fiyatbirim = new Text(" ₺");
		fiyatbirim.setLayoutX(293);
		fiyatbirim.setLayoutY(35);
		item.getChildren().add(fiyatbirim);
		Button duzenle = new Button("Düzenle");
		duzenle.setLayoutX(390);
		duzenle.setLayoutY(15);
		duzenle.setOnAction(duzenleevent);
		item.getChildren().add(duzenle);
		Button cikar = new Button("Çıkar");
		cikar.setLayoutX(522);
		cikar.setLayoutY(15);
		cikar.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		cikar.setOnAction(cikarevent);
		item.getChildren().add(cikar);
		return item;
	}
}