import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/echo")
public class EchoEndpoint {


    @OnMessage
    public void onMessage(Session session, String msg) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (Exception e) {

        }
    }

    @OnOpen
    public void open(Session session) {
        System.out.println("Open session:" + session.getId());
    }

    @OnClose
    public void close(Session session, CloseReason c) {
        System.out.println("Closing:" + session.getId());
    }

}
