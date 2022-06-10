package com.shoeshop.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoeshop.entity.Order;
import com.shoeshop.entity.OrderDetail;
import com.shoeshop.service.OrderDetailService;
import com.shoeshop.service.OrderService;
import static java.util.Collections.reverseOrder;
@Controller
@RequestMapping("/admin")
public class AdminController {

	private ModelAndView _mvShare = new ModelAndView();
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private OrderService orderService;
	private int MAX_SHOW = 6;

	@GetMapping("")
	public ModelAndView getHomePage() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance(); 
		Date today = new Date();
		cal.setTime(today);
		// YEAR CURRENT
		int yearNow = new Date().getYear() + 1900;
		List<Order> ordersDate = orderService.findOrdersByCreatedDate(yearNow, -1, -1);
		double totalMoneyInYear = 0;
		for (Order order : ordersDate) {
			if (order.getStatus().equalsIgnoreCase("Đã thanh toán")) {
				totalMoneyInYear += order.calculateAmount();
			}
		}
		;
		// YEAR BEFORE
		int yearBefore = (new Date().getYear() - 1) + 1900;
		List<Order> ordersDateBefore = orderService.findOrdersByCreatedDate(yearBefore, -1, -1);
		double totalMoneyInYearBefore = 0;
		for (Order order : ordersDateBefore) {
			if (order.getStatus().equalsIgnoreCase("Đã thanh toán")) {
				totalMoneyInYearBefore += order.calculateAmount();
			}
		}
		double percentYear = 0;
		if(totalMoneyInYearBefore != 0) {
			percentYear = ((totalMoneyInYear - totalMoneyInYearBefore) / totalMoneyInYearBefore) * 100;

		}

		_mvShare.addObject("totalMoneyInYear", totalMoneyInYear);
		_mvShare.addObject("inYear", yearNow);
		_mvShare.addObject("beforeYear", yearBefore);
		_mvShare.addObject("percentYear", toFixed(percentYear, 2));
		// END YEAR

		// Month CURRENT
		int monthNow = new Date().getMonth() + 1;
		List<Order> ordersDateMonth = orderService.findOrdersByCreatedDate(yearNow, monthNow, -1);
		double totalMoneyInMonth = 0;
		for (Order order : ordersDateMonth) {
			if (order.getStatus().equalsIgnoreCase("Đã thanh toán")) {
				totalMoneyInMonth += order.calculateAmount();
			}
		}
		
		// Month BEFORE
		cal.add(Calendar.MONTH, -1);
		String dateMonth = dateFormat.format(cal.getTime());
		
		int monthBefore = Integer.parseInt(dateMonth.split("-")[1]);
		List<Order> ordersDateMonthBefore = orderService.findOrdersByCreatedDate(yearNow, monthBefore, -1);
		double totalMoneyInMonthBefore = 0;
		for (Order order : ordersDateMonthBefore) {
			if (order.getStatus().equalsIgnoreCase("Đã thanh toán")) {
				totalMoneyInMonthBefore += order.calculateAmount();
			}
		}
		

		double percentMonth = Math
				.round(((totalMoneyInMonth - totalMoneyInMonthBefore) / totalMoneyInMonthBefore) * 100);
		_mvShare.addObject("totalMoneyInMonth", totalMoneyInMonth);
		_mvShare.addObject("inMonth", monthNow >= 10 ? monthNow + "" : "0" + monthNow);
		_mvShare.addObject("beforeMonth", monthBefore >= 10 ? monthBefore + "" : "0" + monthBefore);
		_mvShare.addObject("percentMonth", toFixed(percentMonth, 2));

		// END MONTH

		// Day CURRENT
		cal.setTime(today);
		int dayNow =  cal.get(Calendar.DAY_OF_MONTH); 
		List<Order> ordersDateDay = orderService.findOrdersByCreatedDate(-1, monthNow, dayNow);
		double totalMoneyInDay = 0;
		for (Order order : ordersDateDay) {
			if (order.getStatus().equalsIgnoreCase("Đã thanh toán")) {
				totalMoneyInDay += order.calculateAmount();
			}
		}
		;
		// DAY BEFORE
		cal.add(Calendar.DATE, -1);
		String dateYesterday = dateFormat.format(cal.getTime());
		
