package com.nico.store.store.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nico.store.store.domain.Order;
import com.nico.store.store.domain.Article;
import com.nico.store.store.domain.ArticleBuilder;
import com.nico.store.store.domain.Brand;
import com.nico.store.store.domain.Category;
import com.nico.store.store.domain.Size;
import com.nico.store.store.domain.User;
import com.nico.store.store.service.ArticleService;
import com.nico.store.store.service.OrderService;
import java.nio.file.Path;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/add")
	public String addArticle(Model model) {
		Article article = new Article();
		model.addAttribute("article", article);
		model.addAttribute("allSizes", articleService.getAllSizes());
		model.addAttribute("allBrands", articleService.getAllBrands());
		model.addAttribute("allCategories", articleService.getAllCategories());
		return "addProduct";
	}
	
@RequestMapping(value = "/add", method = RequestMethod.POST)
public String addArticlePost(@ModelAttribute("article") Article article,
                             @RequestParam("pictureFile") MultipartFile file,
                             HttpServletRequest request) {
    try {
        // Define external upload directory
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/products/";


        // Create directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Get original filename and create full path
        String originalFilename = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(originalFilename);

        // Save the file
        file.transferTo(filePath.toFile());

        // Save only filename to the database
        article.setPicture(originalFilename);

    } catch (Exception e) {
        e.printStackTrace();
        return "error"; // handle error properly
    }

    Article newArticle = new ArticleBuilder()
            .withTitle(article.getTitle())
            .stockAvailable(article.getStock())
            .withPrice(article.getPrice())
            .imageLink(article.getPicture())
            .sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
            .ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
            .ofBrand(Arrays.asList(request.getParameter("brand").split("\\s*,\\s*")))
            .build();

    articleService.saveArticle(newArticle);
    return "redirect:article-list";
}

	
	@RequestMapping("/article-list")
	public String articleList(Model model) {
		List<Article> articles = articleService.findAllArticles();
		model.addAttribute("articles", articles);
		return "articleList";
	}

@RequestMapping("/customers")
public String customerList(Model model) {
	// List<User> users = userService.findAll();

	// List<UserDTO> customerDTOs = users.stream().map(user -> {
	// 	UserDTO dto = new UserDTO();
	// 	dto.setUsername(user.getUsername());
	// 	dto.setFirstName(user.getFirstName());
	// 	dto.setLastName(user.getLastName());
	// 	dto.setEmail(user.getEmail());
	// 	return dto;
	// }).collect(Collectors.toList());
	// model.addAttribute("customers", customerDTOs);
	return "customers";
}


		
@GetMapping("/order-details")
public String getOrderDetails(@RequestParam("id") Long orderId, Model model) {
    Order order = orderService.findOrderWithDetails(orderId);

    if (order == null) {
        return "error"; // or redirect with error message
    }

    model.addAttribute("order", order);
    return "order-details"; // This is your Thymeleaf page
}

	
	@RequestMapping("/edit")
	public String editArticle(@RequestParam("id") Long id, Model model) {
		Article article = articleService.findArticleById(id);
		String preselectedSizes = "";
		for (Size size : article.getSizes()) {
			preselectedSizes += (size.getValue() + ",");
		}
		String preselectedBrands = "";
		for (Brand brand : article.getBrands()) {
			preselectedBrands += (brand.getName() + ",");
		}
		String preselectedCategories = "";
		for (Category category : article.getCategories()) {
			preselectedCategories += (category.getName() + ",");
		}		
		model.addAttribute("article", article);
		model.addAttribute("preselectedSizes", preselectedSizes);
		model.addAttribute("preselectedBrands", preselectedBrands);
		model.addAttribute("preselectedCategories", preselectedCategories);
		model.addAttribute("allSizes", articleService.getAllSizes());
		model.addAttribute("allBrands", articleService.getAllBrands());
		model.addAttribute("allCategories", articleService.getAllCategories());
		return "editArticle";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editArticlePost(@ModelAttribute("article") Article article, HttpServletRequest request) {		
		Article newArticle = new ArticleBuilder()
				.withTitle(article.getTitle())
				.stockAvailable(article.getStock())
				.withPrice(article.getPrice())
				.imageLink(article.getPicture())
				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
				.ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
				.ofBrand(Arrays.asList(request.getParameter("brand").split("\\s*,\\s*")))
				.build();
		newArticle.setId(article.getId());
		articleService.saveArticle(newArticle);	
		return "redirect:article-list";
	}
	
	@RequestMapping("/delete")
	public String deleteArticle(@RequestParam("id") Long id) {
		articleService.deleteArticleById(id);
		return "redirect:article-list";
	}
	
}
