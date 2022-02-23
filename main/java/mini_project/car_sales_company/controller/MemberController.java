package mini_project.car_sales_company.controller;

import lombok.RequiredArgsConstructor;
import mini_project.car_sales_company.domain.Member;
import mini_project.car_sales_company.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/new")
    public String create(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password){
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPassword(password);

        memberService.join(member);
        return "redirect:/"; //홈으로 리다이렉트

    }

    @GetMapping("members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
        //model객체를 아래 String url에 전달한다.
    }
}
