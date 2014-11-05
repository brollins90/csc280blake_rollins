//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.nio.ByteBuffer;
//
//@ServerEndpoint("/cam")
//public class WebcamEndpoint {
//
//
//    @OnMessage
//    public void binaryMessage(Session session, ByteBuffer msg) {
//        System.out.println("Binary message: " + msg.toString());
//
//        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
//        msg.get(data);
//    }
//
//    @OnOpen
//    public void open(Session session) {
//        System.out.println("Open session:" + session.getId());
//    }
//
//    @OnClose
//    public void close(Session session, CloseReason c) {
//        System.out.println("Closing:" + session.getId());
//    }
//
////    @Override
////    public void onOpen(final Session session, EndpointConfig config) {
////
////        session.addMessageHandler(new MessageHandler.Whole<String>() {
////            @Override
////            public void onMessage(String msg) {
////                try {
////                    for (Session sess : session.getOpenSessions()) {
////                        if (sess.isOpen())
////                            sess.getBasicRemote().sendText(msg);
////                    }
////                } catch (IOException e) {
////                }
////            }
////        });
////    }
//
//
//}
