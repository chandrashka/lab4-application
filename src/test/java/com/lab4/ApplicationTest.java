package com.lab4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@PropertySource("classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void notFound() throws Exception {
        mvc.perform(get(new URI("/api/lab4/-1"))).andExpect(content().string(containsString("Not found")));
    }

    @Test
    public void postExpression() throws Exception {
        String request = """
                 {
                      "surfaceDTO": {
                          "a": 100,
                          "b": 2,
                          "c": 2,
                          "d": 0
                      },
                      "lineDTO": {
                          "x0": 3,
                          "y0": 2,
                          "z0": 2,
                          "l": 12,
                          "m": 2,
                          "n": 2
                      }
                  }
                """;

        mvc.perform(post(new URI("/api/lab4/")).contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(content().string(containsString("78")));
    }

    @Test
    public void getExpression() throws Exception {
        String request = """
                 {
                      "surfaceDTO": {
                          "a": 5,
                          "b": 4,
                          "c": 2,
                          "d": 3
                      },
                      "lineDTO": {
                          "x0": 34,
                          "y0": 25,
                          "z0": 2.2,
                          "l": -12,
                          "m": 200,
                          "n": 0
                      }
                  }
                """;

        mvc.perform(post(new URI("/api/lab4/")).contentType(MediaType.APPLICATION_JSON).content(request));

        mvc.perform(get(new URI("/api/lab4/0"))).andExpect(content().string(containsString(":0")));
    }

    @Test
    public void perpendicular() throws Exception {
        String request = """
                 {
                      "surfaceDTO": {
                          "a": 1,
                          "b": 1,
                          "c": 1,
                          "d": 1
                      },
                      "lineDTO": {
                          "x0": 0,
                          "y0": 0,
                          "z0": 0,
                          "l": 1,
                          "m": 1,
                          "n": 1
                      }
                  }
                """;

        mvc.perform(post(new URI("/api/lab4/")).contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(content().string(containsString("perpendicular")));
    }

    @Test
    public void badInput() throws Exception {
        String request = """
                 {
                      "surfaceDTO": {
                          "a": 1,
                          "b": 1,
                          "c": 1,
                          "d": 1
                      },
                      "lineDTO": {
                          "x0": 0,
                          "y0": 0,
                          "z0": 0,
                          "l": 0,
                          "m": 0,
                          "n": 0
                      }
                  }
                """;
        mvc.perform(post(new URI("/api/lab4/")).contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(content().string(containsString("Bad input")));
    }

    @Test
    public void superBadInput() throws Exception {
        String request = """
                 {
                      "surfaceDTO": {
                          "a": "a",
                          "b": "b",
                          "c": 1,
                          "d": 1
                      },
                      "lineDTO": {
                          "x0": "x",
                          "y0": 0,
                          "z0": 0,
                          "l": 0,
                          "m": 0,
                          "n": 0
                      }
                  }
                """;
        mvc.perform(post(new URI("/api/lab4/")).contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().is(400));
    }
}
