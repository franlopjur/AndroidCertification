/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.franlopez.androidcertification;

import android.support.test.filters.SmallTest;

import com.franlopez.androidcertification.manager.Calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)
@SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    @Test
    public void multiplyTwoNumbers() {
        double resultMultiply = mCalculator.mul(10d, 5d);
        assertThat(resultMultiply, is(equalTo(50d)));
    }

    @Test
    /**
     * {[(2 + 4) * 5] / 3} - 4 = 6
     */
    public void multipleOperators() {
        double resultAdd = mCalculator.add(2d, 4d);
        double resultMul = mCalculator.mul(resultAdd, 5d);
        double resultDiv = mCalculator.div(resultMul, 3d);
        double resultFinal = mCalculator.sub(resultDiv, 4d);
        assertThat(resultFinal, is(not(equalTo(60d))));
        assertThat(resultFinal, is(equalTo(6d)));
    }

    @Test
    /**
     * A test with positive integer operands.
     * A test with a negative integer as the first operand.
     * A test with a negative integer as the second operand.
     * A test with 0 as the first operand and a positive integer as the second operand.
     * A test with 0 as the second operand.
     * A test with 0 as the first operand and -1 as the second operand. (Hint: consult the documentation for Double.POSITIVE_INFINITY.)
     * A test with -0 as the first operand and any negative number as the second operand.
     */
    public void powOptions() {
        double resultFirstCase = mCalculator.pow(2, 4);
        assertThat(resultFirstCase, is(equalTo(16d)));

        double resultSecondCasePair = mCalculator.pow(-2, 4);
        assertThat(resultSecondCasePair, is(equalTo(16d)));

        double resultSecondCaseOdd = mCalculator.pow(-2, 3);
        assertThat(resultSecondCaseOdd, is(equalTo(-8d)));

        double resultThirdCase = mCalculator.pow(2, -4);
        assertThat(resultThirdCase, is(equalTo(0.0625)));

        double resultFourthCase = mCalculator.pow(0, 2);
        assertThat(resultFourthCase, is(equalTo(0d)));

        double resultFifthCase = mCalculator.pow(2, 0);
        assertThat(resultFifthCase, is(equalTo(1d)));

        double resultSixthCase = mCalculator.pow(0, -1);
        assertThat(resultSixthCase, is(equalTo(Double.POSITIVE_INFINITY)));

        double resultSeventhCase = mCalculator.pow(-0, -3);
        assertThat(resultSeventhCase, is(equalTo(Double.POSITIVE_INFINITY)));
    }
}