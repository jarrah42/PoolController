package pool.controller.comms;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class Header {
	Logger logger = Logger.getLogger(Header.class.getName());
	
	private int src;
	private int dst;
	private int len;
	private Protocol protocol;
	
	public Header(Protocol protocol) {
		this.protocol = protocol;
	}

	public boolean read(InputStream stream) throws IOException {
        switch ( protocol )
        {
            case Broadcast:
            	byte[] bytes = new byte[6];
            	stream.read(bytes);
            	src = bytes[2] & 0xff;
            	dst = bytes[3] & 0xff;
            	len = bytes[5] & 0xff;
                if ( src >= 96 && src <= 111 ) {
                	protocol = Protocol.Pump;
                }
                if ( dst >= 96 && dst <= 111 ) {
                	protocol = Protocol.Pump;
                }
                break;
            case Chlorinator:
            	src = 2;
            	dst = 2;
            	// length determined by variable length payload
                break;
            default:
                logger.info("Invalid protocol type when reading header: " + protocol);
                return false;
        }

		return true;
	}
	
	public int getSrc() {
		return src;
	}
	public int getDst() {
		return dst;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public Protocol getProtocol() {
		return protocol;
	}
}
