package javamudavimleri.siparistakip.spring.veritabani.db;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import javamudavimleri.siparistakip.spring.veritabani.OdemeYontemi;

@Transactional
public interface OdemeYontemiDB extends JpaRepository<OdemeYontemi, Long> {
	OdemeYontemi findByOdemeYontemiAdi(String odemeYontemiAdi);
}