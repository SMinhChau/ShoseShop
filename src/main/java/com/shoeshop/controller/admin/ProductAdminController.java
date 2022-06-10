package com.shoeshop.controller.admin;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shoeshop.entity.Category;
import com.shoeshop.entity.Product;
import com.shoeshop.entity.ProductLine;
import com.shoeshop.entity.ProductSize;
import com.shoeshop.entity.Size;
import com.shoeshop.service.BrandService;
import com.shoeshop.service.CategoriesService;
import com.shoeshop.service.IStorageService;
import com.shoeshop.service.ProductLineService;
import com.shoeshop.service.ProductService;
import com.shoeshop.service.ProductSizeService;
import com.shoeshop.service.SizeService;

@Controller
@RequestMapping("/admin")
public class ProductAdminController {

	private static final int MAX_TOTAL_PAGES = 7;
	@Autowired
	private ProductService productService;
	@Autowired
	private SizeService sizeService;
	@Autowired
	private ProductSizeService productSizeService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private ProductLineService productLineService;
	@Autowired
	private IStorageService imageStoreService;

	private ModelAndView _mvShare = new ModelAndView();

	@GetMapping("/product")
	public ModelAndView loadAddProduct() {
		List<Category> categories = categoriesService.findAll();
		_mvShare.addObject("brands", brandService.getAllBrandAndListProduct());
		_mvShare.addObject("sizes", sizeService.findSizeAll());
		_mvShare.addObject("product", new Product());
		_mvShare.addObject("categories", categories);
		_mvShare.setViewName("admin/product_add_admin");
		_mvShare.addObject("title", "Thêm sản phẩm mới");
		return _mvShare;
	}

	@GetMapping("/product/{id}")
	public ModelAndView loadEditProduct(@PathVariable Long id) {

		List<Category> categories = categoriesService.findAll();
		Product product = productService.getProductById(id);
		_mvShare.addObject("sizes", sizeService.findSizeAll());
		_mvShare.addObject("brands", brandService.getAllBrandAndListProduct());
		_mvShare.addObject("product", product);
		List<ProductSize> productsizes = productSizeService.getProductSizeByProductId(id);
		_mvShare.addObject("productSizes", productsizes);
		_mvShare.addObject("categories", categories);
		_mvShare.addObject("title", "Chỉnh sửa sản phẩm ID: " + id);
		_mvShare.setViewName("admin/product_edit_admin");
		_mvShare.addObject("productLineCurrent", product.getProductLine().getId());
		return _mvShare;
	}

