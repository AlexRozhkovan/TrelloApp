package spd.trello.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spd.trello.domain.Workspace;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkspaceRepository extends IRepository<Workspace> {

    List<Workspace> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT w.* FROM workspaces w INNER JOIN workspaces_members wm on w.id = wm.workspace_id " +
            "INNER JOIN members m on wm.member_id = m.id")
    List<Workspace> findByMemberId(UUID membersIds);
}