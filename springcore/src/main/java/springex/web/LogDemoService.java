package springex.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import springex.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final MyLogger myLogger;
//private final ObjectProvider<MyLogger> myLoggerProvider;
    public void logic(String id){
//        MyLogger myLogger= myLoggerProvider.getObject();
        myLogger.log("service id = "+id);
    }

}
