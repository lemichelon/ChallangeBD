package com.exemplo.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título não pode estar vazio.")
    private String title;

    @NotBlank(message = "A descrição não pode estar vazia.")
    private String description;

    public Topic() {}

    public Topic(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and Setters
}
package com.exemplo.api.repository;

import com.exemplo.api.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
package com.exemplo.api.service;

import com.exemplo.api.model.Topic;
import com.exemplo.api.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public Optional<Topic> findById(Long id) {
        return topicRepository.findById(id);
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public void delete(Long id) {
        topicRepository.deleteById(id);
    }

    public Topic update(Long id, Topic topic) {
        if (topicRepository.existsById(id)) {
            topic.setId(id);
            return topicRepository.save(topic);
        } else {
            throw new RuntimeException("Tópico não encontrado.");
        }
    }
}
package com.exemplo.api.service;

import com.exemplo.api.model.Topic;
import com.exemplo.api.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public Optional<Topic> findById(Long id) {
        return topicRepository.findById(id);
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public void delete(Long id) {
        topicRepository.deleteById(id);
    }

    public Topic update(Long id, Topic topic) {
        if (topicRepository.existsById(id)) {
            topic.setId(id);
            return topicRepository.save(topic);
        } else {
            throw new RuntimeException("Tópico não encontrado.");
        }
    }
}
package com.exemplo.api.controller;

import com.exemplo.api.model.Topic;
import com.exemplo.api.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        Optional<Topic> topic = topicService.findById(id);
        return topic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Topic> createTopic(@Valid @RequestBody Topic topic) {
        Topic createdTopic = topicService.save(topic);
        return ResponseEntity.status(201).body(createdTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @Valid @RequestBody Topic topic) {
        try {
            Topic updatedTopic = topicService.update(id, topic);
            return ResponseEntity.ok(updatedTopic);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
        package com.exemplo.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topics/**").permitAll()  // Permite leitura pública
                .antMatchers(HttpMethod.POST, "/topics/**").hasRole("ADMIN")  // Permite criação apenas para ADMIN
                .antMatchers(HttpMethod.PUT, "/topics/**").hasRole("ADMIN")  // Permite edição apenas para ADMIN
                .antMatchers(HttpMethod.DELETE, "/topics/**").hasRole("ADMIN")  // Permite exclusão apenas para ADMIN
                .anyRequest().authenticated()
                .and()
                .httpBasic();  // Utiliza autenticação básica
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
package com.exemplo.api.controller;

import com.exemplo.api.model.Topic;
import com.exemplo.api.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        Optional<Topic> topic = topicService.findById(id);
        return topic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Topic> createTopic(@Valid @RequestBody Topic topic) {
        Topic createdTopic = topicService.save(topic);
        return ResponseEntity.status(201).body(createdTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @Valid @RequestBody Topic topic) {
        try {
            Topic updatedTopic = topicService.update(id, topic);
            return ResponseEntity.ok(updatedTopic);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
