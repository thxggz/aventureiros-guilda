package com.guilda.aventureiros;

import com.guilda.aventureiros.audit.entity.Organization;
import com.guilda.aventureiros.audit.entity.Role;
import com.guilda.aventureiros.audit.entity.User;
import com.guilda.aventureiros.audit.repository.OrganizationRepository;
import com.guilda.aventureiros.audit.repository.RoleRepository;
import com.guilda.aventureiros.audit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuditRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    @Transactional
    void deveCarregarUsuariosComRoles() {
        List<User> users = userRepository.findAll();

        assertThat(users).isNotNull();
        assertThat(users).isNotEmpty();

        User user = users.get(0);

        assertThat(user.getOrganization()).isNotNull();
        assertThat(user.getOrganization().getNome()).isNotBlank();
        assertThat(user.getRoles()).isNotNull();
    }

    @Test
    @Transactional
    void deveCarregarRolesComPermissions() {
        List<Role> roles = roleRepository.findAll();

        assertThat(roles).isNotNull();
        assertThat(roles).isNotEmpty();

        Role role = roles.get(0);

        assertThat(role.getNome()).isNotBlank();
        assertThat(role.getPermissions()).isNotNull();
    }

    @Test
    @Transactional
    void devePersistirNovoUsuarioAssociadoAOrganizacaoExistente() {
        Organization organization = organizationRepository.findAll().stream().findFirst().orElse(null);

        assertThat(organization).isNotNull();

        User user = new User();
        user.setOrganization(organization);
        user.setNome("Usuario Teste TP2");
        user.setEmail("usuarioteste_tp2@infnet.com");
        user.setSenhaHash("hash_teste");
        user.setStatus("ATIVO");
        user.setCreatedAt(OffsetDateTime.now());
        user.setUpdatedAt(OffsetDateTime.now());

        User saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getOrganization()).isNotNull();
        assertThat(saved.getOrganization().getId()).isEqualTo(organization.getId());
    }
}