package me.sola.kriptomer;

import java.net.URI;
import java.util.Map;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

/**
 * This example demonstrates how to create a websocket connection to a server.
 * Only the most important callbacks are overloaded.
 */
public class BitStampWebsocket extends WebSocketClient {

//    public BitStampWebsocket(URI serverUri, Draft draft) {
//        super(serverUri, draft);
//    }

    String valuta = "";
    String fiat = "";
    
    public BitStampWebsocket(URI serverURI) {
        super(serverURI);
    }

//    public BitStampWebsocket(URI serverUri, Map<String, String> httpHeaders) {
//        super(serverUri, httpHeaders);
//    }

    //String subscribeMsg = "{\"event\": \"bts:subscribe\",\"data\": {\"channel\": \"live_trades_btceur\"}}";
    
    
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        
        String subscribeMsg = "{\"event\": \"bts:subscribe\",\"data\": {\"channel\": \"live_trades_" + valuta + fiat + "\"}}";
        
        send(subscribeMsg);
        //System.out.println("opened connection");
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage("opened connection", valuta);
        }
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        //System.out.println("received: " + message);
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message, valuta);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    private MessageHandler messageHandler;
    
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }
    
    public static interface MessageHandler {
        public void handleMessage(String message, String valuta);
    }
    
    public void addValutaInFiat(String valuta_, String fiat_){
        this.valuta = valuta_;        
        this.fiat = fiat_;
    }
    
    public String ija(){
        return this.valuta + this.fiat;
    }
//    public static void main(String[] args) throws URISyntaxException {
//        BitStampWebsocket c = new BitStampWebsocket(new URI(
//                "wss://ws.bitstamp.net"));
//        c.connect();
//    }

}
