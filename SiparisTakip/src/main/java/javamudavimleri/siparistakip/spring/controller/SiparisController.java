package javamudavimleri.siparistakip.spring.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javamudavimleri.siparistakip.spring.veritabani.Personel;
import javamudavimleri.siparistakip.spring.veritabani.Siparis;
import javamudavimleri.siparistakip.spring.veritabani.db.PersonelDB;
import javamudavimleri.siparistakip.spring.veritabani.db.SiparisDB;
import javamudavimleri.siparistakip.spring.veritabani.db.UrunDB;

@Controller
public class SiparisController {
	@Autowired
    private PersonelDB personeldb;
	@Autowired
    private UrunDB urundb;
	@Autowired
    private SiparisDB siparisdb;
	@PostMapping("/siparisGuncelle")
    @ResponseBody
    public ResponseEntity<String> siparisGuncelle(@RequestParam(name = "sifreHashed")String sifreHashed
    							, @RequestParam(name = "siparisID")String siparisID
    							, @RequestParam(name = "urunID")String urunID
    							, @RequestParam(name = "miktar")String miktar){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
			siparisID = URLDecoder.decode(siparisID, "UTF-8");
			urunID = URLDecoder.decode(urunID, "UTF-8");
			miktar = URLDecoder.decode(miktar, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametreler hatalı! ");
		}
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erişim izniniz yok! ");
    	}
		if(urundb.findById(Long.parseLong(urunID))==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ürun bulunamadı! ");
		}
		if(siparisdb.findById(Long.parseLong(siparisID))==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sipariş bulunamadı! ");
		}
		Siparis siparis = siparisdb.findById(Long.parseLong(siparisID)).get();
		siparis.siparisGuncelle(Long.parseLong(urunID), urundb.findById(Long.parseLong(urunID)).get().getUrunFiyati(), Integer.parseInt(miktar));
		siparisdb.save(siparis);
		return ResponseEntity.status(HttpStatus.OK).body("İşlem tamamlandı! ");
    }
}