package javamudavimleri.siparistakip.spring.veritabani.db;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import javamudavimleri.siparistakip.spring.veritabani.IzinSeviye;

@Transactional
public interface IzinSeviyeDB extends JpaRepository<IzinSeviye, Long> {
	IzinSeviye findByIzinSeviyeAdi(String izinSeviyeAdi);
}