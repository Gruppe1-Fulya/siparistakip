package javamudavimleri.siparistakip.spring.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javamudavimleri.siparistakip.spring.veritabani.IzinSeviye;
import javamudavimleri.siparistakip.spring.veritabani.Personel;
import javamudavimleri.siparistakip.spring.veritabani.db.IzinSeviyeDB;
import javamudavimleri.siparistakip.spring.veritabani.db.PersonelDB;

@Controller
public class PersonelController {
	private String masterKey = "JavaMudavimleri";
	@Autowired
    private IzinSeviyeDB izinseviyedb;
	@Autowired
	private PersonelDB personeldb;
	@PostMapping("/personelEkle")
    @ResponseBody
    public ResponseEntity<String> personelEkle(@RequestParam(name = "masterKey")String masterKey
    							, @RequestParam(name = "personelAdi")String personelAdi
    							, @RequestParam(name = "sifreHashed")String sifreHashed
    							, @RequestParam(name = "izinSeviyeAdi")String izinSeviyeAdi){
		try {
			masterKey = URLDecoder.decode(masterKey, "UTF-8");
			personelAdi = URLDecoder.decode(personelAdi, "UTF-8");
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
			izinSeviyeAdi = URLDecoder.decode(izinSeviyeAdi, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametreler hatalı! ");
		}
		
		if(masterKey.equals(this.masterKey)) {
			IzinSeviye izinseviye = izinseviyedb.findByIzinSeviyeAdi(izinSeviyeAdi);
			if(izinseviye==null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("İzin Seviyesi bulunamadı! ");
			}
			if(personeldb.findByPersonelSifreHashed(sifreHashed)!=null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zaten var! ");
			}
			personeldb.save(new Personel(personelAdi, izinseviye.getId(), sifreHashed));
			return ResponseEntity.status(HttpStatus.OK).body("İşlem tamamlandı! ");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erişim izniniz yok! ");
    }
	@GetMapping("/girisYap")
    @ResponseBody
    public ResponseEntity<Personel> sifreKontrol(@RequestParam(name = "sifreHashed")String sifreHashed){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Personel(-1L));
		}
		
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Personel(-1L));
    	}
    	return  ResponseEntity.status(HttpStatus.OK).body(girisYapan);
    }
}