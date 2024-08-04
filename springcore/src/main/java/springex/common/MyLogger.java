package springex.common;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
//@Scope(value="request") //리퀘스트 요청이 와야 빈 등록이 된다.
@Scope(value="request",proxyMode = ScopedProxyMode.TARGET_CLASS) //리퀘스트 전에 가짜 프록시객체  빈에 등록 해놓음 실제 요청이 오면 실제빈을 요청
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL){
        this.requestURL=requestURL;
    }
    public void log(String message){
        System.out.printf("[%s] [%s] %s",uuid,requestURL,message);
    }
    @PostConstruct
    public void init(){
       uuid= UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }
    @PreDestroy
    public void close(){
        System.out.println();
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
