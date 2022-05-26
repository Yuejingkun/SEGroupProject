package test.main.controller;

import main.controller.JudgeController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
/**
 *  The class tests the tool methods of judge.
 *  @Author Jingkun Yue
 *  @Version 1.0
 */
class JudgeControllerTest {

    JudgeController judgeController = new JudgeController();

    @BeforeEach
    void setUp() {
        System.out.println("begin...");
    }
    @AfterEach
    void tearDown() {
        System.out.println("finish...");
    }

    @Test
    void isExtraSeat() throws IOException {
        System.out.println("test isExtraSeat");
        assertTrue(judgeController.isExtraSeat("8201013215955"), "isExtraSeat can judge extraSeat");
        assertFalse(judgeController.isExtraSeat("8207330135955"), "isExtraSeat can not judge economicSeat");
    }

    @Test
    void isCorrectID() throws IOException {
        System.out.println("test isCorrectID");
        assertTrue(judgeController.isCorrectID("8201465425955","190896542"),"isCorrectID is correct");
        assertFalse(judgeController.isCorrectID("8201465425955","190897274"),"isCorrectID isn't correct");
    }

    @Test
    void isCorrectCardNum() throws IOException {
        System.out.println("test isCorrectCardNum");
        assertTrue(judgeController.isCorrectCardNum("12783621932196492"),"isCorrectID isn't correct");
        assertFalse(judgeController.isCorrectCardNum("82014DFSFD5"),"isCorrectID isn't correct");
        assertFalse(judgeController.isCorrectCardNum("8442"),"isCorrectID is correct");
    }


}