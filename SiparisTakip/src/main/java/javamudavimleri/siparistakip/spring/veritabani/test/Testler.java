package javamudavimleri.siparistakip.spring.veritabani.test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

import org.junit.jupiter.api.Test;

import javamudavimleri.siparistakip.spring.veritabani.*;
import javamudavimleri.siparistakip.spring.veritabani.db.*;

@DataJpaTest
public class Testler {
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
	@Autowired
    private OdemeYontemiDB odemeyontemidb;
	@Autowired
    private OdemeDB odemedb;
	
	@Test
	public void izinSeviyeTest() {
		String izinSeviyeAdi = "IzinSeviyeTest";
		int izinSeviyesi = 1;
		izinseviyedb.save(new IzinSeviye(izinSeviyeAdi, izinSeviyesi));
		assertEquals(izinSeviyesi, izinseviyedb.findByIzinSeviyeAdi(izinSeviyeAdi).getIzinSeviyesi());
	}
	
	@Test
	public void personelTest() {
		String personelAdi = "PersonelTest";
		String sifre = "1234";
		long izinSeviyeID = 2L;
		HexFormat hf = HexFormat.of();
		String sifreHashed = "basarisiz";
		try {
			sifreHashed =  hf.formatHex(MessageDigest.getInstance("SHA-256").digest(sifre.getBytes(StandardCharsets.UTF_8)));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		if(sifreHashed == "basarisiz") {
			assertEquals("sifreHashed", "basarisiz");
		}
		else {
			personeldb.save(new Personel(personelAdi, izinSeviyeID, sifreHashed));
			assertEquals(personelAdi, personeldb.findByPersonelSifreHashed(sifreHashed).getPersonelAdi());
		}
	}
	
	@Test
	public void urunTuruTest() {
		String urunTuruAdi = "UrunTuruTest";
		urunturudb.save(new UrunTuru(urunTuruAdi));
		assertEquals(urunTuruAdi, urunturudb.findByUrunTuruAdi(urunTuruAdi).getUrunTuruAdi());
	}
	
	@Test
	public void urunTest() {
		String urunAdi = "UrunTest";
		double urunFiyati = 1.25;
		long urunTuruID = 2L;
		urundb.save(new Urun(urunAdi, urunFiyati, urunTuruID));
		assertEquals(urunTuruID, urundb.findByUrunTuruID(urunTuruID).get(0).getUrunTuruID());
	}
	
	@Test
	public void siparisTest() {
		long personelID = 1L;
		long siparisZamani = System.currentTimeMillis();
		String masaAdi = "MasaTest";
		siparisdb.save(new Siparis(personelID, siparisZamani, masaAdi));
		assertEquals(1, siparisdb.count());
	}
	
	@Test
	public void masaTest() {
		String masaAdi = "MasaTest";
		long siparisID = 163L;
		masadb.save(new Masa(masaAdi, siparisID));
		assertEquals(masaAdi, masadb.findByMasaAdi(masaAdi).getMasaAdi());
	}
	
	@Test
	public void odemeYontemiTest() {
		String odemeYontemiAdi = "OdemeYontemiTest";
		odemeyontemidb.save(new OdemeYontemi(odemeYontemiAdi));
		assertEquals(odemeYontemiAdi, odemeyontemidb.findByOdemeYontemiAdi(odemeYontemiAdi).getOdemeYontemiAdi());
	}
	
	@Test
	public void odemeTest() {
		long siparisID = 163L;
		long odemeYontemiID = 1L;
		long personelID = 1L;
		double bahsis = 0.75;
		double araToplam =  75.00;
		String odemeNotu = "OdemeNotuTest";
		long odemeZamani = System.currentTimeMillis();
		odemedb.save(new Odeme(siparisID, odemeYontemiID, personelID, bahsis, araToplam, odemeNotu, odemeZamani));
		assertEquals(1, odemedb.count());
	}
	
}