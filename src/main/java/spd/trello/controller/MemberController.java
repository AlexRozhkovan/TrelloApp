package spd.trello.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.CardList;
import spd.trello.domain.Member;
import spd.trello.service.CardListService;
import spd.trello.service.MemberService;

@RestController
@RequestMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController extends AbstractController<Member, MemberService> {

    public MemberController(MemberService service) {
        super(service);
    }
}