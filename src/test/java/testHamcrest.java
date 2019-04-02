import org.junit.Assert;
import org.junit.Test;

//import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by helena.liu on 2019/4/2.
 */
public class testHamcrest {
    @Test
    public void test1(){
        assertThat("1",equalTo("1"));
    }
    @Test
    public void test2(){
        assertThat("手机号码填写错误",equalTo("手机号码填写错误"));
    }
    @Test
    public void test3(){
        assertThat("abc",equalTo("1"));
    }
    @Test
    public void test4(){
        Assert.assertEquals("abc","111");
    }

}
