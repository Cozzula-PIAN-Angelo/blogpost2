package it.epicode.blog.controllers;

import it.epicode.blog.entities.BlogPost;
import it.epicode.blog.services.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogposts")
public class BlogPostController {

	private final BlogPostService blogPostService;

	public BlogPostController(BlogPostService blogPostService) {
		this.blogPostService = blogPostService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // 201
	public BlogPost create(@RequestBody BlogPost blogPost) {
		return blogPostService.create(blogPost);
	}

	@GetMapping("/{id}")
	public BlogPost findById(@PathVariable Long id) {
		return blogPostService.findById(id); // 200
	}

	@GetMapping
	public List<BlogPost> findAll(@RequestParam(required = false) String pubblicato) {
		return blogPostService.findAll(pubblicato); // 200
	}

	@PutMapping("/{id}")
	public BlogPost update(@PathVariable Long id, @RequestBody BlogPost blogPost) {
		return blogPostService.update(id, blogPost); // 200
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	public void delete(@PathVariable Long id) {
		blogPostService.delete(id);
	}

}
