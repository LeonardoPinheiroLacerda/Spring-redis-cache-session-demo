package com.leonardo.springredisdemo.resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unchecked")

@RestController
@RequestMapping("/sessions")
public class SessionResources {
    
    
    @GetMapping("/getmessages")
    public List<String> getMessages(HttpSession session){    
        return  (List<String>) session.getAttribute("messages");
    }

    @PostMapping("/addmessage")
    public void addMessage(@RequestParam String message, HttpSession session){
        List<String> messages = (List<String>) session.getAttribute("messages");

        if(messages == null) { 
            messages = new ArrayList<>();
        }

        messages.add(message);

        session.setAttribute("messages", messages);
    }

}
