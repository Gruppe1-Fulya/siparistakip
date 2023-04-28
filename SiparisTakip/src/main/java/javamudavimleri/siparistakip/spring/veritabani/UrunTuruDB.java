package javamudavimleri.siparistakip.spring.veritabani;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrunTuruDB extends JpaRepository<UrunTuru, Long> {
	UrunTuru findByUrunTuruAdi(String urunTuruAdi);
}