package javamudavimleri.siparistakip.spring.veritabani.db;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import javamudavimleri.siparistakip.spring.veritabani.UrunTuru;

@Transactional
public interface UrunTuruDB extends JpaRepository<UrunTuru, Long> {
	UrunTuru findByUrunTuruAdi(String urunTuruAdi);
}