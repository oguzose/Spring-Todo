package tr.lkd.lyk2015.springtodo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.lkd.lyk2015.springtodo.model.Todo;
import tr.lkd.lyk2015.springtodo.service.TodoService;

@Controller
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService; //autowired olmasa nullPointerException

	@RequestMapping("")
	public String list(Model model) {
		
		List<Todo> todos = todoService.getAll();
		model.addAttribute("todoList", todos);

		return "todoList";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm(@ModelAttribute Todo todo) {
		
		return "createForm";
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String checked(HttpServletRequest req, Model model) {
		
		System.out.println(req.getParameter("active")+ " -----------------------");
		todoService.markAsDone(req.getParameter("active"));
		
		return "redirect:/todo";
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute Todo todo) {
		
		todoService.create(todo);
		
		return "redirect:/todo";
	}
	
	
	@RequestMapping(value = "/updateForm", method = RequestMethod.GET, params = {"id"})
	public String update(@RequestParam("id") String id, Model model, @RequestParam(value="message", required=false) String message ) {
		//model contenir görevi görüyor
		
		Todo todo = todoService.getById(Long.parseLong(id)); //editlenecek id buraya düştü
		model.addAttribute("todo", todo);
		model.addAttribute("message", message);
				
		//veritabanından gelen nesneleri viewe yolluyoruz
		
		return "updateForm";
		
		//yukarıda ModelAttribure diyebiliriz
	}

	
	@RequestMapping(value = "/updateForm", method = RequestMethod.POST)
	public String update(@ModelAttribute Todo todo) {
		
		todoService.update(todo);
		
		return "redirect:/todo/update?id="+ todo.getId() + "&message=success";
		
		//redirect te get kullanılıyor. kullanıcı bozabilir.
	}

}