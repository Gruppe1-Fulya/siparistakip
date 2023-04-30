package javamudavimleri.siparistakip.spring.veritabani.db;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import javamudavimleri.siparistakip.spring.veritabani.Masa;

@Transactional
public interface MasaDB extends JpaRepository<Masa, Long> {
	Masa findByMasaAdi(String masaAdi);
	void deleteByMasaAdi(String masaAdi);
}