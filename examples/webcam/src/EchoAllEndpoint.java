//import javax.websocket.OnMessage;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//
//@ServerEndpoint("/echoall")
//public class EchoAllEndpoint {
//    @OnMessage
//    public void onMessage(Session session, String msg) {
//        try {
//            for (Session sess : session.getOpenSessions()) {
//                if (sess.isOpen())
//                    sess.getBasicRemote().sendText(msg);
//            }
//        } catch (IOException e) {
//        }
//    }
//}
