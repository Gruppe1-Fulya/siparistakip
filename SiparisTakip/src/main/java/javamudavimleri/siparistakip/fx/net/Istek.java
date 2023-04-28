package javamudavimleri.siparistakip.fx.net;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Istek {
	public Istek() {}
	public JSONObject girisYap(String sifre) {
		String sifreHashed = hashSHA256(sifre);
		if(sifreHashed=="basarisiz") {
			JSONObject yanit = new JSONObject();
			yanit.put("yanitKodu", -1);
			return yanit;
		}
		return istekJSON("http://localhost:8080/girisYap?sifreHashed="+sifreHashed, "GET");
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
	public JSONObject istekJSON(String url, String istek_turu) {
		JSONObject yanit = new JSONObject();
		int yanitKodu = -1;
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
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
			yanit= new JSONObject(response.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		yanit.put("yanitKodu", yanitKodu);
		return yanit;
	}
}