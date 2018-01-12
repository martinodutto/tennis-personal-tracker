package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.User;
import com.martinodutto.tpt.database.mappers.UsersMapper;
import com.martinodutto.tpt.exceptions.DuplicateKeyException;
import com.martinodutto.tpt.exceptions.UnregisteredRoleException;
import com.martinodutto.tpt.security.Authorities;
import com.martinodutto.tpt.security.TptGrantedAuthority;
import com.martinodutto.tpt.security.TptUser;
import com.martinodutto.tpt.security.UserAuthentication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void loadUserByUsernameWorks() {
        UserDetails userDetails = userService.loadUserByUsername("mdutto");
        assertEquals("mdutto", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Object[] objects = authorities.toArray();
        assertEquals(new TptGrantedAuthority("ROLE_ADMIN"), objects[0]);
    }

    @Test
    public void aUserWithNoRoleHasEmptyAuthorities() {
        UserDetails userDetails = userService.loadUserByUsername("ezra15");
        assertEquals("ezra15", userDetails.getUsername());
        assertEquals("mypassword", userDetails.getPassword());
        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertEquals(0, userDetails.getAuthorities().size());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadingAnInexistentUserProducesAnError() {
        userService.loadUserByUsername("XXX");
    }

    @Test
    public void addUserWorks() throws UnregisteredRoleException, DuplicateKeyException {
        TptUser user = new TptUser("marco76",
                "$2a$12$G9ucueHuIcE/tttbW9ROdOQPdbx0z4y6qLeeL7de.GJ/QO76K.saO",
                Collections.singletonList(new TptGrantedAuthority("ROLE_USER")));
        userService.addUser(user);
        User marco76 = usersMapper.selectByUsername("marco76");
        assertNotNull(marco76);
        assertEquals("marco76", marco76.getUsername());
        assertEquals("$2a$12$G9ucueHuIcE/tttbW9ROdOQPdbx0z4y6qLeeL7de.GJ/QO76K.saO", marco76.getPassword());
        assertEquals(Authorities.ROLE_USER.getId(), (long) marco76.getRoleId());
    }

    @Test(expected = DuplicateKeyException.class)
    public void aDuplicateUsernameProducesACheckedException() throws UnregisteredRoleException, DuplicateKeyException {
        TptUser user = new TptUser("mdutto",
                "XXXYYYZZZ",
                Collections.singletonList(new TptGrantedAuthority("ROLE_USER")));
        userService.addUser(user);
    }

    @Test(expected = NullPointerException.class)
    public void anEmptyAuthenticationProducesAnError() {
        SecurityContextHolder.clearContext();
        userService.getCurrentUser();
    }

    @Test
    public void getCurrentUserWorks() {
        UserAuthentication authentication = new UserAuthentication(userService.getUserBy(0));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TptUser currentUser = userService.getCurrentUser();
        SecurityContextHolder.clearContext();

        assertEquals("mdutto", currentUser.getUsername());
        assertEquals("password", currentUser.getPassword());
        Collection<? extends GrantedAuthority> authorities = currentUser.getAuthorities();
        Object[] objects = authorities.toArray();
        assertEquals(new TptGrantedAuthority("ROLE_ADMIN"), objects[0]);
    }

    @Test
    public void getUserByWorks() {
        TptUser user = userService.getUserBy(1);
        assertEquals("pcometto", user.getUsername());
        assertEquals("thisismykey", user.getPassword());
        assertEquals(1, user.getUserId());

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        Object[] objects = authorities.toArray();
        assertEquals(new TptGrantedAuthority("ROLE_USER"), objects[0]);
    }
}