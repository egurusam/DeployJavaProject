package github;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/**
 * @author C
 *
 */
 
public class CLoadGithubContent {

//		String link = "https://github.com/geb/geb-example-cucumber-jvm/blob/master/src/cucumber/resources/features/gebish-custom.feature";
		static String link = "https://raw.githubusercontent.com/geb/geb-example-cucumber-jvm/master/src/cucumber/resources/features/";
//		String link = "https://raw.githubusercontent.com/C/All-in-One-Webmaster/master/all-in-one-webmaster-premium.php";

 
	public static HashMap<String,String> loadFeatureFromGithub(String link) throws Exception {
		URL cUrl = new URL(link);
		HttpURLConnection cHttp = (HttpURLConnection) cUrl.openConnection();
		Map<String, List<String>> cHeader = cHttp.getHeaderFields();
 
		// If URL is getting 301 and 302 redirection HTTP code then get new URL link.
		// This below for loop is totally optional if you are sure that your URL is not getting redirected to anywhere
		for (String header : cHeader.get(null)) {
			if (header.contains(" 302 ") || header.contains(" 301 ")) {
				link = cHeader.get("Location").get(0);
				cUrl = new URL(link);
				cHttp = (HttpURLConnection) cUrl.openConnection();
				cHeader = cHttp.getHeaderFields();
			}
		}
		InputStream cStream = cHttp.getInputStream();
		FeatureFile featureFile = new FeatureFile();
		featureFile.load(cStream);
		System.out.println("*****" + cGetStringFromStream(cStream));
		cStream.close();
		return featureFile;
	}

	public static HashMap<String,String> loadFeatureFromDisk(String filePath) throws Exception {
		InputStream cStream = new FileInputStream(filePath);
		FeatureFile featureFile = new FeatureFile();
		featureFile.load(cStream);
		cStream.close();
		return featureFile;
	}
	
        // ConvertStreamToString() Utility - we name it as cGetStringFromStream()
	private static String cGetStringFromStream(InputStream cStream) throws IOException {
		if (cStream != null) {
			Writer cWriter = new StringWriter();
 
			char[] cBuffer = new char[2048];
			try {
				Reader cReader = new BufferedReader(new InputStreamReader(cStream, "UTF-8"));
				int counter;
				while ((counter = cReader.read(cBuffer)) != -1) {
					cWriter.write(cBuffer, 0, counter);
				}
			} finally {
				cStream.close();
			}
			return cWriter.toString();
		} else {
			return "No Contents";
		}
	}
	
	public static void main(String[] args) throws Exception {
		CLoadGithubContent cLoadGithubContent = new CLoadGithubContent();
		System.out.println("Loading feature from disk ..");
		//System.out.println(cLoadGithubContent.loadFeatureFromDisk(args[0]));
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Loading feature from URL ..");
		System.out.println(cLoadGithubContent.loadFeatureFromGithub(link));
	}
}