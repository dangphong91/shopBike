function addNewSize() {
    //lay du lieu
    let name = $('#nameSize').val();
    let newSize = {
        nameSize: name
    };
    // goi ajax
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newSize),
        //tên API
        url: "/createSize",
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
        url: "/listSize",
        //xử lý khi thành công
        success: function (data) {
            // hien thi danh sach o day
            let content1 =   '<tr><th>Id</th>' +
                '<th>Name</th>' +
                '<th>Action</th></tr>';
            for (let i = 0; i < data.length; i++) {
                content1 += getSize(data[i]);
            }
            document.getElementById('listSize').innerHTML = content1;
            $('.deleteSize').click(function (event) {
                //lay du lieu
                let a = $(this);
                let sizeId = a.attr("href");
                // goi ajax
                $.ajax({
                    type: "DELETE",
                    //tên API
                    url: `/size/${sizeId}`,
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

function getSize(size) {
    return `<tr><td>${size.idSize}</td>` +
        `<td>${size.nameSize}</td>` +
        `<td class="text-center align-middle"><div class="btn-group align-top">` +
        `<button class="btn btn-sm btn-outline-secondary badge" type="button" data-toggle="modal" data-target="#user-form-modal" onclick="editProduct()">Edit</button>` +
        `<a class="deleteSize" href="${size.idSize}">` +
        `<button class="btn btn-sm btn-outline-secondary badge" type="button"><i class="fa fa-trash"></i></button>` +
        `</a>` +
        `</div></td></tr>`;
}

$(document).ready(function () {
    //sư kiện nào thực hiện Ajax
    $('.deleteSize').click(function (event) {
        //lay du lieu
        let a = $(this);
        let sizeId = a.attr("href");
        // goi ajax
        $.ajax({
            type: "DELETE",
            //tên API
            url: `/size/${sizeId}`,
            //xử lý khi thành công
            success: function (data) {
                a.parent().parent().parent().remove();
            }

        });
        //chặn sự kiện mặc định của thẻ
        event.preventDefault();
    });
})