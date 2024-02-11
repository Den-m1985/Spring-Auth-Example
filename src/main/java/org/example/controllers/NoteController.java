package org.example.controllers;

import org.example.model.Note;
import org.example.model.User;
import org.example.repository.NoteRepository;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/notes")
    public String notes(Principal principal, Model model) {
        User user = (User) userService.loadUserByUsername(principal.getName());

        List<Note> notes = noteRepository.findByUserId(user.getId());
        model.addAttribute("notes", notes);
        model.addAttribute("user", user);

        return "notes";
    }

    @PostMapping("/addnote")
    public String addNote(Principal principal, String title, String note) {
        User user = (User) userService.loadUserByUsername(principal.getName());

        Note newNote = new Note();
        newNote.setTitle(title);
        newNote.setNote(note);
        newNote.setUserId(user.getId());

        noteRepository.save(newNote);

        return "redirect:/notes";
    }

}
