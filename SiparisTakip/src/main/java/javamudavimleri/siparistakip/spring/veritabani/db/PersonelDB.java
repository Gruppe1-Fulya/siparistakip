package javamudavimleri.siparistakip.spring.veritabani.db;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import javamudavimleri.siparistakip.spring.veritabani.Personel;

@Transactional
public interface PersonelDB extends JpaRepository<Personel, Long> {
	Personel findByPersonelSifreHashed(String personelSifreHashed);
}