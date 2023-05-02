package javamudavimleri.siparistakip.spring.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javamudavimleri.siparistakip.spring.veritabani.OdemeYontemi;
import javamudavimleri.siparistakip.spring.veritabani.Personel;
import javamudavimleri.siparistakip.spring.veritabani.db.OdemeYontemiDB;
import javamudavimleri.siparistakip.spring.veritabani.db.PersonelDB;

@Controller
public class OdemeYontemiController {
	private String masterKey = "JavaMudavimleri";
	@Autowired
    private PersonelDB personeldb;
	@Autowired
    private OdemeYontemiDB odemeyontemidb;
	@PostMapping("/odemeYontemiEkle")
    @ResponseBody
    public ResponseEntity<String> odemeYontemiEkle(@RequestParam(name = "masterKey")String masterKey
    							, @RequestParam(name = "odemeYontemiAdi")String odemeYontemiAdi){
		try {
			masterKey = URLDecoder.decode(masterKey, "UTF-8");
			odemeYontemiAdi = URLDecoder.decode(odemeYontemiAdi, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametreler hatalı! ");
		}
		
		if(masterKey.equals(this.masterKey)) {
			if(odemeyontemidb.findByOdemeYontemiAdi(odemeYontemiAdi)!=null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zaten var! ");
			}
			odemeyontemidb.save(new OdemeYontemi(odemeYontemiAdi));
			return ResponseEntity.status(HttpStatus.OK).body("İşlem tamamlandı! ");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erişim izniniz yok! ");
    }
	@GetMapping("/odemeYontemleri")
    @ResponseBody
    public ResponseEntity<List<OdemeYontemi>> odemeYontemleri(@RequestParam(name = "sifreHashed")String sifreHashed){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<OdemeYontemi>());
		}
		
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<OdemeYontemi>());
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(odemeyontemidb.findAll());
    }
}