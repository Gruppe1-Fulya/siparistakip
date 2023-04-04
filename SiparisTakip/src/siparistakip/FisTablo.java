package siparistakip;

import java.util.Date;

public class FisTablo {
	private String masaAdi;
	private int adisyonNo;
	private double tutar;
	private double tipToplami;
	private double odenenTutar;
	private String odemeTuru;
	private Date odenilenTarih;
	private String personelAdi;
	private String odemeNotu;
	public FisTablo(String masaAdi, int adisyonNo, double tutar, double tipToplami, double odenenTutar, 
			String odemeTuru, Date odenilenTarih, String personelAdi, String odemeNotu) {
		this.setMasaAdi(masaAdi);
		this.setAdisyonNo(adisyonNo);
		this.setTutar(tutar);
		this.setTipToplami(tipToplami);
		this.setOdenenTutar(odenenTutar);
		this.setOdemeTuru(odemeTuru);
		this.setOdenilenTarih(odenilenTarih);
		this.setPersonelAdi(personelAdi);
		this.setOdemeNotu(odemeNotu);
	}
	public String getMasaAdi() {
		return masaAdi;
	}
	public void setMasaAdi(String masaAdi) {
		this.masaAdi = masaAdi;
	}
	public int getAdisyonNo() {
		return adisyonNo;
	}
	public void setAdisyonNo(int adisyonNo) {
		this.adisyonNo = adisyonNo;
	}
	public double getTutar() {
		return tutar;
	}
	public void setTutar(double tutar) {
		this.tutar = tutar;
	}
	public double getTipToplami() {
		return tipToplami;
	}
	public void setTipToplami(double tipToplami) {
		this.tipToplami = tipToplami;
	}
	public double getOdenenTutar() {
		return odenenTutar;
	}
	public void setOdenenTutar(double odenenTutar) {
		this.odenenTutar = odenenTutar;
	}
	public String getOdemeTuru() {
		return odemeTuru;
	}
	public void setOdemeTuru(String odemeTuru) {
		this.odemeTuru = odemeTuru;
	}
	public Date getOdenilenTarih() {
		return odenilenTarih;
	}
	public void setOdenilenTarih(Date odenilenTarih) {
		this.odenilenTarih = odenilenTarih;
	}
	public String getPersonelAdi() {
		return personelAdi;
	}
	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}
	public String getOdemeNotu() {
		return odemeNotu;
	}
	public void setOdemeNotu(String odemeNotu) {
		this.odemeNotu = odemeNotu;
	}
}
