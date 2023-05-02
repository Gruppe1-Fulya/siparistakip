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

import javamudavimleri.siparistakip.spring.veritabani.Masa;
import javamudavimleri.siparistakip.spring.veritabani.Personel;
import javamudavimleri.siparistakip.spring.veritabani.Siparis;
import javamudavimleri.siparistakip.spring.veritabani.db.IzinSeviyeDB;
import javamudavimleri.siparistakip.spring.veritabani.db.MasaDB;
import javamudavimleri.siparistakip.spring.veritabani.db.PersonelDB;
import javamudavimleri.siparistakip.spring.veritabani.db.SiparisDB;

@Controller
public class MasaController {
	@Autowired
    private PersonelDB personeldb;
	@Autowired
    private IzinSeviyeDB izinseviyedb;
	@Autowired
    private MasaDB masadb;
	@Autowired
    private SiparisDB siparisdb;
	@GetMapping("/masaSiparisi")
    @ResponseBody
    public ResponseEntity<Siparis> masaSiparisi(@RequestParam(name = "sifreHashed")String sifreHashed
    							, @RequestParam(name = "masaAdi")String masaAdi){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
			masaAdi = URLDecoder.decode(masaAdi, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Siparis(-1L));
		}
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Siparis(-1L));
    	}
    	if(masadb.findByMasaAdi(masaAdi)==null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Siparis(-1L));
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(siparisdb.findById(masadb.findByMasaAdi(masaAdi).getSiparisID()).get());
		
    }
	@PostMapping("/masaAc")
    @ResponseBody
    public ResponseEntity<String> masaAc(@RequestParam(name = "sifreHashed")String sifreHashed
    							, @RequestParam(name = "masaAdi")String masaAdi){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
			masaAdi = URLDecoder.decode(masaAdi, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametreler hatalı! ");
		}
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erişim izniniz yok! ");
    	}
		if(masadb.findByMasaAdi(masaAdi)!=null) {
			masadb.deleteByMasaAdi(masaAdi);
		}
		masadb.save(new Masa(masaAdi, siparisdb.save(new Siparis(girisYapan.getId(), System.currentTimeMillis(), masaAdi)).getId()));
		return ResponseEntity.status(HttpStatus.OK).body("İşlem tamamlandı! ");
    }
	@PostMapping("/masaKapat")
    @ResponseBody
    public ResponseEntity<String> masaKapat(@RequestParam(name = "sifreHashed")String sifreHashed
    							, @RequestParam(name = "masaAdi")String masaAdi){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
			masaAdi = URLDecoder.decode(masaAdi, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametreler hatalı! ");
		}
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erişim izniniz yok! ");
    	}
    	if(izinseviyedb.findById(girisYapan.getIzinSeviyeID()).get().getIzinSeviyesi() 
    			< izinseviyedb.findByIzinSeviyeAdi("Kasiyer").getIzinSeviyesi()) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erişim izniniz yok! ");
    	}
    	if(masadb.findByMasaAdi(masaAdi)==null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Masa bulunamadı! ");
    	}
    	try {
    		masadb.deleteByMasaAdi(masaAdi);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		return ResponseEntity.status(HttpStatus.OK).body("İşlem tamamlandı! ");
    }
}