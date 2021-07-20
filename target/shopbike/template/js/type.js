function addNewType() {
    //lay du lieu
    let name = $('#nameType').val();
    let newType = {
        nameType: name
    };
    // goi ajax
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newType),
        //tên API
        url: "/createType",
        //xử lý khi thành công
        success: successHandler

    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}

function successHandler() {
    $.ajax({
        type: "GET",
        //tên API
        url: "/listType",
        //xử lý khi thành công
        success: function (data) {
            // hien thi danh sach o day
            let content1 =   '<tr><th>Id</th>' +
                '<th>Name</th>' +
                '<th>Action</th></tr>';
            for (let i = 0; i < data.length; i++) {
                content1 += getType(data[i]);
            }
            document.getElementById('listType').innerHTML = content1;
            $('.deleteType').click(function (event) {
                //lay du lieu
                let a = $(this);
                let typeId = a.attr("href");
                // goi ajax
                $.ajax({
                    type: "DELETE",
                    //tên API
                    url: `/type/${typeId}`,
                    //xử lý khi thành công
                    success: function (data) {
                        a.parent().parent().parent().remove();
                    }

                });
                //chặn sự kiện mặc định của thẻ
                event.preventDefault();
            });
        }
    });
}

function getType(type) {
    return `<tr><td>${type.idType}</td>` +
        `<td>${type.nameType}</td>` +
        `<td class="text-center align-middle"><div class="btn-group align-top">` +
        `<button class="btn btn-sm btn-outline-secondary badge" type="button" data-toggle="modal" data-target="#user-form-modal" onclick="editProduct()">Edit</button>` +
        `<a class="deleteType" href="${type.idType}">` +
        `<button class="btn btn-sm btn-outline-secondary badge" type="button"><i class="fa fa-trash"></i></button>` +
        `</a>` +
        `</div></td></tr>`;
}

$(document).ready(function () {
    //sư kiện nào thực hiện Ajax
    $('.deleteType').click(function (event) {
        //lay du lieu
        let a = $(this);
        let typeId = a.attr("href");
        // goi ajax
        $.ajax({
            type: "DELETE",
            //tên API
            url: `/type/${typeId}`,
            //xử lý khi thành công
            success: function (data) {
                a.parent().parent().parent().remove();
            }

        });
        //chặn sự kiện mặc định của thẻ
        event.preventDefault();
    });
})