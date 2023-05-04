package com.cookeasy.model.view;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CommentAdminPanelViewModel {
    private Long id;
    private String authorUsername;
    private String recipeTitle;
    private LocalDate createdOn;
    private String content;
    private boolean approved;
    private boolean archived;
}