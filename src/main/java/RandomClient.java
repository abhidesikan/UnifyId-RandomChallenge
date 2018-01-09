import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.security.*;

public class RandomClient {


	private static String randomUrl = "https://www.random.org/integers/?";

	public static String getRandomNumber(Integer num, Integer min, Integer max, Integer col, int base, String format, String rnd) throws Exception {

		randomUrl = randomUrl + "num=" + num + "&min=" + min + "&max=" + max + "&col=" + col + "&base=" + base
				+ "&format=" + format + "&rnd=" + rnd + "/";
		URL myUrl = new URL(randomUrl);
		HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
		InputStream is = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String inputLine;
		String value = "";

		while ((inputLine = br.readLine()) != null) {
			value = inputLine;
		}

		br.close();
		return value;
	}


	public static void main(String[] args) throws Exception {

		SecureRandom random = new SecureRandom();
		byte[] bytes = random.generateSeed(Integer.parseInt(getRandomNumber(1, 1, 1000000, 1, 10, "plain", "new")));
		random = new SecureRandom(bytes);

		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024, random);
		KeyPair pair = keyPairGenerator.generateKeyPair();
		PrivateKey priv = pair.getPrivate();
		PublicKey pub = pair.getPublic();

		System.out.println(priv);
		System.out.println(pub);
	}
}
