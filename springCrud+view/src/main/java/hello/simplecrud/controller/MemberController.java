package hello.simplecrud.controller;

import hello.simplecrud.domain.Member;
import hello.simplecrud.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired //생성자 주입으로 스프링이 연관된 객체(멤버서비스)를 스프링 컨테이너에서 찾아서 의존성 주입을 한다.
    public MemberController(MemberService memberService){
        this.memberService=memberService;
        System.out.println("memberService= "+ memberService.getClass());
    }
    @GetMapping("/members/new")// 포스트 호출을 하기위한 html반환
    public String createForm(){
        return "createForm";
    }

    @PostMapping("/members/new") // html에서 호출한 포스트를 처리 ,..  메소드에서 포스트방식으로 매핑
    public String create(MemberForm form){ //멤버폼 객체는 post매핑되면서 생겨남 ...
        Member member =new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/"; //등록하면 리다이텍트됨
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members= memberService.findMember();
        model.addAttribute("members",members);
        return "memberList";
    }



}
