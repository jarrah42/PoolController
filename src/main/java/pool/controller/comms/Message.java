package pool.controller.comms;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.logging.Logger;

public class Message {
	Logger logger = Logger.getLogger(Message.class.getName());

	private Header header;
	private byte[] payload;
	private boolean isValid;

	private boolean isChlorinatorHeader(byte[] bytes) {
		return bytes[0] == 16 && bytes[1] == 2;
	}

	private boolean isBroadcastHeader(byte[] bytes) {
		return bytes[0] == 255 && bytes[1] == 0 && bytes[2] == 255
				&& bytes[3] == 165;
	}

	private boolean readHeader(PushbackInputStream stream) throws IOException {
		Protocol protocol = Protocol.Unknown;
		boolean foundHeader = false;
		
		byte[] hdr = new byte[4];
		while (!foundHeader) {
			int b = stream.read(hdr);
			if (b < 0) {
				return false;
			}
			if (isChlorinatorHeader(hdr)) {
				protocol = Protocol.Chlorinator;
				foundHeader = true;
			} else if (isBroadcastHeader(hdr)) {
				protocol = Protocol.Broadcast;
				foundHeader = true;
			} else {
				stream.unread(hdr, 1, 3);
			}
		}

		header = new Header(protocol);
		header.read(stream);
		return true;
	}

	private boolean readPayload(PushbackInputStream stream) throws IOException {
		switch (header.getProtocol()) {
		case Broadcast:
		case Pump:
			return readBroadcastPayload(stream);
		case Chlorinator:
			return readChlorinatorPayload(stream);
		default:
			logger.info("Attempt to read payload with unknown header");
			return false;
		}
	}

	private boolean readBroadcastPayload(PushbackInputStream stream) throws IOException {
		payload = new byte[header.getLen()];
		stream.read(payload);
		return true;
	}

    private boolean isChlorTerm (byte[] bytes) { return bytes[1] == 16 && bytes[2] == 3; }

	private boolean readChlorinatorPayload(PushbackInputStream stream) throws IOException {
		byte[] term = new byte[2];
		stream.read(term);
		boolean foundTerm = false;
		int len = 0;
		while (!foundTerm) {
			int b = stream.read();
		}
		return true;
	}

	private boolean readChecksum(byte[] bytes, int ndx) {
		if (!this.isValid)
			return bytes.length;
		if (ndx >= bytes.length)
			return ndx;
		switch (header.getProtocol()) {
		case Broadcast:
		case Pump:
//                if ( this.payload.length >= this.datalen )
//                {
//                    this._complete = true;
//                    ndx = this.pushBytes( this.term, bytes, ndx, 2 );
//                    this.isValid = this.isValidChecksum();
//                }
			break;
		case Chlorinator:
//                if ( this.testChlorTerm( bytes, ndx ) )
//                {
//                    this._complete = true;
//                    ndx = this.pushBytes( this.term, bytes, ndx, 3 );
//                    this.isValid = this.isValidChecksum();
//                }
			break;
		}
		return ndx;
	}

	public boolean readPacket(PushbackInputStream stream) {
		return readHeader(stream) && readPayload(stream) && readChecksum(stream);
	}

}
