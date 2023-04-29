package javamudavimleri.siparistakip.fx.net;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class Istek {
	private String baseURL = "http://localhost:8080/";
	public Istek() {}
	public JSONObject girisYap(String sifre) {
		String sifreHashed = hashSHA256(sifre);
		if(sifreHashed=="basarisiz") {
			JSONObject yanit = new JSONObject();
			yanit.put("yanitKodu", -1);
			return yanit;
		}
		return istekJSONObject("girisYap?sifreHashed="+sifreHashed, "GET", false);
	}
	public JSONArray urunTurleri(String sifreHashed) {
		try {
			return istekJSONArray("urunTurleri?sifreHashed="+URLEncoder.encode(sifreHashed, "UTF-8"), "GET");
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		return istekJSONArray("bos", "GET");
	}
	public JSONObject urunEkle(String sifreHashed, String urunAdi, double urunFiyati, long urunTuruID) {
		try {
			return istekJSONObject("urunEkle?sifreHashed="+URLEncoder.encode(sifreHashed, "UTF-8")
					+"&urunAdi="+URLEncoder.encode(urunAdi, "UTF-8")+"&urunFiyati="+URLEncoder.encode(""+urunFiyati, "UTF-8")
					+"&urunTuruID="+URLEncoder.encode(""+urunTuruID, "UTF-8"), "POST", true);
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return istekJSONObject("bos", "POST", true);
	}
	public JSONObject urunCikar(String sifreHashed, String urunID) {
		try {
			return istekJSONObject("urunCikar?sifreHashed="+URLEncoder.encode(sifreHashed, "UTF-8")
					+"&urunID="+URLEncoder.encode(urunID, "UTF-8"), "POST", true);
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return istekJSONObject("bos", "POST", true);
	}
	public JSONArray urunler(String sifreHashed, long urunTuruID) {
		try {
			return istekJSONArray("urunler?sifreHashed="+URLEncoder.encode(sifreHashed, "UTF-8")
					+"&urunTuruID="+URLEncoder.encode(""+urunTuruID, "UTF-8"), "GET");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return istekJSONArray("bos", "GET");
	}
	public JSONObject masaSiparisi(String sifreHashed, String masaAdi) {
		try {
			return istekJSONObject("masaSiparisi?sifreHashed="+URLEncoder.encode(sifreHashed, "UTF-8")
					+"&masaAdi="+URLEncoder.encode(masaAdi, "UTF-8"), "GET", false);
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return istekJSONObject("bos", "GET", false);
	}
	public JSONObject masaGuncelle(String sifreHashed, String masaAdi) {
		try {
			return istekJSONObject("masaGuncelle?sifreHashed="+URLEncoder.encode(sifreHashed, "UTF-8")
					+"&masaAdi="+URLEncoder.encode(masaAdi, "UTF-8"), "POST", true);
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return istekJSONObject("bos", "POST", true);
	}
	public JSONObject urun(String sifreHashed, long urunID) {
		try {
			return istekJSONObject("urun?sifreHashed="+URLEncoder.encode(sifreHashed, "UTF-8")
					+"&urunID="+URLEncoder.encode(""+urunID, "UTF-8"), "GET", false);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return istekJSONObject("bos", "GET", false);
	}
	public JSONObject siparisGuncelle(String sifreHashed, long siparisID, long urunID, int miktar) {
		try {
			return istekJSONObject("siparisGuncelle?sifreHashed="+URLEncoder.encode(sifreHashed, "UTF-8")
					+"&siparisID="+URLEncoder.encode(""+siparisID, "UTF-8")+"&urunID="+URLEncoder.encode(""+urunID, "UTF-8")
					+"&miktar="+URLEncoder.encode(""+miktar, "UTF-8"), "POST", true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return istekJSONObject("bos", "POST", true);
	}
	public String hashSHA256(String girdi) {
		HexFormat hf = HexFormat.of();
		String sifreHashed = "basarisiz";
		try {
			sifreHashed =  hf.formatHex(MessageDigest.getInstance("SHA-256").digest(girdi.getBytes(StandardCharsets.UTF_8)));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return sifreHashed;
	}
	public JSONObject istekJSONObject(String url, String istek_turu, boolean safStringMi) {
		JSONObject yanit = new JSONObject();
		int yanitKodu = -1;
		if(!url.equals("bos")) {
			try {
				HttpURLConnection con = (HttpURLConnection) new URL(baseURL + url).openConnection();
				con.setRequestMethod(istek_turu);
				con.setRequestProperty("Accept", "application/json");
				yanitKodu = con.getResponseCode();
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuffer response = new StringBuffer();
				String inputLine = in.readLine();
				while (inputLine != null) {
					response.append(inputLine);
					inputLine = in.readLine();
				}
				in.close();
				if(!safStringMi) {
					yanit= new JSONObject(response.toString());
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		yanit.put("yanitKodu", yanitKodu);
		return yanit;
	}
	public JSONArray istekJSONArray(String url, String istek_turu) {
		JSONArray yanit = new JSONArray();
		yanit.put(new JSONObject());
		int yanitKodu = -1;
		if(!url.equals("bos")) {
			try {
				HttpURLConnection con = (HttpURLConnection) new URL(baseURL + url).openConnection();
				con.setRequestMethod(istek_turu);
				con.setRequestProperty("Accept", "application/json");
				yanitKodu = con.getResponseCode();
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuffer response = new StringBuffer();
				String inputLine = in.readLine();
				while (inputLine != null) {
					response.append(inputLine);
					inputLine = in.readLine();
				}
				in.close();
				yanit = new JSONArray(response.toString());
				if(yanit.length()==0) {
					yanit.put(new JSONObject());
					yanitKodu = -1;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		yanit.getJSONObject(0).put("yanitKodu", yanitKodu);
		return yanit;
	}
}