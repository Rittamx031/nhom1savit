package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Role;
import savit.group2.sockstore.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public List<Role> getAll() {
        return repository.getAllByStatus(true);
    }

    public Role create(Role role) {
        role.setStatus(true);
        return repository.save(role);
    }

    public Role update(Role role, UUID id) {
        Optional<Role> optional = repository.findById(id);
        return optional.map(o -> {
            o.setName(role.getName());
            o.setPermissions(role.getPermissions());
            o.setRoles(role.getRoles());
            return repository.save(role);
        }).orElse(null);
    }

    public Role delete(UUID id) {
        Optional<Role> optional = repository.findById(id);
        return optional.map(o -> {
            o.setStatus(false);
            return repository.save(o);
        }).orElse(null);
    }
}
