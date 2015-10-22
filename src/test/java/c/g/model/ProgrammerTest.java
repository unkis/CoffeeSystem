package c.g.model;

import org.junit.Test;

import java.util.List;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class ProgrammerTest {

    @Test
    public void testCreateRandomProgrammers() throws Exception {
        List<Programmer> programmers = Programmer.createRandomProgrammers(100, 24335L);
        programmers.forEach(System.out::println);


    }
}
