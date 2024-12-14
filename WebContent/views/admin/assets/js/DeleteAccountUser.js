let userIdDelete;

const deleteButtons = document.querySelectorAll(".table .delete-btn");

deleteButtons.forEach(deleteButton => {
    deleteButton.addEventListener("click", () => {
        userIdDelete = deleteButton.closest('[user-id]').getAttribute("user-id");
    });
});

function confirmDelete(){
    window.location.href = '/admin/account/delete?userID=' + userIdDelete;
}