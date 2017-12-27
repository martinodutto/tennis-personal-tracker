package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ResultsMapperTest {

    @Autowired
    private ResultsMapper resultsMapper;

    @Test
    public void selectAllWorks() {
        final List<Result> results = resultsMapper.selectAll();
        assertNotNull(results);
        assertEquals(3, results.size());
    }

    @Test
    public void selectByPkWorks() {
        final Result result = resultsMapper.selectByPk(4);
        assertNotNull(result);

        assertEquals(4, result.getActivityId());
        assertEquals(5, result.getThreeOrFiveSetter());
        assertEquals("N", result.getLastSetTiebreak());
        assertEquals(6, (long) result.getSet1P1());
        assertEquals(3, (long) result.getSet1P2());
        assertEquals(5, (long) result.getSet2P1());
        assertEquals(7, (long) result.getSet2P2());
        assertEquals(2, (long) result.getSet3P1());
        assertEquals(6, (long) result.getSet3P2());
        assertEquals(7, (long) result.getSet4P1());
        assertEquals(6, (long) result.getSet4P2());
        assertEquals(6, (long) result.getSet5P1());
        assertEquals(8, (long) result.getSet5P2());
    }

    @Test
    public void selectingAnInexistentPkReturnsNull() {
        assertNull(resultsMapper.selectByPk(6));
    }

    @Test
    public void insertWorks() {
        Result result = new Result();
        result.setActivityId(3);
        result.setThreeOrFiveSetter(5);
        result.setLastSetTiebreak("Y");
        result.setSet1P1(3);
        result.setSet1P2(6);
        result.setSet2P1(2);
        result.setSet2P2(6);
        result.setSet3P1(7);
        result.setSet3P2(6);
        result.setSet4P1(6);
        result.setSet4P2(0);
        result.setSet5P1(3);
        result.setSet5P2(6);

        assertEquals(1, resultsMapper.insert(result));
        
        final Result insertedResult = resultsMapper.selectByPk(result.getActivityId());
        assertNotNull(insertedResult);
        assertEquals(3, insertedResult.getActivityId());
        assertEquals(5, insertedResult.getThreeOrFiveSetter());
        assertEquals("Y", insertedResult.getLastSetTiebreak());
        assertEquals(3, (long) insertedResult.getSet1P1());
        assertEquals(6, (long) insertedResult.getSet1P2());
        assertEquals(2, (long) insertedResult.getSet2P1());
        assertEquals(6, (long) insertedResult.getSet2P2());
        assertEquals(7, (long) insertedResult.getSet3P1());
        assertEquals(6, (long) insertedResult.getSet3P2());
        assertEquals(6, (long) insertedResult.getSet4P1());
        assertEquals(0, (long) insertedResult.getSet4P2());
        assertEquals(3, (long) insertedResult.getSet5P1());
        assertEquals(6, (long) insertedResult.getSet5P2());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void insertingANull3Or5SetterProducesAnError() {
        Result result = new Result();
        result.setActivityId(3);
        // we expressly "forget" the 3-or-5 setter
        result.setLastSetTiebreak("Y");
        result.setSet1P1(3);
        result.setSet1P2(6);
        result.setSet2P1(2);
        result.setSet2P2(6);
        result.setSet3P1(7);
        result.setSet3P2(6);
        result.setSet4P1(6);
        result.setSet4P2(0);
        result.setSet5P1(3);
        result.setSet5P2(6);

        resultsMapper.insert(result);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void insertingANullLastSetTiebreakProducesAnError() {
        Result result = new Result();
        result.setActivityId(3);
        result.setThreeOrFiveSetter(5);
        // we expressly "forget" the last set tiebreak
        result.setSet1P1(3);
        result.setSet1P2(6);
        result.setSet2P1(2);
        result.setSet2P2(6);
        result.setSet3P1(7);
        result.setSet3P2(6);
        result.setSet4P1(6);
        result.setSet4P2(0);
        result.setSet5P1(3);
        result.setSet5P2(6);

        resultsMapper.insert(result);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void aViolatedActivityFkProducesAnError() {
        Result result = new Result();
        result.setActivityId(37);
        result.setThreeOrFiveSetter(5);
        result.setLastSetTiebreak("Y");
        result.setSet1P1(3);
        result.setSet1P2(6);
        result.setSet2P1(2);
        result.setSet2P2(6);
        result.setSet3P1(7);
        result.setSet3P2(6);
        result.setSet4P1(6);
        result.setSet4P2(0);
        result.setSet5P1(3);
        result.setSet5P2(6);

        resultsMapper.insert(result);
    }

    @Test
    public void updateWorks() {
        final Result result = resultsMapper.selectByPk(5);
        assertNotNull(result);
        result.setActivityId(4);
        result.setThreeOrFiveSetter(5);
        result.setLastSetTiebreak("N");
        result.setSet1P1(2);
        result.setSet1P2(6);
        result.setSet2P1(6);
        result.setSet2P2(1);
        result.setSet3P1(4);
        result.setSet3P2(6);
        result.setSet4P1(7);
        result.setSet4P2(5);
        result.setSet5P1(2);
        result.setSet5P2(6);

        assertEquals(1, resultsMapper.update(result));

        final Result updatedResult = resultsMapper.selectByPk(4);
        assertNotNull(updatedResult);
        assertEquals(4, updatedResult.getActivityId());
        assertEquals(5, updatedResult.getThreeOrFiveSetter());
        assertEquals("N", updatedResult.getLastSetTiebreak());
        assertEquals(2, (long) updatedResult.getSet1P1());
        assertEquals(6, (long) updatedResult.getSet1P2());
        assertEquals(6, (long) updatedResult.getSet2P1());
        assertEquals(1, (long) updatedResult.getSet2P2());
        assertEquals(4, (long) updatedResult.getSet3P1());
        assertEquals(6, (long) updatedResult.getSet3P2());
        assertEquals(7, (long) updatedResult.getSet4P1());
        assertEquals(5, (long) updatedResult.getSet4P2());
        assertEquals(2, (long) updatedResult.getSet5P1());
        assertEquals(6, (long) updatedResult.getSet5P2());
    }

    @Test
    public void updatingAnInexistentPkDoesNothing() {
        final Result result = resultsMapper.selectByPk(2);
        assertNotNull(result);
        result.setActivityId(21); // inexistent
        assertEquals(0, resultsMapper.update(result));
        assertNull(resultsMapper.selectByPk(21));
    }

    @Test
    public void deleteWorks() {
        final Result result = resultsMapper.selectByPk(4);
        assertNotNull(result);
        assertEquals(1, resultsMapper.delete(result));
        assertEquals(2, resultsMapper.selectAll().size());
        assertNull(resultsMapper.selectByPk(4));
    }
}