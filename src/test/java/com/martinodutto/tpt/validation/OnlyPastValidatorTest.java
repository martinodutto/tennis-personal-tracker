package com.martinodutto.tpt.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OnlyPastValidatorTest {

    private OnlyPastValidator onlyPastValidator;

    @Before
    public void setUp() {
        onlyPastValidator = new OnlyPastValidator();
    }

    @Test
    public void aNullDateIsAllowed() {
        assertTrue(onlyPastValidator.isValid(null, null));
    }

    @Test
    public void todayIsAllowed() {
        assertTrue(onlyPastValidator.isValid(LocalDate.now(), null));
    }

    @Test
    public void MilleniumDayIsAllowed() {
        assertTrue(onlyPastValidator.isValid(LocalDate.parse("2000-01-01"), null));
    }

    @Test
    public void tomorrowIsNotAllowed() {
        assertFalse(onlyPastValidator.isValid(LocalDate.now().plusDays(1), null));
    }
}