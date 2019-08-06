import com.shiyanlou.springboot.SpringbootApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
// 定义单元测试执行顺序，采取测试用例名称升序
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MVCTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 保存
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("id", "1")
                .param("username", "shiyanlou")
                .param("password", "password")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("save success"))
                .andDo(print());
    }

    /**
     * 查询
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                //采取内容匹配，所以要保证查询出来的数据和我们之前保存的数据是一致的，否则该项单元测试会失败
                .andExpect(MockMvcResultMatchers.content().json("[{id:1,username:\"shiyanlou\",password:\"password\"}]"))
                .andDo(print());
    }

    /**
     * 更新
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("username", "shiyanlou")
                .param("password", "shiyanlou")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("update success"))
                .andDo(print());
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{id:1,username:\"shiyanlou\",password:\"shiyanlou\"}"))
                .andDo(print());
    }

    /**
     * 删除
     *
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("delete success"))
                .andDo(print());
    }
}
