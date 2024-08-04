package hello.simplecrud.controller;

import io.micrometer.observation.transport.Propagator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.processing.Generated;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","Spring!!");// 뷰에 전달될 데이터를 model객체로 전달
        return "hello"; // 리턴된 뷰이름으로 뷰리졸버에서  템플릿에서 파일을 찾아 뷰 객체를 html로 변환
    }
    @GetMapping("kaka")//kaka? para=temp로 param 인자를 넘겨줌 //데이터를 동적으로
    public String helloMvc(@RequestParam("Para")String result,Model model){     //@RequestParam(value= ",request=false)로 하면 파라미터 안넘겨도 됨
        model.addAttribute("name",result); // 뷰에 전달될 데이터를 model객체로 전달
        return "hello-template"; //
    }
    @GetMapping("hello-string")
    @ResponseBody
    public String hellostring(@RequestParam("temp")String result){ //?temp로 인잘 전달한다
        return "hello"+result; //문자열은 뷰 리졸버없이 문자열 html로 반환
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello hellpApi(@RequestParam("name") String name){ //hello객체를 hello-api페이지에 넘겨줌
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체가 제이슨으로 변환됨
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }
    }



}