		int dayBefore = Integer.parseInt(dateYesterday.split("-")[2]);
		List<Order> ordersDateDayBefore = orderService.findOrdersByCreatedDate(-1, monthNow, dayBefore);
		double totalMoneyInDayBefore = 0;
		for (Order order : ordersDateDayBefore) {
			if (order.getStatus().equalsIgnoreCase("Đã thanh toán")) {
				totalMoneyInDayBefore += order.calculateAmount();
			}
		}

		double percentDay = Math.round(((totalMoneyInDay - totalMoneyInDayBefore) / totalMoneyInDayBefore) * 100);
		_mvShare.addObject("totalMoneyInDay", totalMoneyInDay);
		_mvShare.addObject("inDay", dayNow >= 10 ? dayNow + "" : "0" + dayNow);
		_mvShare.addObject("beforeDay", dayBefore >= 10 ? dayBefore + "" : "0" + dayBefore);
		_mvShare.addObject("percentDay", toFixed(percentDay, 2));
		// END Day
		
		
		//Product Quantity
		Long totalQuantityInMonth = 0L;
		Map<String, Integer> productQuantityTemp = new HashMap<String, Integer>();
		for (Order order : ordersDateMonth) {
			if (order.getStatus().equalsIgnoreCase("Đã thanh toán")) {
				List<OrderDetail> orDetail = orderDetailService.findOrdersDetailByOrderId(order.getId());
				for(OrderDetail orderDetail: orDetail) {
					totalQuantityInMonth += orderDetail.getQuantity();
						String key = orderDetail.getProductSize().getProduct().getName();
					if(productQuantityTemp.containsKey(key)) {
						productQuantityTemp.put(key, productQuantityTemp.get(key) + orderDetail.getQuantity());
					} else {
						productQuantityTemp.put(orderDetail.getProductSize().getProduct().getName(), orderDetail.getQuantity());
						
					}
				}
			}
		}
		Stream<Entry<String, Integer>> sorted = productQuantityTemp.entrySet().stream()
			       .sorted(reverseOrder(Map.Entry.comparingByValue()));
		
//		Map<String, Integer> productsQuantity = new HashMap<String, Integer>();
		List<String> productsName = new ArrayList<String>();
		List<Integer> productsQuantity = new ArrayList<Integer>();

		sorted.forEach(e -> {
			productsName.add(e.getKey());
			productsQuantity.add(e.getValue());
		});
		
		_mvShare.addObject("totalQuantityInMonth", totalQuantityInMonth);
		_mvShare.addObject("productsName", productsName);
		_mvShare.addObject("productsQuantity", productsQuantity);
		
		//END QUANTITY
		
		//Bieu Do
		
		List<Double> soLuongInThang = new ArrayList<Double>();
		List<Integer> thang = new ArrayList<Integer>();

		for(int i = 1; i <= cal.getActualMaximum(Calendar.DATE); i++) {
			thang.add(i);
			List<Order> ordersDateDaySub = orderService.findOrdersByCreatedDate(-1, monthNow, i);
			double quantityInDay = 0;
			for(Order order: ordersDateDaySub) {
				if (order.getStatus().equalsIgnoreCase("Đã thanh toán")) { 
					List<OrderDetail> orDetail = orderDetailService.findOrdersDetailByOrderId(order.getId());
					for(OrderDetail orderDetail: orDetail) {
						quantityInDay += orderDetail.getQuantity();
						}
					}
				}
			soLuongInThang.add(quantityInDay);
			}
		_mvShare.addObject("soLuongInThang",soLuongInThang);
		_mvShare.addObject("thang",thang);
		//ENG BIEU DO
		
		_mvShare.setViewName("admin/home_admin");
		return _mvShare;

	}

	@GetMapping("/login")
	public String login() {
		return "admin/login_admin";
	}

	public double toFixed(double num, int to) {
			// Split at the decimal point
			String[] s = String.valueOf(num).split("[.]");
			// Combine the two so and shorten the second
			String ending = s[1].substring(0, ((s[1].length() > to) ? to : s[1].length()));
			String fix = s[0] + '.' + ending;
			return Double.parseDouble(fix);
	}
}
