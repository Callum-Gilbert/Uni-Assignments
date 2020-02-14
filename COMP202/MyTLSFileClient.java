import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import javax.naming.ldap.*;
import javax.net.*;
import java.io.*;

class MyTLSFileClient {

	public static void main(String args[])
	{
		

		try{
		//checking input length
		if(args.length!=3)
		{
		System.err.println("Incorrect user input");
		return;
		}
		//assigning values off of the input 
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		String filename = args[2];
		File newFile = new File("_"+filename);
		byte[] byteArray = new byte[1500];

		//setting ssl socket factory to default
		SSLSocketFactory factory =(SSLSocketFactory)SSLSocketFactory.getDefault();
		SSLSocket socket =(SSLSocket)factory.createSocket(host, port);
		
	

		//automatic verification for host name 		
		SSLParameters params = new SSLParameters();
		params.setEndpointIdentificationAlgorithm("HTTPS");
		socket.setSSLParameters(params);
		socket.startHandshake();
		//creating bufferedoutputstream, bufferedwriter and bufferedinputstream
		BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(bos));
		BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
		//creating the inputstream and outputstream
		InputStream reader = socket.getInputStream();
		OutputStream out = new FileOutputStream(newFile);
		//writing the file name 
		String s = filename + "\n";
		w.write(s);
		w.flush();
		System.out.println(filename+" Sent");
		int counter;
		//writing to the new file
		while((counter = reader.read(byteArray))>0)
		{
			out.write(byteArray, 0,counter);
			out.flush();
		}	
		


		}
		catch(Exception ex)
		{
			System.err.println(ex);
		}
		

		


	
	}




}
