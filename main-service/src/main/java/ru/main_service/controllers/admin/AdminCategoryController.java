package ru.main_service.controllers.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.main_service.model.dto.CategoryDto;
import ru.main_service.services.CategoryService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/admin/categories")
public class AdminCategoryController {

    private final CategoryService adminService;

    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@PathVariable Long catId, @Valid @RequestBody CategoryDto updateDto) {
        log.info("Update category updateDto={}", updateDto);
        return adminService.updateCategory(catId, updateDto);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto newDto) {
        log.info("Create category createDto={}", newDto);
        return adminService.createCategory(newDto);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long catId) {
        log.info("Delete category={}", catId);
        adminService.deleteCategory(catId);
    }

}
