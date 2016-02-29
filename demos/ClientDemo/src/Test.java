import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class Test {

	public static void main(String[] args) {
		String servletUrl = "http://135.23.64.27:8080/SamsungServer/servlet/UploadPDF";
		String filePath = "E:\\filesToUpload\\fff.zip";
		
		try{
			File uploadFile = new File(filePath);
			
			URL url = new URL(servletUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setUseCaches(false);
	        httpConn.setDoOutput(true);
	        httpConn.setRequestMethod("POST");
	        httpConn.setRequestProperty("fileName", uploadFile.getName());
	        
	        OutputStream outputStream = httpConn.getOutputStream();
	        FileInputStream inputStream = new FileInputStream(uploadFile);
	        byte[] buffer = new byte[1024];
	        int len = -1;
	        while((len = inputStream.read(buffer))!=-1){
	        	outputStream.write(buffer, 0, len);
	        }
	       
	        System.out.println("Data was written.");
	        outputStream.close();
	        inputStream.close();
//	        httpConn.getInputStream();
	        System.out.println(HttpURLConnection.HTTP_OK);
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
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
