package com.shoeshop.controller;

import com.shoeshop.dto.CustomerAddressDto;
import com.shoeshop.dto.CustomerDto;
import com.shoeshop.entity.Customer;
import com.shoeshop.entity.CustomerAddress;
import com.shoeshop.entity.Order;
import com.shoeshop.entity.OrderDetail;
import com.shoeshop.service.AddressService;
import com.shoeshop.service.BrandService;
import com.shoeshop.service.CustomerAddressService;
import com.shoeshop.service.CustomerService;
import com.shoeshop.service.OrderDetailService;
import com.shoeshop.service.OrderService;
import com.shoeshop.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private CustomerAddressService customerAddressService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;

	public boolean containsOrder(final List<Order> list, final Long id) {
		return list.stream().anyMatch(o -> o.getId().equals(id));
	}

	@GetMapping("/orders/{id}")
	public ModelAndView ordersDetailUser(Principal principal, @PathVariable Long id,
			RedirectAttributes redirectAttributes) {

		if (principal == null) {
			_mvShare.setView(new RedirectView("/user/login"));
			return _mvShare;
		}

		CustomerDto cus = customerService.getCustomerCurrent(principal.getName());
		List<Order> orders = orderService.findOrdersByCustomerIdByDESC(cus.getId());
		if (!containsOrder(orders, id)) {
			redirectAttributes.addFlashAttribute("message", "<script> alert(\"Không tìm thấy đơn hàng\");</script>");
		} else {
			List<OrderDetail> ors = new ArrayList<OrderDetail>();

			ors = orderDetailService.findOrdersDetailByOrderId(id);
			if (ors.size() == 0) {
				_mvShare.addObject("mesage", "Không tìm thấy đơn hàng");
			}
			_mvShare.addObject("ordersDetail", ors);
		}

		_mvShare.setViewName("/users/orders_detail_user");
		return _mvShare;
	}

	@Autowired
	private BrandService brandService;

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") Customer user, BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("brands", brandService.getAllBrandAndListProduct());
			return "users/account";
		}
		String message = "<script> Swal.fire(\"Đăng ký tài khoản thành công\",\"Mời bạn đăng nhập để có thể mua hàng!\",\"success\"); </script>";
		if (customerService.checkPhoneNotExist(user.getPhone())) {
			if (customerService.checkEmailNotExist(user.getEmail())) {
				user.setCreatedDate(new Date());
				user.setStatus(true);
				if (!customerService.addCustomer(user)) {
					message = "<script> Swal.fire(\"Thất bại!\",\"Đã xảy ra lỗi vui lòng thử lại\",\"error\"); </script>";
				}
			} else {
				message = "<script> Swal.fire(\"Thất bại!\",\"Email này đã được sử dụng bởi một tài khoản khác!\",\"warning\"); </script>";
			}
		} else {
			message = "<script> Swal.fire(\"Thất bại!\",\"Số điện thoại này đã được sử dụng bởi một tài khoản khác!\",\"warning\"); </script>";
		}

		redirectAttributes.addFlashAttribute("message_register", message);
		return "redirect:/user/register";
	}

	@GetMapping("/orders")
	public ModelAndView ordersUser(Principal principal) {
		if (principal == null) {
			_mvShare.setView(new RedirectView("/user/login"));
			return _mvShare;
		}

		CustomerDto cus = customerService.getCustomerCurrent(principal.getName());
		List<Order> orders = orderService.findOrdersByCustomerIdByDESC(cus.getId());

		List<OrderDetail> listOrder = new ArrayList<OrderDetail>();
		List<Integer> quantityOrdersDetail = new ArrayList<Integer>();
		if (orders.size() > 0) {
			orders.forEach(e -> {
				List<OrderDetail> ors = orderDetailService.findOrdersDetailByOrderId(e.getId());
				int tongQuantity = 0;

				for (int i = 0; i < ors.size(); i++) {
					tongQuantity += ors.get(i).getQuantity();
				}
				listOrder.add(ors.get(0));
				quantityOrdersDetail.add(tongQuantity);
			});
		}
		_mvShare.addObject("orders", orders);
		_mvShare.addObject("listOrderDetail", listOrder);
		_mvShare.addObject("quantityOrdersDetail", quantityOrdersDetail);
		_mvShare.setViewName("/users/orders_user");
		return _mvShare;
	}

	@GetMapping(value = { "/login", "/register" })
	public ModelAndView showPageLoginAndRegister(@RequestParam(value = "status", required = false) String status,
			HttpSession session, RedirectAttributes redirectAttributes, Principal principal) {
		Customer user = new Customer();
		if (status != null) {
			if (status.equalsIgnoreCase("error")) {
				_mvShare.addObject("message_login", "<script> Swal.fire(\"Đăng nhập không thành công!\",\"Tên đăng nhập hoặc mật khẩu không chính xác!\",\"warning\"); </script>");
			} else if (status.equalsIgnoreCase("success")) {
				if (customerService.getCustomerByPhoneOrEmail(principal.getName()).isStatus()){
					setUserCurrentLogged(session);
				}else{
					SecurityContextHolder.clearContext();
					redirectAttributes.addFlashAttribute("message_login", "<script> Swal.fire(\"Tài khoản đã bị khóa!\",\"Vui lòng liên hệ shop để mở lại tài khoản!\",\"warning\"); </script>");
				}
				return new ModelAndView(new RedirectView("/"));
			} else {
				_mvShare.addObject("message_login", "");
			}
		}else {
			_mvShare.addObject("message_login", "");
		}
		_mvShare.addObject("user", user);
		_mvShare.setViewName("users/account");
		return _mvShare;
	}


	@PostMapping("/address")
	public String addAddress(@ModelAttribute("customerAddress") CustomerAddressDto customerAddress,
			HttpServletRequest request) {
		CustomerAddress address = new CustomerAddress();
		Customer customer = customerService.getCustomerById(customerAddress.getCustomerId());
		address.setAddress(addressService.getAddressById(customerAddress.getAddressId()));
		address.setCustomer(customer);
		address.setDetail(customerAddress.getDetail());
		address.setStatus(true);
		if (customer.getAddresses().size() > 0) {
			customerAddressService.updateAllStatusCustomerAddressFalse(customer.getAddresses());

		}
		customerAddressService.addCustomerAddress(address);

		return "redirect:" + request.getHeader("Referer");
	}

	@GetMapping("/edit-account")
	public ModelAndView showPageAccountUpdate(Principal principal){
		 Customer user = customerService.getCustomerByPhoneOrEmail(principal.getName());
		 CustomerDto userDto = new CustomerDto();
		 userDto.setPhone(user.getPhone());
		 userDto.setEmail(user.getEmail());
		 userDto.setFullName(user.getFullName());
		 userDto.setStatus(user.isStatus());
		_mvShare.addObject("user",userDto);
		_mvShare.setViewName("users/account_update");
		return _mvShare;
	}

	@PostMapping("/edit-account")
	public String updateAccount(Model model,RedirectAttributes redirectAttributes,@Valid @ModelAttribute("user") CustomerDto user,BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			model.addAttribute("brands", brandService.getAllBrandAndListProduct());
			return "users/account_update";
		}
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		Customer userCurrent = customerService.getCustomerByPhoneOrEmail(user.getPhone());
		if (!user.getEmail().equalsIgnoreCase(userCurrent.getEmail())){
			if (!customerService.checkEmailNotExist(user.getEmail())){
				redirectAttributes.addFlashAttribute("message_edit_account","<script> Swal.fire(\"Thất bại!\",\"Email này đã được sử dụng bởi một tài khoản khác!\",\"warning\"); </script>");
				return "redirect:/user/edit-account";
			}else {
				userCurrent.setEmail(user.getEmail());
			}
		}

		if (!bCryptPasswordEncoder.matches(user.getRePassword(),userCurrent.getPassword())){
			redirectAttributes.addFlashAttribute("message_edit_account","<script> Swal.fire(\"Thất bại!\",\"Mật khẩu xác nhận không đúng!\",\"warning\"); </script>");
			return "redirect:/user/edit-account";
		}
		userCurrent.setFullName(user.getFullName());
		if (!user.getPassword().equalsIgnoreCase("")){
			userCurrent.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		customerService.updateCustomer(userCurrent);
		return "redirect:/";
	}

}
