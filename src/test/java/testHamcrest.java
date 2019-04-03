import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;


import static org.hamcrest.CoreMatchers.equalTo;

public class testHamcrest {
    @Test
    public void test1(){
        System.out.println("11111111");
        assertThat("12",equalTo("手机号码填写错误"));
    }
}
