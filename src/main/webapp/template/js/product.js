function addNewProduct() {
    //lay du lieu
    let name = $('#nameProduct').val();
    // let type = $('#type').val();
    let price = $('#priceProduct').val();
    let description = $('#description').val();
    let image = $('#imageProduct').val();
    let newProduct = {
        nameProduct: name,
        // type: type,
        priceProduct: price,
        description: description,
        image: image
    };
    // goi ajax
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newProduct),
        //tên API
        url: "/createProduct",
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
        url: "/listProduct",
        //xử lý khi thành công
        success: function (data) {
            // hien thi danh sach o day
            let content =   '<tr><th>Id</th>' +
                '<th>Name</th>' +
                '<th>Type</th>' +
                '<th>Size</th>' +
                '<th>Price</th>' +
                '<th>Photo</th>' +
                '<th>Action</th></tr>';
            for (let i = 0; i < data.length; i++) {
                content += getProduct(data[i]);
            }
            document.getElementById('listProduct').innerHTML = content;
            $('.deleteProduct').click(function (event) {
                //lay du lieu
                let a = $(this);
                let productId = a.attr("href");
                // goi ajax
                $.ajax({
                    type: "DELETE",
                    //tên API
                    url: `/product/${productId}`,
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

function getProduct(product) {
    return `<tr><td>${product.idProduct}</td>` +
        `<td>${product.nameProduct}</td><td></td><td></td><td>${product.priceProduct}</td><td><img src="${product.image}"></td>` +
        `<td class="text-center align-middle"><div class="btn-group align-top">` +
            `<button class="btn btn-sm btn-outline-secondary badge" type="button" data-toggle="modal" data-target="#user-form-modal" onclick="editProduct()">Edit</button>` +
            `<a class="deleteProduct" href="${product.idProduct}">` +
                `<button class="btn btn-sm btn-outline-secondary badge" type="button"><i class="fa fa-trash"></i></button>` +
            `</a>` +
        `</div></td></tr>`;
}

$(document).ready(function () {
    //sư kiện nào thực hiện Ajax
    $('.deleteProduct').click(function (event) {
        //lay du lieu
        let a = $(this);
        let productId = a.attr("href");
        // goi ajax
        $.ajax({
            type: "DELETE",
            //tên API
            url: `/product/${productId}`,
            //xử lý khi thành công
            success: function (data) {
                a.parent().parent().parent().remove();
            }

        });
        //chặn sự kiện mặc định của thẻ
        event.preventDefault();
    });
})