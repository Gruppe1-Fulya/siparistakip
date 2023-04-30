package javamudavimleri.siparistakip.spring.veritabani.db;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import javamudavimleri.siparistakip.spring.veritabani.Siparis;

@Transactional
public interface SiparisDB extends JpaRepository<Siparis, Long> {

}