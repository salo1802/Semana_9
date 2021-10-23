import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import events.OnMessageListener;
import model.Orden;



public class UDPConection extends Thread{
	
	private DatagramSocket socket;
	
	OnMessageListener observer;
	
	public void setObsever(OnMessageListener observer){
		this.observer = observer;
	}

	@Override
	public void run() {
		
		Orden orden;
		try {
			socket = new DatagramSocket(5000);
			
			while (true) {
				
				byte[] buffer = new byte[100];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				System.out.println("esperanding");
				socket.receive(packet); 
				
				String mensaje = new String(packet.getData()).trim();
				/*String[] ipYPort;
				ipYPort = packet.getSocketAddress().toString().split(":");
				ipName = ipYPort[0];
				port = Integer.parseInt( ipYPort[1]);*/
				Gson gson = new Gson();
				orden = gson.fromJson(mensaje, Orden.class);
				observer.onOrderReceived(orden);
				
				
				
				System.out.println("llego esto papu: "+ mensaje);
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
		public void sendMsg(String msg, String ipName, int port) {
			
			new Thread(()->{
				
				try {
					InetAddress ip = InetAddress.getByName(ipName);
					DatagramPacket packet = new DatagramPacket(msg.getBytes(),msg.getBytes().length,ip, port);
					socket.send(packet);
					}catch(UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}).start();
			
			
		
	}
	
}
