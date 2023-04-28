package javamudavimleri.siparistakip.spring;

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

import javamudavimleri.siparistakip.spring.veritabani.*;

@Controller
public class SpringController {
	private String masterKey = "JavaMudavimleri";
	
	@Autowired
    private UrunTuruDB urunturudb;
	@Autowired
    private PersonelDB personeldb;
	@Autowired
    private IzinSeviyeDB izinseviyedb;
	@Autowired
    private UrunDB urundb;
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
	@PostMapping("/izinSeviyeEkle")
    @ResponseBody
    public ResponseEntity<String> izinSeviyeEkle(@RequestParam(name = "masterKey")String masterKey
    							, @RequestParam(name = "izinSeviyeAdi")String izinSeviyeAdi){
		try {
			masterKey = URLDecoder.decode(masterKey, "UTF-8");
			izinSeviyeAdi = URLDecoder.decode(izinSeviyeAdi, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametreler hatalı! ");
		}
		
		if(masterKey.equals(this.masterKey)) {
			if(izinseviyedb.findByIzinSeviyeAdi(izinSeviyeAdi)!=null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zaten var! ");
			}
			izinseviyedb.save(new IzinSeviye(izinSeviyeAdi));
			return ResponseEntity.status(HttpStatus.OK).body("İşlem tamamlandı! ");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erişim izniniz yok! ");
    }
	@PostMapping("/personelEkle")
    @ResponseBody
    public ResponseEntity<String> personelEkle(@RequestParam(name = "masterKey")String masterKey
    							, @RequestParam(name = "personelAdi")String personelAdi
    							, @RequestParam(name = "personelSifreHashed")String sifreHashed
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
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Personel());
		}
		
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Personel());
    	}
    	return  ResponseEntity.status(HttpStatus.OK).body(girisYapan);
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
    	if(urunturudb.findById(Long.parseLong(urunTuruID))==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ürun türü bulunamadı! ");
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
    	if(urundb.findById(Long.parseLong(urunID))==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ürun bulunamadı! ");
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
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<Urun>());
		}
    	return ResponseEntity.status(HttpStatus.OK).body(urundb.findByUrunTuruID(Long.parseLong(urunTuruID)));
    }
}