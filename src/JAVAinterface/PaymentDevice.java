package JAVAinterface;

public class PaymentDevice implements PaymentReq{
	private int delay = 0; 
	
	@Override
	public boolean sendPaymentReq(int numCard, int dateYYExpired, int dataMMExpired){
		delay = dateYYExpired;
		if(numCard % 2 == 1) {
			return false;
		}  
		else if(dateYYExpired < 23) {
			return false;
		}
		else if(dateYYExpired == 23 && dataMMExpired < 6) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public int receivePaymentRes() {
		if(delay--<0) {
			return RES_SUCCESS;
		}
		else {
			return RES_WAIT;
		}
	}
}
