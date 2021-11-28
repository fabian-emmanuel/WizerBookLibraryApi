package com.codewithfibbee.wizerbooklibraryapi.integration;


import com.codewithfibbee.wizerbooklibraryapi.dtos.BookDto;
import com.codewithfibbee.wizerbooklibraryapi.model.Book;
import com.codewithfibbee.wizerbooklibraryapi.repository.IBookRepository;
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
import com.codewithfibbee.wizerbooklibraryapi.util.*;

import static com.codewithfibbee.wizerbooklibraryapi.utility.Converter.asJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RequiredArgsConstructor
public class BookTest {

    @Value("${api.basepath-api}")
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

        this.mockMvc.perform(post(path + "books")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void shouldEditBookSuccessfully() throws Exception {

        BookDto form = new BookDto();
        form.setTitle("Prejudice");
        form.setAuthor("John Mich");
        form.setDescription("A nice book");
        form.setPublisher("Finest");

        this.mockMvc.perform(post(path + "books/{bookId}", book.getId())
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Book editedBook = iBookRepository.findById(book.getId()).orElseThrow();

        assertEquals("Prejudice", editedBook.getTitle());
        assertEquals("John Mich", editedBook.getAuthor());
        assertEquals("A nice book", editedBook.getDescription());
        assertEquals("Finest", editedBook.getPublisher());
    }



}
