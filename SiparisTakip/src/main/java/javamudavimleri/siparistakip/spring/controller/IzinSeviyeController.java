package javamudavimleri.siparistakip.spring.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javamudavimleri.siparistakip.spring.veritabani.IzinSeviye;
import javamudavimleri.siparistakip.spring.veritabani.db.IzinSeviyeDB;

@Controller
public class IzinSeviyeController {
	private String masterKey = "JavaMudavimleri";
	@Autowired
    private IzinSeviyeDB izinseviyedb;
	@PostMapping("/izinSeviyeEkle")
    @ResponseBody
    public ResponseEntity<String> izinSeviyeEkle(@RequestParam(name = "masterKey")String masterKey
    							, @RequestParam(name = "izinSeviyeAdi")String izinSeviyeAdi
    							, @RequestParam(name = "izinSeviyesi")String izinSeviyesi){
		try {
			masterKey = URLDecoder.decode(masterKey, "UTF-8");
			izinSeviyeAdi = URLDecoder.decode(izinSeviyeAdi, "UTF-8");
			izinSeviyesi = URLDecoder.decode(izinSeviyesi, "UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametreler hatalı! ");
		}
		
		if(masterKey.equals(this.masterKey)) {
			if(izinseviyedb.findByIzinSeviyeAdi(izinSeviyeAdi)!=null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zaten var! ");
			}
			izinseviyedb.save(new IzinSeviye(izinSeviyeAdi, Integer.parseInt(izinSeviyesi)));
			return ResponseEntity.status(HttpStatus.OK).body("İşlem tamamlandı! ");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erişim izniniz yok! ");
    }
}