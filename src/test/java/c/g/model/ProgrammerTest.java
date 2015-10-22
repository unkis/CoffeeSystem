package c.g.model;

import c.g.Controller;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class ProgrammerTest {

    @Test
    public void testCreateRandomProgrammers() throws Exception {
        List<Programmer> programmers = Programmer.createRandomProgrammers(100, 24335L);
//        programmers.forEach(System.out::println);

        Controller c = new Controller();
        c.start(programmers);

    }
}