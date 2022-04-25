package spd.trello.repository_jpa;

import org.springframework.stereotype.Repository;
import spd.trello.domain.Workspace;

@Repository
public interface WorkspaceRepository extends CommonRepository<Workspace> {
}