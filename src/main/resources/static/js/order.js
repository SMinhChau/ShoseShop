<script th:inline="javascript">
/*PROCESS DISCOUNT*/

/*<![CDATA[*/
var amount = /*[[${cart.totalAmount}]]*/
/*]]>*/
var discount = 0;

function checkDiscount() {
    const numberFormat = new Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND",
    });
    let code = $("#code").val();
    if (code == "") {
        $("#status-code").addClass("d-none");
        $("#discount").addClass("d-none");
        $("#payment").html(numberFormat.format(amount));
        discount = 0;
    } else {
        $.get("/api/promotion",
            {"code": code, "amount": amount},
            function (data) {
                if (data.status) {
                    $("#discount").removeClass("d-none");
                    $("#discount").children("h5").html("-" + numberFormat.format(data.discount));
                    $("#payment").html(numberFormat.format(amount - data.discount));
                    discount = data.discount;
                    $("#status-code").addClass("d-none");
                    $("#promotion-code").val(data.code);
                } else {
                    $("#status-code").removeClass("d-none");
                    $("#status-code").children().html(data.message)
                    $("#discount").addClass("d-none");
                    discount = 0;
                    $("#payment").html(numberFormat.format(amount));
                    $("#promotion-code").val("");
                }

            }
        );
    }
}


/*PROCESS ADD ADDRESS*/
function loadDataCity() {

    /*<![CDATA[*/
    var customerId = /*[[${customer.id}]]*/
    /*]]>*/

    $("#customer-id").val(customerId);

    $.get("/api/address/city", function (data) {
        htmt = data.map(item => {
            return `
                    <option value="${item}">${item}</option>
                 `
        });
        htmt = htmt.join("");
        $("#city").html(htmt);
        $("#city").val("Thành phố Hồ Chí Minh").change();

    })
}

function loadDataDistrict() {
    let city = $("#city").val();
    $.get("/api/address/district?city=" + city, function (data) {
        htmt = data.map(item => {
            return `
                    <option value="${item}">${item}</option>
                 `
        });
        htmt = htmt.join("");
        $("#district").html(htmt);
        $("#district").val(data[0]).change();
        $("#ward").html("");
    })
}

function loadDataWard() {
    let city = $("#city").val();
    let district = $("#district").val();
    let ward = "";
    $.get("/api/address/ward?city=" + city + "&district=" + district, function (data) {

        data.forEach(item => {
            for (var i in item) {
                ward = ward + `<option value="${i}">${item[i]}</option>`
            }
        });

        $("#ward").html(ward);
        $('#ward :nth-child(1)').prop('selected', true);
    })
}

/*PROCESS DELETE AND LOAD CART ITEM*/
function loadDataCart(res) {
    let items = res.items;
    const numberFormat = new Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND",
    });
    let html = items.map(item => {
        return `
                      <tr class="">
                      <td class="order">
                        <img
                          class="order__img"
                          width="70"
                          height="70"
                          alt="Adidas Yeezy Slide Bone"
                          src="/images/products/${item.avatar}"
                        />
                        <span class="order__img__quantity">${item.quantity}</span>
                      </td>
                      <td class="align-middle p-3">
                        <h6>${item.productName}</h6>
                        <span class="text-primary" >( Size ${item.productSize} )</span>
                      </td>
                      <td class="align-middle text-end">
                            ${numberFormat.format(item.price)}
                      </td>
                    </td>
                    <td class="align-middle text-end">
                      <button onclick="deleteCartItem(${item.productSizeId})" class="btn btn-danger"><i class="fa fa-times-circle-o" aria-hidden="true"></i></button>
                    </td>
                </tr>
              `
    });

    html = html.join("");
    $("#item-cart").html(html);
    $("#total-amount").text(numberFormat.format(res.totalAmount));
    $("#payment").html(numberFormat.format(res.totalAmount - discount));
}

function deleteCartItem(productSizeId) {
    if (
        confirm("Bạn có chắc muốn xóa sản phẩm này khỏi đơn hàng không ?")
    ) {
        $.ajax({
            type: "DELETE",
            url: "/api/cart/delete/" + productSizeId,
            success: function (res) {
                if (res.items.length == 0) {
                    window.location.reload();
                }
                amount = res.totalAmount;
                loadDataCart(res);
            },
        });
    }
};
</script>