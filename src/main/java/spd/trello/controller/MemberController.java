package spd.trello.controller;

import spd.trello.domain.Member;
import spd.trello.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController extends AbstractController<Member, MemberService>{

    public MemberController(MemberService service) {
        super(service);
    }
}
