package javamudavimleri.siparistakip.spring;

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
	@Autowired
    private PersonelDB personeldb;
	@Autowired
    private IzinSeviyeDB izinseviyedb;
	private String masterKey = "JavaMudavimleri";
	
	@PostMapping("/izinSeviyeEkle")
    @ResponseBody
    public ResponseEntity<String> izinSeviyeEkle(@RequestParam(name = "masterKey")String masterKey
    							, @RequestParam(name = "izinSeviyeAdi")String izinSeviyeAdi){
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
		Personel girisYapan = personeldb.findByPersonelSifreHashed(sifreHashed);
    	if(girisYapan==null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(girisYapan);
    	}
    	return  ResponseEntity.status(HttpStatus.OK).body(girisYapan);
    }
}