
package github;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
 
/**
 * @author 
 *
 */
 
public class FeatureFile extends HashMap<String,String> {
 
	public static final String SCENARIO_PREFIX = "Scenario:";
 
	public FeatureFile() {
		// do nothing
	}

	public void load(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		load(in);
		in.close();
	}
	
	public void load(InputStream inputStream) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		int colonIndex = -1;
		while ((line=in.readLine()) != null) {
			line = line.trim();
			if (line.startsWith("#")) {
				// it is a comment, skip it
				continue;
			}
			colonIndex = line.indexOf(":");
			if (colonIndex > -1) {
				put(line.substring(0,colonIndex), line.substring(colonIndex+1));
			}
		}
	}
 
	}