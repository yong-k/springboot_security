document.getElementById("deleteBtn").onclick = () => {
    let response = confirm("삭제하시겠습니까?");
    if (response) {
        let id = document.getElementById("deleteBtn").value;
        window.location.href="/users/delete/" + id;
    }
};