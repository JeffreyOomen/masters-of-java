package nl.infosupport.moj.templatebuilder;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DukemailValidatorTest {

    private DukemailValidator dukemailValidator = new DukemailValidator();

    @Test
    public void test1() {
        assertThat("Check the dots.",
                dukemailValidator.isValid(".asgdfe@example.dukemail"), is(false));
    }

    @Test
    public void test2() {
        assertThat("Check the dots.",
                dukemailValidator.isValid("asgd..fe@example.dukemail"), is(false));
    }

    @Test
    public void test3() {
        assertThat("Check the dots.",
                dukemailValidator.isValid("asgdfe.@example.dukemail"), is(false));
    }

    @Test
    public void test4() {
        assertThat("Check the dots.",
                dukemailValidator.isValid("asg.dfe@example.dukemail"), is(true));
    }

    @Test
    public void test5() {
        assertThat("This should not fail.",
                dukemailValidator.isValid("10dfe@example.dukemail"), is(true));
    }

    @Test
    public void test6() {
        assertThat("Non alphanumeric test should fail.",
                dukemailValidator.isValid("as.g.d#fe@example.dukemail"), is(false));
    }

    @Test
    public void test7() {
        assertThat("Upper lower case.",
                dukemailValidator.isValid("aWg.dfW@example.dukemail"), is(true));
    }

    @Test
    public void test8() {
        assertThat("Looks valid to me.",
                dukemailValidator.isValid("1sg.dfe@exaaample.dukemail"), is(true));
    }

    @Test
    public void test10() {
        assertThat("Looks valid to me.",
                dukemailValidator.isValid("1sg.dfe@exaaample1723test.dukemail"), is(true));
    }

    @Test
    public void test11() {
        assertThat("What was the domain name again?",
                dukemailValidator.isValid("1sg.dfe@exaaample1723test.dukemailtje"), is(false));
    }

    @Test
    public void test12() {
        assertThat("What was the domain name again?",
                dukemailValidator.isValid("1sg.dfe@exaaample1723test.dukemaiL"), is(false));
    }

    @Test
    public void test13() {
        assertThat("A monkey has how many tails?",
                dukemailValidator.isValid("1sg.dfe@exa@aample1723test.dukemail"), is(false));
    }

    @Test
    public void test14() {
        assertThat("Your regex is missing something.",
                dukemailValidator.isValid("1sg.dfe@exa@aample1723test.dukemail"), is(false));
    }

    @Test
    public void test15() {
        assertThat("Strange, this should pass, missing some chars?",
                dukemailValidator.isValid("A.BCDEFGHIJK.LMNOPQRST.UVWXYZabcdefghijklmno.pqrstuvwxyz0123.456789@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.dukemail"),
                is(true));
    }

    @Test
    public void test16() {
        assertThat("Shouldn't there be at least one special character?",
                dukemailValidator.isValid("1sg.dfeaample1723test.dukemail"), is(false));
    }

    @Test
    public void test17() {
        assertThat("Are you kidding me? It's a empty string.",
                dukemailValidator.isValid(""), is(false));
    }
}
