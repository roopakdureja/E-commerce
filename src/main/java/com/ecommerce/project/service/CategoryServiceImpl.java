package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;
    ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,
                                             String sortBy, String sortOrder) {

        Sort sortByAndOrder = (sortOrder.equalsIgnoreCase("asc"))
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();
        if(categories.isEmpty()){
            throw new APIException("No category created till now.");
        }

        List<CategoryDTO> categoryDTOS = categories.stream().
                map(category -> modelMapper.map(category,CategoryDTO.class)).toList();

        /*
        Without stream usage -
        List<CategoryDTO> categoryDTOs = new ArrayList<>();

        for (Category category : categories) {
            CategoryDTO dto = modelMapper.map(category, CategoryDTO.class);
            categoryDTOs.add(dto);
        }

        Without modelMapper(manually copying) -
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (Category category : categories) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());

            categoryDTOList.add(dto);
        }

        Manual copying along with stream usage -
        List<CategoryDTO> categoryDTOList = categories.stream()
        .map(category -> {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            return dto; // returned DTO is collected into the final list using .toList()
        })
        .toList();

        */

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        /*
        manually mapping dto to model
        bcoz modelMapper was assigning a id to model along with name
        which will cause error if we are trying to save a new object
         and that id is not in DB. - ObjectOptimisticLockingFailureException
        */
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());

        /*
        or you can use this before below line- categoryDTO.setCategoryId(null);
        Don't know how but when i ran using modelMapper, it worked now
        w/o setting id to null....confused!
        */
        //Category category = modelMapper.map(categoryDTO,Category.class);

        Category categoryFromDb = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDb != null){
            throw new APIException("Category with the name " + category.getCategoryName() +" already exists!");
        }

        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long id) {
        Category categoryToRemove = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category","categoryId",id));

        categoryRepository.delete(categoryToRemove);

        return modelMapper.map(categoryToRemove,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO,Category.class);

        Category categoryToUpdate = categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Category","categoryId",id));

        categoryToUpdate.setCategoryName(category.getCategoryName());
        Category savedCategory = categoryRepository.save(categoryToUpdate);

        return modelMapper.map(savedCategory,CategoryDTO.class);
    }
}
