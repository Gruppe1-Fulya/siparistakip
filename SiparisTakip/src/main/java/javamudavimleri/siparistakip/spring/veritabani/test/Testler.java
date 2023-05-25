package javamudavimleri.siparistakip.spring.veritabani.test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javamudavimleri.siparistakip.spring.veritabani.*;
import javamudavimleri.siparistakip.spring.veritabani.db.*;

@DataJpaTest
public class Testler {
	private String masterKey = "JavaMudavimleri";
	
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
	public Testler() {}
	
	@Test
	public void personelTest() {
		long sayi = personeldb.count();
		System.out.println("onceki sayi: "+sayi);
		personeldb.save(new Personel("test", 2, "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"));
		System.out.println("simdiki sayi: "+personeldb.count());
		assertEquals(sayi+1, personeldb.count());
	}
}