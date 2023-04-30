package javamudavimleri.siparistakip.spring.veritabani.db;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import javamudavimleri.siparistakip.spring.veritabani.Odeme;

@Transactional
public interface OdemeDB extends JpaRepository<Odeme, Long> {
	
}