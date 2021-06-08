import KR.Config.Config;
import KR.Controllers.PathController;
import KR.DAO.Repository;
import KR.Domain.Path;
import KR.Services.MainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(classes = Config.class)
//@WebAppConfiguration
@AutoConfigureMockMvc
@AutoConfigureWebMvc
//@WebMvcTest
public class SecondTryTest {
//    @Mock
    @Autowired
    WebApplicationContext wac;
//    @Autowired
    private MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    @Mock
    private MainService mainService= Mockito.mock(MainService.class);

    @Mock
    private Repository rep = Mockito.mock(Repository.class);
    @Autowired
    public SecondTryTest() {

    }

    @Test
    public void showAllTest2() throws Exception {
        mockMvc.perform(get("/path"))
                .andExpect(status().isOk());
        assertNotNull(rep);
        Path first = new Path(10, "name_1");
        first.setId(1);
        Path second = new Path(11, "name_2");
        second.setId(2);
        when(rep.showAll()).thenReturn(Arrays.asList(first, second));
        MainService s = new MainService(rep);
//        PathController pc = new PathController(s);
        assertEquals(2, s.showAll().size());
    }
    @Test
    public void showAllTest() throws Exception {
        Path first = new Path(10, "name_1");
        first.setId(1);
        Path second = new Path(11, "name_2");
        second.setId(2);

        when(mainService.showAll()).thenReturn(Arrays.asList(first, second));
//
        mockMvc.perform(get("/path"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].pathName", is("name_1")))
                .andExpect((ResultMatcher) jsonPath("$[1].id", is(2)))
                .andExpect((ResultMatcher) jsonPath("$[1].pathName", is("name_2")));
    }

    @Test
    public void addPath() throws SQLException {
        assertNotNull(rep);
        Path first = new Path(10, "name_1");
        first.setId(1);
        when(rep.showAll()).thenReturn(Arrays.asList(first));
        MainService s = new MainService(rep);
//        PathController pc = new PathController(s);
        s.addPath(new Path(2, "So-Os"));
        assertEquals(2, s.showAll().size());
    }

//    @Test
//    public void
}