	public static String deAccent(String str) {
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

	@PostMapping("/product")
	public String addProduct(@Valid @ModelAttribute("product") Product product,
			BindingResult result,
			@RequestParam(name = "sizes", defaultValue = "") List<Integer> sizes,
			@RequestParam(name = "quantity", defaultValue = "") List<Integer> quantitys, Model model,
			@RequestParam("avatarFile") MultipartFile[] files) {
		if (result.hasErrors()) {
			List<Category> categories = categoriesService.findAll();
			model.addAttribute("brands", brandService.getAllBrandAndListProduct());
			model.addAttribute("sizes", sizeService.findSizeAll());
			model.addAttribute("categories", categories);
			model.addAttribute("title", "Thêm sản phẩm mới");
			return "admin/product_add_admin";
		}

		ArrayList<String> listFile = new ArrayList<>();
		imageStoreService.setStorageFolder("products");
		Arrays.asList(files).stream().forEach(file -> {
			String fileName = imageStoreService.storeFile(file);
			listFile.add(fileName);
		});

		if (listFile.size() > 0) {
			int size = listFile.size();
			product.setAvatar(listFile.get(size - 1));
			listFile.remove(size - 1);
			product.setImages(listFile);
		}

		product.setCreatedDate(new Date());
		productService.addProduct(product);
		// ProductLine pr = productLineService.getProductLine(productLineId);
		// product.setProductLine(pr);
		Product newProduct = productService.findLastProduct();
		int i = 0;
		for (Integer qt : quantitys) {
			if (qt != null) {
				ProductSize productSize = new ProductSize();
				productSize.setProduct(newProduct);
				Size size = new Size();
				size.setId(sizes.get(i).longValue());
				productSize.setStatus(!qt.equals(0));
				productSize.setSize(size);
				productSize.setQuantity(qt);
				productSizeService.addProductSize(productSize);
			}
			i++;
		}

		return "redirect:/admin/products";
	}

	@PostMapping(value = "/product/update")
	public String editProduct(@Valid @ModelAttribute("product") Product product,
			BindingResult result,
			@RequestParam(name = "sizes", defaultValue = "") List<Integer> sizes,
			@RequestParam(name = "productLineId", defaultValue = "1") Long productLineId,
			@RequestParam(name = "quantity", defaultValue = "") List<Integer> quantitys, Model model,
			@RequestParam("avatarFile") MultipartFile[] files) {
		try {
			if (result.hasErrors()) {
				List<ProductSize> productsizes = productSizeService.getProductSizeByProductId(product.getId());
				List<Category> categories = categoriesService.findAll();
				model.addAttribute("brands", brandService.getAllBrandAndListProduct());
				model.addAttribute("sizes", sizeService.findSizeAll());
				model.addAttribute("categories", categories);
				model.addAttribute("productSizes", productsizes);
				model.addAttribute("productLineCurrent", product.getProductLine().getId());
				model.addAttribute("title", "Chỉnh sửa sản phẩm ID: " + product.getId());
				return "admin/product_edit_admin";
			}

			Product updateProduct = productService.getProductById(product.getId());
			List<ProductSize> productsizes = productSizeService.getProductSizeByProductId(product.getId());
			productsizes.forEach(e -> {
				ProductSize prosize = productSizeService.getProductSizeById(e.getId());
				prosize.setQuantity(0);
				productSizeService.addProductSize(prosize);
			});
			// updateProduct.setAvatar(product.getAvatar());
			updateProduct.setCategory(product.getCategory());
			updateProduct.setModifiedDate(new Date());
			// nhanvien
			updateProduct.setCreatedBy(product.getAvatar());
			updateProduct.setName(product.getName());
			updateProduct.setPrice(product.getPrice());
			updateProduct.setPromotionPrice(product.getPromotionPrice());
			updateProduct.setStatus(product.getStatus());
			updateProduct.setTags(product.getTags());
			ProductLine pr = productLineService.getProductLine(productLineId);
			updateProduct.setProductLine(pr);
			// product.setAvatar(file.getOriginalFilename());

			ArrayList<String> listFile = new ArrayList<>();
			imageStoreService.setStorageFolder("products");

			try {
				if(files != null && files.length != 0) {
					imageStoreService.deleteFile(updateProduct.getAvatar());
					updateProduct.getImages().forEach(e -> {
						imageStoreService.deleteFile(e);
					});
				}
				Arrays.asList(files).stream().forEach(file -> {
					String fileName = imageStoreService.storeFile(file);
					listFile.add(fileName);
				});
			} catch (Exception e2) {
//				e2.printStackTrace();
			}
			if (listFile.size() > 0) {
				int size = listFile.size();
				updateProduct.setAvatar(listFile.get(size - 1));
				listFile.remove(size - 1);
				updateProduct.setImages(listFile);
			}
			int i = 0;
			for (Integer qt : quantitys) {
				Size size = new Size();
				size.setId(sizes.get(i).longValue());
				ProductSize prsize = productSizeService.getProductSizeByProductIdAndSizeId(updateProduct.getId(),
						size.getId());
				if (prsize == null) {
					ProductSize productSize = new ProductSize();
					productSize.setProduct(updateProduct);
					productSize.setStatus(!qt.equals(0));
					productSize.setSize(size);
					productSize.setQuantity(qt);
					productSizeService.addProductSize(productSize);
				} else {
					prsize.setQuantity(qt);
					productSizeService.addProductSize(prsize);
				}
				i++;
			}
			productService.updateProduct(updateProduct);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:/admin/products";
	}

	@GetMapping("/products")
	public ModelAndView getListProducts(
			@RequestParam(required = false, name = "text", defaultValue = "") String textSearch,
			@RequestParam(required = false, name = "rows", defaultValue = "10") Integer rowOfPage,
			@RequestParam(required = false, name = "sort", defaultValue = "id") String sort,
			@RequestParam(required = false, name = "orderby", defaultValue = "desc") String orderBy) {
		if (!(rowOfPage == 10 || rowOfPage == 25 || rowOfPage == 50 || rowOfPage == 100))
			rowOfPage = 10;
		if (!(sort.equalsIgnoreCase("id") || sort.equalsIgnoreCase("name") || sort.equalsIgnoreCase("productLine")
				|| sort.equalsIgnoreCase("price") || sort.equalsIgnoreCase("promotionPrice")
				|| sort.equalsIgnoreCase("category")))
			sort = "name";
		Direction orderBySort = Sort.Direction.DESC;
		if (orderBy.equalsIgnoreCase("asc"))
			orderBySort = Sort.Direction.ASC;
		Pageable pageable = PageRequest.of(0, rowOfPage, Sort.by(orderBySort, sort));
		Page<Product> pageList = productService.getProductsNew(pageable, textSearch);
		List<Product> listProduct = pageList.getContent();
		List<String> totalPages = new ArrayList<String>();
		int sizePage = pageList.getTotalPages();

		// 1 space for ...
		for (int i = 1; i <= sizePage; i++) {
			if (i <= MAX_TOTAL_PAGES - 2) {
				totalPages.add(i + "");
			}
		}

		if (sizePage > MAX_TOTAL_PAGES) {
			totalPages.add("...");
			totalPages.add(sizePage + "");
		}
		_mvShare.addObject("textSearch", textSearch);
		_mvShare.addObject("maxPages", sizePage);
		_mvShare.addObject("rowOfPage", rowOfPage);
		_mvShare.addObject("maxProducts", pageList.getTotalElements());
		_mvShare.addObject("pageCurrent", pageList.getNumber());
		_mvShare.addObject("totalPages", totalPages);
		_mvShare.addObject("products", listProduct);
		_mvShare.setViewName("admin/products_admin");
		return _mvShare;
	}

	@GetMapping("/products/import")
	public ModelAndView ImportProduct() {
		_mvShare.setViewName("admin/products_preview_admin");
		return _mvShare;
	}

	@PostMapping("/products/import")
	public ModelAndView AddProducts() {
		return _mvShare;
	}
}