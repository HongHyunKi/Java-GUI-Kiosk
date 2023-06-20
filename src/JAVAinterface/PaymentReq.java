package JAVAinterface;

public interface PaymentReq {
	public final int RES_WAIT = 0;
	public final int RES_FAIL = 1;
	public final int RES_SUCCESS = 2;
	
	public abstract boolean sendPaymentReq(int numCard, int dateYYExpired, int dataMMExpired);
	public abstract int receivePaymentRes();
}
