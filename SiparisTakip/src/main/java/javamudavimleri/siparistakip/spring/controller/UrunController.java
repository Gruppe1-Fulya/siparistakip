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

import javamudavimleri.siparistakip.spring.veritabani.Masa;
import javamudavimleri.siparistakip.spring.veritabani.Personel;
import javamudavimleri.siparistakip.spring.veritabani.Siparis;
import javamudavimleri.siparistakip.spring.veritabani.Urun;
import javamudavimleri.siparistakip.spring.veritabani.db.IzinSeviyeDB;
import javamudavimleri.siparistakip.spring.veritabani.db.MasaDB;
import javamudavimleri.siparistakip.spring.veritabani.db.PersonelDB;
import javamudavimleri.siparistakip.spring.veritabani.db.SiparisDB;
import javamudavimleri.siparistakip.spring.veritabani.db.UrunDB;
import javamudavimleri.siparistakip.spring.veritabani.db.UrunTuruDB;

@Controller
public class UrunController {
	@Autowired
    private UrunTuruDB urunturudb;
	@Autowired
    private PersonelDB personeldb;
	@Autowired
    private IzinSeviyeDB izinseviyedb;
	@Autowired
    private UrunDB urundb;
	@Autowired
    private MasaDB masadb;
	@Autowired
    private SiparisDB siparisdb;
	@PostMapping("/urunEkle")
    @ResponseBody
    public ResponseEntity<String> urunEkle(@RequestParam(name = "sifreHashed")String sifreHashed
    							, @RequestParam(name = "urunAdi")String urunAdi
    							, @RequestParam(name = "urunFiyati")String urunFiyati
    							, @RequestParam(name = "urunTuruID")String urunTuruID){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
			urunAdi = URLDecoder.decode(urunAdi, "UTF-8");
			urunFiyati = URLDecoder.decode(urunFiyati, "UTF-8");
			urunTuruID = URLDecoder.decode(urunTuruID, "UTF-8");
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
    	if(urunturudb.findById(Long.parseLong(urunTuruID))==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ürun türü bulunamadı! ");
		}
    	if(urundb.findByUrunAdi(urunAdi)!=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zaten var! ");
		}
    	urundb.save(new Urun(urunAdi, Double.parseDouble(urunFiyati), Long.parseLong(urunTuruID)));
    	return ResponseEntity.status(HttpStatus.OK).body("İşlem tamamlandı! ");
    }
	@PostMapping("/urunCikar")
    @ResponseBody
    public ResponseEntity<String> urunCikar(@RequestParam(name = "sifreHashed")String sifreHashed
    							, @RequestParam(name = "urunID")String urunID){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
			urunID = URLDecoder.decode(urunID, "UTF-8");
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
    	if(urundb.findById(Long.parseLong(urunID))==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ürun bulunamadı! ");
		}
    	for(Masa masa: masadb.findAll()){
    		Siparis siparis = siparisdb.findById(masa.getSiparisID()).get();
    		siparis.urunCikar(Long.parseLong(urunID), urundb.findById(Long.parseLong(urunID)).get().getUrunFiyati());
    		siparisdb.save(siparis);
    	}
    	urundb.deleteById(Long.parseLong(urunID));
    	return ResponseEntity.status(HttpStatus.OK).body("İşlem tamamlandı! ");
    }
	@GetMapping("/urunler")
    @ResponseBody
    public ResponseEntity<List<Urun>> urunler(@RequestParam(name = "sifreHashed")String sifreHashed
    							, @RequestParam(name = "urunTuruID")String urunTuruID){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
			urunTuruID = URLDecoder.decode(urunTuruID, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<Urun>());
		}
		
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<Urun>());
    	}
    	if(urunturudb.findById(Long.parseLong(urunTuruID))==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<Urun>());
		}
    	return ResponseEntity.status(HttpStatus.OK).body(urundb.findByUrunTuruID(Long.parseLong(urunTuruID)));
    }
	@GetMapping("/urun")
    @ResponseBody
    public ResponseEntity<Urun> urun(@RequestParam(name = "sifreHashed")String sifreHashed
    							, @RequestParam(name = "urunID")String urunID){
		try {
			sifreHashed = URLDecoder.decode(sifreHashed, "UTF-8");
			urunID = URLDecoder.decode(urunID, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Urun(-1L));
		}
		
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Urun(-1L));
    	}
    	if(urundb.findById(Long.parseLong(urunID))==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Urun(-1L));
		}
    	return ResponseEntity.status(HttpStatus.OK).body(urundb.findById(Long.parseLong(urunID)).get());
    }
}