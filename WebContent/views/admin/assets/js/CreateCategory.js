const formCreateModal = document.querySelector("#createModal form #parent_id");

function loadCategories(){
    const link = "/admin/category/load";
    const option = {
      method: "GET"
    }

    fetch(link, option)
        .then(res => res.json())
        .then(data => {
            if(data.code == 200){
                data = JSON.parse(data.categories);

                let innerHTML = "<option value=''>-- Chọn danh mục cha --</option>";

                data.forEach(item => {
                    innerHTML += `<option value=${item.categoryID}>${item.title}</option>`;
                });

                formCreateModal.innerHTML = innerHTML;
            }
        });
}