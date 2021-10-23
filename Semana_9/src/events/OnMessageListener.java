package events;

import model.Orden;

public interface OnMessageListener {

	public void onOrderReceived(Orden orden);
	public void onOrderReady(Orden orden);
	
	
	
}
