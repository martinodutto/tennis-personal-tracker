package com.martinodutto.tpt.services;

import com.martinodutto.tpt.security.TptGrantedAuthority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

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
        assertEquals(Collections.singletonList(new TptGrantedAuthority("ROLE_ADMIN")).get(0), objects[0]);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadingAnInexistentUserProducesAnError() {
        userService.loadUserByUsername("XXX");
    }
}