package javamudavimleri.siparistakip.spring.veritabani;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MasaDB extends JpaRepository<Masa, Long> {
	Masa findByMasaAdi(String masaAdi);
	void deleteByMasaAdi(String masaAdi);
}