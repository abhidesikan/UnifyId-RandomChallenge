import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.security.*;

public class RandomClient {


	private static String randomUrl = "https://www.random.org/integers/?";

	/**
	 *
	 * @param num number of integers
	 * @param min lower bound of integers
	 * @param max upper bound of integers
	 * @param col number of columns in which integers will be arranged
	 * @param base base for numbers
	 * @param format html/plain
	 * @param rnd randmoization used to generate numbers
	 * @return
	 * @throws Exception
	 */
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

		//create a secure random number generator
		SecureRandom random = new SecureRandom();
		//get seed bytes from random input source
		byte[] bytes = random.generateSeed(Integer.parseInt(getRandomNumber(1, 1, 1000000, 1, 10, "plain", "new")));
		//initialize SecureRandom constructor with seed bytes
		random = new SecureRandom(bytes);

		//Generate key pair generator using secure random as source of randomness
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024, random);
		KeyPair pair = keyPairGenerator.generateKeyPair();
		PrivateKey priv = pair.getPrivate();
		PublicKey pub = pair.getPublic();

		//Output private and public key
		System.out.println(priv);
		System.out.println(pub);
	}
}
