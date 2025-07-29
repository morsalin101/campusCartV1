package com.nico.store.store.controller;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nico.store.store.domain.Article;
import com.nico.store.store.service.ArticleService;
import com.nico.store.store.service.CategoryService;

@Controller
public class HomeController {
		
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/")
	public String index(Model model) {		
		List<Article> articles = articleService.findFirstArticles();
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("articles", articles);
		return "index";
	}
  



	@GetMapping("/product-detail")
	public String  productDetail(Model model){
		return "productDetail";
	}
	@GetMapping("/account")
	public String account(Model model) {
		return "userAccount";
	}

}
