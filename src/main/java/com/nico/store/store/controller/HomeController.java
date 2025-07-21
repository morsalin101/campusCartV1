package com.nico.store.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nico.store.store.domain.Article;
import com.nico.store.store.service.ArticleService;

@Controller
public class HomeController {
		
	@Autowired
	private ArticleService articleService;
	
	
	@RequestMapping("/")
	public String index(Model model) {		
		List<Article> articles = articleService.findFirstArticles();
		model.addAttribute("articles", articles);
		return "index";
	}
	@RequestMapping("/index")
	public String index2(Model model) {	
		List<Article> articles = articleService.findFirstArticles();
		model.addAttribute("articles", articles);	
		return "index2";	
	}

	@GetMapping("/admin-panel")
    public String  adminDash(Model model){
		return "adminPanel";

	}
	// @GetMapping("/add-product")
    // public String addProduct(Model model){
	// 	Article article = new Article();
	// 	model.addAttribute("article", article);
	// 	model.addAttribute("allSizes", articleService.getAllSizes());
	// 	model.addAttribute("allBrands", articleService.getAllBrands());
	// 	model.addAttribute("allCategories", articleService.getAllCategories());
	// 	return "addProduct";
	// }

	
}
