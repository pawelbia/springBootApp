package springboot.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.library.model.Author;
import springboot.library.repository.AuthorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new IllegalStateException("author not found"));
    }

    public List<Author> findByLastName(String lastName) {
        return authorRepository.findAuthorsByLastName(lastName);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public boolean existsAuthor(String firstName, String lastName) {
        return authorRepository.existsAuthorByFirstNameAndLastName(firstName, lastName);
    }
}
