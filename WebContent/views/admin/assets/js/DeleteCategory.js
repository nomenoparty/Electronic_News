let categoryIdDelete;

const deleteButtons = document.querySelectorAll(".table .delete-btn");

deleteButtons.forEach(deleteButton => {
    deleteButton.addEventListener("click", () => {
        categoryIdDelete = deleteButton.closest('[category-id]').getAttribute("category-id");
    });
});

function confirmDelete(){
    window.location.href = '/admin/category/delete?categoryID=' + categoryIdDelete;
}