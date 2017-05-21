package com.codelearn.suny.notee;

import android.util.Log;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        Calendar c = Calendar.getInstance();
        String[] date = c.getTime().toString().split(" ");
        Log.i("TAG", date.toString());
    }
}