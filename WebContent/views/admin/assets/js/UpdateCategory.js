const editButtons = document.querySelectorAll(".table .edit-btn");
const formEditModal = document.querySelector("#editModal form");
editButtons.forEach(button => {
    button.addEventListener("click", async () => {
        const link1 = "/admin/category/load";
        const option1 = {
          method: "GET"
        }

        const res1 = await fetch(link1, option1);
        const data1 = await res1.json();

        if(data1.code == 200){
            const categories = JSON.parse(data1.categories);

            let innerHTML = "<option value=''>-- Chọn danh mục cha --</option>";

            categories.forEach(item => {
                innerHTML += `<option value=${item.categoryID}>${item.title}</option>`;
            });

            formEditModal.querySelector("#parent_id").innerHTML = innerHTML;
        }

        const tr = button.closest("[category-id]");
        const categoryID = tr.getAttribute("category-id");

        const link = "/admin/category/update?categoryID=" + categoryID;
        const option = {
          method: "GET"
        }

        const res = await fetch(link, option);
        const data = await res.json();

        if(data.code == 200){
            const category = JSON.parse(data.category);
            formEditModal.querySelector("#title").value = category.title;
            formEditModal.querySelector("#categoryID").value = category.categoryID;

            const options = formEditModal.querySelectorAll(`#parent_id option`);

            options.forEach(option2 => {
                if (option2.value == category.parentID) {
                    option2.selected = true;
                }
            });
        }
    });
});