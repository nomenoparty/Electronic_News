package service.admin;

import dao.impl.CategoryDAO;
import helper.GenerateSlug;
import model.CategoryModel;

import java.util.ArrayList;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();
    public int insertCategory(String title, int parentID){
        CategoryModel categoryModel = new CategoryModel();

        String slug = GenerateSlug.toSlug(title);

        categoryModel.setTitle(title);
        categoryModel.setSlug(slug);
        categoryModel.setParentID(parentID);

        return categoryDAO.insert(categoryModel);
    }
    public ArrayList<CategoryModel> selectAllCategories(){
        return categoryDAO.selectAll();
    }
    public ArrayList<CategoryModel> selectAllCategoriesWithParentID(int parentID){
        return categoryDAO.selectAllWithParentID(parentID);
    }
    public ArrayList<CategoryModel> selectAllCategoriesExceptParentID(int parentID){
        return categoryDAO.selectAllExceptParentID(parentID);
    }
    public CategoryModel selectById(int id){
        return categoryDAO.selectById(id);
    }
    public int updateCategory(int categoryID, String title, int parentID){
        CategoryModel categoryModel = new CategoryModel();

        String slug = GenerateSlug.toSlug(title);

        categoryModel.setCategoryID(categoryID);
        categoryModel.setTitle(title);
        categoryModel.setSlug(slug);
        categoryModel.setParentID(parentID);

        return categoryDAO.update(categoryModel);
    }
    public int deleteCategory(int id){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setCategoryID(id);
        categoryDAO.delete(categoryModel);

        categoryDAO.deleteByParentID(id);

        return 0;
    }
}
