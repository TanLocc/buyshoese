package com.example.buyshoes.controller;


import com.example.buyshoes.dto.UserDto;
import com.example.buyshoes.dto.UserResponseDto;
import com.example.buyshoes.entities.User;
import com.example.buyshoes.repository.UserRepository;
import com.example.buyshoes.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // reder view trang thêm mới sách
    @RequestMapping("add")
    public String save(@PathVariable(value="name",required = false) String name, Model model){

        model.addAttribute("title", "Thêm người dùng");
        model.addAttribute("user",new UserDto());
        return "user/add";
    }
    @PostMapping
    @RequestMapping("edit/{id}")
    public String editUser (Model model, @PathVariable String id, RedirectAttributes redirectAttributes) {
        model.addAttribute("title", "Sửa người dùng");
        User user =  userRepository.findById(Long.parseLong(id)).orElse(new User());
        if(StringUtils.isEmpty(user.getId())){
            redirectAttributes.addFlashAttribute("message", "Sản phẩm không tồn tại");
            return "redirect:/user";
        }
        UserDto userDto = modelMapper.map(user, UserDto.class);
        model.addAttribute("user", userDto);
        return  "user/add";
    }
    @PostMapping
    @RequestMapping("save")
//    @ModelAttribute("book") <=> với RequestBody
    public String save (@Valid @ModelAttribute("user")UserDto userDto, BindingResult bindingResult,
                        Model model, RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()){
            model.addAttribute("message", "Thêm thất bại, kiểm tra lại các trường");
            return "user/add";
        }
        userService.saveUser(userDto);

        redirectAttributes.addFlashAttribute("message", "Thêm thành công");
        return "redirect:/user";
    }


    @RequestMapping("")
    public String viewHomePage(Model model) {
        return pagingUser(model, 1, "username", "asc");
    }

    @RequestMapping("page/{pageNum}")
    public String pagingUser(Model model,
                             @PathVariable(name = "pageNum") int pageNum,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir) {

        Page<User> page = userService.listAll(pageNum, sortField, sortDir);

        List<User> users = page.getContent();
        List<UserResponseDto> userResponseDtos = users.stream().map(user -> modelMapper.map(user,UserResponseDto.class))
                .collect(Collectors.toList());

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listUser", userResponseDtos);

        return "user/list";
    }



    @DeleteMapping
    @RequestMapping("delete/{id}")
    public String deleteUser(Model model, @PathVariable String id, RedirectAttributes redirectAttributes){
        User user =  userRepository.findById(Long.parseLong(id)).orElse(new User());
        if(StringUtils.isEmpty(user.getId())){
            redirectAttributes.addFlashAttribute("message","Người dùng không tồn tại");
            return "redirect:/user";
        }
        userService.deleteUser(Long.parseLong(id));
        redirectAttributes.addFlashAttribute("message", "You have successfully deleted");
        return "redirect:/user";
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
            throws ServletException {

        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

    }

    @RequestMapping(value = "/getUserPhoto",method = RequestMethod.GET)
    public void getUserPhoto(HttpServletResponse response, @RequestParam("id") Long id) throws Exception {

        userService.showPhoto(response,id);

    }
}
