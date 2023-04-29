package javamudavimleri.siparistakip.spring.veritabani;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdemeDB extends JpaRepository<Odeme, Long> {
	
}