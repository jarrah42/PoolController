package pool.controller.comms;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

public class Comms {
	private boolean finished = false;

	private final class PacketListener implements SerialPortPacketListener {
		@Override
		public int getListeningEvents() {
			return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
		}

		@Override
		public int getPacketSize() {
			return 10;
		}

		@Override
		public void serialEvent(SerialPortEvent event) {
			byte[] newData = event.getReceivedData();
			System.out.println(byteArrayToDec(newData));
		}
	}

	public void run() {
		SerialPort comPort = SerialPort.getCommPort("/dev/ttyUSB0");
		if (!comPort.openPort()) {
			System.out.println("unable to open port");
			return;
		}
		comPort.addDataListener(new PacketListener());
		while (!finished) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		comPort.addDataListener(new SerialPortDataListener() {
//		   @Override
//		   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
//		   @Override
//		   public void serialEvent(SerialPortEvent event)
//		   {
//		      if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
//		         return;
//		      byte[] newData = new byte[comPort.bytesAvailable()];
//		      int numRead = comPort.readBytes(newData, newData.length);
//		      System.out.println("Read " + numRead + " bytes.");
//		   }
//		});
	}

	public static String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder(a.length * 2);
		for (byte b : a) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	public static String byteArrayToDec(byte[] a) {
		StringBuilder sb = new StringBuilder(a.length * 2);
		for (byte b : a) {
			sb.append(String.format("%d ", b & 0xff));
		}
		return sb.toString();
	}
}
