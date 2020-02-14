import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import javax.naming.ldap.*;
import javax.net.*;
import java.io.*;

class MyTLSFileServer{
	
	public static void main(String args[])
	{
		try
		{	
			//initilize array and counter
			byte[] byteArray = new byte[1500];
			int counter;
			/*
			* use the getSSF method to get a
			* SSLServerSocketFactory and create our
			* SSLServerSocket, bound to specified port
			*/
			ServerSocketFactory ssf = getSSF();
			if(ssf==null)return;

		
			//creating ssl socket 
			SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(40202);
			String EnabledProtocols[] = {"TLSv1.2", "TLSv1.1"};
			ss.setEnabledProtocols(EnabledProtocols);
			SSLSocket s = (SSLSocket)ss.accept();
			//creating bufferedoutputstream, bufferedinputstream and a buffereed reader
System.out.println("file exists");
			BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
			BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
			BufferedReader r = new BufferedReader(new InputStreamReader(bis));

			//reading filename and printing 
			String filename = r.readLine();		
			System.out.println(filename+" recieved");
			//checking to see that the files isnt null and it exists
			if(filename !=null && new File(filename).exists())
			{	
					
				System.out.println("file exists");
				
				FileInputStream input = new FileInputStream(filename);
				//while not at end of file
				while((counter=input.read(byteArray)) !=-1)
				{	
					System.out.println("file sending");
					bos.write(byteArray,0,counter);
					bos.flush();
	
				}
				

			}
			//close socket
			s.close();
		}
		catch(Exception ex)
		{
			System.err.println(ex);
		}

		
	}

	private static ServerSocketFactory getSSF()
	{

		try
		{
			/*
			* Get an SSL Context that speaks some version
			* of TLS, a KeyManager that can hold certs in
			* X.509 format, and a JavaKeyStore (JKS)
			* instance
			*/
			SSLContext ctx = SSLContext.getInstance("TLS");
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			KeyStore ks = KeyStore.getInstance("JKS");

			//bad way of storing password
			char[] passphrase = "serverpass".toCharArray();
			/*
			* load the keystore file. The passhrase is
			* an optional parameter to allow for integrity
			* checking of the keystore. Could be null
			*/
			ks.load(new FileInputStream("server.jks"),passphrase); 
			/*
			* init the KeyManagerFactory with a source
			* of key material. The passphrase is necessary
			* to unlock the private key contained.
			*/
			kmf.init(ks, passphrase);
			/*
			* initialise the SSL context with the keys.
			*/
			ctx.init(kmf.getKeyManagers(), null, null);
	
			/*
			* get the factory we will use to create
			* our SSLServerSocket
			*/
			SSLServerSocketFactory ssf = ctx.getServerSocketFactory();
			return ssf;
		}
		catch(Exception ex)
		{
			System.err.println(ex);
			return null;
		}
		

	}


}
