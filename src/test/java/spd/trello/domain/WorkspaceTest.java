package spd.trello.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.exception.NotFoundException;
import spd.trello.repository.WorkspaceRepository;
import spd.trello.service.WorkspaceService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WorkspaceTest extends BaseTest {

    @Mock
    private WorkspaceRepository repository;

    @InjectMocks
    private WorkspaceService service;

    @BeforeEach
    public void createTestSpace() {
        workspace.setId(UUID.fromString("1ee887d3-9065-421d-93bd-7ad5f30c3bd9"));
        workspace.setWorkspaceMembers(Set.of(member.getId()));
    }

    @Test
    public void successCreate() {
        when(repository.save(workspace)).thenReturn(workspace);

        Workspace actual = service.create(workspace);
        assertAll(
                () -> assertNotNull(actual.getCreatedDate()),
                () -> assertNull(actual.getUpdatedDate()),
                () -> assertEquals(workspace.getVisibility(), actual.getVisibility()),
                () -> assertEquals(workspace.getCreatedBy(), actual.getCreatedBy())
        );

        verify(repository, times(1)).save(workspace);

    }

    @Test
    public void successUpdate() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(workspace));
        when(repository.save(workspace)).thenReturn(workspace);

        workspace.setUpdatedBy("test");
        workspace.setVisibility(WorkspaceVisibility.PRIVATE);
        Workspace actual = service.update(workspace);

        assertAll(
                () -> assertNotNull(actual.getUpdatedDate()),
                () -> assertEquals(workspace.getVisibility(), actual.getVisibility()),
                () -> assertEquals(workspace.getUpdatedBy(), actual.getUpdatedBy()),
                () -> assertEquals(1, actual.getWorkspaceMembers().size())
        );

        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).save(workspace);
    }

    @Test
    public void successGetObjects() {
        Page<Workspace> expected = new PageImpl(List.of(workspace, workspace), pageable, 2);

        when(repository.save(workspace)).thenReturn(workspace);
        when(repository.findAll(pageable)).thenReturn(expected);

        List<Workspace> actual = service.readAll(pageable).getContent();

        assertEquals(expected.getContent().size(), actual.size());
    }

    @Test
    public void failReadById() {
        assertThrows(NotFoundException.class, () -> service.readById(UUID.randomUUID()));
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void successDelete() {
        when(repository.save(workspace)).thenReturn(workspace);
        when(repository.findById(any())).thenReturn(Optional.ofNullable(workspace));
        doNothing().when(repository).deleteById(any());

        workspace.setId(UUID.randomUUID());
        Workspace expected = service.create(BaseTest.workspace);
        Workspace actual = service.delete(expected.getId());

        assertEquals(expected.getName(), actual.getName());

        verify(repository, times(1)).findById(any());
    }
}
