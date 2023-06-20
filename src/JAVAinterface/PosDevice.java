package JAVAinterface;

public class PosDevice implements PurchaseOrder{
	@Override
	public void sendOrder(int OrderNum, String menuName, int pcs) {
		System.out.println("Order["+OrderNum+"] : "+menuName+"("+pcs+")");
		return;
	};
}
