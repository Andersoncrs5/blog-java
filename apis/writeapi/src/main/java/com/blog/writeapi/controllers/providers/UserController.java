package com.blog.writeapi.controllers.providers;

import com.blog.writeapi.controllers.docs.UserControllerDocs;
import com.blog.writeapi.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController implements UserControllerDocs {

    private final IUserService userService;


}
