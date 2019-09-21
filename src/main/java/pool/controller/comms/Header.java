package pool.controller.comms;

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

	public int read(byte[] bytes, int ndx) {
        switch ( protocol )
        {
            case Broadcast:
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
                return -1;
        }

		return ndx;
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
