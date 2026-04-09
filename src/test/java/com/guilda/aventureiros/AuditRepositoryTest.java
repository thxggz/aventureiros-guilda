package com.guilda.aventureiros;

import com.guilda.aventureiros.audit.entity.User;
import com.guilda.aventureiros.audit.repository.UserRepository;
import com.guilda.aventureiros.audit.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuditRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Transactional
    void deveCarregarUsuariosComRoles() {
        List<User> users = userRepository.findAll();
        assertThat(users).isNotNull();
        users.forEach(user -> {
            System.out.println("Usuário: " + user.getNome());
            System.out.println("Organização: " + user.getOrganization().getNome());
            user.getRoles().forEach(role -> {
                System.out.println("  Role: " + role.getNome());
                role.getPermissions().forEach(permission ->
                        System.out.println("    Permission: " + permission.getCode())
                );
            });
        });
    }
}