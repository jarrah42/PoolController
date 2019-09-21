package pool.controller.comms;

public class Message {
	private Protocol protocol;
	private boolean isValid;
	
	private boolean testChlorHeader(byte[] bytes, int ndx) {
		return (ndx + 1 < bytes.length && bytes[ndx] == 16 && bytes[ndx + 1] == 2);
	}

	private boolean testBroadcastHeader(byte[] bytes, int ndx) {
		return ndx < bytes.length - 3 && bytes[ndx] == 255 && bytes[ndx + 1] == 0 && bytes[ndx + 2] == 255
				&& bytes[ndx + 3] == 165;
	}
	
    private int readHeader ( byte[] bytes, int ndx)
    {
        while ( ndx < bytes.length )
        {
            if ( testChlorHeader( bytes, ndx ) )
            {
                protocol = Protocol.Chlorinator;
                break;
            }
            if ( testBroadcastHeader( bytes, ndx ) )
            {
                protocol = Protocol.Broadcast;
                break;
            }
        }
        switch ( protocol )
        {
            case Pump:
            case Broadcast:
                ndx = this.pushBytes( this.preamble, bytes, ndx, 3 );
                ndx = this.pushBytes( this.header, bytes, ndx, 6 );
                if ( this.source >= 96 && this.source <= 111 ) this.protocol = Protocol.Pump;
                if ( this.dest >= 96 && this.dest <= 111 ) this.protocol = Protocol.Pump;
                if ( this.protocol === Protocol.Broadcast )
                    break;
                break;
            case Chlorinator:
                ndx = this.pushBytes( this.header, bytes, ndx, 2 );
                break;
            default:
                //console.log(this.protocol);
                break;
        }
        return ndx;
    }
    
    private int readPayload ( byte[] bytes, int ndx)
    {
        if ( !isValid ) return bytes.length;
        switch ( protocol )
        {
            case Broadcast:
            case Pump:
                ndx = this.pushBytes( this.payload, bytes, ndx, this.datalen - this.payload.length );
                break;
            case Chlorinator:
                while ( ndx < bytes.length && !this.testChlorTerm( bytes, ndx ) )
                    this.payload.push( bytes[ ndx++ ] );
                break;
        }
        return ndx;
    }
    
    private int readChecksum ( byte[] bytes, int ndx)
    {
        if ( !this.isValid ) return bytes.length;
        if ( ndx >= bytes.length ) return ndx;
        switch ( this.protocol )
        {
            case Broadcast:
            case Pump:
                if ( this.payload.length >= this.datalen )
                {
                    this._complete = true;
                    ndx = this.pushBytes( this.term, bytes, ndx, 2 );
                    this.isValid = this.isValidChecksum();
                }
                break;
            case Chlorinator:
                if ( this.testChlorTerm( bytes, ndx ) )
                {
                    this._complete = true;
                    ndx = this.pushBytes( this.term, bytes, ndx, 3 );
                    this.isValid = this.isValidChecksum();
                }
                break;
        }
        return ndx;
    }

	public int readPacket(byte[] bytes) {
        int ndx = readHeader( bytes, 0 );
        ndx = readPayload( bytes, ndx );
        ndx = readChecksum( bytes, ndx );
        return ndx;
	}

}
