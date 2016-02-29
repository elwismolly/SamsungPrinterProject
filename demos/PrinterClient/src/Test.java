import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
	public static void main(String[] args) {
		String servletUrl = "http://135.23.64.27:8080/SamsungServer/servlet/DownloadPDF";
		String filePath = "E:\\filesToUpload\\fff1.zip";
		try{
			URL url = new URL(servletUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setUseCaches(false);
	        httpConn.setDoOutput(true);
	        httpConn.setRequestMethod("POST");
	        
	        InputStream in = httpConn.getInputStream();
	        
	        File file = new File(filePath);
	        FileOutputStream fileOut = new FileOutputStream(file);
	        byte[] buffer = new byte[1024];
	        int len = -1;
	        while((len = in.read(buffer))!=-1){
	        	fileOut.write(buffer, 0, len);
	        }
	        
	        int responseCode = httpConn.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            // reads server's response
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    httpConn.getInputStream()));
	            String response = reader.readLine();
	            System.out.println("Server's response: " + response);
	        } else {
	            System.out.println("Server returned non-OK code: " + responseCode);
	        }
	        
	        
			in.close();
			fileOut.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
