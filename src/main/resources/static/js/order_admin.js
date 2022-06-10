searchOrder

function formatDate(date) {
    let day = ("0" + date.getDate()).slice(-2);
    let month = ("0" + (date.getMonth() + 1)).slice(-2);
    return date.getFullYear() + "-" + (month) + "-" + (day);
}

var pageOrder;
var phone;
var name;
var status;
var dateFrom;
var dateTo;

function searchOrder(idSearch) {

    if (idSearch == 'btn-phone') {
        $("#name").val("");
    }
    if (idSearch == 'btn-search') {
        $("#phone").val("");
    }

    phone = $("#phone").val();
    name = $("#name").val();
    status = $("#status").val();
    dateFrom = $("#date-from").val();
    dateTo = $("#date-to").val();

    $.get(
        "/admin/api/order/search", {
            "phone": phone,
            "name": name,
            "status": status,
            "from": dateFrom,
            "to": dateTo,
            "page": 1
        },
        function(res) {
            pageOrder = res;
            loadDataOrder(res);
            pagination(res);
        }
    )

}

function loadDataOrder(data) {
    let page = Number(data.number);
    let html = data.content.map((item, index) => {
        return `
              <tr>
                        <th scope="row">${item.code}</th>
                        <td>${item.createdDate}</td>
                        <td>${item.customerPhone}</td>
                        <td>${item.customerName}</td>
                        <td>${format(item.amount)}</td>
                        <td id="status${item.id}">${item.status}</td>
                        <td>
                            <a class="btn btn-info" href="/admin/order/detail/${item.id}" target="_blank">
                                <i class="fa fa-eye" aria-hidden="true"></i>
                            </a>
                            <button class="btn btn-success" onclick="updateOrderStatus(${item.id})">
                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                            </button>
                        </td>
               </tr>
          `;
    });

    html = html.join("");
    $("#table").html(html);

}

function format(n) {
    return n.toFixed(0).replace(/./g, function(c, i, a) {
        return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "," + c : c;
    }) + " đ";
}

function pagination(data) {
    let currentPage = Number(data.number);
    let totalPage = Number(data.totalPages);
    let html = `
          <li class="page-item" onclick="prePage()">
               <a class="page-link" href="#" aria-label="Previous">
                   <span aria-hidden="true">&laquo;</span>
               </a>
          </li>
        `;

    for (let i = 1; i <= data.totalPages; i++) {
        if ((i >= currentPage - 5 || i == 1) && (i <= currentPage + 5 || i == totalPage)) {
            if (i == currentPage + 1) {
                html = html + `<li class="page-item active" onclick="goToPage(${i})" id="p${i}"><a class="page-link" href="#" >${i}</a></li>`;
            } else {
                html = html + `<li class="page-item" onclick="goToPage(${i})" id="p${i}"><a class="page-link" href="#" >${i}</a></li>`;
            }

        }
        if (i > currentPage + 5 && i <= currentPage + 6 && i < totalPage) {
            html = html + `<li class="page-item disabled"><a class="page-link" href="#" >...</a></li>`;
        }
        if (i > currentPage - 7 && i <= currentPage - 6 && i > 1) {
            html = html + `<li class="page-item disabled"><a class="page-link" href="#" >...</a></li>`;
        }
    }



    html = html + `<li class="page-item" onclick="nextPage()">
                            <a class="page-link" href="#" aria-label="Next">
                              <span aria-hidden="true">&raquo;</span>
                            </a>
                          </li>`;
    $("#pagination").html(html);

}

function goToPage(page) {
    $.get(
        "/admin/api/order/search", {
            "phone": phone,
            "name": name,
            "status": status,
            "from": dateFrom,
            "to": dateTo,
            "page": page
        },
        function(res) {
            pageOrder = res;
            loadDataOrder(res);
            pagination(res);
        }
    )

    $("#pagination li").removeClass("active");
    $("#p" + page).addClass("active");

}

function nextPage() {
    let currentPage = Number(pageOrder.number) + 1;
    if (currentPage < pageOrder.totalPages) {
        goToPage(currentPage + 1)
    }
}

function prePage() {
    let currentPage = Number(pageOrder.number) + 1;
    if (currentPage > 1) {
        goToPage(currentPage - 1)
    }
}

function updateOrderStatus(id) {
    $.get("/admin/api/order/status?id=" + id,
        function(res) {
            let html = res.map((item, index) => {
                if (index == 0) {
                    return `
                        <option value="${item}"  selected>${item}</option>
                    `
                } else {
                    return `
                        <option value="${item}" >${item}</option>
                    `
                }

            });
            $("#order-status").html(html.join(""));
            $("#order-id").val(id);
            $("#modal").modal("show");
        }
    );
}

function updateStatus() {
    let id = $("#order-id").val();
    let status = $("#order-status").val();
    $.ajax({
            method: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/admin/api/order/status/" + id,
            data: {
                'status': status
            }
        })
        .done(function(res) {
            if (res) {
                $("#status" + id).text(status);
                $("#status" + id).val(status);
            }
        });
    $("#modal").modal("hide");
}

$(document).ready(function() {
    let now = new Date();
    let date = new Date(now);
    date.setDate(date.getDate() - 1);
    $("#date-to").val(formatDate(now));
    $("#date-from").val(formatDate(date));
    searchOrder();
})