package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class RolesMapperTest {

    @Autowired
    private RolesMapper rolesMapper;

    @Test
    public void selectAllWorks() {
        final List<Role> roles = rolesMapper.selectAll();
        assertNotNull(roles);
        assertEquals(2, roles.size());

        // to test the mapping of the fields
        final Optional<Role> opAdmin = roles.stream().filter(r -> "ROLE_ADMIN".equals(r.getRole())).findFirst();
        assertTrue(opAdmin.isPresent());
        final Role admin = opAdmin.get();
        assertEquals(1, admin.getRoleId());
        assertEquals("ROLE_ADMIN", admin.getRole());
    }

    @Test
    public void selectByPkWorks() {
        final Role role = rolesMapper.selectByPk(2);
        assertNotNull(role);
        assertEquals(2, role.getRoleId());
        assertEquals("ROLE_USER", role.getRole());
    }

    @Test
    public void selectingAnInexistentPkReturnsNull() {
        assertNull(rolesMapper.selectByPk(16));
    }
}