package javamudavimleri.siparistakip.spring.controller;

import java.net.URLDecoder;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javamudavimleri.siparistakip.spring.veritabani.Odeme;
import javamudavimleri.siparistakip.spring.veritabani.Personel;
import javamudavimleri.siparistakip.spring.veritabani.db.IzinSeviyeDB;
import javamudavimleri.siparistakip.spring.veritabani.db.OdemeDB;
import javamudavimleri.siparistakip.spring.veritabani.db.OdemeYontemiDB;
import javamudavimleri.siparistakip.spring.veritabani.db.PersonelDB;
import javamudavimleri.siparistakip.spring.veritabani.db.SiparisDB;

@Controller
public class OdemeController {
	@Autowired
    private PersonelDB personeldb;
	@Autowired
    private IzinSeviyeDB izinseviyedb;
	@Autowired
    private SiparisDB siparisdb;
	@Autowired
    private OdemeYontemiDB odemeyontemidb;
	@Autowired
    private OdemeDB odemedb;
	@PostMapping("/odeme")
    @ResponseBody
    public ResponseEntity<String> odeme(@RequestParam(name = "sifreHashed")String sifreHashed
    							, @RequestParam(name = "siparisID")String siparisID
    							, @RequestParam(name = "odemeYontemiID")String odemeYontemiID
    							, @RequestParam(name = "bahsis")String bahsis
    							, @RequestParam(name = "odemeNotu")String odemeNotu){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
			siparisID = URLDecoder.decode(siparisID, "UTF-8");
			odemeYontemiID = URLDecoder.decode(odemeYontemiID, "UTF-8");
			bahsis = URLDecoder.decode(bahsis, "UTF-8");
			odemeNotu = URLDecoder.decode(odemeNotu, "UTF-8");
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
    	if(odemeyontemidb.findById(Long.parseLong(odemeYontemiID)).get()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ödeme yöntemi bulunamadı! ");
		}
		if(siparisdb.findById(Long.parseLong(siparisID))==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sipariş bulunamadı! ");
		}
		odemedb.save(new Odeme(Long.parseLong(siparisID), Long.parseLong(odemeYontemiID), girisYapan.getId(), Double.parseDouble(bahsis)
				, siparisdb.findById(Long.parseLong(siparisID)).get().getToplamTutar(), odemeNotu, System.currentTimeMillis()));
		return ResponseEntity.status(HttpStatus.OK).body("İşlem tamamlandı! ");
    }
	@GetMapping("/odemeGecmisi")
    @ResponseBody
    public ResponseEntity<String> odemeGecmisi(@RequestParam(name = "sifreHashed")String sifreHashed){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JSONArray().toString());
		}
		
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JSONArray().toString());
    	}
    	if(izinseviyedb.findById(girisYapan.getIzinSeviyeID()).get().getIzinSeviyesi() 
    			< izinseviyedb.findByIzinSeviyeAdi("Kasiyer").getIzinSeviyesi()) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JSONArray().toString());
    	}
    	JSONArray odemeler = new JSONArray(odemedb.findAll());
    	for(int i = 0 ; i < odemeler.length() ; i++) {
    		odemeler.getJSONObject(i).put("masaAdi"
    				, siparisdb.findById(odemeler.getJSONObject(i).getLong("siparisID")).get().getMasaAdi());
    		odemeler.getJSONObject(i).put("odemeYontemiAdi"
    				, odemeyontemidb.findById(odemeler.getJSONObject(i).getLong("odemeYontemiID")).get().getOdemeYontemiAdi());
    		odemeler.getJSONObject(i).put("personelAdi"
    				, personeldb.findById(odemeler.getJSONObject(i).getLong("personelID")).get().getPersonelAdi());
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(odemeler.toString());
    }
}