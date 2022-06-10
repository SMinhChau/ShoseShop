let arrProductImport = [];
function handleFile(e) {
  //Get the files from Upload control
  var files = e.target.files;
  var i, f;
  //Loop through files
  for (i = 0, f = files[i]; i != files.length; ++i) {
    var reader = new FileReader();
    //    var name = f.name;
    reader.onload = function (e) {
      var data = e.target.result;

      var result;
      var workbook = XLSX.read(data, { type: "binary" });

      var sheet_name_list = workbook.SheetNames;
      sheet_name_list.forEach(function (y) {
        /* iterate through sheets */ //Convert the cell value to Json
        var roa = XLSX.utils.sheet_to_json(workbook.Sheets[y]);
        if (roa.length > 0) {
          result = roa;
        }
      });
      //Get the result
      const products = result.map((e) => Object.values(e));
      loadProducts(products);
    };
    reader.readAsArrayBuffer(f);
  }
}
function loadProducts(products) {
  arrProductImport = products;
  $("#tableProduct tr").remove();
  const valueShowProduct = $("#selectShowProduct").val();
  products.map((product) =>
    $("#tableProduct").append(
      `
	<tr class="h-90px" >
							<td class="align-middle">
							</td>
							<td class="align-middle"><img
								src="/images/products/${product[1]}" alt="image"
								width="70px" height="70px"></td>
							<td class="align-middle">
								<div class="col product__name">
									<a href="#">${product[0]}</a>
                  <input hidden id="name" name="name" type="text" th:value="${
                    product[0]
                  }"/>
								</div>
							</td>
							<td class="align-middle product__line"><a href="#"
								class="align-items-center"
								>${product[2]}</a></td>
							<td class="align-middle product__line"><a href="#"
								class="align-items-center">${product[3]}</a></td>
							<td class="align-middle">
								<h6 href="#" class="align-middle m-0 text-end priceVietNam"
									>${numberFormat.format(product[4])}</h6>
							</td>
							<td class="align-middle">
								<h6 href="#" class="align-middle m-0 text-end priceVietNam"
									>${numberFormat.format(product[5])}</h6>
							</td>
							<td class="align-middle text-center"><button
									class="btn btn-outline-dark">
									<i class="fa fa-ellipsis-v" aria-hidden="true"></i>
								</button></td>
						</tr>
	`
    )
  );
}
//Change event to dropdownlist
$(document).ready(function () {
  $("#importEXCELabel").change(handleFile);
});
$("#btnImport").click(function () {
  $.ajax({
    url: "/admin/products/import",
    type: "POST",
    data: [{ asd: 123 }],
    success: function (response) {
      // do something
    },
    error: function (jqXHR, textStatus, errorThrown) {
      // do something
    },
  });
});
