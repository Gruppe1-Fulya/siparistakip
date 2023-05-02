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

import javamudavimleri.siparistakip.spring.veritabani.Personel;
import javamudavimleri.siparistakip.spring.veritabani.UrunTuru;
import javamudavimleri.siparistakip.spring.veritabani.db.PersonelDB;
import javamudavimleri.siparistakip.spring.veritabani.db.UrunTuruDB;

@Controller
public class UrunTuruController {
	private String masterKey = "JavaMudavimleri";
	@Autowired
    private UrunTuruDB urunturudb;
	@Autowired
    private PersonelDB personeldb;
	@PostMapping("/urunTuruEkle")
    @ResponseBody
    public ResponseEntity<String> urunTuruEkle(@RequestParam(name = "masterKey")String masterKey
    							, @RequestParam(name = "urunTuruAdi")String urunTuruAdi){
		try {
			masterKey = URLDecoder.decode(masterKey, "UTF-8");
			urunTuruAdi = URLDecoder.decode(urunTuruAdi, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametreler hatalı! ");
		}
		
		if(masterKey.equals(this.masterKey)) {
			if(urunturudb.findByUrunTuruAdi(urunTuruAdi)!=null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zaten var! ");
			}
			urunturudb.save(new UrunTuru(urunTuruAdi));
			return ResponseEntity.status(HttpStatus.OK).body("İşlem tamamlandı! ");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erişim izniniz yok! ");
    }
	@GetMapping("/urunTurleri")
    @ResponseBody
    public ResponseEntity<List<UrunTuru>> urunTurleri(@RequestParam(name = "sifreHashed")String sifreHashed){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<UrunTuru>());
		}
		
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<UrunTuru>());
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(urunturudb.findAll());
    }
}