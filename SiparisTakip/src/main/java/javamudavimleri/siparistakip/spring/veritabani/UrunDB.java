package javamudavimleri.siparistakip.spring.veritabani;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrunDB extends JpaRepository<Urun, Long> {
	Urun findByUrunAdi(String urunAdi);
	List<Urun> findByUrunTuruID(Long urunTuruID);
}