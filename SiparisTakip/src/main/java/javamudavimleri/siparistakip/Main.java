package javamudavimleri.siparistakip;

import org.springframework.boot.SpringApplication;

import javamudavimleri.siparistakip.fx.FXMain;
import javamudavimleri.siparistakip.spring.SpringMain;

import javafx.application.Application;

public class Main{
	public static void main(String[] args) {
		SpringApplication.run(SpringMain.class, args);
		Application.launch(FXMain.class, args);
	}
}