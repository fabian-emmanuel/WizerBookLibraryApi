package com.codewithfibbee.wizerbooklibraryapi.integration;


import com.codewithfibbee.wizerbooklibraryapi.dtos.BookDto;
import com.codewithfibbee.wizerbooklibraryapi.model.Book;
import com.codewithfibbee.wizerbooklibraryapi.repository.IBookRepository;
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
public class BookTest {

    @Value("${api.basepath}")
    private String path = "";

    @Autowired
    private MockMvc mockMvc;
    private HttpHeaders headers;

    @Autowired
    private IBookRepository iBookRepository;
    private Book book;

    @BeforeAll
    void setUpBeforeClass() throws Exception {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeEach
    void setUp(){
        book = TestModels.book("Prejudice", "John Mich", "A nice book", "Finest");
        this.iBookRepository.save(book);
    }

    @AfterEach
    void tearDown(){
        this.iBookRepository.deleteAll();
    }

    @Test
    void shouldAddBookSuccessfully() throws Exception {
        BookDto form = new BookDto();
        form.setTitle("Prejudice");
        form.setAuthor("John Mich");
        form.setDescription("A nice book");
        form.setPublisher("Finest");

        this.mockMvc.perform(post(path + "/books")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Prejudice"))
                .andExpect(jsonPath("$.author").value("John Mich"))
                .andExpect(jsonPath("$.description").value("A nice book"))
                .andExpect(jsonPath("$.publisher").value("Finest"));

    }

    @Test
    void shouldEditBookSuccessfully() throws Exception {

        BookDto form = new BookDto();
        form.setTitle("new Prejudice");
        form.setAuthor("new John Mich");
        form.setDescription("new A nice book");
        form.setPublisher("new Finest");

        this.mockMvc.perform(put(path + "/books/{bookId}", book.getId())
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("new Prejudice"))
                .andExpect(jsonPath("$.author").value("new John Mich"))
                .andExpect(jsonPath("$.description").value("new A nice book"))
                .andExpect(jsonPath("$.publisher").value("new Finest"));

    }



}
