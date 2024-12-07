package helper;

import model.CategoryModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CategoryTree {
    public ArrayList<CategoryModel> getCategoryListByParentId(ArrayList<CategoryModel> categoryList, int parentID){
        ArrayList<CategoryModel> result = new ArrayList<>();

        for(CategoryModel categoryModel: categoryList){
            if(categoryModel.getParentID() == parentID) result.add(categoryModel);
        }

        return result;
    }

    public Map<CategoryModel, ArrayList<CategoryModel>> getCategoryTree(ArrayList<CategoryModel> categoryList){
        Map<CategoryModel, ArrayList<CategoryModel>> map = new HashMap<>();

        for(CategoryModel categoryModel: categoryList){
            if(categoryModel.getParentID() == 0){
                ArrayList<CategoryModel> childCategories = getCategoryListByParentId(categoryList, categoryModel.getCategoryID());
                map.put(categoryModel, childCategories);
            }
        }

        return map;
    }
}
