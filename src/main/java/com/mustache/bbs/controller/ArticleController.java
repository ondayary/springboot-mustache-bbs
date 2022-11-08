package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j // 로깅을 위한 어노테이션 log를 사용할 수있다.
public class ArticleController {

    // DAO의 추상화된 버전 Repository 만들기
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
        // 정보를 담을 list 선언
        List<Article> articles = articleRepository.findAll(); // 리포지토리에 있는 전부를 찾아온다.
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping(value = "/new")
    public String newArticleForm() {
        return "articles/new";
        // /articles/new url로 들어오면 artices/new.mustache를 출력하는 메소드
    }

    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);

        if (!optArticle.isEmpty()) {
            // Optional.get() ---> Article
            model.addAttribute("article", optArticle.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }

    @PostMapping(value = "/posts")
    public String createArticleForm(ArticleDto form) {

        // 실무에서 로그를 쓴다(서버에서 일어나는 일을 기록하는것)
        log.info(form.toString());

        Article article = form.toEntity();
        articleRepository.save(article);
        return String.format("redirect:/articles/%d", article.getId());
    }
}