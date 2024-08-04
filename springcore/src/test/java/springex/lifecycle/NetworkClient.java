package springex.lifecycle;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient  {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + "message = " + message);
    }

    //서비스 종료시 호출
    @PreDestroy
    public void disconnect() {
        System.out.println("소멸 메소드: ");
        System.out.println("close: " + url);
    }

    @PostConstruct
    public void init(){
        System.out.println("초기화 메소드: ");
        connect();
        call("초기화 연결 메세지");
    }
//    public void close(){
//        disconnect();
//    }



//    @Override
//    public void afterPropertiesSet() throws Exception{ //의존 관계가 끝나고 호출
//        connect();
//        call("초기화 연결 메시지");
//    }
//    @Override
//    public void destroy() throws Exception{ //빈이 종료될때 호출
//        disconnect();
//    }
}
