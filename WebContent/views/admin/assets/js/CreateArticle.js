const formCreateModal = document.querySelector("#createModal form #category_id");

function loadCategories(){
    const link = "/admin/article/category/load";
    const option = {
      method: "GET"
    }

    fetch(link, option)
        .then(res => res.json())
        .then(data => {
            if(data.code == 200){
                data = JSON.parse(data.categories);

                let innerHTML = "";

                data.forEach(item => {
                    innerHTML += `<option value=${item.categoryID}>${item.title}</option>`;
                });

                formCreateModal.innerHTML = innerHTML;
            }
        });
}