package com.example.buyshoes.controller;

import com.example.buyshoes.dto.ProductDto;
import com.example.buyshoes.dto.ProductResponseDto;
import com.example.buyshoes.dto.UserDto;
import com.example.buyshoes.entities.Category;
import com.example.buyshoes.entities.ShoesProduct;
import com.example.buyshoes.entities.User;
import com.example.buyshoes.repository.ProductRepository;
import com.example.buyshoes.service.CategoryService;
import com.example.buyshoes.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;





    @RequestMapping("admin")
    public String viewHomePage(Model model) {
        return pagingProduct(model, 1, "name", "asc");
    }

    @RequestMapping("page/{pageNum}")
    public String pagingProduct(Model model,
                             @PathVariable(name = "pageNum") int pageNum,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir) {

        Page<ShoesProduct> page = productService.listAll(pageNum, sortField, sortDir);

        List<ShoesProduct> shoesProducts = page.getContent();
        List<ProductResponseDto> productResponseDtos = shoesProducts.stream().map(shoesProduct -> modelMapper.map(shoesProduct,ProductResponseDto.class))
                .collect(Collectors.toList());

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listProduct", productResponseDtos);

        return "product/list";
    }

    @RequestMapping("customer")
    public String customerPage(Model model) {
        return getProducts(model, 1, "");
    }

    @RequestMapping("customerPage/{pageNum}")
    public String getProducts(Model model,
                             @PathVariable(name = "pageNum") int pageNum,
                              @Param(value="category") String category) {

        Page<ShoesProduct> page = productService.listAll(category,pageNum);

        List<ShoesProduct> shoesProducts = page.getContent();
        List<ProductResponseDto> productResponseDtos = shoesProducts.stream().map(shoesProduct -> modelMapper.map(shoesProduct, ProductResponseDto.class))
                .collect(Collectors.toList());

        model.addAttribute("category", category);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        model.addAttribute("products", productResponseDtos);

        return "product/shoesView";
    }



    @RequestMapping(value = "productImage",method = RequestMethod.GET)
    public void getProductPhoto(HttpServletResponse response, @RequestParam("id") Long id) throws Exception {

        productService.showPhoto(response,id);

    }

    @RequestMapping("add")
    public String save(Model model){
        model.addAttribute("title", "Th??m s???n ph???m");
        List<String> categorys = categoryService.getCategoryList();
        model.addAttribute("categorys",categorys);
        model.addAttribute("shoesProduct",new ProductDto());
        return "product/add";
    }

    @PostMapping
    @RequestMapping("edit/{id}")
    public String editUser (Model model, @PathVariable String id, RedirectAttributes redirectAttributes) {
        model.addAttribute("title", "S???a th??ng tin s???n ph???m");
        ShoesProduct shoesProduct =  productRepository.findById(Long.parseLong(id)).orElse(new ShoesProduct());
        if(StringUtils.isEmpty(shoesProduct.getId())){
            redirectAttributes.addFlashAttribute("message", "S???n ph???m kh??ng t???n t???i");
            return "redirect:/product/admin";
        }
        List<String> categorys = categoryService.getCategoryList();
        model.addAttribute("categorys",categorys);
        ProductDto productDto = modelMapper.map(shoesProduct, ProductDto.class);
        model.addAttribute("shoesProduct", productDto);
        return  "product/add";
    }

    @PostMapping
    @RequestMapping("save")
//    @ModelAttribute("book") <=> v???i RequestBody
    public String save (@Valid @ModelAttribute("user")ProductDto productDto, BindingResult bindingResult,
                        Model model, RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()){
            model.addAttribute("message", "Th??m th???t b???i, ki???m tra l???i c??c tr?????ng");
            return "user/add";
        }
        productService.save(productDto);

        redirectAttributes.addFlashAttribute("message", "Th??m th??nh c??ng");
        return "redirect:/product/admin";
    }

    @DeleteMapping
    @RequestMapping("delete/{id}")
    public String deleteProduct(Model model, @PathVariable String id, RedirectAttributes redirectAttributes){
        ShoesProduct shoesProduct =  productRepository.findById(Long.parseLong(id)).orElse(new ShoesProduct());
        if(StringUtils.isEmpty(shoesProduct.getId())){
            redirectAttributes.addFlashAttribute("message","S???n ph???m kh??ng t???n t???i");
            return "redirect:/product/admin";
        }
        productService.deleteProduct(Long.parseLong(id));
        redirectAttributes.addFlashAttribute("message", "You have successfully deleted");
        return "redirect:/product/admin";
    }
}
