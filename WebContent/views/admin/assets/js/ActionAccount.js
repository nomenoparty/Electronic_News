const editButtons = document.querySelectorAll(".table .edit-btn");
const formEditModal = document.querySelector("#editModal form");
editButtons.forEach(button => {
    button.addEventListener("click", () => {
        const tr = button.closest("[user-id]");
        const userID = tr.getAttribute("user-id");

        const link = "/admin/account/update?userID=" +userID;
        const option = {
          method: "GET"
        }

        fetch(link, option)
            .then(res => res.json())
            .then(data => {
                if(data.code == 200){
                    data = JSON.parse(data.user);
                    formEditModal.querySelector("#userID").value = data.userID;
                    formEditModal.querySelector("#fullName1").value = data.fullName;
                    formEditModal.querySelector("#username1").value = data.username;
                    formEditModal.querySelector("#password1").value = data.password;

                    const radios = formEditModal.querySelectorAll('input[name="role"]');

                    radios.forEach(radio => {
                        if (radio.value === data.permission) {
                            radio.checked = true;
                        }
                    });

                    const radioStatus = formEditModal.querySelectorAll('input[name="status"]');

                    radioStatus.forEach(radio => {
                        if (radio.value === data.status) {
                            radio.checked = true;
                        }
                    });
                }
            })
    });
});