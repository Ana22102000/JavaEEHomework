package com.example.demo.Controllers;

import com.example.demo.Config.MyUserDetails;
import com.example.demo.Database.Entity.BookEntity;
import com.example.demo.Database.Entity.PermissionEntity;
import com.example.demo.Database.Entity.UserEntity;
import com.example.demo.Database.Repository.BookRepository;
import com.example.demo.Database.Repository.PermissionRepository;
import com.example.demo.Database.Repository.UserRepository;
import com.example.demo.Permission;
import com.example.demo.UserModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/sign_in")
    public String signIn() {
        return "signin";
    }

    @PreAuthorize("hasAuthority('VIEW_SELECTED')")
    @RequestMapping(value = "/selected", method = RequestMethod.GET)
    public String selected(Model model) {
        final MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity;// = userRepository.findByLogin(userDetails.getUsername()).get();
        userEntity = userRepository.findByLoginWithBooks(userDetails.getUsername()).get();
        ArrayList<BookEntity> arr = new ArrayList<BookEntity>(userEntity.getSelectedBooks());
        System.out.println(arr.toString());

        model.addAttribute("books", arr);

        return "selected";

    }

    @PreAuthorize("hasAuthority('VIEW_SELECTED')")
    @RequestMapping(value = "/delete_selected", method = RequestMethod.POST)
    public ResponseEntity<Boolean> delete(@RequestBody final String param) {
        final MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByLogin(userDetails.getUsername()).get();


        try {
            JSONObject json = new JSONObject(param);
            String isbn = json.getString("isbn");
            BookEntity bookEntity = bookRepository.getBookEntityByIsbn(isbn);
            Set<BookEntity> books = userEntity.getSelectedBooks();
            if(books == null) books = new HashSet<BookEntity>();
            books.remove(bookEntity);
            userEntity.setSelectedBooks(books);
            userEntity = userRepository.save(userEntity);

//            System.out.println(userEntity);
//            System.out.println(isbn);
//            System.out.println(bookEntity);

        } catch (JSONException e) {
            e.printStackTrace();
        }




        return ResponseEntity.ok(true); //todo return false

    }

    @PreAuthorize("hasAuthority('VIEW_SELECTED')")
    @RequestMapping(value = "/add_to_selected", method = RequestMethod.POST)
    public ResponseEntity<Boolean> add(@RequestBody final String param) {
        final MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByLogin(userDetails.getUsername()).get();


        try {
            JSONObject json = new JSONObject(param);
            String isbn = json.getString("isbn");
            BookEntity bookEntity = bookRepository.getBookEntityByIsbn(isbn);
            Set<BookEntity> books = userEntity.getSelectedBooks();
            if(books == null) books = new HashSet<BookEntity>();
            books.add(bookEntity);
            userEntity.setSelectedBooks(books);
            userEntity = userRepository.save(userEntity);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return ResponseEntity.ok(true); //todo return false

    }



    @RequestMapping(value = "/my-sign-in", method = RequestMethod.POST)
    public ResponseEntity<Boolean> signIn(@Valid @RequestBody final UserModel userModel) {


            String login = userModel.getLogin();
            String password = userModel.getPassword();

            Optional<UserEntity> userEntity = userRepository.findByLogin(login);
            if(!userEntity.isPresent()){
                PermissionEntity permissionEntity = new PermissionEntity();
                permissionEntity = permissionRepository.findFirstByPermission(Permission.VIEW_SELECTED);

                UserEntity newUser = new UserEntity();
                newUser.setLogin(login);
                newUser.setPassword(password);
                newUser.setPermissions(List.of(permissionEntity));
                userRepository.save(newUser);

                return ResponseEntity.ok(true);

            }


        return ResponseEntity.ok(false);

    }

}
