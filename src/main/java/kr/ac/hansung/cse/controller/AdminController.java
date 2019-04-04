package kr.ac.hansung.cse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping
	public String adminPage() {
		return "admin";
	}
	
	@RequestMapping("/productInventory")
	public String getProduct(Model model) {
		// database를 조회해서 product 정보를 가져온 후 Model에 넣기
		// controller -> model -> view
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		
		return "productInventory";
	}
	
	// addProduct 버튼을 눌렀을 때
	@RequestMapping(value="/productInventory/addProduct", method=RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		product.setCategory("컴퓨터");
		model.addAttribute("product", product);
		return "addProduct";
	}
	
	// web form을 작성하고 submit 했을 때
	@RequestMapping(value="/productInventory/addProduct", method=RequestMethod.POST)
	public String addProductPost(@Valid Product product, BindingResult result) {
		// 함수의 인자로 Product 객체가 만들어지고 web form을 통해 넘어온 data가 자동으로 객체에 담기게 됨
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
				// 지정한 에러메세지 출력
			}
			
			// 에러가 있는 경우 다시 입력 가능한 addProduct로 가기
			return "addProduct";
		}
		
		if(!productService.addProduct(product))
			System.out.println("Addingproduct cannot be done");
		
		// return "productInventory";
		// 라고 하면 새로 추가한 product를 포함하는 model객체가 productInventory.jsp에 넘어가지 않기 때문에
		// redirect를 해서 위으 getProduct함수가 호출되도록 한다.
		return "redirect:/admin/productInventory";
	}
	
	@RequestMapping(value="/productInventory/deleteProduct/{id}", method=RequestMethod.GET)
	public String deleteProduct(@PathVariable int id) { // {id} : PathVariable, id가 넘어오면 id 값이 int i에 들어가게 됨
		if(!productService.deleteProduct(id)) {
			System.out.println("Deelting prouct cannot be done");
		}
		
		return "redirect:/admin/productInventory";
	}
	
	// 특정 제품의 수정 아이콘을 눌렀을 때
	@RequestMapping(value="/productInventory/updateProduct/{id}", method=RequestMethod.GET)
	public String updateProduct(@PathVariable int id, Model model) { // {id} : PathVariable, id가 넘어오면 id 값이 int i에 들어가게 됨
		Product product = productService.getProductById(id);
		// id를 바탕으로 하나의 product 정보를 가져옴
		
		model.addAttribute("product", product);
		// 받아온 product를 model에 저장해서 jsp에 전달
		
		return "updateProduct";
	}
	
	// web form을 작성하고 submit 했을 때
	@RequestMapping(value="/productInventory/updateProduct", method=RequestMethod.POST)
	public String updateProductPost(@Valid Product product, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
				// 지정한 에러메세지 출력
			}
			
			// 에러가 있는 경우 다시 입력 가능한 addProduct로 가기
			return "updateProduct";
		}
		
		
		//System.out.println(product);
		if(!productService.updateProduct(product))
			System.out.println("Updating product cannot be done");
		
		return "redirect:/admin/productInventory";
	}

}
