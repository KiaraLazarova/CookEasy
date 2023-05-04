package com.cookeasy.web;

import com.cookeasy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentAdminPanelController {
    private final CommentService commentService;

    @Autowired
    public CommentAdminPanelController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/comments")
    public String retrieveCommentsPage(Model model) {
        model.addAttribute("viewModels", this.commentService.getCommentAdminPanel());

        return "comments-admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/comments/{id}/approve")
    public String approveComment(@PathVariable(name = "id") Long id) {
        this.commentService.approveComment(id);

        return "redirect:/comments";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/comments/{id}/archive")
    public String archiveComment(@PathVariable(name = "id") Long id) {
        this.commentService.archiveComment(id);

        return "redirect:/comments";
    }
}