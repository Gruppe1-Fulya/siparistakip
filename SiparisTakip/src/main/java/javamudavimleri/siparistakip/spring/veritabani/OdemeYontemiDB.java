package javamudavimleri.siparistakip.spring.veritabani;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdemeYontemiDB extends JpaRepository<OdemeYontemi, Long> {
	OdemeYontemi findByOdemeYontemiAdi(String odemeYontemiAdi);
}