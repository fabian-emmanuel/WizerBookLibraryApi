package com.codewithfibbee.wizerbooklibraryapi.integration;

import com.codewithfibbee.wizerbooklibraryapi.dtos.CategoryDto;
import com.codewithfibbee.wizerbooklibraryapi.model.Book;
import com.codewithfibbee.wizerbooklibraryapi.model.Category;
import com.codewithfibbee.wizerbooklibraryapi.repository.IBookRepository;
import com.codewithfibbee.wizerbooklibraryapi.repository.ICategoryRepository;
import com.codewithfibbee.wizerbooklibraryapi.util.TestModels;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.codewithfibbee.wizerbooklibraryapi.utility.Converter.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RequiredArgsConstructor
class CategoryTest {
    @Value("${api.basepath}")
    private String path = "";

    @Autowired
    private MockMvc mockMvc;
    private HttpHeaders headers;

    @Autowired
    private ICategoryRepository iCategoryRepository;

    private Category category;

    @BeforeAll
    void setUpBeforeClass() throws Exception {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeEach
    void setUp(){
        category = TestModels.category("Fiction");
        this.iCategoryRepository.save(category);
    }

    @AfterEach
    void tearDown(){
        this.iCategoryRepository.deleteAll();
    }

    @Test
    void shouldAddCategorySuccessfully() throws Exception {
        CategoryDto form = new CategoryDto();
        form.setName("Social");

        this.mockMvc.perform(post(path + "/categories")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Social"));

    }

}
