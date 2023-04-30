package javamudavimleri.siparistakip.spring.veritabani.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import javamudavimleri.siparistakip.spring.veritabani.Urun;

@Transactional
public interface UrunDB extends JpaRepository<Urun, Long> {
	Urun findByUrunAdi(String urunAdi);
	List<Urun> findByUrunTuruID(long urunTuruID);
}