package main.controller;

import java.io.IOException;

/**
 *  Interface for judge
 *  @Author Jingkun Yue
 *  @Version 1.0
 */
public interface JudgeUtl {
    public boolean isExtraSeat(String orderNo) throws IOException;
    public boolean isCorrectID(String orderNo, String inputIDNo) throws IOException;
}
