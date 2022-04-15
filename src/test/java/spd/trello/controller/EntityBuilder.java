package spd.trello.controller;

import spd.trello.domain.*;
import spd.trello.domain.enumerations.Role;
import spd.trello.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class EntityBuilder {


    static User buildUser() {
        User user = new User();
        user.setFirstName("test");
        user.setEmail("test@email.com");
        user.setLastName("test");
        user.setCreatedBy("test");
        user.setCreatedDate(LocalDateTime.now());
        return user;
    }

    static User getUser(UserRepository userRepository) {
        return userRepository.save(buildUser());
    }

    static Member buildMember() {
        Member member = new Member();
        User user = new User();
        user.setId(UUID.fromString("7ee897d3-9065-421d-93bd-7ad5f30c3bd9"));
        member.setUser(user);
        member.setRole(Role.ADMIN);
        member.setCreatedDate(LocalDateTime.now());
        member.setCreatedBy("test");
        return member;
    }

    static Member getMember(MemberRepository repository) {
        return repository.save(buildMember());
    }

    static Workspace buildWorkspace() {
        Workspace workspace = new Workspace();
        workspace.setName("test");
        workspace.setDescription("test desc");
        workspace.setWorkspaceMembers(Set.of(UUID.fromString("7ee897d3-9065-421d-93bd-7ad5f30c5bd4")));
        workspace.setCreatedDate(LocalDateTime.now());
        workspace.setCreatedBy("test");
        return workspace;
    }

    static Workspace getWorkspace(WorkspaceRepository repository) {
        return repository.save(buildWorkspace());
    }

    static Board buildBoard() {
        Board board = new Board();
        board.setWorkspaceId(UUID.fromString("7ee897d3-9065-471d-53bd-7ad5f30c5bd4"));
        board.setName("board test");
        board.setDescription("desc test");
        board.setCreatedDate(LocalDateTime.now());
        board.setCreatedBy("test");
        return board;
    }

    static Board getBoard(BoardRepository repository) {
        return repository.save(buildBoard());
    }

    static CardList buildCardList() {
        CardList cardList = new CardList();
        cardList.setBoardId(UUID.fromString("7ee897d3-9065-821d-93bd-4ad6f30c5bd4"));
        cardList.setArchived(false);
        cardList.setName("test column");
        cardList.setCreatedDate(LocalDateTime.now());
        cardList.setCreatedBy("test");
        return cardList;
    }

    static CardList getCardList(CardListRepository repository) {
        return repository.save(buildCardList());
    }

    static Card buildCard() {
        Card card = new Card();
        card.setCardListId(UUID.fromString("7ee897d3-9065-885d-93bd-4ad6f30c5fd4"));
        card.setName("test");
        card.setArchived(true);
        card.setDescription("card desc");
        card.setCreatedDate(LocalDateTime.now());
        card.setCreatedBy("test");
        return card;
    }

    static CheckList buildCheckList() {
        CheckList checkList = new CheckList();
        CheckableItem check = new CheckableItem();
        check.setCheck(true);
        check.setName("test check");
        check.setCheckList(checkList);

        checkList.setName("test");
        checkList.setCreatedDate(LocalDateTime.now());
        checkList.setItems(List.of(check));
        return checkList;
    }

    static Card getCard(CardRepository repository) {
        return repository.save(buildCard());
    }

    static Card getCardWithCheckList(CardRepository repository) {
        Card card = buildCard();
        card.setCheckList(buildCheckList());
        return repository.save(card);
    }
}
