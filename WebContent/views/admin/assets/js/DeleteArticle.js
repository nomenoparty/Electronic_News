let articleIdDelete;

const deleteButtons = document.querySelectorAll(".table .delete-btn");

deleteButtons.forEach(deleteButton => {
    deleteButton.addEventListener("click", () => {
        articleIdDelete = deleteButton.closest('[article-id]').getAttribute("article-id");
    });
});

function confirmDelete(){
    window.location.href = '/admin/article/delete?articleID=' + articleIdDelete;
}