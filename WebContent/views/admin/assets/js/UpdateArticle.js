const editButtons = document.querySelectorAll(".table .edit-btn");
const formEditModal = document.querySelector("#editModal form");
editButtons.forEach(button => {
    button.addEventListener("click", async () => {
        const link1 = "/admin/article/category/load";
        const option1 = {
          method: "GET"
        }

        const res1 = await fetch(link1, option1);
        const data1 = await res1.json();

        if(data1.code == 200){
            const categories = JSON.parse(data1.categories);

            let innerHTML = "";

            categories.forEach(item => {
                innerHTML += `<option value=${item.categoryID}>${item.title}</option>`;
            });

            formEditModal.querySelector("#category_id").innerHTML = innerHTML;
        }

        const tr = button.closest("[article-id]");
        const articleID = tr.getAttribute("article-id");

        const link = "/admin/article/update?articleID=" + articleID;
        const option = {
          method: "GET"
        }

        const res = await fetch(link, option);
        const data = await res.json();

        if(data.code == 200){
            const article = JSON.parse(data.article);

            formEditModal.querySelector("#title").value = article.title;

            tinymce.get("content1").setContent(article.content);
            tinymce.get("content1").save();

            formEditModal.querySelector("#articleID").value = article.articleID;

            const options = formEditModal.querySelectorAll(`#category_id option`);

            options.forEach(option2 => {
                if (option2.value == article.categoryID) {
                    option2.selected = true;
                }
            });
        }
    });
});