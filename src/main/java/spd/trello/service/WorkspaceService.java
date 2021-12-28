package spd.trello.service;

import spd.trello.domain.Card;
import spd.trello.domain.Workspace;
import spd.trello.repository.CardRepository;
import spd.trello.repository.WorkspaceRepository;

import java.util.List;
import java.util.Scanner;

public class WorkspaceService  extends AbstractService<Workspace> {
    public WorkspaceService(WorkspaceRepository repository) {
        super(repository);
    }

    @Override
    public Workspace create(Scanner scanner) {
        Workspace workspace = new Workspace();
        System.out.println("Enter workspace name");
        workspace.setName(scanner.nextLine());
        System.out.println("Enter workspace description");
        workspace.setDescription(scanner.nextLine());
        repository.create(workspace);
        return workspace;
    }

    @Override
    public List<Workspace> findAll() {
        return repository.findAll();
    }

    @Override
    public void print(Workspace entity) {
        System.out.println(entity);
    }

}
